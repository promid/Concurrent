package TestBlockingDeque;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by DBQ on 2016/12/19.
 */
public class TestBlockingDeque {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<String> bd = new LinkedBlockingDeque<>();
        bd.addFirst("1");
        bd.addLast("2");
        String a = bd.takeFirst();
        String b = bd.takeLast();
        System.out.println(a + b);
    }
}
