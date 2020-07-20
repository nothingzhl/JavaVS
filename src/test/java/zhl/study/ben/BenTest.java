package zhl.study.ben;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import zhl.study.benchmark.JMHTestCase;

public class BenTest {

    @Test
    public void benTest() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHTestCase.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
