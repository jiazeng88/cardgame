package cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
	String name;
	ArrayList<Card> cards = new ArrayList<Card> ();
	Player nextplayer = null;
	int tricks = 0;
	int games = 0;
	
	public Player(String name) {
		this.name = name;
	}

	public void addCard(Card card) {
		//this.cards.add(card);
		cards.add(card);
	}
	
	public void startNewGame() {
		//this.cards.add(card);
		Collections.sort(cards);
	}
	
	public void reset() {
		cards = new ArrayList<Card> ();
		tricks = 0;
	}	
	
	public ArrayList<Card> getPayableCards (Card card_to_beat) {
		ArrayList<Card> cards = new ArrayList<Card> ();
		if (card_to_beat == null) {
			cards = this.cards;
		} else {
			for (Card c: this.cards) {
				if (c.getSuit().equals(card_to_beat.getSuit())) {
					cards.add(c);
				}
			}
			
			if (cards.isEmpty()) {
				cards = this.cards;
			}
		}
		return cards;
	}
	
	
	public Card selectCard (Card card_to_beat) {
		ArrayList<Card> cards = this.getPayableCards(card_to_beat);
		
		int minimal = 15;
		Card ret_card = null;
		for (Card c : cards) {
			if (c.getRankPower()<minimal) {
				minimal = c.getRankPower();
				ret_card = c;
			}
		}
		
		return ret_card;
	}	
	
	public Card playcard (Card card_to_beat) {
		Card card = this.selectCard(card_to_beat);
		this.cards.remove(card);
		card.state = "played";
        System.out.println(card.getPlayer().toString()+" played "+card.toString());
		if (card_to_beat == null) {
			return card;
		}
		
		//return the winning card
		if (card_to_beat.compareTo(card)>0) {
			return card_to_beat;
		} else {
			return card;
		}
	}
	
	public String toString() {
		return this.name;
	}		
}