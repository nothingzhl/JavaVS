package zhl.study.thread;

import org.junit.jupiter.api.Test;
import org.omg.PortableServer.THREAD_POLICY_ID;

import lombok.SneakyThrows;

class ThreadStopTest {

    @Test
    @SneakyThrows
    void testNormalInterrupt() {
        Thread thread = new Thread(() -> {
            int n = 0;
            while (!Thread.currentThread().isInterrupted() && n <= Integer.MAX_VALUE / 2) {
                System.out.println(n++);
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

    @Test
    @SneakyThrows
    void testUnNormalInterrupt() {
        Thread thread = new Thread(() -> {
            int n = 0;
            try {
                while (!Thread.currentThread().isInterrupted() && n <= 300) {
                    if (n % 100 == 0) {
                        System.out.println(n);
                    }
                    n++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

    @Test
    @SneakyThrows
    void testUnNormalInterruptWithTwo() {
        Thread thread = new Thread(() -> {
            int n = 0;
            try {
                while (n <= 30000) {
                    if (n % 100 == 0) {
                        System.out.println(n);
                    }
                    n++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

    void testUnNormalInterruptWithThree() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int n = 0;
            while (n <= 30000 && !Thread.currentThread().isInterrupted()) {
                if (n % 100 == 0) {
                    System.out.println(n);
                }
                n++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // sleep 标记位被清楚
                    e.printStackTrace();
                    // 重置标记位
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(6000);
        thread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
       new ThreadStopTest().testUnNormalInterruptWithThree();
    }
}
