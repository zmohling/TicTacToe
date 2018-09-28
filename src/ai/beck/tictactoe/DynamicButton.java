package ai.beck.tictactoe;

import java.awt.*;
import javax.swing.*;

/**
 * The standardized button for tic-tac-toe
 * @author Zachary Mohling
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DynamicButton extends JButton {
	
	private ImageIcon X = new ImageIcon("./Resources/X.png");
	private ImageIcon O = new ImageIcon("./Resources/O.png");

	public DynamicButton()
	{
		DynamicButton btn = this;

		btn.setBackground(Color.WHITE);

	}
	
	/**
	 * Change the button's icon to represent the player's move.
	 * @param turn Integer to indicate which player made the turn.
	 * @return Return true if icon change successful.
	 */
	public boolean changeIcon(int turn)
	{
		if (turn < 0 || turn > 1) { throw new IndexOutOfBoundsException("The turn is not 0 or 1; Out of bounds."); }		
		
		if (turn == 0)												// X's turn
		{
			this.setIcon(X);
			this.setDisabledIcon(X);
		}

		
		if (turn == 1)												// O's turn
		{
			this.setIcon(O);
			this.setDisabledIcon(O);
		}

		
		return true;
	}
	
}
