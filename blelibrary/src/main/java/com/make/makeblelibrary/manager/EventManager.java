package com.make.makeblelibrary.manager;

import com.make.makeblelibrary.XxlBle;
import com.make.makeblelibrary.bean.NotifyMessage;
import com.make.utilcode.util.LogUtils;

import org.greenrobot.eventbus.EventBus;

public class EventManager {

    private static final EventBus libraryEvent = EventBus.builder().build();

    /**
     * 蓝牙依赖内部的EventBus
     *
     * @return
     */
    public static EventBus getLibraryEvent() {
        return libraryEvent;
    }

    public static void removeAllEvent() {
        if (libraryEvent != null) libraryEvent.removeAllStickyEvents();
    }

    /**
     * 接收方 发送
     *
     * @param notifyMessage
     */
    public static void recePost(NotifyMessage notifyMessage) {
        if (XxlBle.DEBUG)
            LogUtils.e("recePost:" + notifyMessage);
        libraryEvent.post(notifyMessage);
    }

    /**
     * 服务里面 发送
     *
     * @param notifyMessage
     */
    public static void servicePost(NotifyMessage notifyMessage) {
        if (XxlBle.DEBUG)
            LogUtils.e("servicePost:" + notifyMessage);
        libraryEvent.post(notifyMessage);
    }
}
