package com.make.httplibrary.net.response;

/**
 * Created by wuhf on 2017/11/3.
 */

public class RelayResponse {

    public boolean isConnected;

    public String operation;

    public RelayResponse(boolean isConnected, String operation) {
        this.isConnected = isConnected;
        this.operation = operation;
    }
}
