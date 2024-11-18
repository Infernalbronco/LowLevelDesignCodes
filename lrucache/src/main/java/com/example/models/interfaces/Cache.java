package  com.example.models.interfaces;

public interface Cache<K, V> {

    public boolean put(K key, V value);

    public CacheEntry<K, V> get(K key);

    public void evict();
    
}
