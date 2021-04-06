package com.make.httplibrary.net.response;


import com.make.httplibrary.net.Response;

/**
 * Created by wuhf on 2017/9/11.
 */

public class AuthenticodeResponse extends Response<AuthenticodeResponse.Code> {

    public class Code{

        private String authenticodeUuid;

        public String getAuthenticodeUuid() {
            return authenticodeUuid;
        }

        public void setAuthenticodeUuid(String authenticodeUuid) {
            this.authenticodeUuid = authenticodeUuid;
        }
    }
}
