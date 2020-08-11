package zhl.study.thread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import zhl.study.exception.InstantiateException;

/**
 * 线程工具类
 */
public final class ThreadUtils {

    private ThreadUtils() {
        throw new InstantiateException("工具类不准实例化");
    }

    /**
     * 随机生成一堆task
     *
     * @return
     */
    public static List<Callable<Double>> randomTaskList(int size) {
        return IntStream.range(0, size)
                .mapToObj(index ->(Callable<Double>)()->Math.random())
                .collect(Collectors.toList());
    }

}
