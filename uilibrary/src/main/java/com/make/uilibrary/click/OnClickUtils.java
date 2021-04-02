package com.make.uilibrary.click;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OnClickUtils {

    public static void init(Activity target) {
        View sourceView = target.getWindow().getDecorView();
        initClick(target, sourceView);
    }

    public static void init(Object target, View view) {
        initClick(target, view);
    }

    private static void initClick(@NonNull final Object target, @NonNull View source) {
        String packageName = source.getContext().getPackageName();
        final Method[] methods = target.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            boolean hasAnno = methods[i].isAnnotationPresent(AClick.class);
            if (hasAnno) {
                AClick aClick = methods[i].getAnnotation(AClick.class);
                assert aClick != null;
                int[] ids = aClick.value();
                for (int j = 0; j < ids.length; j++) {
                    final int finalI = i;
                    source.findViewById(ids[j]).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                methods[finalI].invoke(target, v);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
            boolean hasAnnoStr = methods[i].isAnnotationPresent(AClickStr.class);
            if (hasAnnoStr) {
                AClickStr aClick = methods[i].getAnnotation(AClickStr.class);
                final String[] ids = aClick.value();
                for (int j = 0; j < ids.length; j++) {
                    final int finalI = i;
                    int id = source.getResources().getIdentifier(ids[j], "id", packageName);
                    if (id == 0) {
                        continue;
                    }
                    final int finalJ = j;
                    source.findViewById(id).setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            try {
                                methods[finalI].invoke(target, v, ids[finalJ]);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }
}
