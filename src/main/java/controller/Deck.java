package controller;

import java.util.Random;


/**
 * <h1>Class for storing a 52-card deck.</h1>
 * The Deck class stores cards in the following way:<br>
 * -Each card is represented by an integer<br>
 * -Suits are from 1 to 4 and are stored in the first digit<br>
 * -Values are from 01 to 13 where 01 is Ace and 13 is King and they are stored in the last two digits
 * 
 * @author breaker
 */
public class Deck {

	/**
	 * An integer array for storing the integers that represent the cards.
	 */
	public int[] cards;

	/**
	 * Constructor for generating the cards from a loop.
	 */
	public Deck() {
		int i;
		this.cards = new int[52];
		for (i = 0; i < 52; ++i) {
			this.cards[i] = (i / 13 + 1) * 100 + i % 13 + 1;
		}
	}

	/**
	 * Returns the cards represented by integers converted to a String.
	 * 
	 * @return the cards represented by integers converted to a String
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		int i;

		StringBuffer result = new StringBuffer();
		for (i = 0; i < 52; i++) {
			result.append(Integer.toString(this.cards[i]));
			result.append(" ");
		}
		return result.toString();
	}

	/**
	 * This method randomizes the deck.
	 */
	public void Randomize() {
		Random randomGenerator = new Random();
		int temp;
		int i;
		for (i = 0; i < 100; ++i) {
			int a = randomGenerator.nextInt(52);
			int b = randomGenerator.nextInt(52);
			temp = this.cards[a];
			this.cards[a] = this.cards[b];
			this.cards[b] = temp;
		}
	}

}