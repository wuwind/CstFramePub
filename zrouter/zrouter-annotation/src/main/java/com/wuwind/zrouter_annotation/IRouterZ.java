package com.wuwind.zrouter_annotation;


import com.wuwind.zrouter_annotation.facade.model.RouteMeta;

import java.util.Map;

/**
 * 路由 接口类
 * 路由的作用就是：注册Activity 或者 Fragment 或者 Provider
 */
public interface IRouterZ {

    void onLoad(Map<String, RouteMeta> routes);

}
