package zhl.study.thread;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 利用信号量实现池化技术
 */
public class SemaphoreObjectPool<T, R> {

    final List<T> pool;

    //信号量
    final Semaphore semaphore;

    public SemaphoreObjectPool(int size, T t) {
        pool = new Vector<T>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        semaphore = new Semaphore(size);
    }

    R exec(Function<T,R> function){
        T t = null;
        try{
            semaphore.acquire();
            t = pool.remove(0);
            return function.apply(t);
        }catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }finally {
            pool.add(t);
            semaphore.release();
        }
    }
}
