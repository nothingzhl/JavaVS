package zhl.study.thread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟管程相关概念
 * @param <T>
 */
public class BlockedQueue<T> {

    final Lock lock =
            new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull =
            lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty =
            lock.newCondition();

    Queue queue = new ArrayDeque(13);

    // 入队
    void enq(T x) throws InterruptedException {
        lock.lock();
        try {
            //队列已满
            while (queue.size() == 13) {
                // 等待队列不满
                notFull.await();
            }
            // 省略入队操作...

            //入队后,通知可出队
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 出队
    void deq() throws InterruptedException {
        lock.lock();
        try {
            //队列已空
            while (queue.isEmpty()) {
                // 等待队列不空
                notEmpty.await();
            }
            // 省略出队操作...
            //出队后，通知可入队
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
