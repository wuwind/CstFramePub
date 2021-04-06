package com.make.httplibrary.net.request;


import com.make.httplibrary.net.Request;
import com.make.httplibrary.net.Response;

/**
 * Created by wuhf on 2017/9/11.
 */

public class LogoutRequest extends Request<Response> {
    @Override
    public String url() {
        return "/logout.do";
    }
}
