package com.example.models;

import com.example.models.interfaces.CacheEntry;

public class CacheEntryImpl<K, V> implements CacheEntry<K,V> {

    private final K key;
    private final V value;
    private CacheEntry<K,V> nexCacheEntry, prevCacheEntry;

    public CacheEntryImpl(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public CacheEntry<K, V> getNextEntry() {
        return this.nexCacheEntry;
    }

    @Override
    public CacheEntry<K, V> getPrevEntry() {
        return this.prevCacheEntry;
    }

    @Override
    public void setNextEntry(CacheEntry<K,V> cacheEntry) {
        this.nexCacheEntry = cacheEntry;
    }

    @Override
    public void setPrevEntry(CacheEntry<K,V> cacheEntry) {
        this.prevCacheEntry = cacheEntry;
    }
    
}
