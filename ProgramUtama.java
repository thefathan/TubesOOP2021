// Program ini berjalan menurut urutaan kode dari atas ke bawah


import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import Class.Game.*;
import Class.Card.*;
import Class.Player.*;


public class ProgramUtama {
    public static class Timer implements Runnable {  
        Game game;
        public Timer(Game game) {
            this.game = game;
        }

        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println("Kamu belum declare Hiji dalam 3 detik, kartu di tangan kamu otomatis bertambah 2\n");
                for (int i = 0; i < 2; i++) {
                    game.draw();
                }
                System.out.println("Tekan Enter untuk melanjutkan");
            }
            catch (InterruptedException e) {
                System.out.println("Kamu mendeclare hiji di bawah 3 detik");
            }
        }
    }

    public static void main(String[] args) throws Exception {

        // nyimpen variable untuk "klik enter"
        String klikLanjut;
        Scanner scan = new Scanner(System.in); 

        String start = "";
        while (!start.equalsIgnoreCase("START")) {
            System.out.println("Tuliskan START untuk memulai permainan");
            start = scan.nextLine();
        }

        // cuma ngeprint ascii art loading
        printAscii("Asset/loading-art.txt");
        printWithDelaysEveryChar("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||", TimeUnit.MILLISECONDS, 10);
        printWithDelays("", TimeUnit.MILLISECONDS, 0);

        // cuma print ascii art tulisan hiji
        printAscii("Asset/hiji-art.txt");


        // MULAI MASUK MENU
        printWithDelays("\nTekan Enter untuk memulai...", TimeUnit.MILLISECONDS, 0);
        klikLanjut = scan.nextLine();
        System.out.println("\n===============SELAMAT DATANG DI PERMAINAN HIJI!===============");
        
        
        int jumlahPemain = 0;
        // block looping untuk menghasilkan nilai jumlahPemain
        while (true) {
            printWithDelays("Untuk memulai gamenya, silahkan masukkan pemain yang mau bermain (hanya boleh 2 - 6 orang):\n>> ", TimeUnit.MILLISECONDS, 0);
            try {
                jumlahPemain = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.print(e.getMessage()); //try to find out specific reason.
            }
            if ((jumlahPemain < 2) || (jumlahPemain > 6)) {
                printAscii("Asset/invalid-art.txt");
                printWithDelays("", TimeUnit.MILLISECONDS, 350);
                klikLanjut = scan.nextLine();
            }
            else {
                printWithDelays("\nMemulai setup game.....", TimeUnit.MILLISECONDS, 0);
                break;
            }
        }
        printWithDelays("\n", TimeUnit.MILLISECONDS, 850);
        System.out.println("\nJumlah pemain yang akan bermain ada " +jumlahPemain+ ", Siapa aja nih??\n");
        printWithDelays("", TimeUnit.MILLISECONDS, 850);
        
        // INISIASI GAME berdasar jumlah pemain yang dimasukkan
        Game game = new Game();
        game.GeneratePlayer(jumlahPemain);
        game.shufflePlayer();
        game.shuffleFirstCard();
        printWithDelays(game.getFirstFromlistPlayer(), TimeUnit.MILLISECONDS, 0);
        System.out.println("\n");

        
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

    // clear screen function
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
     }

    




    public static void core(Game game) throws Exception {
        int discardCounter = 0;
        int drawCounter = 0;
        boolean haveWinner = false;
        Player winner = new Player("Default");

        Thread t = new Thread(new Timer(game));
        while (true) {
            Scanner scanmenu = new Scanner(System.in);

            printWithDelays("\nTekan enter untuk lanjut.....", TimeUnit.MILLISECONDS, 200);
            String klikLanjut = scanmenu.nextLine();
            printAscii("Asset/menu-art.txt");
            System.out.print("\nEnter Menu : ");
            String menu = scanmenu.nextLine();
            System.out.println("");

            if (!t.isAlive()) {
                if (menu.equalsIgnoreCase("Discard")) {
                    clearScreen();
                    System.out.println("Giliran: " + game.getCurrentPlayer().getPlayerName());
                    if (!game.hasMatchingCard()) {
                        System.out.println("Anda tidak memiliki kartu yang dapat dikeluarkan");
                        if ((game.getTotalPlus() > 0) && (discardCounter == 0)) {
                            for (int i = 0; i < game.getTotalPlus(); i++) {
                                game.draw();
                            }
                            game.setTotalPlus(0);
                        }
                    }
                    else {
                        int sum = game.getCurrentPlayer().getPlayerSumCard();
    
                        if ((game.hasMultipleCard()) && (discardCounter >= 1)) {
                            Scanner scanAngka = new Scanner(System.in);
    
                            System.out.println("Warna yang saat ini dimainkan adalah " +game.getWarnaKartuYangDimainkan()+ " (Jika warna kartu bertuliskan NULL, maka kartu sebelumnya bukan kartu angka)");
                            System.out.print("Nama kartu yang terakhir diturunkan adalah ");
                            System.out.println("");
                            game.printKartuTerakhirYangDiturunkan();
                            System.out.println("\nPerhatikan juga bahwa kartu yg bisa didiscard hanya kartu yang ada di tangan kamu.");
                            for (int i = 0; i < game.getCurrentPlayerCardList().size(); i++) {
                                System.out.print((i+1)+ ". ");
                                game.getCurrentPlayerCardList().get(i).infoKartu();
                            }
                            System.out.println("\nPilihlah kartu yg akan didiscard (tuliskan nomor urutannya) :");
    
                            int pilihanKartu = scanAngka.nextInt();
                            while ((pilihanKartu > game.getCurrentPlayerCardList().size()) || (pilihanKartu < 1)) {
                                System.out.println("\nInput tidak valid, silakan input ulang!");
                                System.out.println("Pilihlah kartu yg akan didiscard (tuliskan nomor urutannya) :");
                                pilihanKartu = scanAngka.nextInt();
                            }
                            Card c = game.getCurrentPlayerCardList().get(pilihanKartu-1);
                            game.discard2(c);
    
                            if (game.getCurrentPlayerCardList().size() == sum-1) {
                                discardCounter++;
                            }
                        }
                        else if (discardCounter == 0) {
                            Scanner scanAngka = new Scanner(System.in);
    
                            System.out.println("Warna yang saat ini dimainkan adalah " +game.getWarnaKartuYangDimainkan()+ " (Jika warna kartu bertuliskan NULL, maka kartu sebelumnya bukan kartu angka)");
                            System.out.print("Nama kartu yang terakhir diturunkan adalah ");
                            System.out.println("");
                            game.printKartuTerakhirYangDiturunkan();
                            System.out.println("\nPerhatikan juga bahwa kartu yg bisa didiscard hanya kartu yang ada di tangan kamu.");
                            for (int i = 0; i < game.getCurrentPlayerCardList().size(); i++) {
                                System.out.print((i+1)+ ". ");
                                game.getCurrentPlayerCardList().get(i).infoKartu();
                            }
                            System.out.println("\nPilihlah kartu yg akan didiscard (tuliskan nomor urutannya) :");
    
                            int pilihanKartu = scanAngka.nextInt();
                            while ((pilihanKartu > game.getCurrentPlayerCardList().size()) || (pilihanKartu < 1)) {
                                System.out.println("\nInput tidak valid, silakan input ulang!");
                                System.out.println("Pilihlah kartu yg akan didiscard (tuliskan nomor urutannya) :");
                                pilihanKartu = scanAngka.nextInt();
                            }
                            Card c = game.getCurrentPlayerCardList().get(pilihanKartu-1);
                            game.discard(c);
    
                            if (game.getCurrentPlayerCardList().size() == sum-1) {
                                discardCounter++;
                            }
                        }
                        else {
                            System.out.println("Maaf, anda tidak memiliki kartu yang dapat dikeluarkan");
                        }

                        if (game.getCurrentPlayerCardList().size() == 0) {
                            winner = game.getCurrentPlayer();
                            haveWinner = true;
                        }
                    }
                }

                else if (menu.equalsIgnoreCase("Draw")) {
                    clearScreen();
                    if (drawCounter == 0) {
                        if (discardCounter == 0) {
                            game.draw();
                            drawCounter++;
                        }
                        else {
                            System.out.println("Anda tidak dapat mengambil kartu karena telah melakukan discard");
                        }
                    }
                    else {
                        System.out.println("Maaf, anda sudah melakukan draw!");
                    }
                }
    
                else if (menu.equalsIgnoreCase("Declare Hiji")) {
                    clearScreen();
                    if (t.isAlive()) {
                        t.interrupt();
                    }
                    game.declareHiji();
                }
    
                else if (menu.equalsIgnoreCase("View Turn")) {
                    clearScreen();
                    game.viewTurn();
                }
    
                else if (menu.equalsIgnoreCase("List Card")) {
                    clearScreen();
                    game.listCard();
                }
    
    
                else if (menu.equalsIgnoreCase("List Player")) {
                    clearScreen();
                    game.listPlayer();
                }
    
    
                else if (menu.equalsIgnoreCase("Help")) {
                    clearScreen();
                    game.help();
                }
    
                else if (menu.equalsIgnoreCase("Credits")) {
                    clearScreen();
                    printAscii("Asset/credits-art.txt");
                }
    
                else if (menu.equalsIgnoreCase("Next Turn")) {
                    clearScreen();
                    if ((drawCounter == 1) || (discardCounter > 0)) {
                        System.out.println("Bersiap untuk pemain selanjutnya");
                        game.nextTurn();
                        drawCounter = 0;
                        discardCounter = 0;
                    }
                    else {
                        System.out.println("Anda belum melakukan aksi apa pun");
                    }
                }
    
                else if (menu.equalsIgnoreCase("Keluar")) {
                    clearScreen();
                    System.out.println("Keluar program...\n");
                    break;
                }
    
                else {
                    clearScreen();
                    printAscii("Asset/invalid-art.txt");
                }
    
                if ((game.getCurrentPlayerCardList().size() == 1) && (!game.getCurrentPlayer().isHiji())) {
                    t.start();
                }
            }
            
            else {
                if (menu.equalsIgnoreCase("Discard")) {
                    clearScreen();
                    System.out.println("Giliran: " + game.getCurrentPlayer().getPlayerName());
                    if (!game.hasMatchingCard()) {
                        System.out.println("Anda tidak memiliki kartu yang dapat dikeluarkan");
                        if (game.getTotalPlus() > 0) {
                            for (int i = 0; i < game.getTotalPlus(); i++) {
                                game.draw();
                            }
                            game.setTotalPlus(0);
                        }
                    }
                    else {
                        int sum = game.getCurrentPlayer().getPlayerSumCard();
    
                        if ((game.hasMultipleCard()) && (discardCounter >= 1)) {
                            Scanner scanAngka = new Scanner(System.in);
    
                            System.out.println("Warna yang saat ini dimainkan adalah " +game.getWarnaKartuYangDimainkan()+ " (Jika warna kartu bertuliskan NULL, maka kartu sebelumnya bukan kartu angka)");
                            System.out.print("Nama kartu yang terakhir diturunkan adalah ");
                            System.out.println("");
                            game.printKartuTerakhirYangDiturunkan();
                            System.out.println("\nPerhatikan juga bahwa kartu yg bisa didiscard hanya kartu yang ada di tangan kamu.");
                            for (int i = 0; i < game.getCurrentPlayerCardList().size(); i++) {
                                System.out.print((i+1)+ ". ");
                                game.getCurrentPlayerCardList().get(i).infoKartu();
                            }
                            System.out.println("\nPilihlah kartu yg akan didiscard (tuliskan nomor urutannya) :");
    
                            int pilihanKartu = scanAngka.nextInt();
                            while ((pilihanKartu > game.getCurrentPlayerCardList().size()) || (pilihanKartu < 1)) {
                                System.out.println("\nInput tidak valid, silakan input ulang!");
                                System.out.println("Pilihlah kartu yg akan didiscard (tuliskan nomor urutannya) :");
                                pilihanKartu = scanAngka.nextInt();
                            }
                            Card c = game.getCurrentPlayerCardList().get(pilihanKartu-1);
                            game.discard2(c);
    
                            if (game.getCurrentPlayerCardList().size() == sum-1) {
                                discardCounter++;
                            }
                        }
                        else if (discardCounter == 0) {
                            Scanner scanAngka = new Scanner(System.in);
    
                            System.out.println("Warna yang saat ini dimainkan adalah " +game.getWarnaKartuYangDimainkan()+ " (Jika warna kartu bertuliskan NULL, maka kartu sebelumnya bukan kartu angka)");
                            System.out.print("Nama kartu yang terakhir diturunkan adalah ");
                            System.out.println("");
                            game.printKartuTerakhirYangDiturunkan();
                            System.out.println("\nPerhatikan juga bahwa kartu yg bisa didiscard hanya kartu yang ada di tangan kamu.");
                            for (int i = 0; i < game.getCurrentPlayerCardList().size(); i++) {
                                System.out.print((i+1)+ ". ");
                                game.getCurrentPlayerCardList().get(i).infoKartu();
                            }
                            System.out.println("\nPilihlah kartu yg akan didiscard (tuliskan nomor urutannya) :");
    
                            int pilihanKartu = scanAngka.nextInt();
                            while ((pilihanKartu > game.getCurrentPlayerCardList().size()) || (pilihanKartu < 1)) {
                                System.out.println("\nInput tidak valid, silakan input ulang!");
                                System.out.println("Pilihlah kartu yg akan didiscard (tuliskan nomor urutannya) :");
                                pilihanKartu = scanAngka.nextInt();
                            }
                            Card c = game.getCurrentPlayerCardList().get(pilihanKartu-1);
                            game.discard(c);
    
                            if (game.getCurrentPlayerCardList().size() == sum-1) {
                                discardCounter++;
                            }
                        }
                        else {
                            System.out.println("Maaf, anda tidak memiliki kartu yang dapat dikeluarkan");
                            // game.nextTurn();
                            
                        }
                        if (game.getCurrentPlayerCardList().size() == 0) {
                            winner = game.getCurrentPlayer();
                            haveWinner = true;
                        }
                    }
                }

                else if (menu.equalsIgnoreCase("Draw")) {
                    clearScreen();
                    if (drawCounter == 0) {
                        if (discardCounter == 0) {
                            game.draw();
                            drawCounter++;
                        }
                        else {
                            System.out.println("Anda tidak dapat mengambil kartu karena telah melakukan discard");
                        }
                    }
                    else {
                        System.out.println("Maaf, anda sudah melakukan draw!");
                    }
                }
    
                else if (menu.equalsIgnoreCase("Declare Hiji")) {
                    clearScreen();
                    t.interrupt();
                    game.declareHiji();
                }
    
                else if (menu.equalsIgnoreCase("View Turn")) {
                    clearScreen();
                    game.viewTurn();
                }
    
                else if (menu.equalsIgnoreCase("List Card")) {
                    clearScreen();
                    game.listCard();
                }
    
    
                else if (menu.equalsIgnoreCase("List Player")) {
                    clearScreen();
                    game.listPlayer();
                }
    
    
                else if (menu.equalsIgnoreCase("Help")) {
                    clearScreen();
                    game.help();
                }
    
                else if (menu.equalsIgnoreCase("Credits")) {
                    clearScreen();
                    printAscii("Asset/credits-art.txt");
                }
    
                else if (menu.equalsIgnoreCase("Next Turn")) {
                    clearScreen();
                    if ((drawCounter == 1) || (discardCounter > 0)) {
                        System.out.println("Bersiap untuk pemain selanjutnya");
                        game.nextTurn();
                        drawCounter = 0;
                        discardCounter = 0;
                    }
                    else {
                        System.out.println("Anda belum melakukan aksi apa pun");
                    }
                }
    
                else if (menu.equalsIgnoreCase("Keluar")) {
                    clearScreen();
                    System.out.println("Keluar program...\n");
                    break;
                }
    
                else {
                    clearScreen();
                    printAscii("Asset/invalid-art.txt");
                }
            }

            if (haveWinner) {
                System.out.println("Game dimenangkan oleh: " + winner.getPlayerName() + " \n");
                break;
            }            
        }
    }
}