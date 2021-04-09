package com.make.makeblelibrary.interfaces;

import com.make.makeblelibrary.XxlBle;

/**
 * 指令类
 */
public class Instructions {

    private final Object tag;

    public Instructions(Object tag) {
        this.tag = tag;

    }

    protected void blueWriteData(String data) {
        XxlBle.blueWriteData(data, tag);
    }

    protected void blueWriteDataByteArray(byte data[]) {
        XxlBle.blueWriteDataByteArray(data, tag);
    }

    protected void blueWriteDataStr2Hex(String data) {
        XxlBle.blueWriteDataStr2Hex(data, tag);
    }

    @Override
    public String toString() {
        return "Instructions{" +
                "tag=" + tag +
                '}';
    }
}
