package cardgame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonPlayer extends Player {	
	public PersonPlayer (String name) {
		super(name);
	}
	
	public Card selectCard (Card card_to_beat) {
		ArrayList<Card> cards = this.getPayableCards(card_to_beat);
		
		Scanner scanner = new Scanner(System.in);
		int s = cards.size()-1;
		
		while (true) {
			System.out.println("Select from card number 0 to "+s);
			for (Card c: cards) {
				System.out.print(cards.indexOf(c)+" :"+c.toString()+";\t");
			}
			short num = scanner.nextShort();
			if (num<0 || num>s) {
				System.out.println("Selected number "+"num"+" is out of range, please select again");
				continue;
			}
			return cards.get(num);
		}
	}
}
