import java.util.Scanner;

import Class.Game.*;

public class Driver {
    public static void main(String[] args) {
        Game g = new Game();
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan jumlah pemain: ");
        System.out.println("");
        int jumlah = sc.nextInt();
        g.GeneratePlayer(jumlah);
        g.shufflePlayer();
        g.shuffleFirstCard();
        g.listPlayer();
        g.help();
        g.viewTurn();
        sc.close();
    }
}
