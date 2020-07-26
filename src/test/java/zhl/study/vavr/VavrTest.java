package zhl.study.vavr;

import org.junit.jupiter.api.Test;

import io.vavr.control.Option;

/**
 * @see com.google.errorprone.annotations.Var
 */
public class VavrTest {

    private String testValue = "test option";
    private String testNullValue = null;

    @Test
    void testOption() {
        Option.of(testValue).toStream().forEach(System.out::println);
        Option.narrow(Option.of(testNullValue)).stdout();
        Option.of(2).stdout();
    }
}
