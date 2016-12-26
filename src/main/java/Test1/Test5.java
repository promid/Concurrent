package Test1;

import java.util.concurrent.*;

/**
 * Created by DBQ on 2016/12/26.
 */
public class Test5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new C1());
        while (!future.isDone()){
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("Task is running!");
        }
        if(future.isDone()){
            System.out.println("Task is done.");
        }
        String s = future.get();
        System.out.println("Result: " + s);
        executorService.shutdown();
    }
}

class C1 implements Callable<String>{

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        return "Done!";
    }
}
