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
        System.out.println("\n===============SELAMAT DATANG DI PERMAINAN GOBLOK HIJI!===============");
        
        
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
                printWithDelays("\nMemulai setup game.....", TimeUnit.MILLISECONDS, 600);
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
        printWithDelays(game.getFirstFromlistPlayer(), TimeUnit.MILLISECONDS, 300);
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
        while (true) {
            int gameCounter = 0;
            Scanner scanmenu = new Scanner(System.in);

            printWithDelays("\nTekan enter untuk lanjut.....", TimeUnit.MILLISECONDS, 600);
            String klikLanjut = scanmenu.nextLine();
            printAscii("Asset/menu-art.txt");
            System.out.print("\nEnter Menu : ");
            String menu = scanmenu.nextLine();
            System.out.println("");

            if (menu.equalsIgnoreCase("Discard")) {
                Card last = game.getLastCard();
                String color = game.getCardColor();
                List<Card> list = game.getCurrentPlayerCardList();
                boolean hasMatchingCard = false;
                int i = 0;

                while ((!hasMatchingCard)  && (i < list.size())){
                    boolean isNumSame = list.get(i).getAngkaKartu() == last.getAngkaKartu();
                    boolean isColorSame = game.isStringSame(list.get(i).getWarnaKartu(), last.getWarnaKartu());
                    boolean isSkillSame = game.isStringSame(list.get(i).getSkillKartu(), last.getSkillKartu());

                    if (list.get(i) instanceof WildCard) {
                        // System.out.println("kena di 1");
                        hasMatchingCard = true;
                    }
                    else if (last.getWarnaKartu().equals(color)) {
                        if (isColorSame) {
                            // System.out.println("kena di 2");
                            hasMatchingCard = true;
                        }
                        else if ((isSkillSame) && (last instanceof PowerCard)) {
                            // System.out.println("kena di 3");
                            hasMatchingCard = true;
                        }
                        else if (isNumSame) {
                            // System.out.println("kena di 4");
                            hasMatchingCard = true;
                        }
                    }
                    else if (!last.getWarnaKartu().equals(color)) {
                        if (game.isStringSame(list.get(i).getWarnaKartu(), color)) {
                            // System.out.println("kena di 5");
                            hasMatchingCard = true;
                        }
                    }
                    i++;
                }

                if (!hasMatchingCard) {
                    System.out.println("Anda tidak memiliki kartu yang dapat dikeluarkan, silakan draw kartu terlebih dahulu!");
                }
                else {
                    Scanner scanAngka = new Scanner(System.in);

                    System.out.println("Warna yang saat ini dimainkan adalah " +game.getWarnaKartuYangDimainkan()+ " (Jika warna kartu bertuliskan NULL, maka kartu sebelumnya bukan kartu angka)");
                    System.out.print("Nama kartu yang terakhir diturunkan adalah ");
                    System.out.println("");
                    game.printKartuTerakhirYangDiturunkan();
                    System.out.println("\nPerhatikan juga bahwa kartu yg bisa didiscard hanya kartu yang ada di tangan kamu.");
                    for (i = 0; i < game.getCurrentPlayerCardList().size(); i++) {
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

                    gameCounter++;
                }
            }


            else if (menu.equalsIgnoreCase("Draw")) {
                game.draw();

                gameCounter++;
            }

            else if (menu.equalsIgnoreCase("Declare Hiji")) {
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


            else if (menu.equalsIgnoreCase("Keluar")) {
                System.out.println("Keluar program...\n");
                break;
            }


            else {
                printAscii("Asset/invalid-art.txt");
            }
        }
    }
}
