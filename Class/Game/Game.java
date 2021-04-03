
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
        this.playerList = new ArrayList();
        this.shuffledPlayerList = new ArrayList();
        this.cardrandom = new CardRandomer();
        this.cardrandom.addcard();
        this.cardColor = "NULL";
        this.totalPlus = 0;
    }

    // Generate some players based on user input
    public void GeneratePlayer(int jumlah) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < jumlah; i++) {
            System.out.print("Masukkan nama pemain " +(i+1)+ " : ");
            String s = sc.nextLine();
            Player player = new Player(s);
            this.cardrandom.generatePlayerCard(player);
            this.playerList.add(player);
        }
        // sc.close();
        System.out.println("");
    }

    // Setter to set player in each list
    public void setListPlayer(Player P, List<Player> L) {
        int idx = L.indexOf(P);
        L.set(idx, P);
    }

    // Shuffle player turn
    public void shufflePlayer() {
        this.shuffledPlayerList.addAll(this.playerList);
        Collections.shuffle(this.shuffledPlayerList);
        Player p1 = this.shuffledPlayerList.get(0);
        p1.setPlaying(true);
        this.currentPlayer = p1;

        setListPlayer(p1, this.playerList);
        setListPlayer(p1, this.shuffledPlayerList);
    }

    // Shuffle First Card
    public void shuffleFirstCard() {
        this.lastCard = this.cardrandom.shuffleCard();
        this.cardColor = this.cardrandom.shuffleCard().getWarnaKartu();
    }

    // Getter to current player index
    public int getPlayerIdx() {
        return this.playerList.indexOf(this.currentPlayer);
    }

    // getter current player
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    // getter current player cardlist
    public List<Card> getCurrentPlayerCardList() {
        return this.currentPlayer.getHandCard().getCardList();
    }

    public boolean isStringSame(String s1, String s2) {
        return s1.equals(s2);
    }

    // mengetahui nama kartu terakhir yang diturunkan pemain sebelumnya
    public void printKartuTerakhirYangDiturunkan() {
        this.lastCard.infoKartu();
    }

    // getter untuk warna kartu yaang terakhir diperintahkan
    public String getWarnaKartuYangDimainkan() {
        return this.cardColor;
    }

    // Draw a card
    public void draw() {
        Card drawCard = this.cardrandom.shuffleCard(); 
        System.out.print("Anda mendapatkan kartu: ");
        drawCard.infoKartu();
        System.out.println("");

        Player.PlayerCard pCard = this.currentPlayer.getHandCard();
        pCard.addCard(drawCard);
        this.currentPlayer.setSumCard();
        setListPlayer(this.currentPlayer, this.playerList);
        setListPlayer(this.currentPlayer, this.shuffledPlayerList);
    }

    // Discard a card
    public void discard(Card C) {
        int angka = C.getAngkaKartu();
        String warna = C.getWarnaKartu();
        String skill = C.getSkillKartu();
        String jenis = C.getJenisKartu();

        int angkaLast = this.lastCard.getAngkaKartu();
        String warnaLast = this.lastCard.getWarnaKartu();
        String skillLast = this.lastCard.getSkillKartu();
        String jenisLast = this.lastCard.getJenisKartu();

        boolean isAngkaSama = (angka == angkaLast);
        boolean isWarnaSama = isStringSame(warna, warnaLast);
        boolean isSkillSama = isStringSame(skill, skillLast);
        
        int idxPlayer = getPlayerIdx();
        
        // Kondisi ketika kartu yang dikeluarkan bertipe WildCard
        if (C instanceof WildCard) {
            // Kartu Draw +4
            if (isStringSame(skill, "Draw +4")) {
                this.totalPlus += 4;
            }
            Scanner sc = new Scanner(System.in);

            System.out.println("Pilih salah satu warna: ");
            System.out.println("1. Red");
            System.out.println("2. Blue");
            System.out.println("3. Green");
            System.out.println("4. Yellow");

            System.out.print("Pilihan warna: ");
            String input = sc.nextLine();
            this.cardColor = input;

            System.out.print("Anda mengeluarkan kartu: ");
            C.infoKartu();
            this.lastCard = C;
            
            Player.PlayerCard pCard = this.currentPlayer.getHandCard();
            pCard.removeCard(C);
            this.currentPlayer.setSumCard();

            setListPlayer(this.currentPlayer, this.playerList);
            setListPlayer(this.currentPlayer, this.shuffledPlayerList);
        }

        // Kondisi ketika warna kartu tidak diganti
        else if (isStringSame(warnaLast, this.cardColor)) {
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
                        if ((newidx > (this.playerList.size() - 1))) {
                            newidx = idxPlayer - this.playerList.size();
                        }
                        this.nextPlayer = this.playerList.get(newidx);
                    }
                    // Reverse
                    else if (isStringSame(skill, "Reverse")) {
                        Collections.reverse(this.playerList);
                    }
                    // Draw +2
                    else {
                        // Kartu sebelumnya Draw +2 berwarna sama
                        if(isStringSame(skillLast, "Draw +2")) {
                            this.totalPlus += 2;
                        }
                        else {
                            this.totalPlus = 2;
                        }
                    }
                }
                // Kondisi ini juga berlaku untuk kartu dengan angka yang sama
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                this.lastCard = C;

                Player.PlayerCard pCard = this.currentPlayer.getHandCard();
                pCard.removeCard(C);
                this.currentPlayer.setSumCard();

                setListPlayer(this.currentPlayer, this.playerList);
                setListPlayer(this.currentPlayer, this.shuffledPlayerList);
            }

            // Skill kartu sama dengan skill kartu sebelumnya
            else if (isSkillSama) {
                // Block
                if (isStringSame(skill, "Block")) {
                    // Kartu sebelumnya Block dengan warna berbeda
                    int newidx = idxPlayer + 1;
                    if (newidx > (this.playerList.size() - 1)) {
                        newidx = idxPlayer - this.playerList.size();
                    }
                    this.nextPlayer = this.playerList.get(newidx);
                }
                // Reverse
                else if (isStringSame(skill, "Reverse")) {
                    Collections.reverse(this.playerList);
                }
                // Draw +2
                else {
                    // Kartu sebelumnya Draw +2 dengan warna berbeda
                    this.totalPlus += 2;
                }
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                this.lastCard = C;

                Player.PlayerCard pCard = this.currentPlayer.getHandCard();
                pCard.removeCard(C);
                this.currentPlayer.setSumCard();

                setListPlayer(this.currentPlayer, this.playerList);
                setListPlayer(this.currentPlayer, this.shuffledPlayerList);
            }

            // Kondisi ketika kartu tidak sesuai peraturan
            // Warna/skill/angka tidak ada yang sama
            else {
                System.out.println("Maaf, kartu ini tidak dapat dikeluarkan karena tidak sesuai dengan peraturan, silahkan pilih kartu lain/draw card");
            }
        }

        // Kondisi ketika warna kartu sudah diganti
        else if (!isStringSame(warnaLast, this.cardColor)) {
            // Warna kartu sama dengan pilihan warna baru
            if (isStringSame(warna, this.cardColor)) {
                if (C instanceof PowerCard) {
                    // Block
                    if (isStringSame(skill, "Block")) {
                        int newidx = idxPlayer + 2;
                        if (newidx > (this.playerList.size() - 1)) {
                            newidx = idxPlayer - this.playerList.size();
                        }
                        this.nextPlayer = this.playerList.get(newidx);
                    }
                    // Reverse
                    else if (isStringSame(skill, "Reverse")) {
                        Collections.reverse(this.playerList);
                    }
                    // Draw +2
                    else {
                        this.totalPlus = 2;
                    }
                }
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                this.lastCard = C;

                Player.PlayerCard pCard = this.currentPlayer.getHandCard();
                pCard.removeCard(C);
                this.currentPlayer.setSumCard();

                setListPlayer(this.currentPlayer, this.playerList);
                setListPlayer(this.currentPlayer, this.shuffledPlayerList);
            }
            // Kondisi ketika kartu tidak sesuai peraturan
            else {
                System.out.println("Maaf, kartu ini tidak dapat dikeluarkan karena tidak sesuai dengan peraturan, silahkan pilih kartu lain/draw card");
            }
        }
    }

    public void declareHiji() {
        // if (sumCard > 1) {
        //     for (int i = 0; i < 2; i++) {
        //         draw();
        //     }
        // }
        
        // // Kartu sisa 1
        // else {
        //     if (timer < 3) {
        //         currentPlayer.setHiji(true); 
        //     }
        //     else {
        //         for (int i = 0; i < 2; i++) {
        //             draw();
        //         }
        //     }
        // }
    }

    // Print player's card
    public void listCard() {
        System.out.print("Daftar kartu pemain ");
        System.out.println(this.currentPlayer.getPlayerName() + ":");
        this.currentPlayer.printCard();
    }

    // Print all player in game
    public void listPlayer() {
        for (int i = 0; i < this.playerList.size(); i++) {
            Player p = this.playerList.get(i);
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
        int totalPlayer = this.shuffledPlayerList.size();
        int idx = this.shuffledPlayerList.indexOf(this.currentPlayer);
        int count = 0;

        System.out.print("Sedang giliran : ");
        System.out.println(this.currentPlayer.getPlayerName());

        System.out.println("Giliran selanjutnya : ");

        while (count < totalPlayer-1) {
            System.out.print(count+1 + ". ");
            idx = idx + 1;
            if (idx > (totalPlayer - 1)) {
                idx = idx - totalPlayer;
            }
            
            Player p = this.shuffledPlayerList.get(idx);
            System.out.println(p.getPlayerName());
            count++;
        }
        System.out.println("");
    }

    // Print game's rule
    public void help() {
        String line = null;
        try {
            FileReader fileReader = new FileReader("Asset/help-asset.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            // bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Gabisa buka file '" + "Asset/help-asset.txt" + "'");
        }
    }


    // get first player in game
    public String getFirstFromlistPlayer() {
        String retval = "Orang yang saat ini main adalah ";
        for (int i = 0; i < this.playerList.size(); i++) {
            Player p = this.playerList.get(i);
            if (p.isPlaying()) {
                retval += p.getPlayerName();
            }
        }
        return retval;
    }
}