package com.make.register.services.impl;

import com.make.register.services.api.bean.UserBean;
import com.make.register.services.api.common.RegisterUser;
import com.make.utilcode.util.SPUtils;
import com.wuwind.common.RouterPathConst;
import com.wuwind.zrouter_annotation.ZRoute;

@ZRoute(RouterPathConst.PATH_PROVIDER_MINE) // 数据交互的Provider，必须用接口SimpleName来注册
public class RegisterUserImpl implements RegisterUser {
    @Override
    public UserBean getUserName() {
        UserBean userBean = new UserBean();
        userBean.name = SPUtils.getInstance().getString("register", "1");;
        return userBean;
    }

    @Override
    public void init(Object mContext) {

    }
}
