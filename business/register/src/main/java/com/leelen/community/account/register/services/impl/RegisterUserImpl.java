package com.leelen.community.account.register.services.impl;

import com.leelen.community.account.register.services.api.bean.UserBean;
import com.leelen.community.account.register.services.api.common.RegisterUser;
import com.wuwind.common.RouterPathConst;
import com.wuwind.zrouter_annotation.ZRoute;

@ZRoute(RouterPathConst.PATH_PROVIDER_MINE) // 数据交互的Provider，必须用接口SimpleName来注册
public class RegisterUserImpl implements RegisterUser {
    @Override
    public UserBean getUserName() {
        UserBean userBean = new UserBean();
        userBean.name = "Register user name";
        return userBean;
    }

    @Override
    public void init(Object mContext) {

    }
}
