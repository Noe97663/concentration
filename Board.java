/* AUTHOR: Noel Martin Poothokaran
* FILE: Board.java
* 
* Used to handle the game logic for the Concentration game.
* 
* Course: CSc 335, Fall 2022
*/
import java.util.ArrayList;
public class Board {
	//Initializing fields that are hardcoded with file information that can
	//be added to later to expand on the features of the game
	private String[] NameArr = {"Noel","Emy","Cam","Arin","Joe","Marv"};
	private String[] VegArr = {"apple.jpg","pear.jpg","peach.jpg","pineapple.jpg",
			"greenapple.jpg","avocado.jpg","orange.jpg","banana.jpg",
			"carrot.jpg","chili.jpg"};
	private String[] AnimalArr = {"chicken.jpg","cow.jpg","dog.jpg","duck.jpg"};
	
	UserInterface UI;
	ArrayList<Player> PlayerList = new ArrayList<>();
	ArrayList<Card> CardDeck = new ArrayList<>();
	private String[] CardArr = new String[6];
	//^^^ this array holds the various card deck options
	//currently just 2 options fill the available 6 slots
	private int GameMode;
	/*	
	 *  Constructor that assigns all fields to their custom values
	 *  input by the person that called the constructor.
	 */
	public Board(int PlayerCount, int CardCount, int GameMode, int CardSet)
	{
		this.GameMode = GameMode;
		
		//adding players to the PlayerList
		for(int i=0; i<PlayerCount; i++) {
			this.PlayerList.add(new Player(NameArr[i]));
		}
		
		//deciding the Card set, CardArr will hold the jpg filenames
		if (CardSet==1) {
			CardArr = VegArr;
		}
		else if (CardSet==2) {
			CardArr = AnimalArr;
		}
		
		//adding cards to the CardList which is used to help draw and nothing else
		for (int i = 0; i<CardCount; i++) {
			CardDeck.add(new Card(CardArr[i]));
		}
	}
	
	/*	Function that checks if two cards are equal and returns true if they are.
	 * 
	 *	Takes 3 arguments:
	 *	checked - an arraylist that contains two indexes of the cards we are checking
	 *	CardList - an arraylist containing the cards we are checking
	 *	Player - an integer indicating which player's move we are checking
	 *
	 *	Returns true if it was a pair, false if not
	 */
	
	public boolean CheckPair(ArrayList<Integer> checked, ArrayList<Card> CardList, int Player) {
		int idx1 = checked.get(0);
		int idx2 = checked.get(1);
		if(CardList.get(idx1).GetName().equals(CardList.get(idx2).GetName())){
			CardList.set(idx1, null);
			CardList.set(idx2, null);
			PlayerList.get(Player).GotPoint();
			return true;
		}
		else {
			CardList.get(idx1).flip();
			CardList.get(idx2).flip();
		}
		return false;
	}
	
	/*	Function that returns the name of the required player
	 * 
	 *	Takes one arguments:
	 *	PlayerIndex - an integer representing the player whose name is needed

	 *	Returns a String that is the player's name.
	 */
	public String GetPlayerName(int PlayerIndex) {
		return NameArr[PlayerIndex];
	}
	
	/*	Function that gets a card object from the cardDeck based on its index
	 * 
	 *	Takes one argument:
	 *	index - an integer represting the position of the card in the cardDeck

	 *	Returns a Card object.
	 */
	public Card GetCard(int index) {
		return CardDeck.get(index);
	}
	
	/*	Function that checks who the winner is.
	 * 
	 *	Takes no arguments.
	 *
	 *	Returns a string that represents the name of the player that won.
	 */
	public String CheckWinner() {
		int most = 0;
		int CurrScore;
		String CurrWinner="";
		for(int i = 0; i<PlayerList.size(); i++) {
			Player CurrPlayer = PlayerList.get(i);
			CurrScore = CurrPlayer.GetPoints();
			if(CurrScore>most) {
				most = CurrScore;
				CurrWinner = CurrPlayer.GetName();
			}
		}
		return CurrWinner;
	}
	
	/*	Function that returns the number of players in the game
	 * 
	 *	Takes no arguments.

	 *	Returns an integer representing the numebr of players.
	 */
	public int NumPlayers() {
		return PlayerList.size();
	}
	
	public int getGameMode() {
		return this.GameMode;
	}
	
	
	
	
}
