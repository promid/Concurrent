package Test1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by DBQ on 2016/12/26.
 */
public class TestAwaitTermination {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            return "Done";
        });
        service.shutdown(); // shutdown 放在这里, awaitTermination 返回 true
        /**
         * Blocks until all tasks have completed execution after a shutdown request,
         * or the timeout occurs,
         * or the current thread is interrupted, whichever happens first.
         */
        boolean b = service.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println(b);
        //service.shutdown(); // shutdown 放在这里,  awaitTermination 返回 false
    }
}
