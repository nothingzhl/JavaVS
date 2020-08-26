package zhl.study.thread;

import org.junit.jupiter.api.Test;
import org.omg.PortableServer.THREAD_POLICY_ID;

import lombok.SneakyThrows;

import java.sql.SQLOutput;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class ThreadStopTest {

    @Test
    void testNormalInterrupt() throws InterruptedException {
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
    void testUnNormalInterrupt() throws InterruptedException {
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
    void testUnNormalInterruptWithTwo() throws InterruptedException {
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

    @Test
    void testWrongWayVolatile() throws InterruptedException {

        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();

        Thread thread = new Thread(wrongWayVolatile);

        thread.start();

        Thread.sleep(5000);

        wrongWayVolatile.cancleed = true;

    }

    private class WrongWayVolatile implements Runnable{
        private volatile boolean cancleed = false;

        @Override
        public void run() {
             int num = 0;
             try {
                 while (num <= 10000 && !cancleed){
                     if ((num % 100 == 0)) {
                         System.out.println(num);
                     }
                     num ++;
                     Thread.sleep(10);
                 }
             }catch (InterruptedException e){
                 e.printStackTrace();
             }
        }
    }

    @Test
    void testWrongWayVolatileWithTwo() throws InterruptedException {
        BlockingDeque storage = new LinkedBlockingDeque(16);

        Producer producer = new Producer(storage);
        new Thread(producer).start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()){
            Thread.sleep(100);
            System.out.println(storage.take());
        }

        producer.cancleed = true;
    }

    class Producer implements Runnable{

        private volatile boolean cancleed = false;

        BlockingDeque storage ;

        public Producer(BlockingDeque storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 100000 && !cancleed){
                    // 阻塞后，设置标志位没有用
                  storage.put(num);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println("生产者ok");
            }
        }
    }

    class Consumer{

        BlockingDeque storage ;

        public Consumer(BlockingDeque storage) {
            this.storage = storage;
        }

        public boolean needMoreNums(){
            if (Math.random() > 0.95){
                return false;
            }
            return true;
        }

    }

    public static void main(String[] args) throws InterruptedException {
       new ThreadStopTest().testUnNormalInterruptWithThree();
    }
}
