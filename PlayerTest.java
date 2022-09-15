import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testPlayer() {
		Player Tester = new Player("Joey");
		assertTrue(Tester.GetName().equals("Joey"));
		assertTrue(Tester.GetPoints()==0);
	}

	@Test
	public void testGotPoint() {
		Player Tester = new Player("Noel");
		Tester.GotPoint();
		Tester.GotPoint();
		Tester.GotPoint();
		Tester.GotPoint();
		assertTrue(Tester.GetPoints()==4);
	}

	@Test
	public void testGetPoints() {
		Player Tester = new Player("Emily");
		assertTrue(Tester.GetPoints()==0);
		Tester.GotPoint();
		Tester.GotPoint();
		Tester.GotPoint();
		assertTrue(Tester.GetPoints()==3);
	}

	@Test
	public void testGetName() {
		Player Tester = new Player("Cameron");
		assertTrue(Tester.GetName().equals("Cameron"));
	}

}
