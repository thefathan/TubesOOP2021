
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
        shuffledPlayerList.addAll(playerList);
        Collections.shuffle(shuffledPlayerList);
        Player p1 = shuffledPlayerList.get(0);
        p1.setPlaying(true);
        currentPlayer = p1;

        setListPlayer(p1, playerList);
        setListPlayer(p1, shuffledPlayerList);
    }

    // Shuffle First Card
    public void shuffleFirstCard() {
        lastCard = cardrandom.shuffleCard();
        while ((lastCard instanceof PowerCard) || (lastCard instanceof WildCard)) {
            lastCard = cardrandom.shuffleCard();
        }
        cardColor = lastCard.getWarnaKartu();
    }

    // Getter to current player index
    public int getPlayerIdx() {
        return playerList.indexOf(currentPlayer);
    }

    // getter current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // getter current player cardlist
    public List<Card> getCurrentPlayerCardList() {
        return currentPlayer.getHandCard().getCardList();
    }

    public boolean isStringSame(String s1, String s2) {
        return s1.equals(s2);
    }

    // mengetahui nama kartu terakhir yang diturunkan pemain sebelumnya
    public void printKartuTerakhirYangDiturunkan() {
        lastCard.infoKartu();
    }

    // getter untuk warna kartu yaang terakhir diperintahkan
    public String getWarnaKartuYangDimainkan() {
        return cardColor;
    }

    // Draw a card
    public void draw() {
        Card drawCard = cardrandom.shuffleCard(); 
        System.out.print("Anda mendapatkan kartu: ");
        drawCard.infoKartu();
        System.out.println("");

        Player.PlayerCard pCard = currentPlayer.getHandCard();
        pCard.addCard(drawCard);
        currentPlayer.setSumCard(pCard);
        currentPlayer.setPlayerCard(pCard);
        setListPlayer(currentPlayer, playerList);
        setListPlayer(currentPlayer, shuffledPlayerList);
    }

    // Getter last card
    public Card getLastCard() {
        return lastCard;
    }

    // Getter game's card color
    public String getCardColor() {
        return cardColor;
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
            if ((skillLast.equals("Draw +4")) && (skill.equals("Wildcard"))) {
                System.out.println("Kartu Wildcard tidak bisa dikeluarkan setelah kartu Draw +4!");
                System.out.print("Anda mendapatkan " + totalPlus + " kartu dari akumulasi plus sebelumnya");
                for (int i = 0; i < totalPlus; i++){
                    draw();
                }
                totalPlus = 0;
            }
            else {
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

                System.out.print("Pilihan warna (tuliskan nomor urutannya): ");
                int input = sc.nextInt();
                while ((input < 1) || (input > 4)) {
                    System.out.println("Input tidak valid, silakan masukan nomor yang sesuai!");
                    System.out.print("Pilihan warna (tuliskan nomor urutannya): ");
                    input = sc.nextInt();
                }

                if (input == 1) {
                    cardColor = "Red";
                }
                else if (input == 2) {
                    cardColor = "Blue";
                }
                else if (input == 3) {
                    cardColor = "Green";
                }
                else {
                    cardColor = "Yellow";
                }

                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;
                
                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard(pCard);

                setListPlayer(currentPlayer, playerList);
                setListPlayer(currentPlayer, shuffledPlayerList);
            }
        }
        
        // Kondisi ketika warna kartu tidak diganti
        else if (isStringSame(warnaLast, cardColor)) {
            // Warna kartu sama dengan warna kartu sebelumnya
            if (isWarnaSama) {
                if (C instanceof PowerCard) {
                    // Block
                    if (isStringSame(skill, "Block")) {
                        int newidx = idxPlayer + 2;
                        // // Kartu sebelumnya Block berwarna sama 
                        // if (isStringSame(skill, "Block")) {
                        //     newidx = idxPlayer + 1;
                        // }
                        if ((newidx > (playerList.size() - 1))) {
                            newidx = idxPlayer - playerList.size();
                        }
                        nextPlayer = playerList.get(newidx);
                    }
                    // Reverse
                    else if (isStringSame(skill, "Reverse")) {
                        Collections.reverse(shuffledPlayerList);
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
                cardColor = C.getWarnaKartu();

                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard(pCard);
                currentPlayer.setPlayerCard(pCard);

                setListPlayer(currentPlayer, playerList);
                setListPlayer(currentPlayer, shuffledPlayerList);
            }

            // Skill kartu sama dengan skill kartu sebelumnya
            else if ((isSkillSama) && (C instanceof PowerCard)) {
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
                    Collections.reverse(shuffledPlayerList);
                }
                // Draw +2
                else {
                    // Kartu sebelumnya Draw +2 dengan warna berbeda
                    totalPlus += 2;
                }
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;
                cardColor = C.getWarnaKartu();

                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard(pCard);
                currentPlayer.setPlayerCard(pCard);

                setListPlayer(currentPlayer, playerList);
                setListPlayer(currentPlayer, shuffledPlayerList);
            }

            else if (isAngkaSama) {
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;
                cardColor = C.getWarnaKartu();

                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard(pCard);
                currentPlayer.setPlayerCard(pCard);

                setListPlayer(currentPlayer, playerList);
                setListPlayer(currentPlayer, shuffledPlayerList);
            }
            // Kondisi ketika kartu tidak sesuai peraturan
            // Warna/skill/angka tidak ada yang sama
            else {
                System.out.println("Maaf, kartu ini tidak dapat dikeluarkan karena tidak sesuai dengan peraturan, silahkan pilih kartu lain/draw card");
                if ((!isStringSame(skillLast, skill)) && (totalPlus > 0)) {
                    System.out.print("Anda mendapatkan " + totalPlus + " kartu dari akumulasi plus sebelumnya");
                    for (int i = 0; i < totalPlus; i++) {
                        draw();
                    }
                    // nextTurn();
                }
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
                        Collections.reverse(shuffledPlayerList);
                    }
                    // Draw +2
                    else {
                        totalPlus = 2;
                    }
                }
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;
                cardColor = C.getWarnaKartu();

                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard(pCard);
                currentPlayer.setPlayerCard(pCard);

                setListPlayer(currentPlayer, playerList);
                setListPlayer(currentPlayer, shuffledPlayerList);
            }
            // Kondisi ketika kartu tidak sesuai peraturan
            else {
                System.out.println("Maaf, kartu ini tidak dapat dikeluarkan karena tidak sesuai dengan peraturan, silahkan pilih kartu lain/draw card");
                if ((!isStringSame(skillLast, skill)) && (totalPlus > 0)) {
                    System.out.print("Anda mendapatkan " + totalPlus + " kartu dari akumulasi plus sebelumnya");
                    for (int i = 0; i < totalPlus; i++) {
                        draw();
                    }
                    // nextTurn();
                }
            }
        }
    }

    // Check is player has multiple same card
    public boolean hasMultipleCard() {
        List<Card> list = getCurrentPlayerCardList();
        boolean hasMultiple = false;
        int i = 0;

        while ((!hasMultiple) && (i < list.size())) {
            boolean isNumSame = list.get(i).getAngkaKartu() == lastCard.getAngkaKartu();
            boolean isColorSame = isStringSame(list.get(i).getWarnaKartu(), lastCard.getWarnaKartu());
            boolean isSkillSame = isStringSame(list.get(i).getSkillKartu(), lastCard.getSkillKartu());

            if ((list.get(i) instanceof WildCard) && (isSkillSame)) {
                hasMultiple = true;
            }
            else if (list.get(i) instanceof NumberCard) {
                if ((isNumSame) && (isColorSame)) {
                    hasMultiple = true;
                }
            }
            else if (list.get(i) instanceof PowerCard) {
                if ((isSkillSame) && (isColorSame)) {
                    hasMultiple = true;
                }
            }
            i++;
        }
        return hasMultiple;
    }

    // Multiple Discard
    public void discard2(Card C) {
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

        if (C instanceof NumberCard) {
            if (isAngkaSama && isWarnaSama) {
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;
                cardColor = C.getWarnaKartu();
    
                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard(pCard);
                currentPlayer.setPlayerCard(pCard);
    
                setListPlayer(currentPlayer, playerList);
                setListPlayer(currentPlayer, shuffledPlayerList);
            }
        }
        
        else if (C instanceof PowerCard) {
            if (isSkillSama && isWarnaSama) {
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;
                cardColor = C.getWarnaKartu();
    
                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard(pCard);
                currentPlayer.setPlayerCard(pCard);
    
                setListPlayer(currentPlayer, playerList);
                setListPlayer(currentPlayer, shuffledPlayerList);
            }
        }

        else if (C instanceof WildCard) {
            if (isSkillSama) {
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;
                cardColor = C.getWarnaKartu();
    
                Player.PlayerCard pCard = currentPlayer.getHandCard();
                pCard.removeCard(C);
                currentPlayer.setSumCard(pCard);
                currentPlayer.setPlayerCard(pCard);
    
                setListPlayer(currentPlayer, playerList);
                setListPlayer(currentPlayer, shuffledPlayerList);
            }
        }

        else {
            System.out.println("Maaf, kartu ini tidak dapat dikeluarkan karena tidak sesuai dengan peraturan, silahkan pilih kartu lain/draw card");
            // nextTurn();
        }
    }

    public void declareHiji() {
        if (this.currentPlayer.getHandCard().lengthPC() > 1) {
            System.out.println("Kamu mendeclare Hiji, padahal kartu anda masih lebih dari 1, kartu di tangan kamu otomatis bertambah 2\n");
            for (int i = 0; i < 2; i++) {
                draw();
            }
        }
        // else if ((timer >= 3) && (currentPlayer.getHandCard().lengthPC() == 1)) {
        //     System.out.println("Meski kamu hanya memiliki 1 kartu, tetapi kamu mendeclare Hiji lebih dari 3 detik, kartu di tangan kamu otomatis bertambah 2\n");
        //     for (int i = 0; i < 2; i++) {
        //         draw();
        //     }
        // }
        // // Kartu sisa 1, (( BELUM MENGIMPLEMENTASIKAN TIMER ))
        else {
            this.currentPlayer.setHiji(true);
            System.out.println("Kamu berhasil mendeclare Hiji. sisa kartu ditanganmu adalah\n");
            getCurrentPlayer().printCard();
        }
    }

    // Print player's card
    public void listCard() {
        System.out.print("Daftar kartu pemain ");
        System.out.println(currentPlayer.getPlayerName() + ":");
        currentPlayer.printCard();
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
        int idx = shuffledPlayerList.indexOf(currentPlayer);
        int count = 0;

        System.out.print("Sedang giliran : ");
        System.out.println(currentPlayer.getPlayerName());

        System.out.println("Giliran selanjutnya : ");

        while (count < totalPlayer-1) {
            System.out.print(count+1 + ". ");
            idx = idx + 1;
            if (idx > (totalPlayer - 1)) {
                idx = idx - totalPlayer;
            }
            
            Player p = shuffledPlayerList.get(idx);
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
        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            if (p.isPlaying()) {
                retval += p.getPlayerName();
            }
        }
        return retval;
    }

    public boolean hasMatchingCard() {
        List<Card> list = getCurrentPlayerCardList();
        boolean hasCard = false;
        int i = 0;

        while ((!hasCard)  && (i < list.size())){
            boolean isNumSame = list.get(i).getAngkaKartu() == lastCard.getAngkaKartu();
            boolean isColorSame = isStringSame(list.get(i).getWarnaKartu(), lastCard.getWarnaKartu());
            boolean isSkillSame = isStringSame(list.get(i).getSkillKartu(), lastCard.getSkillKartu());

            if (list.get(i) instanceof WildCard) {
                // System.out.println("kena di 1");
                hasCard = true;
            }
            else if (lastCard.getWarnaKartu().equals(cardColor)) {
                if (isColorSame) {
                    // System.out.println("kena di 2");
                    hasCard = true;
                }
                else if ((isSkillSame) && (lastCard instanceof PowerCard)) {
                    // System.out.println("kena di 3");
                    hasCard = true;
                }
                else if (isNumSame) {
                    // System.out.println("kena di 4");
                    hasCard = true;
                }
            }
            else if (!lastCard.getWarnaKartu().equals(cardColor)) {
                if (isStringSame(list.get(i).getWarnaKartu(), cardColor)) {
                    // System.out.println("kena di 5");
                    hasCard = true;
                }
            }
            i++;
        }
        return hasCard;
    }

    public void nextTurn() {
        int totalPlayer = shuffledPlayerList.size();
        int playerIdx = shuffledPlayerList.indexOf(currentPlayer);
        int idx = playerIdx + 1;
        if (idx > (totalPlayer - 1)) {
            idx = idx - totalPlayer;
        }
        currentPlayer.setPlaying(false);

        if (lastCard.getSkillKartu().equals("Block")) {
            currentPlayer = nextPlayer;
            // nextPlayer = currentPlayer;
            currentPlayer.setPlaying(true);
        }
        else {
            currentPlayer = shuffledPlayerList.get(idx);
            currentPlayer.setPlaying(true);
        }
    }
}