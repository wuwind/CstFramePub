package com.make.serial.protocol;

import com.make.serial.bean.ProtocolBean;
import com.make.utilcode.util.HexUtil;
import com.make.utilcode.util.LogUtils;

public class OpenHandler extends BaseHandler {

    public OpenHandler(int number) {
        this.protocolBean = new ProtocolBean();
        protocolBean.setCmd(0x8A);
        protocolBean.setAddress(0x01);
        protocolBean.setNumber(number);
        protocolBean.setFunction(0x11);
    }

    @Override
    public void handleComplete(boolean success) {
        LogUtils.e( "handleComplete " + success);
    }

    @Override
    public String getSeq() {
        return "lock";
    }

    @Override
    public void timeOut() {
        super.timeOut();
        LogUtils.e("timeout");
    }

    @Override
    public void callback(ProtocolBean resultInfo) {
        LogUtils.e("callback "+ HexUtil.ByteArrToHex(resultInfo.getDataArray()));
    }
}
