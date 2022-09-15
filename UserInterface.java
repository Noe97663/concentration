/* AUTHOR: Noel Martin Poothokaran
* FILE: UserInterface.java
* 
* Used to handle the concetration game's
* graphics and user interface.
* 
* Course: CSc 335, Fall 2022
*/
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import java.util.List;
import java.util.*;
public class UserInterface
{
	//initializing fields that will be useful for the UI
	public UserInterface(Board GameBoard, int CardCount)
	{
		Display display = new Display();
		//List that will handle the randomization of the card positions
		ArrayList<Integer> CardPosiList = new ArrayList<>();
		for(int i=0; i<CardCount; i++) {
			CardPosiList.add(i);
			CardPosiList.add(i);
		}
		Collections.shuffle(CardPosiList);
		
		//rule change if we are playing a certain game mode
		boolean PlayOn = false;
		if(GameBoard.getGameMode()==2) {
			PlayOn = true;
		}
		// ImList is a list of image objects to actually draw the images
		// CardList holds the cards that we're using in the game logic
		ArrayList<Card> CardList = new ArrayList<>();
		List<Image> imList = new ArrayList<Image>();
		//start from player 1
		int CurrPlayer = 0;
		
		for(int i=0; i<(CardCount*2); i++) {
			int CardIndex = CardPosiList.get(i);
			imList.add(new Image(display,GameBoard.GetCard(CardIndex).GetName()));
			CardList.add(new Card(GameBoard.GetCard(CardIndex).GetName()));
			
		}
		//used to check if the game is over
		int CardsLeft = CardCount*2;
		
		
		
		//'blank' image to simulate card flips
		Image blank = new Image(display, "blank.jpg");
		
		//UI shell boilerplate
		Shell shell = new Shell(display);
		shell.setSize(430,600);
		shell.setText("Concentration");
		GridLayout gridLayout = new GridLayout();
		shell.setLayout( gridLayout);

		//widget boilerplate
	    Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
	    Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
	    
	    //also drawing canvas
		Canvas canvas = new Canvas(upperComp, SWT.NONE);
		canvas.setSize(500,500);
		
		//quit button
		Button quitButton = new Button(lowerComp, SWT.PUSH);
		quitButton.setText("Quit");
		quitButton.setSize(100, 40);
		quitButton.setLocation(new Point(0,-10));
		quitButton.addSelectionListener(new ButtonSelectionListener());	
		
		//button being used as a text prompt since other methods of showing text
		//decide to not work
		String CurrPlayerName =GameBoard.GetPlayerName(CurrPlayer);
		Button Prompt = new Button(lowerComp, SWT.PUSH);
		Prompt.setLocation(new Point(170,-10));
		Prompt.setText(CurrPlayerName+" will now play");
		Prompt.setSize(300, 40);
		Prompt.setEnabled(false);
		
		
		//listen for events, action from the users
		canvas.addPaintListener(new CanvasPaintListener(shell, imList, blank,CardList,display));		
		canvas.addMouseListener(new CanvasMouseListener(shell, imList, blank,canvas,
				CardList,GameBoard,CurrPlayer,CardsLeft,CurrPlayerName,Prompt,PlayOn));
		//---- our Widgets end here
		
		//display canvas loop
		shell.open();
		while( !shell.isDisposed())
		{
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	} 
}

/*
 * Object that handles all the mouse clicks that occur in the canvas
 * 
 */
class CanvasMouseListener implements MouseListener 
{
	
	//these fields will be required in certain methods or just due to how swt works
    Shell shell;
    Display display;
    List<Image> imList;
    ArrayList<Card> CardList;
    Image blank;
    Canvas canvas;
    Button Prompt;
    Board GameBoard;
    boolean PlayOn;
    int clicks=0;
    int CurrPlayer;
    int CardsLeft;
    String CurrPlayerString;
	ArrayList<Integer> selected = new ArrayList<>();
	ArrayList<Integer> takenOut = new ArrayList<>();
	
	/* Function used to determine which card the user clicked on
	 * 
	 * returns an int representing the index of the card in CardList
	*/
    public int getImgIdx(MouseEvent event) {
		ImageData data = new Image(display, "blank.jpg").getImageData();
		int idx=0;
		int col = event.x/data.width;
		int row = event.y/data.height;
		idx+=(row*4);
		idx+=col;
		return idx;
    }
    
    //passes the required objects to the mouselistener object
    public CanvasMouseListener(Shell sh, List<Image> im, Image blankImage, Canvas cvs,
    		ArrayList<Card> cd, Board GmBrd, int CurPlyr, int CrdsLft, String CurPlyrStr,
    		Button Prmpt, boolean PlyOn)
    	{
    	shell = sh; imList = im; blank = blankImage; canvas = cvs; CardList = cd;
    	GameBoard=GmBrd; CurrPlayer = CurPlyr; CardsLeft = CrdsLft;
    	CurrPlayerString = CurPlyrStr; Prompt = Prmpt; PlayOn = PlyOn;
    } 
    
    //unused feature as of now
	public void mouseDoubleClick(MouseEvent event){}
	
	/* 
	 * Handles the events in case of a mouse click
	 * Right now a refactor to take some game logic away from this file would
	 * be ideal but a clean refactor is proving difficult.
	*/
	public void mouseDown(MouseEvent event){
		boolean PickedRight;
		clicks++;
		int idx = getImgIdx(event);
		
		if((selected.size()>0)&&(idx==selected.get(0))) {
			;//pass statement for when the same card is clicked
		}
		else {
		//this idx variable is the card in the game which has been flipped
		//starts from 0
			if ((idx < imList.size())&&(!takenOut.contains(idx))) {
				selected.add(idx);
				CardList.get(idx).flip();
			//	imList.set(idx, imList.get(idx));
			}
		
			canvas.redraw();
			canvas.update();
			
			//check to see if two cards have been revealed
			if(selected.size()==2) {
				
				//checking to see if the right cards were picked
				PickedRight = GameBoard.CheckPair(selected,CardList,CurrPlayer);
				if(PickedRight) {
					takenOut.addAll(selected);
					CardsLeft-=2;
				}
				selected.clear();
				
				//exchanging info between game logic and UI
				for(int i =0; i<CardList.size();i++) {
					if(CardList.get(i)==null) {
						imList.set(i, null);
					}
				}
				//check to see if the game is over
				if(CardsLeft==0) {
					Prompt.setText(GameBoard.CheckWinner()+" WINS!");
				}
				//handles passing the move on to the next player
				else {
					if(!PlayOn || !PickedRight) {
						CurrPlayer++;
						if(CurrPlayer==GameBoard.NumPlayers()) {
							CurrPlayer = 0;
						}
					}
					//prompting the current player to take their turn
					CurrPlayerString = GameBoard.GetPlayerName(CurrPlayer);
					Prompt.setText(CurrPlayerString+" will now play");
				}
			}
		}
		
	}
	//unused feature as of now
	public void mouseUp(MouseEvent e){}
}	


/**
 * Method that handles repaints of the canvas
 * 
 */
class CanvasPaintListener implements PaintListener 
{
	//initializing fields that will be required when painting the canvas
    Shell shell;
    List<Image> imList;
    ArrayList<Card> CardList;
    Image BlankImg;
    ArrayList<Integer> selected;
    Device display;
    
    //passing useful values to the CanvasPaintListener class
    public CanvasPaintListener(Shell sh, List<Image> im, Image blank, ArrayList<Card> cd,
    		Device dsply)
    {
    	shell = sh; imList = im; BlankImg = blank; CardList = cd; display=dsply;
    }
    
    //Method that paints all the cards according to their state in the game.
	public void paintControl(PaintEvent event) 
	{
		//Some UI math
		Rectangle rect = shell.getClientArea();
		ImageData data = new Image(display, "blank.jpg").getImageData();
		int stride = rect.width/data.width;
		
		//loop that handles the cards that are out of the game already
		// and cards that are face up or face down
        for (int i = 0; i < imList.size(); i++) {
        	//removed cards
        	if(imList.get(i)==null) {
        		continue;
        	}
        	//face up cards
        	else if (CardList.get(i).IsFaceUp()){
        		event.gc.drawImage(imList.get(i), (i%stride)*data.width, (i/stride)*data.height);
        		continue;
        	}
        	//face down cards
        	else if(!CardList.get(i).IsFaceUp()) {
        		event.gc.drawImage(BlankImg, (i%stride)*data.width, (i/stride)*data.height);
        	}
        }
	}
}  

/*Code that checks if the quit button has been clicked
 * 
 * exits the program if yes.
 */
class ButtonSelectionListener implements SelectionListener 
{
	public void widgetSelected(SelectionEvent event) {System.exit(0);}
	public void widgetDefaultSelected(SelectionEvent event){
		
	}    
}
