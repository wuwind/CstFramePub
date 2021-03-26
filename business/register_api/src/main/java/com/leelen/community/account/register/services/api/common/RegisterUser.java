package com.leelen.community.account.register.services.api.common;

import com.leelen.community.account.register.services.api.bean.UserBean;
import com.wuwind.zrouter_annotation.facade.template.IProvider;

/**
 * Created by wuhf on 2020/6/18.
 * Description:
 */
public interface RegisterUser<T> extends IProvider<T> {
    UserBean getUserName();
}
