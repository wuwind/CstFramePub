package com.wuwind.zrouter_api.core;

import android.content.Context;
import android.util.LruCache;

import com.wuwind.zrouter_annotation.ZRoute;
import com.wuwind.zrouter_annotation.facade.service.AutowiredService;
import com.wuwind.zrouter_annotation.facade.template.ISyringe;

import java.util.ArrayList;
import java.util.List;

import static com.wuwind.zrouter_api.utils.Consts.SUFFIX_AUTOWIRED;


/**
 * Autowired service impl.
 *
 * @author zhilong <a href="mailto:zhilong.lzl@alibaba-inc.com">Contact me.</a>
 * @version 1.0
 * @since 2017/2/28 下午6:08
 */
@ZRoute("/arouter/service/autowired")
public class AutowiredServiceImpl implements AutowiredService<Context> {
    private LruCache<String, ISyringe> classCache;
    private List<String> blackList;

    @Override
    public void init(Context context) {
        classCache = new LruCache<>(66);
        blackList = new ArrayList<>();
    }

    @Override
    public void autowire(Object instance) {
        String className = instance.getClass().getName();
        try {
            if (!blackList.contains(className)) {
                ISyringe autowiredHelper = classCache.get(className);
                if (null == autowiredHelper) {  // No cache.
                    autowiredHelper = (ISyringe) Class.forName(instance.getClass().getName() + SUFFIX_AUTOWIRED).getConstructor().newInstance();
                }
                autowiredHelper.inject(instance);
                classCache.put(className, autowiredHelper);
            }
        } catch (Exception ex) {
            blackList.add(className);    // This instance need not autowired.
        }
    }

}
