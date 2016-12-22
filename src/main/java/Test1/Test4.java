package Test1;

import java.util.concurrent.*;

/**
 * Created by DBQ on 2016/12/21.
 */
public class Test4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(
                () -> {
                    System.out.println("Executed! ");
                    return "Called! ";
                },
                3,
                TimeUnit.SECONDS
        );
        System.out.println(scheduledFuture.get());
        scheduledExecutorService.shutdown();
    }
}
