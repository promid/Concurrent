package TestBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by DBQ on 2016/12/18.
 */
public class BlockingQueueExample {

    public static void main(String[] args) throws Exception {

        BlockingQueue queue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(queue);

        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();

        //  Thread.sleep(1000);

        new Thread(consumer).start();

    }
}
