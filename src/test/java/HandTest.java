import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.Deck;
import controller.Hand;

public class HandTest {
	
	Deck d;
	Hand h;
	
	@Before
	public void setUp() {
		d = new Deck();
		d.Randomize();
		h = new Hand(d);
		h.Set(101, 102, 103, 104, 105, 110, 107, 108, 109, 106);
	}
	
	@Test
	public void testtoString() {
		assertEquals(h.toString(), "101 102 103 104 105 ");
	}
	
	@Test
	public void testThrow() {
		h.Throw(0, 1, 2, 3, 4);
		int[] test = new int[5];
		test[0] = 106;
		test[1] = 107;
		test[2] = 108;
		test[3] = 109;
		test[4] = 110;
		for (int i=0; i<5; ++i) {
			assertEquals(h.getCards()[i], test[i]);
		}
	}

	@Test
	public void testWhat() {

		h.Set(101, 102, 103, 104, 206);
		assertEquals("Nothing", 0, h.What());

		h.Set(101, 102, 103, 104, 201);
		assertEquals("Nothing", 0, h.What());

		h.Set(102, 210, 211, 212, 213);
		assertEquals("Nothing", 0, h.What());

		h.Set(101, 102, 103, 211, 311);
		assertEquals("Jacks or Better", 1, h.What());

		h.Set(101, 102, 103, 202, 203);
		assertEquals("Two Pair", 2, h.What());

		h.Set(101, 102, 103, 203, 303);
		assertEquals("Three of a Kind", 3, h.What());

		h.Set(110, 111, 112, 113, 201);
		assertEquals("Straight", 4, h.What());

		h.Set(101, 102, 103, 104, 205);
		assertEquals("Straight", 4, h.What());

		h.Set(101, 102, 103, 104, 106);
		assertEquals("Flush", 6, h.What());

		h.Set(101, 102, 201, 202, 301);
		assertEquals("Full House", 9, h.What());

		h.Set(101, 102, 202, 302, 402);
		assertEquals("Four of a Kind", 25, h.What());

		h.Set(101, 102, 103, 104, 105);
		assertEquals("Straight Flush", 50, h.What());

		h.Set(101, 110, 111, 112, 113);
		assertEquals("Royal Flush", 800, h.What());
	}

}
