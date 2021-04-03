package Class.Player;
/* CardList.java
 * Interface CardList terdiri dari method-method yang dapat digunakan untuk
 * mengakses atau modifikasi kartu setiap pemain 
 */
import Class.Card.*;
import java.util.List;

public interface CardList {
    // Adding one card to player's card list
    public void addCard(Card C);

    // Removing one card to player's card list
    public void removeCard(Card C);

    // Print each player's card
    public void printPCard();

    // Getter player's total card in list
    public int lengthPC();

    // Getter player's card list
    public List<Card> getCardList();
}