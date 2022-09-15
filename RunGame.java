/* AUTHOR: Noel Martin Poothokaran
* FILE: GameClass.java
* 
* Used to run the Concentration game and allow 
* for several compile time customizations like
* number of players(1-6), 
* number of cards(1-10),
* different card decks (animal or vegatable),
* different game modes (normal or always switch turns.
* 
* Course: CSc 335, Fall 2022
*/
public class RunGame {
	public static void main(String args[]) {
		//max players allowed now is 6 can be extended with more names added to a list
		int PlyrCt = 2;
		//number of cards range upto 10(vegetable max), 4(animal max) currently
		int Cardct = 6;
		//game modes: 1 - alternate turns (one-flip), 2 - continue turn if successful
		int Gamemd = 1;
		//card sets are numbered: 1 - vegetables, 2 - animals, 
		//extended by added by adding lists of filenames
		int Cardst = 1;
		
		//begin game
		Board GameBoard = new Board(PlyrCt,Cardct,Gamemd,Cardst);
		UserInterface UI = new UserInterface(GameBoard,Cardct);
		
	}

}
