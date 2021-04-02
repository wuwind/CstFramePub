package com.make.serial.protocol;


import com.make.serial.bean.ProtocolBean;

/**
 * Created by Wuhf on 2019/12/19.
 * Description ：iot处理接口
 */
public interface IHandler<K extends ProtocolBean> {

    /**
     *  处理消息
     */
    void handleMessage();

    /**
     * @param resultInfo 消息回调
     */
    void callback(K resultInfo);

    /**
     * 超时
     */
    void timeOut();
}
