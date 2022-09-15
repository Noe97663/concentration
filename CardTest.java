import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void testCard() {
		Card Test = new Card("bruh");
		assertTrue(Test.GetName().equals("bruh"));
		assertFalse(Test.IsFaceUp());
	}

	@Test
	void testFlip() {
		Card Test = new Card("sis");
		Test.flip();
		assertTrue(Test.IsFaceUp());
		Test.flip();
		assertFalse(Test.IsFaceUp());
		Test.flip();
		assertTrue(Test.IsFaceUp());
	}

	@Test
	void testIsFaceUp() {
		Card Test = new Card("fam");
		Test.flip();
		assertTrue(Test.IsFaceUp());
		Test.flip();
		assertFalse(Test.IsFaceUp());
		Test.flip();
		assertTrue(Test.IsFaceUp());
	}

	@Test
	void testGetName() {
		Card Test = new Card("dad");
		assertTrue(Test.GetName().equals("dad"));
	}

}
