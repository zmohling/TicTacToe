package ai.beck.tictactoe;

import java.awt.*;
import javax.swing.*;
import ai.beck.tictactoe.TicTacToe.Marks;

/**
 * The standardized space of tic-tac-toe. Space stores the appropriate null, X, or O data value and acts as a button for sending action events.
 * @author Zachary Mohling
 * @version 1.1
 */
@SuppressWarnings("serial")
public class Space extends JButton {
	
	private ImageIcon X = new ImageIcon("./Resources/X.png");
	private ImageIcon O = new ImageIcon("./Resources/O.png");
	private Marks mark;																// Mark in this space
	
	public Space()
	{
		Space space = this;

		space.setBackground(Color.WHITE);											// Change background color of space to white
		space.mark = null;															// Initialize mark to null

	}
		
	/**
	 * Change the space's icon to represent the player's mark.
	 * @param mark Mark to be displayed in the space.
	 */
	public void changeIcon(Marks mark)
	{		
		if (mark == Marks.X)
		{
			this.setIcon(X);
			this.setDisabledIcon(X);
		}
		else if (mark == Marks.O)
		{
			this.setIcon(O);
			this.setDisabledIcon(O);
		}
	}
	
	/**
	 * Set the space's mark
	 * @param mark
	 */
	public void markSpace(Marks mark)
	{
		this.mark = mark;
	}
	
	/**
	 * Get the space's mark
	 * @return
	 */
	public Marks getMark()
	{
		return mark;
	}
	
	@Override
	public String toString()
	{
		String result = "null";
		
		if (this.mark == Marks.X)
			result = "X";
		if (this.mark == Marks.O)
			result = "O";
		
		return result;
	}
	
}
