package Class.Card;

import Class.Player.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Math;


public class CardRandomer {
    private List<Card> list = new ArrayList<Card>();
    private List<Card> shuffled = new ArrayList<Card>();

    public void addcard() {
        
        for (int i = 0; i <= 9; i++) {
            NumberCard numbercardred = new NumberCard("Number Card", "Red", "NULL", i);
            this.list.add(numbercardred);
            NumberCard numbercardblue = new NumberCard("Number Card", "Blue", "NULL", i);
            this.list.add(numbercardblue);
            NumberCard numbercardgreen = new NumberCard("Number Card", "Green", "NULL", i);
            this.list.add(numbercardgreen);
            NumberCard numbercardyellow = new NumberCard("Number Card", "Yellow", "NULL", i);
            this.list.add(numbercardyellow);
        }
        PowerCard reversered = new PowerCard("Power Card", "Red", "Reverse", -99);
        this.list.add(reversered);
        PowerCard reverseblue = new PowerCard("Power Card", "Blue", "Reverse", -99);
        this.list.add(reverseblue);
        PowerCard reversegreen = new PowerCard("Power Card", "Green", "Reverse", -99);
        this.list.add(reversegreen);
        PowerCard reverseyellow = new PowerCard("Power Card", "Yellow", "Reverse", -99);
        this.list.add(reverseyellow);
        
        PowerCard blockred = new PowerCard("Power Card", "Red", "Block", -99);
        this.list.add(blockred);
        PowerCard blockblue = new PowerCard("Power Card", "Blue", "Block", -99);
        this.list.add(blockblue);
        PowerCard blockgreen = new PowerCard("Power Card", "Green", "Block", -99);
        this.list.add(blockgreen);
        PowerCard blockyellow = new PowerCard("Power Card", "Yellow", "Block", -99);
        this.list.add(blockyellow);

        PowerCard drawred = new PowerCard("Power Card", "Red", "Draw +2", -99);
        this.list.add(drawred);
        PowerCard drawblue = new PowerCard("Power Card", "Blue", "Draw +2", -99);
        this.list.add(drawblue);
        PowerCard drawgreen = new PowerCard("Power Card", "Green", "Draw +2", -99);
        this.list.add(drawgreen);
        PowerCard drawyellow = new PowerCard("Power Card", "Yellow", "Draw +2", -99);
        this.list.add(drawyellow);

        WildCard wildcard = new WildCard("Wild Card", "NULL", "Wildcard", -99);
        this.list.add(wildcard);
        WildCard draw4 = new WildCard("Wild Card", "NULL", "Draw +4", -99);
        this.list.add(draw4);
    }

    public void printCard() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof NumberCard) {
                // System.out.println("Number Card");
                NumberCard numbercard = (NumberCard)list.get(i);
                numbercard.infoKartu();
            }
            else if (list.get(i) instanceof PowerCard) {
                // System.out.println("Power Card");
                PowerCard powercard = (PowerCard)list.get(i);
                powercard.infoKartu();
            }
            else if (list.get(i) instanceof WildCard) {
                // System.out.println("Wild Card");
                WildCard wildcard = (WildCard)list.get(i);
                wildcard.infoKartu();
            }
        }
    }

    public void shufflingCard() {
    	// int i = 0;
    	// int r = i + (int) (Math.random() * (list.size() - i));
    	Collections.shuffle(list);
    	shuffled.addAll(list);

    }

    public void printShuffledCard() {
        for (int i = 0; i < shuffled.size(); i++) {
            if (shuffled.get(i) instanceof NumberCard) {
                // System.out.println("Number Card");
                NumberCard numbercard = (NumberCard)shuffled.get(i);
                numbercard.infoKartu();
            }
            else if (shuffled.get(i) instanceof PowerCard) {
                // System.out.println("Power Card");
                PowerCard powercard = (PowerCard)shuffled.get(i);
                powercard.infoKartu();
            }
            else if (shuffled.get(i) instanceof WildCard) {
                // System.out.println("Wild Card");
                WildCard wildcard = (WildCard)shuffled.get(i);
                wildcard.infoKartu();
            }
        }
    }
    
    public void printFirstShuffled() {
    	if (shuffled.get(0) instanceof NumberCard) {
            // System.out.println("Number Card");
            NumberCard numbercard = (NumberCard)shuffled.get(0);
            numbercard.infoKartu();
        }
        else if (shuffled.get(0) instanceof PowerCard) {
        	shufflingCard();
            // System.out.println("Power Card");
            PowerCard powercard = (PowerCard)shuffled.get(0);
            powercard.infoKartu();
        }
        else if (shuffled.get(0) instanceof WildCard) {
            // System.out.println("Wild Card");
            WildCard wildcard = (WildCard)shuffled.get(0);
            wildcard.infoKartu();
        }
    }

    // Generate card for each player
    public void generatePlayerCard(Player P) {
        int min = 0;
        int max = 53;
        int range = max - min + 1;
        PlayerCard player_card = P.getHandCard();

        for (int i = 0; i < 7; i++) {
            int rand = (int)(Math.random() * range) + min;
            player_card.addCard(list.get(rand));
        }
        P.setSumCard();
    }
}
