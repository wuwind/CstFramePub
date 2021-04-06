package com.make.httplibrary.net.response;


import com.make.httplibrary.net.Response;

/**
 * Created by wuhf on 2017/9/22.
 */

public class UpdateResponse extends Response<UpdateResponse.Info> {

    public static class Info{
        public Update version;

        public static class Update {
            public int version;
            //版本号
            public String versionName;
            //版本名
            public String mandatoryUpgrade;
            //是否强制升级
            public String upgradeRemark;
            //更新说明
            public String publishDateTime;
            //版本发布时间
            public String attachName;
            //升级文件名
            public String attachPath;
            //升级文件地址
        }
    }
}
