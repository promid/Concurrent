package Test1;

/**
 * Created by DBQ on 2016/12/20.
 */
public class test2 {
    public static void main(String[] args) {
        final Thread t1 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t1.interrupt();
            }
        };
        t1.start();
        t2.start();
    }
}
