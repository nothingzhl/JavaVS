package zhl.study.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 线程的相关知识测试
 */
public class ThreadSomethingTest {

    private long count = 0;

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    private long calc() throws InterruptedException {
        Thread t1 = new Thread(() -> add10K());
        Thread t2 = new Thread(() -> add10K());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return count;
    }

    /**
     * 缓存导致的可见性问题
     *
     * @throws InterruptedException
     */
    @Test
    void testCalcAboutThread() throws InterruptedException {
        //打印出的不是20000，应该在10000~20000
        System.out.println(calc());
    }

    /**
     * 存在 并发安全隐患
     * <p>
     * instance = new Singleton();不是原子化的<br/>
     * 分配一块内存 M；<br/>
     * 将 M 的地址赋值给 instance 变量；<br/>
     * 最后在内存 M 上初始化 Singleton 对象。<br/>
     * </p>
     */
    public static class Singleton {
        static Singleton instance;

        static Singleton getInstance() {
            if (instance == null) {
                synchronized(Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}
