package com.make.httplibrary.net.request;


import com.make.httplibrary.net.Request;
import com.make.httplibrary.net.response.AuthenticodeResponse;

/**
 * Created by wuhf on 2017/9/11.
 */

public class AuthenticodeRequest extends Request<AuthenticodeResponse> {

    public String mobile;

    public AuthenticodeRequest(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String url() {
        return "/public/retrieve.do";
    }
}
