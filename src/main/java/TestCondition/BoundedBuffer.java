package TestCondition;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by DBQ on 2016/12/23.
 */
public class BoundedBuffer {
    private final Lock lock = new ReentrantLock();//锁对象
    private final Condition notFull = lock.newCondition();//写线程条件
    private final Condition notEmpty = lock.newCondition();//读线程条件

    private final Object[] items = new Object[20];//缓存队列
    private int putptr/*写索引*/, takeptr/*读索引*/, count/*队列中存在的数据个数*/;

    void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)//如果队列满了
                notFull.await();//阻塞写线程
            items[putptr] = x;//赋值
            if (++putptr == items.length) putptr = 0;//如果写索引写到队列的最后一个位置了，那么置为0
            ++count;//个数++
            System.out.println("count = " + count);
            notEmpty.signal();//唤醒读线程
        } finally {
            lock.unlock();
        }
    }

    Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)//如果队列为空
                notEmpty.await();//阻塞读线程
            Object x = items[takeptr];//取值
            if (++takeptr == items.length) takeptr = 0;//如果读索引读到队列的最后一个位置了，那么置为0
            --count;//个数--
            System.out.println("count = " + count);
            notFull.signal();//唤醒写线程
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        T1 t1 = new T1(boundedBuffer);
        T2 t2 = new T2(boundedBuffer, new Date());
        new Thread(t1).start();
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
    }
}

class T1 implements Runnable{

    private BoundedBuffer boundedBuffer;


    T1(BoundedBuffer boundedBuffer) {
        this.boundedBuffer = boundedBuffer;
    }

    @Override
    public void run() {
        while(true){
            try {
                Date date = (Date) boundedBuffer.take();
//                System.out.println("take " + date.toString());
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class T2 implements Runnable{

    private BoundedBuffer boundedBuffer;
    private Object object;

    T2(BoundedBuffer boundedBuffer, Object object) {
        this.boundedBuffer = boundedBuffer;
        this.object = object;
    }

    @Override
    public void run() {
        while (true) {
            try {
                boundedBuffer.put(object);
//                System.out.println("put " + object.toString());
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

