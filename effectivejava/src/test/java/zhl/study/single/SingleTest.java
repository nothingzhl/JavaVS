package zhl.study.single;

import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

class SingleTest {

    @Test
    public void testSingle() {
        IntStream.range(0, 10)
                .parallel()
                .forEach(index -> System.out.println(Single.getInstance()));
        Assert.assertEquals(1,1);
    }
}