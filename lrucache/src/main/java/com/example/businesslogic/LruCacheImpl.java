package com.example.businesslogic;

import java.util.HashMap;

import com.example.models.CacheEntryImpl;
import com.example.models.CacheImpl;
import com.example.models.interfaces.CacheEntry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LruCacheImpl<K,V> extends CacheImpl<K,V> {

    public LruCacheImpl(int capacity) {
        super(new HashMap<>(), 
            new CacheEntryImpl<>(null, null), 
            new CacheEntryImpl<>(null, null), 
            capacity,
            5);
    }

    @Override
    public CacheEntry<K, V> get(K key) {
        this.readLock.lock();
        try {
            //Check is key exists in map or not
            if (!this.map.containsKey(key)) {
                return null;
            }

            //If map contains key, update the list
            executorService.submit(() -> {
                System.out.println("Thread:"+ Thread.currentThread().getName() + " evicting " + this.map.get(key).getValue());
                moveToFront(this.map.get(key));
            });


            return this.map.get(key);
        } finally {
            this.readLock.unlock();
        }
    }

    @Override
    public void evict() {
        CacheEntry<K,V> toBeEvicted = this.listTailNode.getPrevEntry();
        removeFromList(toBeEvicted);
        this.map.remove(toBeEvicted.getKey());

    }
    
}
