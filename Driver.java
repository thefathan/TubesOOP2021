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
        System.out.println(g.getFirstFromlistPlayer()); 
        g.listPlayer();
        // g.help();
        // g.viewTurn();
        g.listCard();
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
