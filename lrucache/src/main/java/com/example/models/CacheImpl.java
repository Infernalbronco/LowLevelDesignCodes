package com.example.models;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


import com.example.models.interfaces.Cache;
import com.example.models.interfaces.CacheEntry;

public abstract class CacheImpl<K, V> implements Cache<K,V> {

    protected final Map<K, CacheEntry<K, V>> map;
    protected final CacheEntry<K, V> listHeadNode, listTailNode;
    protected final int capacity;
    protected ReentrantReadWriteLock lock;
    protected ReadLock readLock;
    protected WriteLock writeLock;
    protected ExecutorService executorService;

    public CacheImpl(Map<K, CacheEntry<K, V>> map, 
                        CacheEntry<K, V> listHeadNode, 
                        CacheEntry<K, V> listTailNode, 
                        int capacity, 
                        int threadPool) {
        this.map = map;
        this.listHeadNode = listHeadNode;
        this.listTailNode = listTailNode;
        this.capacity = capacity;
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
        this.executorService = Executors.newCachedThreadPool();


        this.listHeadNode.setNextEntry(this.listTailNode);
        this.listTailNode.setPrevEntry(this.listHeadNode);
    }

    @Override
    public boolean put(K key, V value) {
        this.writeLock.lock();
        try {
            if (key == null) {
                throw new IllegalArgumentException("Key must not be null");
            }
            if (value == null) {
                throw new IllegalArgumentException("Value must not be null");
            }

            if (this.capacity == map.size()) {
                evict();
            }

            if (!map.containsKey(key)) {
                CacheEntry<K, V> cacheEntry = new CacheEntryImpl<K,V>(key, value);
                addEntryToMap(cacheEntry);
                insertAtHead(cacheEntry);
                return true;
            } else {
                moveToFront(this.map.get(key));
                return true;
            }
        } finally {
            this.writeLock.unlock();
        }
        
    }

    private void addEntryToMap(CacheEntry<K,V> cacheEntry) {
        this.map.put(cacheEntry.getKey(), cacheEntry);
    }

    private void insertAtHead(CacheEntry<K,V> cacheEntry) {
        //Updating new nodes pointer's head and head->next nodes
        cacheEntry.setNextEntry(listHeadNode.getNextEntry());
        cacheEntry.setPrevEntry(listHeadNode);

        //Updating head and head->next node's pointer to new node
        this.listHeadNode.getNextEntry().setPrevEntry(cacheEntry);
        this.listHeadNode.setNextEntry(cacheEntry);
    }

    protected void moveToFront(CacheEntry<K, V> cacheEntry) {
        this.writeLock.lock();
        try {
            //remove node from list
            removeFromList(cacheEntry);

            //insert at the head
            insertAtHead(cacheEntry);
        } finally {
            this.writeLock.unlock();
        }
    }

    protected void removeFromList(CacheEntry<K,V> cacheEntry) {
        //updating prev and next node's pointers
        cacheEntry.getNextEntry().setPrevEntry(cacheEntry.getPrevEntry());
        cacheEntry.getPrevEntry().setNextEntry(cacheEntry.getNextEntry());

        //updating current node's pointers to null
        cacheEntry.setNextEntry(null);
        cacheEntry.setPrevEntry(null);
    }

    @Override
    public abstract CacheEntry<K, V> get(K key);

    @Override
    public abstract void evict();

    
}
