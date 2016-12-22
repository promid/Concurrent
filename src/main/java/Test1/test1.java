package Test1;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by DBQ on 2016/12/20.
 */
public class test1 {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setKeepAliveSeconds(30000);
        threadPoolTaskExecutor.setMaxPoolSize(1000);
        threadPoolTaskExecutor.setQueueCapacity(200);
        threadPoolTaskExecutor.initialize();
        //ExecutorService executorService = Executors.newFixedThreadPool(2);
        Thread t = new Thread(new Th1());
        //executorService.execute(t);

        threadPoolTaskExecutor.execute(t);
        //threadPoolTaskExecutor.
    }
}

class Th1 implements Runnable{

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1111");
        }
    }
}
