package Test1;


import org.testng.annotations.Test;

/**
 * Created by DBQ on 2016/12/20.
 */
public class test3 {

    @Test
    public void test1() throws InterruptedException {

        Th3 t = new Th3();
        new Thread(t).start();

    }
}

class Th3 implements Runnable{



    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("123");
        }
    }
}
