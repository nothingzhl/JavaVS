package zhl.study.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestParameterized {

    private int expected;
    private int first;
    private int second;

    public TestParameterized(int expected, int firstNumber, int secondNumber) {
        this.expected = expected;
        this.first = firstNumber;
        this.second = secondNumber;
    }

    @Parameterized.Parameters
    public static List<Integer[]> parameters() {
        return Arrays.asList(new Integer[][]{{3, 1, 2}, {5, 2, 3}, {7, 3, 4}, {9, 4, 5}});
    }

    @Test
    public void testAdd() {
        String format = "Using parameters: expect=%d, first=%d, second=%d";
        System.out.println(String.format(format, expected, first, second));

        Feature feature = new Feature();
        assertEquals(expected, feature.add(first, second));
    }

    @Test
    public void testPrint() {
        String format = "Print ----------: expect=%d, first=%d, second=%d";
        System.out.println(String.format(format, expected, first, second));
    }

}
class Feature {
    public int add(int i1, int i2) {
        return i1 + i2;
    }
}
