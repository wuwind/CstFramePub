package com.make.serial;

import com.make.serial.bean.ComBean;
import com.make.serial.bean.ProtocolBean;
import com.make.serial.protocol.BaseHandler;
import com.make.serial.utils.HandlerArray;
import com.make.utilcode.util.HexUtil;
import com.make.utilcode.util.LogUtils;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android_serialport_api.SerialPortFinder;

public class SerialManager {

    private Map<String, SerialHelper> serials = new HashMap<>();

    private SerialManager() {
    }

    static class Instance {
        public static SerialManager instance = new SerialManager();
    }

    private HandlerArray<BaseHandler, ProtocolBean> handlerArray = new HandlerArray<BaseHandler, ProtocolBean>() {
        @Override
        public boolean getEq(BaseHandler handler, ProtocolBean key) {
            ProtocolBean protocolBean = handler.getProtocolBean();
            return protocolBean.getCmd() == key.getCmd() && protocolBean.getAddress() == key.getAddress() && protocolBean.getNumber() == key.getNumber();
        }

        @Override
        public void timeOut(BaseHandler handler) {
            handler.timeOut();
        }
    };

    public void addHandler(BaseHandler handler) {
        handlerArray.add(handler);
    }

    public static SerialManager getInstance() {
        return Instance.instance;
    }

    public void init() {
        serials.clear();
        List<String> allDevices = findSerial();
        initSerial(allDevices);
    }

    private List<String> findSerial() {
        SerialPortFinder mSerialPortFinder = new SerialPortFinder();
        String[] entryValues = mSerialPortFinder.getAllDevicesPath();
        List<String> allDevices = new ArrayList<>();
        for (String entryValue : entryValues) {
            if (entryValue.contains("ttyUSB")) {
                allDevices.add(entryValue);
                LogUtils.i(entryValue);
            }
        }
        return allDevices;
    }

    private void initSerial(List<String> allDevices) {
        switch (allDevices.size()) {
            case 0:
                LogUtils.e("未检测出串口!");
                break;
            case 1:
//                fingerSerial = allDevices.get(0);
                confirmSerial(allDevices.get(0));
                break;
            case 2:
//                lockSerial = allDevices.get(0);
//                fingerSerial = allDevices.get(1);
//                LogUtils.i(lockSerial + ":" + fingerSerial);
//                confirmSerial(allDevices.get(1));
                break;
            default:
                break;
        }
    }

    private void confirmSerial(String serialPort) {
        SerialControl pwdSerial = new SerialControl();
        pwdSerial.setPort(serialPort);
        pwdSerial.setBaudRate(9600);
        if (openSerial(pwdSerial)) {
            serials.put("lock", pwdSerial);
//            try {
//                sendPortData(pwdSerial, Constant.ReadCardCmd.READ_CARD_CMD);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } else {
            LogUtils.e("打开串口失败:" + serialPort);
        }
    }

    private boolean openSerial(SerialHelper pwdSerial) {
        try {
            pwdSerial.open();
            LogUtils.i("打开串口成功!");
            return true;
        } catch (SecurityException e) {
            LogUtils.e("打开串口失败:没有串口读/写权限!");
        } catch (IOException e) {
            LogUtils.e("打开串口失败:未知错误!");
        } catch (InvalidParameterException e) {
            LogUtils.e("打开串口失败:参数错误!");
        }
        return false;
    }

    // 串口发送
    public void sendLockData(BaseHandler handler) {
        sendPortData(handler, serials.get("lock"), handler.getDataArray());
    }

    // 串口发送
    public void sendPortData(BaseHandler handler, SerialHelper pwdSerial, byte[] sOut) {
        if (pwdSerial != null && pwdSerial.isOpen() && null != sOut) {
            pwdSerial.send(sOut);
            LogUtils.e(HexUtil.ByteArrToHex(sOut));
            if (null != handler) {
                handler.handleComplete(true);
                addHandler(handler);
            }
        } else {
            LogUtils.e("serial is null or data is null");
            if (null != handler) {
                handler.handleComplete(false);
            }
        }
    }

    // 关闭串口
    public void closeComPort(SerialHelper pwdSerial) {
        if (pwdSerial != null) {
            pwdSerial.stopSend();
            pwdSerial.close();
        }
    }

    // 关闭串口
    public void closeAll() {
        Collection<SerialHelper> values = serials.values();
        for (SerialHelper value : values) {
            closeComPort(value);
        }
    }

    // 串口控制类
    private class SerialControl extends SerialHelper {

        public SerialControl() {
        }

        @Override
        protected void onDataReceived(final ComBean rec) {
//            Log.i("App", MyFunc.ByteArrToHex(ComRecData.bRec).replaceAll(" ", "").toLowerCase());
            SerialHelper lock = serials.get("lock");
            if (null != lock && lock.getPort().equals(rec.sComPort)) {
                ProtocolBean protocolBean = new ProtocolBean(rec.bRec);
                BaseHandler handler = handlerArray.getAndRemove(protocolBean);
                if (null != handler) {
                    handler.callback(protocolBean);
                }
            }
        }
    }


}
