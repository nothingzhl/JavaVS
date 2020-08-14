package zhl.study.thread;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * ThreadLocal 测试
 */
class ThreadLocalTest {

    @Test
    void testLocalSomething() {
        IntStream.range(0,100)
                .mapToObj(index->(Runnable)()-> System.out.println(ThreadId.get()))
                .map(Thread::new)
                .forEach(Thread::start);
    }

    static class ThreadId {
        static final AtomicLong nextId = new AtomicLong(0);
        //定义ThreadLocal变量
        static final ThreadLocal<Long> tl =
                ThreadLocal.withInitial(() -> nextId.getAndIncrement());

        //此方法会为每个线程分配一个唯一的Id
        static long get() {
            return tl.get();
        }
    }
}
