package zhl.study.thread;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExecutorServiceTest {

    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @Test
    void testInvokeAny() throws ExecutionException, InterruptedException {
        Double result = executorService.invokeAny(ThreadUtils.randomTaskList(13));
        assertNotNull(result);
    }

    @Test
    void testInvokeAll() throws InterruptedException {
        List<Future<Double>> list =
                executorService.invokeAll(ThreadUtils.randomTaskList(13));
        assertNotNull(list);
    }
}