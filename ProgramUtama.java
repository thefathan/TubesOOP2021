// Program ini berjalan menurut urutaan kode dari atas ke bawah


import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import Class.Game.*;
import Class.Card.*;
import Class.Player.*;


public class ProgramUtama {
    public static void main(String[] args) throws Exception {

        // nyimpen variable untuk "klik enter"
        String klikLanjut;
        Scanner scan = new Scanner(System.in); 

        // cuma ngeprint ascii art loading
        printAscii("Asset/loading-art.txt");
        printWithDelaysEveryChar("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||", TimeUnit.MILLISECONDS, 10);
        printWithDelays("", TimeUnit.MILLISECONDS, 400);

        // cuma print ascii art tulisan hiji
        printAscii("Asset/hiji-art.txt");


        // MULAI MASUK MENU
        printWithDelays("\nTekan Enter untuk memulai...", TimeUnit.MILLISECONDS, 800);
        klikLanjut = scan.nextLine();
        System.out.println("\nSELAMAT DATANG DI PERMAINAN GOBLOK HIJI!");
        
        
        int jumlahPemain;
        // block looping untuk menghasilkan nilai jumlahPemain
        while (true) {
            printWithDelays("Silahkan masukkan pemain yang mau main (2 - 6 orang): ", TimeUnit.MILLISECONDS, 600);
            jumlahPemain = scan.nextInt();
            if ((jumlahPemain < 2) || (jumlahPemain > 6)) {
                printAscii("Asset/invalid-art.txt");
                printWithDelays("", TimeUnit.MILLISECONDS, 350);
                klikLanjut = scan.nextLine();
            }
            else {
                System.out.println("\nMASUK KE PROGRAM GAME...");
                break;
            }
        }
        System.out.println("\njumlah pemain yang bermain: " +jumlahPemain+ "\n");
        
        // inisiasi game berdasar jumlah pemain yang dimasukkan
        Game game = new Game();
        game.GeneratePlayer(jumlahPemain);
        game.shufflePlayer();
        game.shuffleFirstCard();
        // game.listPlayer();
        // game.help();

        

        // code dibawah belum bisa jalan ntah kenapa, ceritanya menu yg ada didalam game
        core(game); // jump ke prosedur core
    }











    // ngeprint output dengan delay
    public static void printWithDelays(String data, TimeUnit unit, long delay) throws InterruptedException {
        unit.sleep(delay);    
        System.out.print(data);
            
    }

    // ngeprint output dengan delay setiap hurufnya
    public static void printWithDelaysEveryChar(String data, TimeUnit unit, long delay) throws InterruptedException {
        for (char ch : data.toCharArray()) {
            System.out.print(ch);
            unit.sleep(delay);
        }
    }

    // Baca file external dan tampilin ke terminal
    public static void printAscii(String filename) throws Exception {
        String line = null;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Gabisa buka file '" + filename + "'");
        }
    }

    public static void core(Game game) {
        while (true) {
            Scanner scanmenu = new Scanner(System.in);
            System.out.println("menu");
            String menu = scanmenu.next();

            if (menu.compareTo("ListPlayer") == 0) {
                game.listPlayer();
            }
            else if (menu.compareTo("Help") == 0) {
                game.help();
            }
            else if (menu.compareTo("Keluar") == 0) {
                System.out.println("\nKeluar program...");
                break;
            }
            else {
                System.out.println("\nAnda memasukkan perintah yang salah, mohon diulang...");
            }
        }
    }
}
