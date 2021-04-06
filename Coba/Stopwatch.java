package Coba;

import java.util.Timer;

public class Stopwatch implements Runnable {
    // Game game;
    boolean hiji;

    public Stopwatch(boolean hiji) {
        this.hiji = hiji;
    }

    public boolean isHiji() {
        return hiji;
    }

    public void run() {
        try {
            Thread.sleep(3000);
            if (!hiji) {
                System.out.println("Kamu belum declare Hiji dalam 3 detik, kartu di tangan kamu otomatis bertambah 2\n");
                // for (int i = 0; i < 2; i++) {
                //     game.draw();
                // }
            }
        }
        catch (InterruptedException e) {
            System.out.println("Anda mendeclare hiji");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Stopwatch(false));
        t.start();

        long startTime = System.currentTimeMillis();
        long patience = 3000;
        int i = 0;
        while (t.isAlive()) {
            t.join();
            if (i == 300) {
                t.interrupt();
            }
            i++;
        }
    }

    // if (game.getCurrentPlayerCardList().size() == 1) {
    //     Thread t = new Thread(new Timer(game, game.getCurrentPlayer().isHiji()));
        
    //     long startTime = System.currentTimeMillis();
    //     long patience = 3000;
    //     while (t.isAlive()) {
    //         t.join();
    //         if ((game.getCurrentPlayer().isHiji()) {
    //             t.interrupt();
    //         }
    //     }
    //     // timer jalan
    // }
}