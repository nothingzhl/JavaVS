package zhl.study.bit;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import joptsimple.internal.Strings;

/**
 * 位运算测试
 */
public class TestBitCal {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    @Test
    void testPoolBitCal() {
        stubSign();
        printlnBinaryString(-1);
        printlnBinaryString(RUNNING);

        stubSign();
        printlnBinaryString(0);
        printlnBinaryString(SHUTDOWN);

        stubSign();
        printlnBinaryString(1);
        printlnBinaryString(STOP);

        stubSign();
        printlnBinaryString(2);
        printlnBinaryString(TIDYING);

        stubSign();
        printlnBinaryString(3);
        printlnBinaryString(TERMINATED);

        stubSign();
        printlnBinaryString(RUNNING);
        printlnBinaryString(1);
        printlnBinaryString(RUNNING|1);
    }

    private void printlnBinaryString(int target){
        System.out.println(Integer.toBinaryString(target));
    }
    private void stubSign(){
        System.out.println(Strings.repeat('*', 20));
    }
}
