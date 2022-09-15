/* AUTHOR: Noel Martin Poothokaran
* FILE: Player.java
* 
* Used to handle Players for the Concentration game.
* 
* Course: CSc 335, Fall 2022
*/
public class Player {
	//initializing fields
	private String PlayerName;
    private int Points;

    //constructor
    public Player(String PlyrName) {
    	this.PlayerName = PlyrName;
    	this.Points = 0;
    }

    /*	Function that adds points to the player
	 * 
	 *	Takes no arguments.

	 *	No return value.
	 */
    public void GotPoint() {
    	this.Points++;
    }
    
    /*	Function that returns the player's points
	 * 
	 *	Takes no arguments.

	 *	returns an integer containing the players points
	 */
    public int GetPoints() {
    	return this.Points;
    }
    
    /*	Function that returns the name of the player
	 * 
	 *	Takes no arguments.

	 *	returns a string containing the players name
	 */
    public String GetName() {
    	return this.PlayerName;
    }
}
