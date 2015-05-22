package view;

import java.awt.EventQueue;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Deck;
import controller.Hand;
import controller.Main;

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
@SuppressWarnings("serial")
public class View extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label;
	private JLabel card1;
	private JLabel card2;
	private JLabel card3;
	private JLabel card4;
	private JLabel card5;
	private boolean canEnterBet = true;
	private boolean canDrop = true;
	private Hand h;
	private Deck d;
	/**
	 * The main method of the program. A new deck is generated, and is then
	 * randomized. Then, a 5-card hand is dealt.
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

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

	public View() {
		
		//URL f = this.getClass().getClassLoader().getResource("relative path");
		final String path = "target" + File.separatorChar + "classes" + File.separatorChar;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		card1 = new JLabel("");
		card2 = new JLabel("");
		card3 = new JLabel("");
		card4 = new JLabel("");
		card5 = new JLabel("");

		card1.setBounds(50, 10, 72, 97);
		card2.setBounds(150, 10, 72, 97);
		card3.setBounds(250, 10, 72, 97);
		card4.setBounds(350, 10, 72, 97);
		card5.setBounds(450, 10, 72, 97);

		contentPane.add(card1);
		contentPane.add(card2);
		contentPane.add(card3);
		contentPane.add(card4);
		contentPane.add(card5);

		textField = new JTextField();
		textField.setBounds(150, 250, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		

		label = new JLabel("Your money: " +  Main.getMoney() + " Your bet: ");
		label.setBounds(100, 170, 300, 25);
		contentPane.add(label);

		final JRadioButton rdbtn1 = new JRadioButton("");
		final JRadioButton rdbtn2 = new JRadioButton("");
		final JRadioButton rdbtn3 = new JRadioButton("");
		final JRadioButton rdbtn4 = new JRadioButton("");
		final JRadioButton rdbtn5 = new JRadioButton("");

		final JButton btn = new JButton("");

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canDrop) {
					int howMany = 0;
					howMany += (rdbtn1.isSelected()) ? 1 : 0;
					howMany += (rdbtn2.isSelected()) ? 1 : 0;
					howMany += (rdbtn3.isSelected()) ? 1 : 0;
					howMany += (rdbtn4.isSelected()) ? 1 : 0;
					howMany += (rdbtn5.isSelected()) ? 1 : 0;
					int[] cardsToDrop = new int[0];
					cardsToDrop = new int[howMany];
					
					int c = 0;
					if (rdbtn1.isSelected())
						cardsToDrop[c++] = (rdbtn1.isSelected()) ? 0 : 0;
					if (rdbtn2.isSelected())
						cardsToDrop[c++] = (rdbtn2.isSelected()) ? 1 : 0;
					if (rdbtn3.isSelected())
						cardsToDrop[c++] = (rdbtn3.isSelected()) ? 2 : 0;
					if (rdbtn4.isSelected())
						cardsToDrop[c++] = (rdbtn4.isSelected()) ? 3 : 0;
					if (rdbtn5.isSelected())
						cardsToDrop[c++] = (rdbtn5.isSelected()) ? 4 : 0;

					h.Throw(cardsToDrop);

					String st1 = Integer.toString(h.getCards()[0]);
					String st2 = Integer.toString(h.getCards()[1]);
					String st3 = Integer.toString(h.getCards()[2]);
					String st4 = Integer.toString(h.getCards()[3]);
					String st5 = Integer.toString(h.getCards()[4]);
					
					//URL s = ClassLoader.getSystemResource(path+st1+".png");

					card1.setIcon(new ImageIcon(path+st1+".png"));
					card2.setIcon(new ImageIcon(path+st2+".png"));
					card3.setIcon(new ImageIcon(path+st3+".png"));
					card4.setIcon(new ImageIcon(path+st4+".png"));
					card5.setIcon(new ImageIcon(path+st5+".png"));

					int multipler = h.What();
					Main.setMoney(Main.getMoney()+Main.getBet()*(multipler-1));

					canDrop = false;
					
					btn.setBounds(0, 0, 0, 0);
					rdbtn1.setBounds(0, 0, 0, 0);
					rdbtn2.setBounds(0, 0, 0, 0);
					rdbtn3.setBounds(0, 0, 0, 0);
					rdbtn4.setBounds(0, 0, 0, 0);
					rdbtn5.setBounds(0, 0, 0, 0);
					rdbtn1.setSelected(false);
					rdbtn2.setSelected(false);
					rdbtn3.setSelected(false);
					rdbtn4.setSelected(false);
					rdbtn5.setSelected(false);

					Main.setBet(0);
					label.setText("Your money: " + Main.getMoney() + " Your bet: " + Main.getBet());
					canEnterBet = true;
				}
			}
		});

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String t = textField.getText();
				if (e.getKeyCode() == 10 && t != null && canEnterBet) {
					try {
						int temp = Integer.parseInt(t);
						if (temp > 0 && temp <= Main.getMoney()) {
							Main.setBet(temp);
							label.setText("Your money: " + Main.getMoney()
									+ " Your bet: " + Main.getBet());
							canEnterBet = false;
							d = new controller.Deck();
							d.Randomize();
							h = new controller.Hand(d);

							String st1 = Integer.toString(h.getCards()[0]);
							String st2 = Integer.toString(h.getCards()[1]);
							String st3 = Integer.toString(h.getCards()[2]);
							String st4 = Integer.toString(h.getCards()[3]);
							String st5 = Integer.toString(h.getCards()[4]);
							
							
							//URL s = ClassLoader.getSystemResource(path+st1+".png");
							
							//System.out.println(s.toString());

							card1.setIcon(new ImageIcon(path+st1+".png"));
							card2.setIcon(new ImageIcon(path+st2+".png"));
							card3.setIcon(new ImageIcon(path+st3+".png"));
							card4.setIcon(new ImageIcon(path+st4+".png"));
							card5.setIcon(new ImageIcon(path+st5+".png"));

							rdbtn1.setBounds(50, 120, 100, 20);
							contentPane.add(rdbtn1);
							rdbtn1.setText("DROP");
							rdbtn2.setBounds(150, 120, 100, 20);
							contentPane.add(rdbtn2);
							rdbtn2.setText("DROP");
							rdbtn3.setBounds(250, 120, 100, 20);
							contentPane.add(rdbtn3);
							rdbtn3.setText("DROP");
							rdbtn4.setBounds(350, 120, 100, 20);
							contentPane.add(rdbtn4);
							rdbtn4.setText("DROP");
							rdbtn5.setBounds(450, 120, 100, 20);
							contentPane.add(rdbtn5);
							rdbtn5.setText("DROP");

							btn.setBounds(150, 300, 150, 50);
							contentPane.add(btn);
							btn.setText("Drop");
							canDrop = true;

						}
					} catch (NumberFormatException exc) {
						;
					}
				}
			}
		});

	}

}