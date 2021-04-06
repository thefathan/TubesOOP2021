// import Coba.*;

public class Main implements Runnable {
    // Game game;
    boolean hiji;

    public Main(boolean hiji) {
        this.hiji = hiji;
    }

    public boolean isHiji() {
        return hiji;
    }

    public void run() {
        try {
            Thread.sleep(10000);
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
        Thread t = new Thread(new Main(false));
        t.start();

        long startTime = System.currentTimeMillis();
        long patience = 3000;
        int i = 0;
        while (t.isAlive()) {
            t.join(100);
            System.out.println("Ya banget!");
            if (i == 20) {
                t.interrupt();
            }
            i++;
        }
    }
}