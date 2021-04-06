package com.make.httplibrary.net.request;


import com.make.httplibrary.net.Request;
import com.make.httplibrary.net.response.LoginResponse;

/**
 * Created by hongfengwu on 2017/3/25.
 */

public class LoginRequest extends Request<LoginResponse> {

    public String username;
    public String password;
    public String longitude;
    public String latitude;
    public String deviceToken;
    public String externalAddress;
    public String deviceMac;
    public String voucher;

    public LoginRequest(boolean isTerminal) {
        voucher = isTerminal ? "TERMINAL" : "FRONT ";
    }

    @Override
    public String url() {
        return "/login.do";
    }

    @Override
    public void requset() {
        super.requset();
    }
}
