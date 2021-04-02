package com.make.uilibrary.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类,写Fragment时必须继承
 * Description ：
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(setContentLayoutId(), container, false);
        initView();
        initMonitor();
        return view;
    }

    protected abstract int setContentLayoutId();

    protected abstract void initView();
    protected abstract void initMonitor();


}
