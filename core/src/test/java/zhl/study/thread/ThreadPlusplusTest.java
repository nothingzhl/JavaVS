package zhl.study.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子的相关测试
 */
public class ThreadPlusplusTest implements Runnable {

    private  static ThreadPlusplusTest instance = new ThreadPlusplusTest();

    private int index = 0;

    private boolean[] marked = new boolean[100000];

    CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    AtomicInteger realIndex = new AtomicInteger(0);
    AtomicInteger wrongCount = new AtomicInteger(0);

    public static void main(String[] args) {

        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void run() {

        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            index++;

            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            realIndex.incrementAndGet();

            synchronized(instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生错误" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }

        }

    }
}
