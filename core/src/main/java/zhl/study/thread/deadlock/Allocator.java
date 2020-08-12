package zhl.study.thread.deadlock;

import java.util.ArrayList;
import java.util.List;

/**
 * 锁的持有者
 */
public class Allocator {

    private volatile Allocator instance;

    public Allocator getInstance() {
        if (null == instance) {
            synchronized(this) {
                if (null == instance) {
                    instance = new Allocator();
                }
            }
        }
        return instance;
    }

    /**
     * 持有多重资源
     */
    private List<Object> als = new ArrayList<>();

    /**
     * 一次性获取所有的资源
     *
     * @param from
     * @param to
     *
     * @return
     */
    synchronized boolean apply(Object from, Object to) {
        if (als.contains(from) || als.contains(to)) {
            return false;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    // 归还资源
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }

}
