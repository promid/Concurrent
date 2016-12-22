package TestScheduledExecutorService;

import java.util.concurrent.*;

/**
 * Created by DBQ on 2016/12/20.
 */
public class TestScheduledExecutorService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(
                () -> {
                    System.out.println("Executed!");
                    return "Called!";
                },
                3,
                TimeUnit.SECONDS);

        System.out.println(scheduledFuture.get());

        ScheduledFuture<?> finished = scheduledExecutorService.schedule(
                () -> System.out.println("finished"),
                3,
                TimeUnit.SECONDS
        );

        System.out.println(finished.get() == null);

        System.out.println("1");

        scheduledExecutorService.shutdown();
    }
}
