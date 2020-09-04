package zhl.study.jdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.common.base.Stopwatch;

/**
 * map的测试
 */
public class MapTest {

    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (long i = 0; i < Integer.MAX_VALUE ; i++) {
            map.put(Objects.toString(i),Objects.toString(i));
        }

        stopwatch.stop();
        System.out.println(stopwatch.elapsed());
        System.out.println(map);
    }
}
