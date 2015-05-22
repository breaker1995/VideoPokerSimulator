import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.Deck;

public class DeckTest {
	
	Deck d;
	
	@Before
	public void setUp() {
		d = new Deck();
	}
	
	@Test
	public void testtoString() {
		assertEquals(d.toString(), "101 102 103 104 105 106 107 108 109 110 111 112 113 "
				+ "201 202 203 204 205 206 207 208 209 210 211 212 213 "
				+ "301 302 303 304 305 306 307 308 309 310 311 312 313 "
				+ "401 402 403 404 405 406 407 408 409 410 411 412 413 ");
	}

}
