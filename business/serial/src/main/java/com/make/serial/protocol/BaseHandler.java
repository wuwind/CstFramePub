package com.make.serial.protocol;

import com.make.serial.SerialManager;
import com.make.serial.bean.ProtocolBean;

import java.util.Random;

/**
 * Created by Wuhf on 2019/12/9.
 * Description ：处理基类
 */
public abstract class BaseHandler<K extends ProtocolBean> implements IHandler<K> {

    public ProtocolBean getProtocolBean() {
        return protocolBean;
    }

    protected ProtocolBean protocolBean;

    private String seq;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    /**
     * 处理  构建和发送数据
     */
    @Override
    public void handleMessage() {
        SerialManager.getInstance().sendLockData(this);
    }


    public byte[] getDataArray() {
        if (null != protocolBean) {
            return protocolBean.getDataArray();
        }
        return null;
    }


    @Override
    public void callback(K resultInfo) {

    }

    @Override
    public void timeOut() {

    }


    /**
     * @return 产生序列号
     */
    private int getRandomSeq() {
        return Math.abs(new Random().nextInt());
    }


    /***
     * 发送
     */
    private void sendResponseData(int qos, String topic, String responseInfo) {

    }


    /**
     * @return 是否需要加密
     */
    protected boolean isEncrypt() {
        return true;
    }

    /**
     * @param success 后处理
     */
    public abstract void handleComplete(boolean success);
}
