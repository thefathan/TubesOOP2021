
package Class.Game;

import java.util.*;
import java.io.*;
import Class.Card.*;
import Class.Player.*;

public class Game {
    private List<Player> playerList;
    private List<Player> shuffledPlayerList;
    private Card lastCard;
    private Player currentPlayer;
    private Player nextPlayer;
    private String cardColor;
    private int totalPlus;
    private CardRandomer cardrandom;

    // Constructor
    public Game() {
        playerList = new ArrayList();
        shuffledPlayerList = new ArrayList();
        cardrandom = new CardRandomer();
        cardrandom.addcard();
        cardColor = "NULL";
        totalPlus = 0;
    }

    // Generate some players based on user input
    public void GeneratePlayer(int jumlah) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < jumlah; i++) {
            System.out.print("Masukkan nama pemain " +(i+1)+ " : ");
            String s = sc.nextLine();
            Player player = new Player(s);
            cardrandom.generatePlayerCard(player);
            playerList.add(player);
        }
        sc.close();
        System.out.println("");
    }

    // Shuffle player turn
    public void shufflePlayer() {
        shuffledPlayerList.addAll(playerList);
        Collections.shuffle(shuffledPlayerList);
        Player p1 = shuffledPlayerList.get(0);
        p1.setPlaying(true);
    }

    // Shuffle First Card
    public void shuffleFirstCard() {
        lastCard = cardrandom.shuffleCard();
    }

    // Getter to current player index
    public int getPlayerIdx() {
        return playerList.indexOf(currentPlayer);
    }

    public boolean isStringSame(String s1, String s2) {
        return s1.equals(s2);
    }

    // Draw a card
    public void draw() {
        Card drawCard = cardrandom.shuffleCard(); 
        System.out.print("Anda mendapatkan kartu: ");
        drawCard.infoKartu();
        System.out.println("");

        Player.PlayerCard pCard = currentPlayer.getHandCard();
        pCard.addCard(drawCard);
        currentPlayer.setSumCard();
    }

    // Discard a card
    public void discard(Card C) {
        int angka = C.getAngkaKartu();
        String warna = C.getWarnaKartu();
        String skill = C.getSkillKartu();
        String jenis = C.getJenisKartu();

        int angkaLast = lastCard.getAngkaKartu();
        String warnaLast = lastCard.getWarnaKartu();
        String skillLast = lastCard.getSkillKartu();
        String jenisLast = lastCard.getJenisKartu();

        boolean isAngkaSama = (angka == angkaLast);
        boolean isWarnaSama = isStringSame(warna, warnaLast);
        boolean isSkillSama = isStringSame(skill, skillLast);
        
        int idxPlayer = getPlayerIdx();
        
        // Kondisi ketika kartu yang dikeluarkan bertipe WildCard
        if (C instanceof WildCard) {
            // Kartu Draw +4
            if (isStringSame(skill, "Draw +4")) {
                totalPlus += 4;
            }
            Scanner sc = new Scanner(System.in);

            System.out.println("Pilih salah satu warna: ");
            System.out.println("1. Red");
            System.out.println("2. Blue");
            System.out.println("3. Green");
            System.out.println("4. Yellow");

            System.out.print("Pilihan warna: ");
            String input = sc.nextLine();
            cardColor = input;

            System.out.print("Anda mengeluarkan kartu: ");
            C.infoKartu();
            lastCard = C;
            
            Player.PlayerCard pCard = currentPlayer.getHandCard();
            pCard.removeCard(C);
            currentPlayer.setSumCard();

            sc.close();
        }

        // Kondisi ketika warna kartu tidak diganti
        else if (isStringSame(warnaLast, cardColor)) {
            // Warna kartu sama dengan warna kartu sebelumnya
            if (isWarnaSama) {
                if (C instanceof PowerCard) {
                    // Block
                    if (isStringSame(skill, "Block")) {
                        int newidx = idxPlayer + 2;
                        // Kartu sebelumnya Block berwarna sama 
                        if (isStringSame(skill, "Block")) {
                            newidx = idxPlayer + 1;
                        }
                        if ((newidx > (playerList.size() - 1))) {
                            newidx = idxPlayer - playerList.size();
                        }
                        nextPlayer = playerList.get(newidx);
                    }
                    // Reverse
                    else if (isStringSame(skill, "Reverse")) {
                        Collections.reverse(playerList);
                    }
                    // Draw +2
                    else {
                        // Kartu sebelumnya Draw +2 berwarna sama
                        if(isStringSame(skillLast, "Draw +2")) {
                            totalPlus += 2;
                        }
                        else {
                            totalPlus = 2;
                        }
                    }
                }
                // Kondisi ini juga berlaku untuk kartu dengan angka yang sama
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;

                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard();
            }

            // Skill kartu sama dengan skill kartu sebelumnya
            else if (isSkillSama) {
                // Block
                if (isStringSame(skill, "Block")) {
                    // Kartu sebelumnya Block dengan warna berbeda
                    int newidx = idxPlayer + 1;
                    if (newidx > (playerList.size() - 1)) {
                        newidx = idxPlayer - playerList.size();
                    }
                    nextPlayer = playerList.get(newidx);
                }
                // Reverse
                else if (isStringSame(skill, "Reverse")) {
                    Collections.reverse(playerList);
                }
                // Draw +2
                else {
                    // Kartu sebelumnya Draw +2 dengan warna berbeda
                    totalPlus += 2;
                }
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;

                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard();
            }

            // Kondisi ketika kartu tidak sesuai peraturan
            // Warna/skill/angka tidak ada yang sama
            else {
                System.out.println("Maaf, kartu ini tidak dapat dikeluarkan karena tidak sesuai dengan peraturan");
            }
        }

        // Kondisi ketika warna kartu sudah diganti
        else if (!isStringSame(warnaLast, cardColor)) {
            // Warna kartu sama dengan pilihan warna baru
            if (isStringSame(warna, cardColor)) {
                if (C instanceof PowerCard) {
                    // Block
                    if (isStringSame(skill, "Block")) {
                        int newidx = idxPlayer + 2;
                        if (newidx > (playerList.size() - 1)) {
                            newidx = idxPlayer - playerList.size();
                        }
                        nextPlayer = playerList.get(newidx);
                    }
                    // Reverse
                    else if (isStringSame(skill, "Reverse")) {
                        Collections.reverse(playerList);
                    }
                    // Draw +2
                    else {
                        totalPlus = 2;
                    }
                }
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;

                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard();
            }
            // Kondisi ketika kartu tidak sesuai peraturan
            else {
                System.out.println("Maaf, kartu ini tidak dapat dikeluarkan karena tidak sesuai dengan peraturan");
            }
        }
    }

    // Print all player in game
    public void listPlayer() {
        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            System.out.println("Pemain " + (i+1) + " : " + p.getPlayerName());
            System.out.println("Jumlah Kartu : " + p.getPlayerSumCard());
            if (p.isPlaying()) {
                System.out.println("Sedang giliran");
            }
            else {
                System.out.println("Tidak sedang giliran");
            }
            System.out.println("");
        }
    }

    // See all player turn
    public void viewTurn() {
        int totalPlayer = shuffledPlayerList.size();
        int idx = getPlayerIdx();
        int count = 0;

        System.out.print("Sedang giliran : ");
        System.out.println(currentPlayer.getPlayerName());

        System.out.println("Giliran selanjutnya : ");
        while (count < totalPlayer) {
            System.out.print(count+1 + ". ");
            idx = idx + 1;
            if (idx > (totalPlayer - 1)) {
                idx = idx - totalPlayer;
            }

            Player p = shuffledPlayerList.get(idx);
            System.out.println(p.getPlayerName());
            count++;
        }
    }

    // Print game's rule
    public void help() {
        // System.out.println("Help");
        String line = null;
        try {
            FileReader fileReader = new FileReader("Asset/help-asset.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Gabisa buka file '" + "Asset/help-asset.txt" + "'");
        }
    }
}