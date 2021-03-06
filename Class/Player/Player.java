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
    public Player(String name) {
        this.playerName = name;
        this.sumCard = 0;
        this.hiji = false;
        this.playing = false;
        this.handCard = new PlayerCard();
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
    public void printCard() {
        handCard.printPCard();
    }

    // Setter player's total card
    public void setSumCard(PlayerCard pc) {
        this.sumCard = pc.lengthPC();
    }

    public void setPlayerCard(PlayerCard pc) {
        this.handCard = pc;
    }
    // Setter player's hiji status
    public void setHiji(boolean hiji) {
        this.hiji = hiji;
    }

    // Setter player's playing status
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public class PlayerCard implements CardList {
        private List<Card> cardList;
    
        // Constructor 
        public PlayerCard() {
            this.cardList = new ArrayList<Card>();
        }
    
        // Adding one card to player's card list
        public void addCard(Card C) {
            cardList.add(C);
        }
    
        // Removing one card to player's card list
        public void removeCard(Card C) {
            cardList.remove(C);
        }
    
        // Print each player's card
        public void printPCard() {
            int i = 1;
            for (Card C : cardList) {
                System.out.print(i + ". ");
                C.infoKartu();
                i++;
            }
            System.out.println("");
        }
    
        // Getter player's total card in list
        public int lengthPC() {
            return cardList.size();
        }
    
        // Getter player's card list
        public List<Card> getCardList() {
            return cardList;
        }
    } 
}