import java.util.Scanner;

import Class.Game.*;
import Class.Card.*;
import Class.Player.*;

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
        g.listCard();
        // g.discard(g.getCurrentPlayer().getHandCard().getCardList().get(0));
        Player p = g.getCurrentPlayer();
        Player.PlayerCard hc = p.getHandCard();
        Card c = hc.getCardList().get(0);
        hc.removeCard(c);
        hc.printPCard();
        // g.listCard();

        // System.out.println(g.getFirstFromlistPlayer()); 
        // g.listPlayer();
        // g.printKartuTerakhirYangDiturunkan();
        // // g.help();
        // // g.viewTurn();
        // g.listCard();
        // for (int i = 0; i < g.getCurrentPlayerCardList().size(); i++) {
        //     System.out.println(g.getCurrentPlayerCardList().get(i).getAngkaKartu());
        // }
        // String menu;
        // while (true) {
        //     System.out.println("masukkan pilihan");
        //     menu = sc.next();
        //     System.out.println(menu);
            
            
        //     if (menu.compareTo("ListPlayer") == 0) {
        //         g.listPlayer();
        //     }
        //     else if (menu.compareTo("Help") == 0) {
        //         g.help();
        //     }
        //     else if (menu.compareTo("Keluar") == 0) {
        //         System.out.println("\nKeluar program...");
        //         break;
        //     }
        //     else {
        //         System.out.println("\nAnda memasukkan perintah yang salah, mohon diulang...");
        //         System.out.println(menu);
        //     }
        // }
    }
}
