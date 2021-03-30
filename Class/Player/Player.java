package Class.Player;

import Class.Card.*;
import java.util.*;
import java.time.LocalTime;

public class Player {
    private String playerName;
    private int sumCard;
    private boolean hiji;
    private boolean playing;
    private PlayerCard handCard;

    // User defined constructor
    public Player(String name, PlayerCard pCard) {
        this.playerName = name;
        this.sumCard = 0;
        this.hiji = false;
        this.playing = false;
        this.handCard = pCard;
    }

    // Getter player's name
    public String getPlayerName() {
        return this.playerName;
    }

    // Getter player's sum card
    public int getPlayerSumCard() {
        return this.sumCard;
    }

    // Getter player's hiji status
    public boolean isHiji() {
        return this.hiji;
    }

    // Getter player's turn status
    public boolean isPlaying() {
        return this.playing;
    }

    // Getter to player's card list
    public PlayerCard getHandCard() {
        return handCard;
    }

    // Getter to print player's card
    public void listCard() {
        handCard.printPCard();
    }

    // Setter player's total card
    public void setSumCard() {
        this.sumCard = handCard.lengthPC();
    }

    // Setter player's hiji status
    public void setHiji(boolean p_hiji) {
        this.hiji = p_hiji;
    }

    // Setter player's playing status
    public void setPlaying(boolean p_play) {
        this.playing = p_play;
    }

    public void declareHiji() {
        // Cannot be declare due to timer availability
        // int currentTime = LocalTime.now().getSecond() +
        // LocalTime.now().getHour()*3600 + LocalTime.now().getMinute()*60 ;

        // if (getPlayerSumCard() > 1) {
        // // Draw
        // }
        // int Time = LocalTime.now().getSecond() + LocalTime.now().getHour()*3600 +
        // LocalTime.now().getMinute()*60 ;

        // int TimeDiff = Time - currentTime;
        // else {
        // // if (Timer < 3 detik &&) {
        // setHiji(true);
        // }
        // System.out.println("Hiji!");
        // int timeDiff;
        // }
    }

    public void draw() {
        CardRandomer cardrandom = new CardRandomer();
        Card drawCard = cardrandom.shuffleCard();
        handCard.addCard(drawCard);
        setSumCard();
    }

    public void discard(Card lastCard, Card C, String cardColor, Player currPlayer, Player nextPlayer, 
    List<Player> listP, int sumPlus) {
        // Kondisi ketika kartu yang dikeluarkan bertipe WildCard
        if (C instanceof WildCard) {
            // Kartu Draw +4
            if ((C.getSkillKartu().equals("Draw +4"))) {
                sumPlus += 4;
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
            handCard.removeCard(C);
            setSumCard();

            sc.close();
        }

        // Kondisi ketika warna kartu tidak diganti
        else if (lastCard.getWarnaKartu().equals(cardColor)) {
            // Kondisi ketika kartu memenuhi salah satu kondisi
            // Warna atau skill atau angka sama dengan kartu sebelumnya
            if (((C.getWarnaKartu()).equals(lastCard.getWarnaKartu())) || C.getAngkaKartu() == lastCard.getAngkaKartu() || 
            ((C.getSkillKartu()).equals(lastCard.getSkillKartu()))) {
                // Warna kartu sama dengan warna kartu sebelumnya
                if ((C.getWarnaKartu()).equals(lastCard.getWarnaKartu())) {
                    if (C instanceof PowerCard) {
                        // Block
                        if ((C.getSkillKartu()).equals("Block")) {
                            int idxPlayer = listP.indexOf(currPlayer);
                            int newidx = idxPlayer + 2;
                            // Kartu sebelumnya Block berwarna sama 
                            if (lastCard.getSkillKartu().equals("Block")) {
                                newidx = idxPlayer + 1;
                            }
                            if (newidx > listP.size()) {
                                newidx = idxPlayer - listP.size();
                            }
                            nextPlayer = listP.get(newidx);
                        }
                        // Reverse
                        else if ((C.getSkillKartu()).equals("Reverse")) {
                            Collections.reverse(listP);
                        }
                        // Draw +2
                        else {
                            // Kartu sebelumnya Draw +2 berwarna sama
                            if (lastCard.getSkillKartu().equals("Draw +2")) {
                                sumPlus += 2;
                            }
                            else {
                                sumPlus = 2;
                            }
                        }
                    }
                }
                // Skill kartu sama dengan skill kartu sebelumnya
                else if ((C.getSkillKartu()).equals(lastCard.getSkillKartu())) {
                    // Block
                    if ((C.getSkillKartu()).equals("Block")) {
                        // Kartu sebelumnya Block dengan warna berbeda
                        int idxPlayer = listP.indexOf(currPlayer);
                        int newidx = idxPlayer + 1;
                        if (newidx > listP.size()) {
                            newidx = idxPlayer - listP.size();
                        }
                        nextPlayer = listP.get(newidx);
                    }
                    // Reverse
                    else if ((C.getSkillKartu()).equals("Reverse")) {
                        Collections.reverse(listP);
                    }
                    // Draw +2
                    else {
                        // Kartu sebelumnya Draw +2 dengan warna berbeda
                        sumPlus += 2;
                    }
                }
                // Kondisi berlaku ketika
                // 1. Warna kartu sama
                // 2. Angka kartu sama
                // 3. Skill kartu sama
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;
                handCard.removeCard(C);
                setSumCard();
            }
            // Kondisi ketika kartu tidak sesuai peraturan
            // Warna/skill/angka tidak ada yang sama
            else {
                System.out.println("Maaf, kartu ini tidak dapat dikeluarkan karena tidak sesuai dengan peraturan");
            }
        }

        // Kondisi ketika warna kartu sudah diganti
        else if (!lastCard.getWarnaKartu().equals(cardColor)){
            // Warna kartu sama dengan pilihan warna baru
            if ((C.getWarnaKartu()).equals(cardColor)) {
                if (C instanceof PowerCard) {
                    // Block
                    if ((C.getSkillKartu()).equals("Block")) {
                        int idxPlayer = listP.indexOf(currPlayer);
                        int newidx = idxPlayer + 2;
                        if (newidx > listP.size()) {
                            newidx = idxPlayer - listP.size();
                        }
                        nextPlayer = listP.get(newidx);
                    }
                    // Reverse
                    else if ((C.getSkillKartu()).equals("Reverse")) {
                        Collections.reverse(listP);
                    }
                    // Draw +2
                    else {
                        sumPlus = 2;
                    }
                }
                System.out.print("Anda mengeluarkan kartu: ");
                C.infoKartu();
                lastCard = C;
                handCard.removeCard(C);
                setSumCard();
            }
            // Kondisi ketika kartu tidak sesuai peraturan
            else {
                System.out.println("Maaf, kartu ini tidak dapat dikeluarkan karena tidak sesuai dengan peraturan");
            }
        }
    }
}