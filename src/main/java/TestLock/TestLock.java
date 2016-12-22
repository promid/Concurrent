package TestLock;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by DBQ on 2016/12/20.
 */
public class TestLock extends ReentrantLock {


    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    private String hello = "hello";

    static void PrintLock(TestLock testLock){
        System.out.println(Thread.currentThread().getName() + " locked " + testLock.getClass().getName());
    }

    static void PrintUnLock(TestLock testLock){
        System.out.println(Thread.currentThread().getName() + " unlocked " + testLock.getClass().getName());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        TestLock testLock = new TestLock();
        Th t1 = new Th(testLock);
        Th t2 = new Th(testLock);
        Future<String> future1 = executorService.submit(t1);
        Future<String> future2 = executorService.submit(t2);
        Thread.sleep(1000);
        testLock.lock();
        PrintLock(testLock);
        System.out.println(testLock.getHello());
        testLock.unlock();
        PrintUnLock(testLock);
        executorService.shutdown();
    }

}

class Th implements Callable<String> {

    TestLock testLock;

    public Th(TestLock testLock) {
        this.testLock = testLock;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " is called!");
        testLock.lock();
        TestLock.PrintLock(testLock);
        testLock.setHello(Thread.currentThread().getName());
        testLock.unlock();
        TestLock.PrintUnLock(testLock);
        return "Done";
    }
}


