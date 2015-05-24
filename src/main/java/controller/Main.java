package controller;

import java.awt.EventQueue;

import model.ScoreDAO;

/**
 * This program simulates a <b>Jacks or Better</b> video poker game. You are
 * dealt 5 cards from a deck, and are given the option to throw away any of
 * those cards. For each card you throw away, you are dealt a new card. After
 * throwing away the cards (if any), your hand is checked.
 * 
 * @author breaker
 * @version 1.0
 * @since 2015-03-24
 */
public class Main {

	/**
	 * The bet which is always higher than 0 and lower than the owned money.
	 */
	private static int bet = 0;
	
	/**
	 * The owned money.
	 */
	private static int money = 10000;
	
	/**
	 * A handler for accessing and updating the highscore.
	 */
	private static ScoreDAO sd;

	/**
	 * The main method of the program. A new deck is generated, and is then
	 * randomized. Then, a 5-card hand is dealt.
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {

		/**
		 * A handler for storing and updating the highscore.
		 */
		sd = new ScoreDAO();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view.View frame = new view.View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//sh.xmlFile.close();

		/**
		 * int money = 10000, bet = 0; Scanner sc = new Scanner(System.in);
		 * while (money != 0) { while (bet <= 0 || bet > money) {
		 * System.out.printf( "Enter your bet. Your current balance is %d.\n",
		 * money); bet = sc.nextInt(); } Deck d = new Deck(); d.Randomize();
		 * Hand h = new Hand(d); // h.Set(109, 206, 301, 307, 308); // int w =
		 * h.What(); System.out.println(h);
		 * System.out.println("Enter card(s) to throw (if any).");
		 * BufferedReader br = new BufferedReader(new InputStreamReader(
		 * System.in)); ArrayList<String> t = new ArrayList<String>();
		 * t.add(br.readLine()); int k = 0; int[] v = new int[0]; for (String
		 * wtf : t) { String[] line = wtf.split(" "); v = new int[line.length];
		 * for (String s : line) { v[k] = Integer.parseInt(s); ++k; } }
		 * h.Throw(v); int multipler = h.What(); System.out.println(h);
		 * System.out.println(multipler); money = money - bet + bet * multipler;
		 * // System.out.println(money);
		 * 
		 * // if (true) { System.out.println(h); System.out.println(h.What());
		 * // }
		 * 
		 * bet = 0; } sc.close();
		 */

	}
	
	/**
	 * Returns the bet.
	 * 
	 * @return the bet
	 */
	public static int getBet() {
		return bet;
	}

	/**
	 * Sets the bet.
	 * 
	 * @param bet the bet
	 */
	public static void setBet(int bet) {
		Main.bet = bet;
	}

	/**
	 * Returns the money.
	 * 
	 * @return money the money
	 */
	public static int getMoney() {
		return money;
	}

	/**
	 * Sets the money.
	 * 
	 * @param money the money
	 */
	public static void setMoney(int money, String name) {
		Main.money = money;
		int previousmaxmoney = sd.getScore();
		System.out.println(money + " " + previousmaxmoney);
		if (money > previousmaxmoney) {
			sd.setScore(money, name);
		}
	}

}
