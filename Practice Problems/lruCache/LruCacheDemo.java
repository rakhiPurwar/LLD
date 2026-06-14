package lruCache;

import lruCache.cache.LRUCache;

public class LruCacheDemo {

//supports all king of key value pairs
    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache(3);
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        System.out.println("Cache after adding 3 items: " + lruCache);

        lruCache.get(1);
        System.out.println("Cache after accessing key 1: " + lruCache);

        lruCache.put(4, "Four");
        System.out.println("Cache after adding key 4 (should evict key 2): " + lruCache);

        lruCache.get(2);
        System.out.println("Cache after accessing key 2 (should be null): " + lruCache.get(2));

        lruCache.put(5, "Five");
        System.out.println("Cache after adding key 5 (should evict key 3): " + lruCache);
    }
}
