package com.make.uilibrary.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.viewbinding.ViewBinding;

import com.make.uilibrary.click.OnClickUtils;
import com.make.utilcode.util.GenericUtil;

import java.lang.reflect.Method;

/**
 * Fragment基类,写Fragment时必须继承
 * Description ：
 */
public abstract class BaseFragment<T extends ViewBinding> extends Fragment {

    protected T viewBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = getBinding(inflater, container);
        OnClickUtils.init(this, viewBinding.getRoot());
        init();
        return viewBinding.getRoot();
    }

    protected T getBinding(LayoutInflater inflater,ViewGroup container){
        try {
            Class<?> clazz = GenericUtil.getSuperClassGenricType(this.getClass());
            Method inflate = clazz.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            return (T) inflate.invoke(null, inflater, container, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract void init();




}
