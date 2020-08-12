package zhl.study.thread;

import org.junit.jupiter.api.Test;

class SemaphoreObjectPoolTest {

    @Test
    void exec() {
        SemaphoreObjectPool pool = new SemaphoreObjectPool<Long, String>(10, 2l);
        pool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });
    }
}