package TestExchanger;

import java.util.concurrent.Exchanger;

/**
 * Created by DBQ on 2016/12/19.
 */
public class TestExchanger {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");
        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");
        ExchangerRunnable exchangerRunnable3 = new ExchangerRunnable(exchanger, "C");
        ExchangerRunnable exchangerRunnable4 = new ExchangerRunnable(exchanger, "D");
        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();
        new Thread(exchangerRunnable3).start();
        new Thread(exchangerRunnable4).start();
    }
}

class ExchangerRunnable implements Runnable {
    Exchanger exchanger = null;
    Object object = null;

    public ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    public void run() {
        try {
            Object previous = this.object;
            this.object = this.exchanger.exchange(this.object);
            System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
