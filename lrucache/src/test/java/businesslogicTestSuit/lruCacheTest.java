package businesslogicTestSuit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.businesslogic.LruCacheImpl;
import com.example.models.interfaces.Cache;

public class lruCacheTest<K, V> {

    @Test
    @DisplayName("Test cache get and put")
    public void testCacheGetAndPut() {
        Cache<String,Integer> lruCache = new LruCacheImpl<String, Integer>(2);

        //{"1" : 1}
        lruCache.put("1", 1);
        assertEquals(1, lruCache.get("1").getValue());

        // {"2":2, "1":1}
        lruCache.put("2", 2);
        assertEquals(2, lruCache.get("2").getValue());

        // {"3":3, "2":2}
        lruCache.put("3", 3);
        assertNull(lruCache.get("1"));

        // {"2":2, "3":3}
        lruCache.get("2");
        // {"4":4, "2":2}
        lruCache.put("4", 4);
        assertNull(lruCache.get("3"));

    }

    @Test
    @DisplayName("Test LruCache in multithreaded env")
    public void testCacheInMultithreadedEnv() throws InterruptedException {
        final Cache<String,Integer> lruCache = new LruCacheImpl<String, Integer>(50);
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        final CountDownLatch countDownLatch = new CountDownLatch(50);

        try {
            IntStream.range(0, 50).<Runnable>mapToObj(key -> () -> {
                final String newKey = String.format("value%d", key);
                System.out.printf("Thread:[%s], Key=[%s], Val=[%d]%n",
                                  Thread.currentThread().getName(), newKey, key);
                lruCache.put(newKey, key); // value1, 1
                countDownLatch.countDown();
            }).forEach(executorService::submit);
            countDownLatch.await();
        } finally {
            executorService.shutdown();
        }

        IntStream.range(0, 50).forEach(i -> assertEquals(i, lruCache.get("value" + i).getValue()));

    }

}
