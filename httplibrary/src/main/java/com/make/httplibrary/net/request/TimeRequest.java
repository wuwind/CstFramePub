package com.make.httplibrary.net.request;


import com.make.httplibrary.net.Request;
import com.make.httplibrary.net.response.TimeResponse;

/**
 * Created by wuhf on 2017/10/10.
 */

public class TimeRequest extends Request<TimeResponse> {
    @Override
    public String url() {
        isShowToast = false;
        return "/terminal/application/time.jhtml";
    }
}
