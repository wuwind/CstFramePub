package com.make.httplibrary.net.request;


import com.make.httplibrary.net.Request;
import com.make.httplibrary.net.response.AdResponse;

/**
 * Created by hongfengwu on 2017/9/18.
 */

public class AdRequest extends Request<AdResponse> {
    @Override
    public String url() {
        return "/terminal/material/advertisement/list.do";
    }
}
