package com.make.httplibrary.net;

/**
 * Created by hongfengwu on 2017/3/25.
 */

public class Response<T>{

    public boolean success;
    public String msg;
    public String type;
    public T info;

    public Object tag;

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", info=" + info +
                ", tag=" + tag +
                '}';
    }
}
