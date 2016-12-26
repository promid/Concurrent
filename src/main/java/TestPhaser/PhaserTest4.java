package TestPhaser;

import java.util.concurrent.Phaser;

/**
 * Created by DBQ on 2016/12/25.
 */
public class PhaserTest4 {

    public static void main(String args[]) throws Exception {
        //
        final int count = 5;
        final int phaseToTerminate = 3;
        final Phaser phaser = new Phaser(count) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("====== " + phase + " ======");
                return phase == phaseToTerminate || registeredParties == 0;
            }
        };

        //
        for (int i = 0; i < count; i++) {
            System.out.println("starting thread, id: " + i);
            final Thread thread = new Thread(new Task(i, phaser));
            thread.start();
        }

        //

        awaitPhase(phaser, 1);
        System.out.println("done");
    }

    public static void awaitPhase(Phaser phaser, int phase) {
        int p = phaser.register(); // assumes caller not already registered
        while (p < phase) {
            if (phaser.isTerminated()) {
                break; // ... deal with unexpected termination
            } else {
                p = phaser.arriveAndAwaitAdvance();
            }
        }
        phaser.arriveAndDeregister();
    }

    public static class Task implements Runnable {
        //
        private final int id;
        private final Phaser phaser;

        public Task(int id, Phaser phaser) {
            this.id = id;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            while (!phaser.isTerminated()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // NOP
                }
                System.out.println("in Task.run(), phase: " + phaser.getPhase() + ", id: " + this.id);
                phaser.arriveAndAwaitAdvance();
            }
        }
    }
}
