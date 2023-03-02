package com.geektcp.common.core.cache;

import com.geektcp.common.core.system.Sys;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * @author geektcp on 2019/11/30 0:59.
 */
public class GuavaCacheTest {

    private static RemovalListener<String, Integer> localListener = new RemovalListener<String, Integer>() {
        @Override
        public void onRemoval(RemovalNotification<String, Integer> notification) {
            String describe = String.format("thread id=%s, key=%s, value=%s, reason=%s in localListener",
                    Thread.currentThread().getId(),
                    notification.getKey(),
                    notification.getValue(),
                    notification.getCause());
            Sys.p(describe);

            if (notification.getCause().equals(RemovalCause.EXPIRED) && notification.getValue() != null) {
                Sys.printf("remove %s expired cache\n", notification.getKey());
            }
        }
    };

    public static LoadingCache<String, Integer> LocalCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(7, TimeUnit.SECONDS)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(localListener)
            .build(new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String key) throws Exception {
                    return 0;
                }
            });

}