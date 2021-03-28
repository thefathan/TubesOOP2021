package Tubes;

import java.util.*;

public class Deck {
	
	private String[] Number = {"One", "Two", "Three", "Four" , "Five", "Six", "Seven", "Eight", "Nine", "Zero"};
	private String[] Color = {"Red", "Blue", "Yellow", "Green"};
	private String[] StrongCard = {"Draw +2", "Reverse", "Skip"};
	private String[] SpecialCard = {"Draw +4", "Wildcard","Draw +4", "Wildcard","Draw +4", "Wildcard","Draw +4", "Wildcard"};
	
	private int totalcards = Number.length * Color.length;
	private int othertotalcards = StrongCard.length * Color.length;
	private int allcards = totalcards + othertotalcards + SpecialCard.length;
	private String[] numberdeck = new String[totalcards];
	private String[] otherdeck = new String[othertotalcards];
	private String[] specialdeck = new String[SpecialCard.length];
	private Object[] fulldeck;
	private String[] shuffledeck = new String[allcards];
	
	
	//Inisialisasi
	public void initialize() {
		for (int i = 0; i< Number.length; i++) {
			for (int j = 0; j < Color.length; j++) {
				this.numberdeck[Color.length* i + j] = Number[i] +" " +Color[j]; 
				}
			}
		for (int i = 0; i< StrongCard.length; i++) {
			for (int j = 0; j < Color.length; j++) {
				this.otherdeck[Color.length* i + j] = StrongCard[i] +" " +Color[j]; 
				}
			}
		for (int i = 0; i< SpecialCard.length; i++) {
			this.specialdeck[i] = SpecialCard[i];
		}
		List list = new ArrayList(Arrays.asList(numberdeck));
		list.addAll(Arrays.asList(otherdeck));
		list.addAll(Arrays.asList(SpecialCard));
	    fulldeck = list.toArray();
		}
	
	//print full deck
	public void PrintFullDeck() {
		for (int i = 0; i< allcards; i++) {
			System.out.println(fulldeck[i]);
		}
	}
	
	//Shuffle card 
	public void shuffle() {
        for (int i = 0; i < allcards; i++) {
        int r = i + (int) (Math.random() * (allcards-i));
        String temp = (String) fulldeck[r];
        	fulldeck[r] = fulldeck[i];
        	fulldeck[i] = temp;
        	this.shuffledeck[i] = (String) fulldeck[i];
        	
        }
	}
	
	//print shuffled deck
	public void PrintShuffledDeck() {
        for (int i = 0; i < allcards; i++) {
        	System.out.println(shuffledeck[i]);
            }
	}

}
