package cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	ArrayList<Player> players = new ArrayList<Player>();
	int player_cnt;
	int card_cnt;
	Player lead=null;
	CardDeck cd=new CardDeck();
	
	
	public Game(ArrayList<Player> players, int card_cnt) {
		this.cd.dealCard(players, card_cnt);
		
		Collections.shuffle(players);
		player_cnt = players.size();
		this.players = players;
		this.lead = players.get(0);
		this.card_cnt = card_cnt;
	}
	
	public void reset() {
		for (Player p: this.players) {
			p.reset();
		}
		this.cd.reset();
		this.cd.dealCard(this.players, this.card_cnt);
		for (Player p: this.players) {
			p.startNewGame();
		}		
	}	
	
	public void play() {
		if (this.lead == null) {
			System.out.println("Game is not set up properly, need a leadplayer");
		}
		
		int count = 3;
		while (count > 0) {
			int i = 1;
			while (lead.cards.isEmpty()!= true) {
				System.out.println("=========="+this.lead.toString()+" is leading trick #"+i+"==========");
				this.trick(this.lead);
				System.out.println("========== winner of trick #"+i + " is "+this.lead.toString()+"==========");
				i++;
			}
			this.getSummary();
			this.reset();
			count --;
		}
	}
	
	public void trick(Player leadplayer) {
		Card winning_card = null;
		int current_index = this.players.indexOf(leadplayer);
		for (int i=current_index; i<current_index+this.player_cnt; i++ ) {
			Player p = null;
			if (i<=this.player_cnt-1) {
				p=this.players.get(i);
			} else {
				int j = i % this.player_cnt;
				p=this.players.get(j);
			}
			winning_card = p.playcard(winning_card);
		}
		this.lead = winning_card.getPlayer();
		this.lead.tricks += 1;
	}
	


	private void getSummary() {
		int record = 0;
		for (Player p: this.players) {
			if (p.tricks > record) {
				record = p.tricks;
			}
		}
		System.out.println("TRICK COUNT");
		for (Player p: this.players) {
			if (p.tricks == record) {
				System.out.println(p.name+" won, trick count:" + p.tricks);
				this.lead = p;
				p.games ++;
			} else {
				System.out.println(p.name+" trick count :" + p.tricks);
			}
		}
		
		System.out.println("GAME COUNT");
		for (Player p: this.players) {
			System.out.println(p.name+" game count:" + p.games);
		}			
	}
	
	public static void main(String[] args) {	
		ArrayList<Player> allP = new ArrayList<Player> ();
		Player p1 = new Player("Program");
		Player p2 = new Player("Machine");
		PersonPlayer p3 = new PersonPlayer("Person");		
		allP.add(p1);
		allP.add(p2);
		allP.add(p3);
		
		Game g1 = new Game(allP, 5);
		g1.play();
	}	
	
}