package Class.Card;

// buat coba2 ajaa
import Class.Player.*;
import java.util.*;

public class CardGenerator {
    public static void main(String[] args) {
        CardRandomer cardrandom = new CardRandomer();
        Player player = new Player("Edwin");

        cardrandom.addcard();
        cardrandom.printCard();
        System.out.println(" ");
        cardrandom.shufflingCard();
        // cardrandom.printShuffledCard();
        System.out.println(" ");
        cardrandom.printFirstShuffled();
        cardrandom.generatePlayerCard(player);
        System.out.println(" ");
        player.listCard();
        System.out.println(player.getPlayerName());
        System.out.println(player.getPlayerSumCard());
    }
    
    
    // program dibawah untuk test kartu
    
    //     System.out.println("\nCard generator\n\n");
    //     int angka;
    //     String warna, jenis, skill;
    //     Scanner scan = new Scanner(System.in);
    //     System.out.print("Masukkan jenis card (NumberCard, PowerCard, WildCard): ");
    //     jenis = scan.nextLine();
    //     // System.out.println(jenis);

    //     if (jenis.equals("NumberCard")) {
    //         // System.out.println("1");
    //         System.out.print("Masukkan angka pada kartu biasa (0-9): ");
    //         angka = scan.nextInt();
    //         skill = "NULL";
    //         System.out.print("Masukkan warna pada kartu biasa (Red, Green, Blue, Yellow): ");
    //         warna = scan.nextLine();
    //         NumberCard kartu = new NumberCard(jenis, warna, skill, angka);
    //         System.out.print("Class " +jenis+ " berhasil diinisialisasi, \nKartu yg dihasilkan: ");
    //         kartu.infoKartu();
    //         kartu.cardAbility();
    //     }

    //     else if (jenis.equals("WildCard")) {
    //         // System.out.println("1");
    //         System.out.print("Masukkan skill yg dimiliki action card (Wildcard, Draw +4): ");
    //         skill = scan.nextLine();
    //         warna = "NULL";
    //         angka = -99;
    //         WildCard kartu = new WildCard(jenis, warna, skill, angka);
    //         System.out.print("Class " +jenis+ " berhasil diinisialisasi, \nKartu yg dihasilkan: ");
    //         kartu.infoKartu();
    //         kartu.cardAbility();
    //     }

    //     else if (jenis.equals("PowerCard")) {
    //         // System.out.println("1");
    //         System.out.print("Masukkan skill yg dimiliki skill card (Reverse, Block, Draw +2): ");
    //         skill = scan.nextLine();
    //         System.out.print("Masukkan warna pada kartu biasa (Red, Green, Blue, Yellow): ");
    //         warna = scan.nextLine();
    //         angka = -99;
    //         PowerCard kartu = new PowerCard(jenis, warna, skill, angka);
    //         System.out.print("Class " +jenis+ " berhasil diinisialisasi, \nKartu yg dihasilkan: ");
    //         kartu.infoKartu();
    //         kartu.cardAbility();
    //     }

    //     else {
    //         System.out.println("tidak tau kartu yg dimaksud");
    //     }
    // }
}
