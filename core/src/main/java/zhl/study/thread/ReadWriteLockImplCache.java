package zhl.study.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁实现一个线程安全的Cache
 */
public class ReadWriteLockImplCache<K, V> {

    private Map<K, V> cache = new HashMap<>();

    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    final Lock readLock = readWriteLock.readLock();

    final Lock writeLock = readWriteLock.writeLock();

    V get(K key) {
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    V set(K key, V value) {
        writeLock.lock();
        try {
            return cache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

}
