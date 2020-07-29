package zhl.study.steam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class SteamTest {

    @Test
    void testSelfCollector() {
        Stream.iterate(1, value -> value + 1)
                .limit(10)
                .collect(new ToListCollector<>());

        Supplier<Map<Integer,Integer>> supplier = HashMap::new;
        BiConsumer<Map<Integer,Integer>,Integer> biConsumer =
                (targetMap, value) -> targetMap.putIfAbsent(value,value);
        BinaryOperator<Map<Integer,Integer>> binaryOperator =
                (leftMap, rightMap) -> {
                    leftMap.putAll(rightMap);
                    return leftMap;
                };
        Map<Integer, Integer> collect =
                IntStream.rangeClosed(1, 10).boxed().collect(Collector.of(supplier, biConsumer, binaryOperator));

        System.out.println(collect.toString());
    }

    private class ToListCollector<T> implements Collector<T, List<T>,List<T>> {

        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return (list,value)->list.add(value);
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (left,right)->{
                left.addAll(right);
                return left;
            };
        }

        @Override
        public Function<List<T>, List<T>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH,
                                                                           Collector.Characteristics.CONCURRENT));
        }
    }

}
