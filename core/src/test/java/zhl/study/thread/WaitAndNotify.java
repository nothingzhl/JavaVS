package zhl.study.thread;

import org.junit.jupiter.api.Test;

/**
 * 等待通知相关用法
 */
public class WaitAndNotify {

    private final Object lock = new Object();

    @Test
    void testWaitNotify() throws InterruptedException {

        new Thread(()->{
            synchronized(lock){
                System.out.println(Thread.currentThread().getName()+"开始了");
                try {
                    System.out.println("准备wait");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被唤醒了");
            }
        }).start();

        new Thread(()->{
            synchronized(lock){
                System.out.println(Thread.currentThread().getName()+"开始了");
                try {
                    System.out.println("准备wait");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被唤醒了");
            }
        }).start();

        new Thread(()->{
            synchronized(lock){
                System.out.println(Thread.currentThread().getName()+"开始了");
                try {
                    System.out.println("准备wait");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被唤醒了");
            }
        }).start();

        Thread.sleep(200);

        new Thread(()->{
            synchronized(lock){
                System.out.println(Thread.currentThread().getName()+"开始了");
                lock.notify();
                System.out.println(Thread.currentThread().getName()+"notify");
            }
        }).start();

        Thread.sleep(100);

        new Thread(()->{
            synchronized(lock){
                System.out.println(Thread.currentThread().getName()+"开始了");
                lock.notifyAll();
                System.out.println(Thread.currentThread().getName()+"notify");
            }
        }).start();

    }



}
