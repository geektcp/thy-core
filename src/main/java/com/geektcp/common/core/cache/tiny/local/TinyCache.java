/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.geektcp.common.core.cache.tiny.local;

import com.geektcp.common.core.cache.Store;
import com.geektcp.common.core.cache.tiny.CacheBuilder;
import com.geektcp.common.core.cache.tiny.loader.TinyLoader;
import com.geektcp.common.core.cache.AbstractCache;
import com.geektcp.common.core.cache.tiny.storage.CacheTree;
import com.geektcp.common.core.cache.tiny.storage.NodeKey;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geektcp on 2023/2/26 17:50.
 */
public class TinyCache<K, V> extends AbstractCache<K, V> {

    private Store<NodeKey, V> cacheTree;

    private Map<K, Long> expireTimeMap = new HashMap<>();

    public V putIfAbsent(K key, V value) {
        return null;
    }


    public TinyCache() {
        // build complex cache

    }

    public TinyCache(CacheBuilder<? super K, ? super V> builder, TinyLoader<? super K, V> loader) {


    }


    private void init() {
        cacheTree = new CacheTree<>();
    }

    @Override
    public boolean clear() {
        cacheTree.clear();
        return true;
    }

    @Override
    public boolean refresh(K k) {
        return false;
    }

    @Override
    public V get(K k) {
        return cacheTree.fetch(new NodeKey<>(k));
    }

    @Override
    public boolean put(K k, V v) {
        cacheTree.put(new NodeKey<>(k), v);
        return true;
    }

    @Override
    public boolean delete(K k) {
        cacheTree.delete(new NodeKey<>(k));
        return false;
    }
}
