package zhl.study.single;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 通过原子变量去控制单例的生成
 */
public class Single {

    private volatile static Single instance;
    /**
     * 控制单例生成的原子变量
     */
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static Single getInstance() {
        if (atomicBoolean.compareAndSet(false,true)) {
            instance = new Single();
        }
        return instance;
    }
}
