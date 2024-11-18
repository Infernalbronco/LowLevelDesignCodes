package com.example.models.interfaces;

public interface CacheEntry<K,V> {

    public K getKey();

    public V getValue();

    public CacheEntry<K, V> getNextEntry();

    public CacheEntry<K, V> getPrevEntry();

    public void setNextEntry(CacheEntry<K,V> cacheEntry);

    public void setPrevEntry(CacheEntry<K,V> cacheEntry);
    
}
