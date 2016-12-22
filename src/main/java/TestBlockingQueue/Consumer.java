package TestBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by DBQ on 2016/12/18.
 */
public class Consumer implements Runnable{

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {

        this.queue = queue;

    }

    public void run() {

        try {

            System.out.println("take " + queue.take());

            System.out.println("take " + queue.take());

            System.out.println("take " + queue.take());

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

}
