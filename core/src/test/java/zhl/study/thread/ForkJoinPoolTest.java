package zhl.study.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import org.junit.jupiter.api.Test;

/**
 * Forkoin test
 */
public class ForkJoinPoolTest {

    @Test
    void testForkJoin() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        Fibonacci fibonacci = new Fibonacci(30);

        Integer invoke = forkJoinPool.invoke(fibonacci);

        System.out.println(invoke);

    }

    @Test
    void name() {

        if ("zhanghanlin".equals(new String("zhanghanlin"))) {
            System.out.println(1);
        }

    }

    /**
     * 斐波那契数列
     */
    static class Fibonacci extends RecursiveTask<Integer>{

        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <=1) {
                return n;
            }

            Fibonacci f1 = new Fibonacci(n - 1);
            //创建子任务
            f1.fork();

            Fibonacci f2 = new Fibonacci(n - 2);
            //等待子任务结果，并合并结果
            return f2.compute() + f1.join();
        }

    }
}
