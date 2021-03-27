package com.make.account.login.model;

public interface Callback<T> {

    public void onSuccess(T t);

    public void onFailed(String msg);
}
