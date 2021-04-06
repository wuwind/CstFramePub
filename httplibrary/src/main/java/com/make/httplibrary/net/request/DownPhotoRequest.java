package com.make.httplibrary.net.request;


import com.make.httplibrary.net.Request;
import com.make.httplibrary.net.response.DownPhotoResponse;

/**
 * Created by wuhf on 2017/9/16.
 */

public class DownPhotoRequest extends Request<DownPhotoResponse> {

    public long attachId;

    public DownPhotoRequest( long attachId){
        this.attachId = attachId;
    }

    @Override
    public String url() {
        return "/terminal/material/images/source.do";
    }
}
