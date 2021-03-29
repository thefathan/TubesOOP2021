package Class.Player;
/* PlayerCard.java
 * Class PlayerCard berfungsi untuk menyimpan kartu-kartu yang dimiliki oleh pemain
 * dan implementasi fitur yang dapat digunakan oleh pemain
 */
import Class.Card.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerCard {
    private List<Card> cardList;

    // Constructor 
    public PlayerCard() {
        this.cardList = new ArrayList<Card>();
        // CardRandomer crand = new CardRandomer();
        // crand.generatePlayerCard(cardList);
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
        for (Card C : cardList) {
            C.infoKartu();
            // if (C instanceof NumberCard) {
            //     NumberCard numbercard = (NumberCard)C;
            //     numbercard.infoKartu();
            // }
            // else if (C instanceof PowerCard) {
            //     PowerCard powercard = (PowerCard)C;
            //     powercard.infoKartu();
            // }
            // else if (C instanceof WildCard) {
            //     WildCard wildcard = (WildCard)C;
            //     wildcard.infoKartu();
            // }
        }
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