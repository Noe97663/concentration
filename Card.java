/* AUTHOR: Noel Martin Poothokaran
* FILE: Card.java
* 
* Used to handle Cards for the Concentration game.
* 
* Course: CSc 335, Fall 2022
*/
public class Card {
	//initializing fields
	private String ImgName;
    private boolean FaceUp;

    //constructor
    public Card(String imgName) {
    	this.ImgName = imgName;
    	this.FaceUp = false;
    }

    /*	Function that "flips" the card
	 * 
	 *	Takes no arguments.

	 *	Returns no value. Changes the state of the card.
	 */
    public void flip() {
    	if(!this.FaceUp) {
    		this.FaceUp = true;
    	}
    	else if (this.FaceUp) {
    		this.FaceUp = false;
    	}
    }
    
    /*	Function that checks whether the card is face up
	 * 
	 *	Takes no arguments.

	 *	Returns a boolean, true if the card is face up, else false
	 */
    public boolean IsFaceUp() {
    	return this.FaceUp;
    }

    /*	Function returns the name of the card
	 * 
	 *	Takes no arguments.

	 *	Returns a String with the name of the card.
	 */
    public String GetName() {
    	return this.ImgName;
    }

    
}
