package Tubes;

import java.util.*;

public class Experiment {
	public static void main(String[] args) {
		Deck cards= (Deck) new Deck();
		cards.initialize();
		cards.PrintFullDeck();
		System.out.println(" ");
		cards.shuffle();
		cards.PrintShuffledDeck();

	}

}
