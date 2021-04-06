package com.make.httplibrary.net.response;


import com.make.httplibrary.net.Response;

import java.util.List;

/**
 * Created by hongfengwu on 2017/9/18.
 */

public class AdResponse extends Response {


    public List<Ad> result;

    public static class Ad {
        public long attachId;
        public String attachName;
        public String attachSize;
        public String Extension;
        public String createDateTime;

    }
}
