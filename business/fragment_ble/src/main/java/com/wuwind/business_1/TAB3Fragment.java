package com.wuwind.business_1;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.make.makeblelibrary.XxlBle;
import com.make.makeblelibrary.bean.CharacteristicValues;
import com.make.makeblelibrary.bean.NotifyMessage;
import com.make.makeblelibrary.bean.UUIDMessage;
import com.make.makeblelibrary.interfaces.BaseNotifyListener;
import com.make.uilibrary.base.BaseFragment;
import com.make.uilibrary.click.AClickStr;
import com.make.utilcode.util.LogUtils;
import com.make.utilcode.util.ToastUtils;
import com.wuwind.business_1.bean.DeviceBean;
import com.wuwind.business_1.databinding.FragmentTab3Binding;
import com.wuwind.common.RouterPathConst;
import com.wuwind.zrouter_annotation.ZRoute;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.make.makeblelibrary.manager.EventManager.getLibraryEvent;


@ZRoute(RouterPathConst.PATH_FRAGMENT_TAB3)
public class TAB3Fragment extends BaseFragment<FragmentTab3Binding> implements BaseNotifyListener.DeviceListener, BaseNotifyListener.DeviceDataListener, BaseNotifyListener.ServiceListener {

    //    private Map<String, String> devices = new HashMap<>();
    private List<DeviceBean> data = new ArrayList<>();
    private DataAdapter dataAdapter = new DataAdapter();

    public static TAB3Fragment newInstance(Bundle bundle) {
        TAB3Fragment homeFragment = new TAB3Fragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                //判断是否跟用户做一个说明
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getLibraryEvent().register(this);
        queryStatue();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            List<BluetoothDevice> connDeviceAll = XxlBle.getConnDeviceAll();
                LogUtils.e("query ");
            for (BluetoothDevice d : connDeviceAll) {
                XxlBle.blueWriteDataStr2Hex("550355", d.getAddress());
            }
            queryStatue();
        }
    };

    private void queryStatue() {
        viewBinding.getRoot().postDelayed(runnable, 5000);
    }

    @Override
    public void onStop() {
        super.onStop();
        getLibraryEvent().unregister(this);
        viewBinding.getRoot().removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        XxlBle.blueDisconnectedDeviceAll();
    }

    @Override
    public void init() {
        viewBinding.rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewBinding.rvData.setAdapter(dataAdapter);
        dataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                XxlBle.blueStopScaner();
                ToastUtils.showShort("stop");
                String address = data.get(position).getAddress();
                BluetoothDevice connDevice = XxlBle.getConnDevice(address);
                LogUtils.e("runing " + XxlBle.runing(getActivity()));
                if (null != connDevice) {
                    XxlBle.blueWriteDataStr2Hex("550155", address);
                } else {
                    XxlBle.blueConnectDevice(address, address);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        XxlBle.blueStartScaner(100);//启动蓝牙扫描 不延迟返回  扫到一个返回一个
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NotifyMessage notifyMessage) {
        XxlBle.dealtListener(this, notifyMessage);
    }

    @Override
    public void onDeviceScanner(BluetoothDevice device) {

    }

    @Override
    public void onDeviceScanner(List<BluetoothDevice> mDevices) {
        for (BluetoothDevice device : mDevices) {
            LogUtils.e(device.getName());
            if (device.getName() != null && (device.getName().startsWith("MK") || device.getName().startsWith("WH"))) {
                boolean contains = false;
                for (DeviceBean d : data) {
                    if (d.getAddress().equals(device.getAddress())) {
                        contains = true;
                    }
                }
                if (!contains) {
                    DeviceBean bean = new DeviceBean();
                    bean.setAddress(device.getAddress());
                    bean.setName(device.getName());
                    data.add(bean);
                    XxlBle.blueStopScaner();
                }
            }
        }
        dataAdapter.setNewData(data);
    }

    @Override
    public void onDeviceConnectState(boolean connectState, Object tag) {
        LogUtils.e(tag.toString() + " connectState " + connectState);
    }

    @Override
    public void onDeviceServiceDiscover(List<BluetoothGattService> services, final Object tag) {
        LogUtils.e("onDeviceServiceDiscover");
        UUIDMessage uuidMessage = new UUIDMessage();//创建UUID的配置类
        uuidMessage.setCharac_uuid_service("0003CDD0-0000-1000-8000-00805F9B0131");
        uuidMessage.setCharac_uuid_write("0003CDD2-0000-1000-8000-00805F9B0131");
        uuidMessage.setCharac_uuid_read("0003CDD1-0000-1000-8000-00805F9B0131");
        uuidMessage.setDescriptor_uuid_notify("00002902-0000-1000-8000-00805f9b34fb");
        /**
         * 这里简单说一下  如果设备返回数据的方式不是Notify的话  那就意味着向设备写出数据之后   再自己去获取数据
         * Notify的话 是如果蓝牙设备有数据传递过来  能接受到通知
         * 使用场景中如果没有notify的话  notify uuid留空即可
         */
        XxlBle.blueRegisterDevice(uuidMessage, tag);//注册设备
    }

    @Override
    public void onDeviceRegister(boolean registerState, final Object tag) {
        LogUtils.e("onDeviceRegister " + registerState);
        if (registerState) {
            XxlBle.setBlueWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE, tag);
            viewBinding.tvScanner.postDelayed(new Runnable() {
                @Override
                public void run() {
                    XxlBle.blueWriteDataStr2Hex("550155", tag);
                }
            }, 100);
        }
    }

    @AClickStr({"tv_scanner"})
    public void click(View view, String viewId) {
        switch (viewId) {
            case "tv_scanner":
                XxlBle.blueStartScaner(100);//启动蓝牙扫描 不延迟返回  扫到一个返回一个
                break;
        }
    }

    @Override
    public void onDeviceWriteState(boolean writeState, Object tag) {
        com.make.utilcode.util.LogUtils.e("onDeviceWriteState " + writeState);
    }

    @Override
    public void onDeviceReadMessage(CharacteristicValues characteristicValues, Object tag) {
        com.make.utilcode.util.LogUtils.e("onDeviceReadMessage ");
    }

    @Override
    public void onDeviceNotifyMessage(CharacteristicValues characteristicValues, Object tag) {
        com.make.utilcode.util.LogUtils.e("onDeviceNotifyMessage " + characteristicValues.getHex2Str());
        for (DeviceBean d : dataAdapter.getData()) {
            if (d.getAddress().equals(tag.toString())) {
                d.setDesc(characteristicValues.getHex2Str());
                dataAdapter.notifyItemChanged(dataAdapter.getData().indexOf(d));
            }
        }
    }

    @Override
    public void onServiceStart() {
        XxlBle.blueStartScaner(100);//启动蓝牙扫描 不延迟返回  扫到一个返回一个
    }

    @Override
    public void onServiceStop() {

    }
}
