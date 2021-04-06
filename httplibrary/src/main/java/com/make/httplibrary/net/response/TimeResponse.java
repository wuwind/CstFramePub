package com.make.httplibrary.net.response;


import com.make.httplibrary.net.Response;

/**
 * Created by wuhf on 2017/10/10.
 */

public class TimeResponse extends Response<TimeResponse.TimeNow> {

    public static class TimeNow{
        public long time;
    }
}
