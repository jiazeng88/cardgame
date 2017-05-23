package cardgame;

import java.util.ArrayList;
import java.util.Comparator;
import java.lang.Comparable;

public class Card implements Comparator<Card>, Comparable<Card>{

	String suit;
	String rank;
	String state;
	Player player = null;
	int rankPower = 0;
	int suitRankPower = 0;
	
	public Card(String suit, String rank) {
		switch (suit) {
		case "SPADE":
		case "HEART":
		case "DIAMOND":
		case "CLUB":
			this.suit = suit;
			break;
		default:
			System.out.println("Invalid suit "+suit);
			throw new IllegalArgumentException("Invalid suit");
		}

        switch ( rank ) {
	        case "A":   
	        case "K":   
	        case "Q":   
	        case "J":   
	        case "10":  
	        case "9":  
	        case "8":  
	        case "7":  
	        case "6":  
	        case "5":  
	        case "4":  
	        case "3":  
	        case "2":  
	        	this.rank = rank;
	        	break;
	        default :
	        	System.out.println("Invalid rank: "+rank);
	        	throw new IllegalArgumentException("Invalid rank");
        }
        this.state = "new";
	}	
		
	public void setPlayer(Player player) {
		if (this.player==null) {
			this.player = player;
			this.state = "player";
			player.addCard(this);
		} else {
			System.out.println(this.toString()+" is already dealt to another player " + this.player.name);
			throw new IllegalArgumentException("Card belongs to another player");
		}
	}	
	
	public void reset() {
		this.player = null;
		this.state = "new";
	}
	
	public int getRankPower() {
		if (this.rankPower!=0) {
			return this.rankPower;
		}
		int rp = 0;
        switch ( this.rank ) {
	        case "A":   rp = 13; break;
	        case "K":   rp = 12; break;
	        case "Q":   rp = 11; break;
	        case "J":   rp = 10; break;
	        case "10":  
	        case "9":  
	        case "8":  
	        case "7":  
	        case "6":  
	        case "5":  
	        case "4":  
	        case "3":  
	        case "2":  
	        	 rp = Integer.parseInt(this.rank)-1;
	        	 break;
	        default :
        }		

		this.rankPower = rp;
		return this.rankPower;
	}	
	
	public int getSuitRankPower() {
		if (this.suitRankPower!=0) {
			return this.suitRankPower;
		}		
		
		if (this.rankPower == 0) {
			this.getRankPower();
		}
        switch ( this.suit ) {
	        case "SPADE":   
	        	this.suitRankPower = 3*13 + this.rankPower; 
	        	break;
	        case "HEART":   
	        	this.suitRankPower = 2*13 + this.rankPower;
	        	break;
	        case "DIAMOND": 
	        	this.suitRankPower = 1*13 + this.rankPower;
	        	break;
	        case "CLUB":
	        	this.suitRankPower = this.rankPower;
	        	break;
        }
        return this.suitRankPower;
	}		
	
	
	public String getRank() {
		return rank;
	}
	
	public String getSuit() {
		return suit;
	}	
	
	public Player getPlayer() {
		return player;
	}
	
	public String toString() {
		return this.getSuit() +" "+ this.getRank();
	}	
	
	@Override
	public boolean equals(Object that) {
		Card c1 = (Card) that;
		if (this == that) {
			return true;
		}
		
		if (this.suit.equals(c1.suit) && this.rank.equals(c1.rank)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Card getWinningCard(ArrayList<Card> cards) {
		Card winning_card = this;
		for (Card c: cards) {
			if (winning_card.compareTo(c)<0) {
				winning_card = c;
			}
		}
		return winning_card;
	}

	@Override
	public int compareTo(Card c1) {
		if (this.state != "played") {
			return this.getSuitRankPower() - c1.getSuitRankPower();
		}
		
		if (this.suit.equals(c1.suit)){
			return this.getRankPower() - c1.getRankPower();
		} else {
			return 1;
		}
	}

	@Override
	public int compare(Card o1, Card o2) {
		if (o1.state != "played" || o2.state != "played") {
			return o1.getSuitRankPower() - o2.getSuitRankPower();
		} else {
			if (o1.suit.equals(o2.suit)){
				return o1.getRankPower() - o2.getRankPower();
			} else {
				return 1;
			}			
		}
	}
}