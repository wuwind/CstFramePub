package com.make.httplibrary.net.request;


import com.make.httplibrary.net.Request;
import com.make.httplibrary.net.response.ChangePassResponse;

/**
 * Created by wuhf on 2017/9/11.
 */

public class ChangePassRequest extends Request<ChangePassResponse> {

    public String oldPassword;
    public String newPassword;
    public String againPassword;

    @Override
    public String url() {
        return "/terminal/account/password/update.do";
    }
}
