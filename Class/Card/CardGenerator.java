package Card;

// buat coba2 ajaa
import java.util.*;

public class CardGenerator {
    public static void main(String[] args) {
        System.out.println("\nCard generator\n\n");
        int angka;
        String warna, jenis, skill;
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan jenis card (CardBiasa, ActionCard, SkillCard): ");
        jenis = scan.nextLine();
        // System.out.println(jenis);

        if (jenis.equals("CardBiasa")) {
            // System.out.println("1");
            System.out.print("Masukkan angka pada kartu biasa (0-9): ");
            angka = scan.nextInt();
            skill = "NULL";
            System.out.print("Masukkan warna pada kartu biasa (Red, Green, Blue, Yellow): ");
            warna = scan.nextLine();
            CardBiasa kartu = new CardBiasa(jenis, warna, skill, angka);
            System.out.print("Class " +jenis+ " berhasil diinisialisasi, \nKartu yg dihasilkan: ");
            kartu.infoKartu();
            kartu.cardAbility();
        }

        else if (jenis.equals("ActionCard")) {
            // System.out.println("1");
            System.out.print("Masukkan skill yg dimiliki action card (Wildcard, Draw +4): ");
            skill = scan.nextLine();
            warna = "NULL";
            angka = -99;
            ActionCard kartu = new ActionCard(jenis, warna, skill, angka);
            System.out.print("Class " +jenis+ " berhasil diinisialisasi, \nKartu yg dihasilkan: ");
            kartu.infoKartu();
            kartu.cardAbility();
        }

        else if (jenis.equals("SkillCard")) {
            // System.out.println("1");
            System.out.print("Masukkan skill yg dimiliki skill card (Reverse, Block, Draw +2): ");
            skill = scan.nextLine();
            System.out.print("Masukkan warna pada kartu biasa (Red, Green, Blue, Yellow): ");
            warna = scan.nextLine();
            angka = -99;
            SkillCard kartu = new SkillCard(jenis, warna, skill, angka);
            System.out.print("Class " +jenis+ " berhasil diinisialisasi, \nKartu yg dihasilkan: ");
            kartu.infoKartu();
            kartu.cardAbility();
        }

        else {
            System.out.println("tidak tau kartu yg dimaksud");
        }
    }
}