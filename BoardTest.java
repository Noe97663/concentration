import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	void testBoard() {
		//player card gamemode set
		Board TestBoard = new Board(3,4,2,1);
		assertTrue(TestBoard.NumPlayers()==3);
		assertTrue(TestBoard.CardDeck.size()==4);
		assertTrue(TestBoard.getGameMode()==2);
		assertTrue(TestBoard.GetCard(0).GetName().equals("apple.jpg"));
	}


	@Test
	void testGetPlayerName() {
		Board TestBoard = new Board(3,4,2,1);
		assertTrue(TestBoard.GetPlayerName(0).equals("Noel"));
		assertTrue(TestBoard.GetPlayerName(1).equals("Emy"));
		assertTrue(TestBoard.GetPlayerName(2).equals("Cam"));
	}


	@Test
	void testGetCard() {
		Board TestBoard = new Board(3,4,2,1);
		assertTrue(TestBoard.GetCard(0).GetName().equals("apple.jpg"));
		assertTrue(TestBoard.GetCard(3).GetName().equals("pineapple.jpg"));
	}

	@Test
	void testCheckPair() {
		Board TestBoardA = new Board(3,1,2,1);
		Card cardAA = new Card("check");
		Card cardAB = new Card("check");
		ArrayList<Card> CardListA = new ArrayList<>();
		ArrayList<Integer> idxListA = new ArrayList<>();
		idxListA.add(0);
		idxListA.add(1);
		CardListA.add(cardAA);
		CardListA.add(cardAB);
		assertTrue(TestBoardA.CheckPair(idxListA, CardListA, 0));
		
		Board TestBoardB = new Board(3,1,2,1);
		Card cardBA = new Card("check");
		Card cardBB = new Card("false");
		ArrayList<Card> CardListB = new ArrayList<>();
		ArrayList<Integer> idxListB = new ArrayList<>();
		idxListB.add(0);
		idxListB.add(1);
		CardListB.add(cardBA);
		CardListB.add(cardBB);
		assertFalse(TestBoardB.CheckPair(idxListB, CardListB, 0));
	}
	
	@Test
	void testCheckWinner() {
		Board TestBoardA = new Board(3,1,2,1);
		Card cardAA = new Card("check");
		Card cardAB = new Card("check");
		ArrayList<Card> CardListA = new ArrayList<>();
		ArrayList<Integer> idxListA = new ArrayList<>();
		idxListA.add(0);
		idxListA.add(1);
		CardListA.add(cardAA);
		CardListA.add(cardAB);
		TestBoardA.CheckPair(idxListA, CardListA, 0);
		assertTrue(TestBoardA.CheckWinner().equals("Noel"));
		TestBoardA.PlayerList.get(2).GotPoint();
		TestBoardA.PlayerList.get(2).GotPoint();
		TestBoardA.PlayerList.get(2).GotPoint();
		TestBoardA.PlayerList.get(2).GotPoint();
		assertTrue(TestBoardA.CheckWinner().equals("Cam"));
	}

	@Test
	void testNumPlayers() {
		Board TestBoard = new Board(5,4,2,1);
		assertTrue(TestBoard.NumPlayers()==5);
	}


}
