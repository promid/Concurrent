package TestBlockingQueue;

import java.util.concurrent.*;

/**
 * Created by DBQ on 2016/12/18.
 */
public class DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {

        DelayQueue queue = new DelayQueue();

        Delayed element1 = new DelayedElement();

        queue.put(element1);

        Delayed element2 = queue.take();

    }
}

class DelayedElement implements Delayed{

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(1, TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
