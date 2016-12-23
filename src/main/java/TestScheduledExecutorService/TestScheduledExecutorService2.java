package TestScheduledExecutorService;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestScheduledExecutorService2 {
    public static void main(String[] args) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        final Runnable beeper = new Runnable() {
            int count;
            public void run() {
                System.out.println(Thread.currentThread().getName() + " " + new Date() + " beep " + (++count));
            }
        };
        // 1秒钟后运行，并每隔2秒运行一次 ，period 被解释为前一个执行的开始和下一个执行的开始之间 的间隔时间, 如果执行时间比period长, 则下一个执行推迟
        final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 2, SECONDS);
        // 2秒钟后运行，并每次在上次任务运行完后等待5秒后重新运行 ，delay 则被解释为前一个执行的结束和下一个执行的结束之间的间隔。因此这个延迟是执行结束之间的间隔，而不是执行开始之间的间隔。
        final ScheduledFuture beeperHandle2 = scheduler.scheduleWithFixedDelay(beeper, 2, 5, SECONDS);
        // 30秒后结束关闭任务，并且关闭Scheduler
        scheduler.schedule(() -> {
            beeperHandle.cancel(true);
            beeperHandle2.cancel(true);
            scheduler.shutdown();
        }, 30, SECONDS);
        System.out.println("这个方法阻塞吗?");
    }
}
