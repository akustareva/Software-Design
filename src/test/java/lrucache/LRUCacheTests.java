package lrucache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LRUCacheTests {
    private LRUCache<Integer, Integer> lruCache;
    private final int maxCacheSize = 100;

    @Before
    public void init() {
        lruCache = new LRUCache<>();
    }

    @Test
    public void sequentialAdding() {
        for (int i = 0; i < maxCacheSize; i++) {
            lruCache.put(i, i);
        }
        assertEquals(maxCacheSize, lruCache.size());
        for (int i = 0; i < maxCacheSize; i++) {
            assertEquals((Integer) i, lruCache.get(i));
        }
    }

    @Test
    public void doubleAdding() {
        for (int i = 0; i < maxCacheSize; i++) {
            lruCache.put(i, i);
        }
        for (int i = 0; i < maxCacheSize; i++) {
            lruCache.put(i, i);
        }
        assertEquals(maxCacheSize, lruCache.size());
        for (int i = 0; i < maxCacheSize; i++) {
            assertEquals((Integer) i, lruCache.get(i));
        }
    }

    @Test
    public void addingAndRemoving() {
        for (int i = 0; i < maxCacheSize; i++) {
            lruCache.put(i, i);
        }
        for (int i = maxCacheSize; i < 2 * maxCacheSize; i++) {
            lruCache.put(i, i);
        }
        assertEquals(maxCacheSize, lruCache.size());
        for (int i = maxCacheSize; i < 2 * maxCacheSize; i++) {
            assertEquals((Integer) i, lruCache.get(i));
        }
    }

    @Test
    public void randomTest() {
        int minVal = 0;
        int maxVal = 10000;
        for (int i = 0; i < 1000000; i++) {
            int key = ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
            int value = ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
            lruCache.put(key, value);
            assertEquals((Integer) value, lruCache.get(key));
        }
    }

    @Test
    public void getNonexistentValues() {
        for (int i = 0; i < 10; i++) {
            Integer result = lruCache.get(i);
            assertTrue(result == null);
        }
    }

    @Test(expected = AssertionError.class)
    public void putInvalidInputTest() {
        lruCache.put(null, 123);
    }

    @Test(expected = AssertionError.class)
    public void getInvalidInputTest() {
        lruCache.get(null);
    }

    @After
    public void fin() {
        lruCache = null;
    }
}
