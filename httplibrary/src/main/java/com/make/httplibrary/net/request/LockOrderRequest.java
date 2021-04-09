package com.make.httplibrary.net.request;

import com.make.httplibrary.net.Request;
import com.make.httplibrary.net.response.LockOrderResponse;

/**
 * Created by wuhongfeng
 * data: 2021/4/7
 * desc:
 */
public class LockOrderRequest extends Request<LockOrderResponse> {

    public String data = "82fc5514a7cbbdbe7bb90385e2d4bdf2b14ef2e5a5ffa1b8";

    @Override
    public String url() {
        return "automata/lockOrder/query";
    }
}
