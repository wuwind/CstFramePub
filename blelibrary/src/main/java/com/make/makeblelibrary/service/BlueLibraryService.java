package com.make.makeblelibrary.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.make.makeblelibrary.XxlBle;
import com.make.makeblelibrary.bean.NotifyMessage;
import com.make.makeblelibrary.bean.UUIDMessage;
import com.make.makeblelibrary.callback.BlueGattCallBack;
import com.make.makeblelibrary.code.CodeUtils;
import com.make.makeblelibrary.interfaces.Instructions;
import com.make.makeblelibrary.manager.BluetoothGattManager;
import com.make.makeblelibrary.manager.EventManager;
import com.make.makeblelibrary.utils.HexUtils;
import com.make.utilcode.util.LogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BlueLibraryService extends Service implements BluetoothAdapter.LeScanCallback {

    private BluetoothAdapter bluetoothAdapter;

    private long second;//扫描毫秒延迟

    private long onLeScanTime;//最近一次扫描时间

    private List<BluetoothDevice> deviceList;//设备集合

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*
         * 这里返回状态有三个值，分别是:
         * 1、START_STICKY：当服务进程在运行时被杀死，系统将会把它置为started状态，但是不保存其传递的Intent对象，之后，系统会尝试重新创建服务;
         * 2、START_NOT_STICKY：当服务进程在运行时被杀死，并且没有新的Intent对象传递过来的话，系统将会把它置为started状态，
         *   但是系统不会重新创建服务，直到startService(Intent intent)方法再次被调用;
         * 3、START_REDELIVER_INTENT：当服务进程在运行时被杀死，它将会在隔一段时间后自动创建，并且最后一个传递的Intent对象将会再次传递过来。
         */
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 初始化方法
     */
    private void initConfig() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        if (!EventManager.getLibraryEvent().isRegistered(this))
            EventManager.getLibraryEvent().register(this);
        /**
         * 服务启动完毕  发送一个Event通知
         */
        NotifyMessage notifyMessage = new NotifyMessage(CodeUtils.SERVICE_ONSTART, null);
        EventManager.servicePost(notifyMessage);
    }

    /**
     * 释放EventBus
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.getLibraryEvent().unregister(this);
        EventManager.removeAllEvent();
        if (bluetoothAdapter != null) {
            bluetoothAdapter.stopLeScan(this);
        }
        Map<Object, BlueGattCallBack> gattMap = BluetoothGattManager.getGattMap();
        Collection<BlueGattCallBack> values = gattMap.values();
        for (BlueGattCallBack callBack : values) {
            callBack.disconnect();
            callBack.getGatt().close();
            callBack.close();
        }
        gattMap.clear();
        EventManager.servicePost(NotifyMessage.newInstance().setCode(CodeUtils.SERVICE_ONSTOP));
    }

    @Override
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
        if (!deviceList.contains(bluetoothDevice)) {
            deviceList.add(bluetoothDevice);
            if (second != 0) {
                if (System.currentTimeMillis() - onLeScanTime > second) {
                    onLeScanTime = System.currentTimeMillis();
                    NotifyMessage notifyMessage = NotifyMessage.newInstance().setCode(CodeUtils.SERVICE_ONDEVICE).setData(deviceList);
                    EventManager.servicePost(notifyMessage);
                }
            } else {
                NotifyMessage notifyMessage = new NotifyMessage(CodeUtils.SERVICE_ONDEVICE, bluetoothDevice);
                EventManager.servicePost(notifyMessage);
            }
        }

    }

    /**
     * 收到订阅消息
     *
     * @param notifyMessage
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NotifyMessage notifyMessage) {
        if (notifyMessage == null) return;
        NotifyMessage message = new NotifyMessage();
        LogUtils.e(notifyMessage.getCode());
        switch (notifyMessage.getCode()) {
            case CodeUtils.SERVICE_STARTSCANER://启动扫描
                if (notifyMessage.getData() != null) {
                    second = Long.valueOf(notifyMessage.getData().toString());
                    onLeScanTime = System.currentTimeMillis();
                    deviceList = new ArrayList<>(5);
                }
                bluetoothAdapter.startLeScan(this);
                break;
            case CodeUtils.SERVICE_STOPSCANER://停止扫描
                bluetoothAdapter.stopLeScan(this);
                break;
            case CodeUtils.SERVICE_BLUEENABLE://系统蓝牙的开启状态
            {
                message.setData(bluetoothAdapter.isEnabled());
                message.setCode(CodeUtils.SERVICE_ONBLUEENABLE);
                EventManager.servicePost(message);
            }
            break;
            case CodeUtils.SERVICE_OPENBLUE://打开手机蓝牙
            {
                message.setData(bluetoothAdapter.enable());
                message.setCode(CodeUtils.SERVICE_ONENABLEBLUE);
                EventManager.servicePost(message);
            }
            break;
            case CodeUtils.SERVICE_CLOSEBLUE://关闭手机蓝牙
            {
                message.setCode(CodeUtils.SERVICE_ONDISABLEBLUE);
                message.setData(bluetoothAdapter.disable());
                EventManager.servicePost(message);
            }
            break;
            case CodeUtils.SERVICE_DEVICE_CONN://连接某一个设备  并且主动发现服务
            {
                BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(notifyMessage.getData().toString());
                BlueGattCallBack blueGattCallBack = new BlueGattCallBack();
                blueGattCallBack.setTag(notifyMessage.getTag());
                BluetoothGattManager.putGatt(notifyMessage.getTag(), blueGattCallBack);
                remoteDevice.connectGatt(this, false, blueGattCallBack);
                //blueGattCallBack.close();
                //blueGattCallBack = null;
            }
            break;
            case CodeUtils.SERVICE_REGDEVICE://注册通道
            {
                UUIDMessage uuidMessage = notifyMessage.getData();
                BlueGattCallBack gatt = BluetoothGattManager.getGatt(notifyMessage.getTag());
                gatt.registerDevice(uuidMessage);
            }
            break;
            case CodeUtils.SERVICE_WRITE_DATA://写出字符数据
            {
                BlueGattCallBack gatt = BluetoothGattManager.getGatt(notifyMessage.getTag());
                gatt.write_data(notifyMessage.getData().toString());
            }
            break;
            case CodeUtils.SERVICE_WRITE_DATA_TOHEX://写出十六进制
            {
                BlueGattCallBack gatt = BluetoothGattManager.getGatt(notifyMessage.getTag());
                if (gatt == null) {
                    //XxlBle.blueStopService(getApplicationContext());
                    //XxlBle.blueStartService(getApplicationContext());
                    XxlBle.blueConnectDevice(notifyMessage.getTag().toString(), notifyMessage.getTag());
                    //return;
                } else {
                    boolean success = gatt.write_data(HexUtils.getHexBytes(notifyMessage.getData().toString()));
                    LogUtils.e(success);
                }
                System.out.println("Data:" + notifyMessage.getData().toString() + "gatt:" + gatt);
            }
            break;
            case CodeUtils.SERVICE_WRITE_DATA_TOBYTE://写出字节数据
            {
                BlueGattCallBack gatt = BluetoothGattManager.getGatt(notifyMessage.getTag());
                gatt.write_data((byte[]) notifyMessage.getData());
            }
            break;
            case CodeUtils.SERVICE_READ_DATA://主动读取通道数据
            {
                BlueGattCallBack gatt = BluetoothGattManager.getGatt(notifyMessage.getTag());
                gatt.read_data();
            }
            break;
//            case CodeUtils.SERVICE_READ_DATA_HEX2STR://读取十六进制转字符
//            {
//                BlueGattCallBack gatt = BluetoothGattManager.getGatt(notifyMessage.getTag());
//                gatt.read_data(CodeUtils.SERVICE_READ_DATA_HEX2STR);
//            }
//            break;
//            case CodeUtils.SERVICE_READ_DATA_BYTEARRAY://读取蓝牙原始字节数据
//            {
//                BlueGattCallBack gatt = BluetoothGattManager.getGatt(notifyMessage.getTag());
//                gatt.read_data(CodeUtils.SERVICE_READ_DATA_BYTEARRAY);
//            }
//            break;
            case CodeUtils.SERVICE_DISCONN_DEVICE://断开指定连接
            {
                BlueGattCallBack gatt = BluetoothGattManager.getGatt(notifyMessage.getTag());
                gatt.disconnect();
                BluetoothGattManager.getGattMap().remove(notifyMessage.getTag());
            }
            break;
            case CodeUtils.SERVICE_DISCONN_DEVICE_ALL://断开所有连接
            {
                Map<Object, BlueGattCallBack> gattMap = BluetoothGattManager.getGattMap();
                Collection<BlueGattCallBack> values = gattMap.values();
                for (BlueGattCallBack callBack : values) {
                    callBack.disconnect();
                }
                gattMap.clear();
            }
            break;
            case CodeUtils.SERVICE_SET_INSTRUCTIONS_CLASS://收到关联指令类的消息
            {
                try {
                    Class<? extends Instructions> i = notifyMessage.getData();
                    Constructor<? extends Instructions> constructor = i.getConstructor(Object.class);
                    Instructions instructions = constructor.newInstance(notifyMessage.getTag());
                    BlueGattCallBack gatt = BluetoothGattManager.getGatt(notifyMessage.getTag());
                    gatt.setInstructions(instructions);
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }

}