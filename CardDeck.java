package cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
	ArrayList<Card> allCards = new ArrayList<Card> ();
	private String[] suits = {"SPADE", "HEART", "DIAMOND", "CLUB"};
	private String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	
	public CardDeck() {
		for (String s : suits) {
			for (String r: ranks) {
				//Create Card object and add to CardDeck.allCards
				allCards.add(new Card(s,r));
			}
		}
	}
	
	public void dealCard(ArrayList<Player> players, int card_cnt) {
		Collections.shuffle(this.allCards);
		int i = 0;
		int size = players.size();
		for (Card c: this.allCards) {
			int j = i % size;
			c.setPlayer(players.get(j));
			i ++;
			
	        if( i == card_cnt*size ) {
	             break;
	        }			
		}
	}
	
	public void reset() {
		for (Card c: this.allCards) {
			c.reset();
		}
	}	
	
	public String toString() {
		String ret_string = "";
		for (Card c: this.allCards) {
			if (c.getPlayer()!=null) {
				ret_string += c.toString() + "(" + c.getPlayer().name + ")";
			} else {
				ret_string += c.toString();
			}
		}
		return ret_string;
	}	
}