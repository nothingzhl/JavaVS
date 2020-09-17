package zhl.study.thread;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class SomethingAboutThreadTest {


    private static class Interesting {
        volatile int a = 1;
        volatile int b = 1;
        public void add() {
            System.out.println("add start");
            for (int i = 0; i < 10000; i++) {
                a++;
                b++;
            }
            System.out.println("add done");
        }
        public void compare() {
            System.out.println("compare start");
            for (int i = 0; i < 10000; i++) {
                //a始终等于b吗？
                if (a < b) {
                    System.out.println(String.format("a:%s,b:%s,%s",a, b, a > b));
                    //最后的a>b应该始终是false吗？
                }
            }
            System.out.println("compare done");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Interesting interesting = new Interesting();
        Thread thread = new Thread(() -> interesting.add());
        Thread thread1 = new Thread(() -> interesting.compare());
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
    }
}
