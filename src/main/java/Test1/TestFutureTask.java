package Test1;

import java.util.concurrent.*;

/**
 * Created by DBQ on 2016/12/26.
 */
public class TestFutureTask {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new FT(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        }));
        executorService.shutdown();
    }
}

class FT extends FutureTask<String>{

    public FT(Callable<String> callable) {
        super(callable);
    }

    @Override
    protected void done() {
        System.out.println("done!");
    }
}
