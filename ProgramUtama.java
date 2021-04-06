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
                // System.out.println("\nKamu belum declare Hiji dalam 3 detik, kartu di tangan kamu otomatis bertambah 2\n");
                // if (!hiji) {
                System.out.println("Kamu belum declare Hiji dalam 3 detik, kartu di tangan kamu otomatis bertambah 2\n");
                for (int i = 0; i < 2; i++) {
                    game.draw();
                }
                // }
            }
            catch (InterruptedException e) {
                System.out.println("\nKamu mendeclare hiji di bawah 3 detik");
            }
        }
    }

    public static void main(String[] args) throws Exception {

        // nyimpen variable untuk "klik enter"
        String klikLanjut;
        Scanner scan = new Scanner(System.in); 

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
        
        
        int jumlahPemain;
        // block looping untuk menghasilkan nilai jumlahPemain
        while (true) {
            printWithDelays("Untuk memulai gamenya, silahkan masukkan pemain yang mau bermain (hanya boleh 2 - 6 orang):\n>> ", TimeUnit.MILLISECONDS, 0);
            jumlahPemain = scan.nextInt();
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

    




    public static void core(Game game) throws Exception {
        int gameCounter = 0;
        int discardCounter = 0;
        int drawCounter = 0;
        boolean haveWinner = false;
        Player winner = new Player("Default");

        Thread t = new Thread(new Timer(game));
        while (true) {
            Scanner scanmenu = new Scanner(System.in);

            printWithDelays("\nTekan enter untuk lanjut.....", TimeUnit.MILLISECONDS, 300);
            String klikLanjut = scanmenu.nextLine();
            printAscii("Asset/menu-art.txt");
            System.out.print("\nEnter Menu : ");
            String menu = scanmenu.nextLine();
            System.out.println("");

            if (!t.isAlive()) {
                if (menu.equalsIgnoreCase("Discard")) {
                    if (!game.hasMatchingCard()) {
                        System.out.println("Anda tidak memiliki kartu yang dapat dikeluarkan");
                        if (game.getTotalPlus() > 0) {
                            for (int i = 0; i < game.getTotalPlus(); i++) {
                                game.draw();
                            }
                        }
                    }
                    else {
                        System.out.println(game.hasMultipleCard());
                        int sum = game.getCurrentPlayer().getPlayerSumCard();
    
                        if ((game.hasMultipleCard()) && (discardCounter >= 1)) {
                            System.out.println("Kondisi 1\n");
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
                            gameCounter++;
                        }
                        else if (discardCounter == 0) {
                            System.out.println("Kondisi 2\n");
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
                            gameCounter++;
                        }
                        else {
                            System.out.println("Kondisi 3\n");
                            System.out.println("Maaf, anda tidak memiliki kartu yang dapat dikeluarkan");
                            // game.nextTurn();
                            
                        }
                        System.out.println("Discard Counter " + discardCounter + "\n");
                        if (game.getCurrentPlayerCardList().size() == 0) {
                            winner = game.getCurrentPlayer();
                            haveWinner = true;
                        }
                    }
                }

                else if (menu.equalsIgnoreCase("Draw")) {
                    if (drawCounter == 0) {
                        game.draw();
                        drawCounter++;
                        gameCounter++;
                    }
                    else {
                        System.out.println("Maaf, anda sudah melakukan draw!");
                    }
                }
    
                else if (menu.equalsIgnoreCase("Declare Hiji")) {
                    if (t.isAlive()) {
                        t.interrupt();
                    }
                    game.declareHiji();
                }
    
                else if (menu.equalsIgnoreCase("View Turn")) {
                    game.viewTurn();
                }
    
                else if (menu.equalsIgnoreCase("List Card")) {
                    game.listCard();
                }
    
    
                else if (menu.equalsIgnoreCase("List Player")) {
                    game.listPlayer();
                }
    
    
                else if (menu.equalsIgnoreCase("Help")) {
                    game.help();
                }
    
                else if (menu.equalsIgnoreCase("Credits")) {
                    printAscii("Asset/credits-art.txt");
                }
    
                else if (menu.equalsIgnoreCase("Next Turn")) {
                    System.out.println("Pemain selanjutnya akan bermain dalam 5 detik");
                    // if (timer >= 3) && (!game.getCurrentPlayer().isHiji()) {
                    //     for (int i = 0; i < 2; i++) {
                    //         game.draw();
                    //     }
                    // }
                    game.nextTurn();
                    drawCounter = 0;
                    discardCounter = 0;
                }
    
                else if (menu.equalsIgnoreCase("Keluar")) {
                    System.out.println("Keluar program...\n");
                    break;
                }
    
                else {
                    printAscii("Asset/invalid-art.txt");
                }
    
                if (game.getCurrentPlayerCardList().size() == 1) {
                    t.start();
                }
            }
            
            else {
                if (menu.equalsIgnoreCase("Discard")) {
                    if (!game.hasMatchingCard()) {
                        System.out.println("Anda tidak memiliki kartu yang dapat dikeluarkan");
                        if (game.getTotalPlus() > 0) {
                            for (int i = 0; i < game.getTotalPlus(); i++) {
                                game.draw();
                            }
                        }
                    }
                    else {
                        System.out.println(game.hasMultipleCard());
                        int sum = game.getCurrentPlayer().getPlayerSumCard();
    
                        if ((game.hasMultipleCard()) && (discardCounter >= 1)) {
                            System.out.println("Kondisi 1\n");
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
                            gameCounter++;
                        }
                        else if (discardCounter == 0) {
                            System.out.println("Kondisi 2\n");
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
                            gameCounter++;
                        }
                        else {
                            System.out.println("Kondisi 3\n");
                            System.out.println("Maaf, anda tidak memiliki kartu yang dapat dikeluarkan");
                            // game.nextTurn();
                            
                        }
                        System.out.println("Discard Counter " + discardCounter + "\n");
                        if (game.getCurrentPlayerCardList().size() == 0) {
                            winner = game.getCurrentPlayer();
                            haveWinner = true;
                        }
                    }
                }

                else if (menu.equalsIgnoreCase("Draw")) {
                    if (drawCounter == 0) {
                        game.draw();
                        drawCounter++;
                        gameCounter++;
                    }
                    else {
                        System.out.println("Maaf, anda sudah melakukan draw!");
                    }
                }
    
                else if (menu.equalsIgnoreCase("Declare Hiji")) {
                    t.interrupt();
                    game.declareHiji();
                }
    
                else if (menu.equalsIgnoreCase("View Turn")) {
                    game.viewTurn();
                }
    
                else if (menu.equalsIgnoreCase("List Card")) {
                    game.listCard();
                }
    
    
                else if (menu.equalsIgnoreCase("List Player")) {
                    game.listPlayer();
                }
    
    
                else if (menu.equalsIgnoreCase("Help")) {
                    game.help();
                }
    
                else if (menu.equalsIgnoreCase("Credits")) {
                    printAscii("Asset/credits-art.txt");
                }
    
                else if (menu.equalsIgnoreCase("Next Turn")) {
                    System.out.println("Pemain selanjutnya akan bermain dalam 5 detik");
                    // if (timer >= 3) && (!game.getCurrentPlayer().isHiji()) {
                    //     for (int i = 0; i < 2; i++) {
                    //         game.draw();
                    //     }
                    // }
                    game.nextTurn();
                    drawCounter = 0;
                    discardCounter = 0;
                }
    
                else if (menu.equalsIgnoreCase("Keluar")) {
                    System.out.println("Keluar program...\n");
                    break;
                }
    
                else {
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