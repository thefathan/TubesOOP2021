
package Class.Game;

import java.util.*;
import Class.Card.*;
import Class.Player.*;

public class Game {
    private List<Player> playerList;
    private List<Player> shuffledPlayerList;
    private List<Card> Deck;
    private Card lastCard;
    private Player currentPlayer;
    private Player nextPlayer;
    private String cardColor;
    private int totalPlus;
    private CardRandomer cardrandom;

    // Constructor
    public Game() {
        cardrandom = new CardRandomer();
        totalPlus = 0;
    }

    public void GeneratePlayer(int jumlah) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < jumlah; i++) {
            String s = sc.nextLine();
            PlayerCard pcard = new PlayerCard();
            Player player = new Player(s, pcard);
            cardrandom.generatePlayerCard(playerList.get(i));
            playerList.add(player);
        }
        sc.close();
    }

    public void shufflePlayer() {
        Collections.shuffle(playerList);
        shuffledPlayerList.addAll(playerList);
    }

    public void listPlayer() {
        // for (int i = 0; i < playerList.size(); i++) {
        //     System.out.print(i + ". ");
        //     player.getPlayerName;
        //     player.getPlayerSumCard;
        //     player.isPlaying;
        // }
    }

    public void viewTurn() {

    }

    public void help() {

    }
}