package TestInvokeAnyOrAll;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by DBQ on 2016/12/20.
 */
public class TestInvokeAll {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables = new HashSet<>();
        callables.add(() -> "Task 1");
        callables.add(() -> "Task 2");
        callables.add(() -> "Task 3");
        List<Future<String>> futures = executorService.invokeAll(callables);
        for(Future<String> future : futures){
            System.out.println("future.get = " + future.get());
        }
        executorService.shutdown();
    }
}
