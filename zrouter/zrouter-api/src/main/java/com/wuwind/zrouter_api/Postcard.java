package com.wuwind.zrouter_api;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wuwind.zrouter_annotation.facade.model.RouteMeta;
import com.wuwind.zrouter_annotation.facade.template.IProvider;

/**
 * 用于封装 目的地的各种操作，比如跳转Activity，切换Fragment，或者执行 业务模块暴露出来的service
 */
public class Postcard extends RouteMeta {


    private IProvider provider;//为了模块间数据交互而预备的属性，先放着

    private Bundle mBundle;// activity跳转的时候有可能会携带参数，这里先预留一个属性
    private int flag = FLAG_DEFAULT;// Activity的启动模式，在java里面是用int值表示的，所以这里也留个字段

    public static final int FLAG_DEFAULT = -1;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int mFlag) {
        this.flag = mFlag;
    }

    public Bundle getExtras() {
        return mBundle;
    }

    public void setExtras(Bundle bundle) {
        this.mBundle = bundle;
    }

    public IProvider getProvider() {
        return provider;
    }

    public void setProvider(IProvider provider) {
        this.provider = provider;
    }

    public Postcard(String path) {
        this(path, null);
    }

    public Postcard(String path, Bundle bundle) {
        this.path = path;
        this.mBundle = (null == bundle ? new Bundle() : bundle);
    }

    /**
     * 开始执行任务，是跳转Activity呢？还是切换Fragment呢？还是 执行业务模块暴露的服务呢。。。
     */
    public Object navigation() {
        return navigation(null, -1);
    }

    public Object navigation(Activity activity, int requestCode) {
        return ZRouter.getInstance().navigation(this, activity, requestCode);
    }

    /**
     * Inserts a String value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a String, or null
     * @return current
     */
    public Postcard withString(@Nullable String key, @Nullable String value) {
        mBundle.putString(key, value);
        return this;
    }

    /**
     * Inserts a Boolean value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a boolean
     * @return current
     */
    public Postcard withBoolean(@Nullable String key, boolean value) {
        mBundle.putBoolean(key, value);
        return this;
    }

    /**
     * Inserts a short value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a short
     * @return current
     */
    public Postcard withShort(@Nullable String key, short value) {
        mBundle.putShort(key, value);
        return this;
    }

    /**
     * Inserts an int value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value an int
     * @return current
     */
    public Postcard withInt(@Nullable String key, int value) {
        mBundle.putInt(key, value);
        return this;
    }

    /**
     * Inserts a long value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a long
     * @return current
     */
    public Postcard withLong(@Nullable String key, long value) {
        mBundle.putLong(key, value);
        return this;
    }

    /**
     * Inserts a double value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a double
     * @return current
     */
    public Postcard withDouble(@Nullable String key, double value) {
        mBundle.putDouble(key, value);
        return this;
    }

    /**
     * Inserts a byte value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a byte
     * @return current
     */
    public Postcard withByte(@Nullable String key, byte value) {
        mBundle.putByte(key, value);
        return this;
    }

    /**
     * Inserts a char value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a char
     * @return current
     */
    public Postcard withChar(@Nullable String key, char value) {
        mBundle.putChar(key, value);
        return this;
    }

    /**
     * Inserts a float value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a float
     * @return current
     */
    public Postcard withFloat(@Nullable String key, float value) {
        mBundle.putFloat(key, value);
        return this;
    }

    /**
     * Inserts a CharSequence value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a CharSequence, or null
     * @return current
     */
    public Postcard withCharSequence(@Nullable String key, @Nullable CharSequence value) {
        mBundle.putCharSequence(key, value);
        return this;
    }
}
