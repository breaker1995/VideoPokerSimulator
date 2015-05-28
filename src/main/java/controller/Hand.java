package controller;

/**
 * <h1>Class for storing a 5-card hand and checking it.</h1>
 * This class stores a 5-card hand when given a 52-card {@link Deck
 * Deck}.
 * It also stores 5 additional cards which are used when cards are thrown away.
 * 
 * @author breaker
 *
 */
public class Hand {

	/**
	 * An integer array for storing the integers that represent the cards.
	 */
	private int[] cards;
	
	/**
	 * Takes the top 5 cards from the given deck and sorts them for better usage.
	 * It also takes 5 additional cards from the deck.
	 * 
	 * @param d A 52-card deck represented by the {@link Deck
 * Deck} class.
	 */
	public Hand(Deck d) {
		int i, j;
		this.cards = new int[10];
		for (i = 0; i < 10; ++i) {
			this.cards[i] = d.cards[i];
		}

		int temp;
		for (i = 0; i < 4; ++i) {
			for (j = i + 1; j < 5; ++j) {
				if (this.cards[i] > this.cards[j]) {
					temp = this.cards[i];
					this.cards[i] = this.cards[j];
					this.cards[j] = temp;
				}
			}
		}

	}

	/**
	 * This method was used for debugging purposes.
	 * You can forcefully set a hand to certain cards with this method in order to easily
	 * check the result of the {@link #What() What()} method for certain hands.
	 * 
	 * @param a First card represented by an integer.
	 * @param b Second card represented by an integer.
	 * @param c Third card represented by an integer.
	 * @param d Fourth card represented by an integer.
	 * @param e Fifth card represented by an integer.
	 */
	public void Set(int a, int b, int c, int d, int e) {
		this.cards[0] = a;
		this.cards[1] = b;
		this.cards[2] = c;
		this.cards[3] = d;
		this.cards[4] = e;
	}
	
	/**
	 * This method was used for debugging purposes.
	 * You can forcefully set a hand to certain cards with this method in order to easily
	 * check the result of the {@link #What() What()} method for certain hands.
	 * 
	 * @param a First card represented by an integer.
	 * @param b Second card represented by an integer.
	 * @param c Third card represented by an integer.
	 * @param d Fourth card represented by an integer.
	 * @param e Fifth card represented by an integer.
	 * @param f First unseen card represented by an integer.
	 * @param g Second unseen card represented by an integer.
	 * @param h Third unseen card represented by an integer.
	 * @param i Fourth unseen card represented by an integer.
	 * @param j Fifth unseen card represented by an integer.
	 */
	public void Set(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		this.cards[0] = a;
		this.cards[1] = b;
		this.cards[2] = c;
		this.cards[3] = d;
		this.cards[4] = e;
		this.cards[0+5] = f;
		this.cards[1+5] = g;
		this.cards[2+5] = h;
		this.cards[3+5] = i;
		this.cards[4+5] = j;
	}
	
	/**
	 * Returns the array of cards represented by integers.
	 * 
	 * @return the array of cards represented by integers
	 */
	public int[] getCards() {
		return cards;
	}
	
	/**
	 * This method throws away up to 5 cards from a hand, and takes the same number of new cards
	 * from the additional 5 cards. It also sorts them afterwards.
	 * 
	 * @param args Up to 5 cards represented by integers.
	 */
	public void Throw(int... args) {
		for (int arg : args) {
			this.cards[arg] = this.cards[arg+5];
		}
		
		int temp, i, j;
		for (i = 0; i < 4; ++i) {
			for (j = i + 1; j < 5; ++j) {
				if (this.cards[i] > this.cards[j]) {
					temp = this.cards[i];
					this.cards[i] = this.cards[j];
					this.cards[j] = temp;
				}
			}
		}
	}
	
	/**
	 * Checks if a hand is a Royal Flush.
	 * 
	 * @return boolean Returns true if the hand is a Royal Flush.
	 */
	public boolean IsRoyalFlush() {
		return (this.cards[0]%100 == 1 && this.cards[4]%100 == 13 && this.IsStraightFlush());
	}
	
	/**
	 * Checks if a hand is a Straight Flush.
	 * 
	 * @return boolean Returns true if the hand is a Straight Flush.
	 */
	public boolean IsStraightFlush() {
		return (this.IsStraight() && this.IsFlush());
	}

	/**
	 * Checks if a hand is a Four of a Kind.
	 * 
	 * @return boolean Returns true if the hand is a Four of a Kind.
	 */
	public boolean IsFourOfAKind() {
		int counter = 0;
		int i, j;
		for (i = 0; i < 4; ++i) {
			for (j = i + 1; j < 5; ++j) {
				if (this.cards[i] % 100 != this.cards[j] % 100)
					counter++;
			}
		}
		if (counter == 4)
			return true;
		return false;
	}

	/**
	 * Checks if a hand is a Full House.
	 * 
	 * @return boolean Returns true if the hand is a Full House.
	 */
	public boolean IsFullHouse() {
		int counter = 0;
		int i, j;
		for (i = 0; i < 4; ++i) {
			for (j = i + 1; j < 5; ++j) {
				if (this.cards[i] % 100 != this.cards[j] % 100)
					counter++;
			}
		}
		if (counter == 6)
			return true;
		return false;
	}

	/**
	 * Checks if a hand is a Flush.
	 * 
	 * @return boolean Returns true if the hand is a Flush.
	 */
	private boolean IsFlush() {
		int i;
		int x = this.cards[0] - this.cards[0] % 100;
		for (i = 1; i < 5; ++i) {
			if (this.cards[i] - this.cards[i] % 100 != x)
				return false;
		}
		return true;
	}

	/**
	 * Checks if a hand is a Straight.
	 * 
	 * @return boolean Returns true if the hand is a Straight.
	 */
	public boolean IsStraight() {
		int temp;
		int i, j;

		for (i = 0; i < 4; ++i) {
			for (j = i + 1; j < 5; ++j) {
				if (this.cards[i] % 100 > this.cards[j] % 100) {
					temp = this.cards[i];
					this.cards[i] = this.cards[j];
					this.cards[j] = temp;
				}
			}
		}

		int counter = 0;
		for (i = 0; i < 4; ++i) {
			if (this.cards[i] % 100 + 1 != this.cards[i + 1] % 100)
				counter = 1;
		}
		
		if (counter==1) {
			for (i = 1; i < 4; ++i) {
				if (this.cards[i] % 100 + 1 != this.cards[i + 1] % 100)
					counter = 2;
			}
			if (this.cards[0]%100 != 1 || this.cards[4]%100 != 13)
				counter = 2;
		}

		if (counter != 2) {
			for (i = 0; i < 4; ++i) {
				for (j = i + 1; j < 5; ++j) {
					if (this.cards[i] > this.cards[j]) {
						temp = this.cards[i];
						this.cards[i] = this.cards[j];
						this.cards[j] = temp;
					}
				}
			}
			return true;
		}

		return false;
	}

	/**
	 * Checks if a hand is a Three of a Kind.
	 * 
	 * @return boolean Returns true if the hand is a Three of a Kind.
	 */
	public boolean IsThreeOfAKind() {
		int counter = 0;
		int i, j;
		for (i = 0; i < 4; ++i) {
			for (j = i + 1; j < 5; ++j) {
				if (this.cards[i] % 100 != this.cards[j] % 100)
					counter++;
			}
		}
		if (counter == 7)
			return true;
		return false;
	}

	/**
	 * Checks if a hand is a Two Pair.
	 * 
	 * @return boolean Returns true if the hand is a Two Pair.
	 */
	public boolean IsTwoPair() {
		int counter = 0;
		int i, j;
		for (i = 0; i < 4; ++i) {
			for (j = i + 1; j < 5; ++j) {
				if (this.cards[i] % 100 != this.cards[j] % 100)
					counter++;
			}
		}
		if (counter == 8)
			return true;
		return false;
	}

	/**
	 * Checks if a hand is a Jacks or Better pair.
	 * 
	 * @return boolean Returns true if the hand is a Jacks or Better pair.
	 */
	public boolean IsJacksOrBetter() {
		int counter = 0;
		int i, j;
		for (i = 0; i < 4; ++i) {
			for (j = i + 1; j < 5; ++j) {
				if (this.cards[i] % 100 != this.cards[j] % 100)
					counter++;
				else if (this.cards[i] % 100 < 11)
					return false;
			}
		}
		if (counter == 9)
			return true;
		return false;
	}

	/**
	 * Determines the value of a hand using several other methods.
	 * 
	 * @return int Returns a multiplier based on the value of the hand.
	 */
	public int What() {
		if (this.IsRoyalFlush())
			return 800;
		if (this.IsStraightFlush())
			return 50;
		if (this.IsFourOfAKind())
			return 25;
		if (this.IsFullHouse())
			return 9;
		if (this.IsFlush())
			return 6;
		if (this.IsStraight())
			return 4;
		if (this.IsThreeOfAKind())
			return 3;
		if (this.IsTwoPair())
			return 2;
		if (this.IsJacksOrBetter())
			return 1;
		return 0;
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
		for (i = 0; i < 5; i++) {
			result.append(Integer.toString(this.cards[i]));
			result.append(" ");
		}
		return result.toString();
	}

}
