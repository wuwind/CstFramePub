package com.make.utilcode.util;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class HandlerArray<T, K> {

    private CopyOnWriteArrayList<TimeObject<T>> vector = new CopyOnWriteArrayList<>();
    private long mTimeOut = 800;
    private Runnable runnable = new Runnable() {
        public void run() {
            long currentTime = System.currentTimeMillis();
            for (TimeObject<T> tTimeObject : vector) {
                if ((currentTime - tTimeObject.time) > mTimeOut) {
                    vector.remove(tTimeObject);
                    timeOut(tTimeObject.object);
                }
            }
        }
    };

    public HandlerArray() {
        super();
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, mTimeOut, mTimeOut, TimeUnit.MILLISECONDS);
    }

    public boolean add(T t) {
        long ct = System.currentTimeMillis();
        return vector.add(new TimeObject<>(ct, t));
    }

    public T get(K key) {
        for (TimeObject<T> tTimeObject : vector) {
            T object = tTimeObject.object;
            if (getEq(object, key)) {
                return object;
            }
        }
        return null;
    }

    public abstract boolean getEq(T t, K key);

    public T getAndRemove(K key) {
        for (TimeObject<T> tTimeObject : vector) {
            T object = tTimeObject.object;
            if (getEq(object, key)) {
                vector.remove(tTimeObject);
                return object;
            }
        }
        return null;
    }

    public abstract void timeOut(T t);

    static class TimeObject<T> {
        private long time;
        private T object;

        TimeObject(long time, T object) {
            this.time = time;
            this.object = object;
        }
    }

}
