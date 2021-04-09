package com.make.httplibrary.net;

/**
 * Created by hongfengwu on 2017/3/25.
 */

public class Response<T> {

    public int code;
    public String message;
    public String type;
    public T data;

    public Object tag;

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", data=" + data +
                ", tag=" + tag +
                '}';
    }
}
