package com.wuwind.zrouter_annotation.facade.template;

/**
 * IProvider，为模块之间提供数据交互的接口
 */
public interface IProvider<T> {
    void init(T mContext);
}
