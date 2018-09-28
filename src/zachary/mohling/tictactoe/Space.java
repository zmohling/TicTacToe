package zachary.mohling.tictactoe;

import java.awt.*;
import javax.swing.*;

/**
 * The standardized space of Tic-Tac-Toe. Space stores the appropriate null, X, or O data value and acts as a button for sending action events.
 * @author Zachary Mohling
 * @version 1.4
 */
@SuppressWarnings("serial")
public class Space extends JButton {
	
	private ImageIcon X = new ImageIcon(this.getClass().getClassLoader().getResource("X.png"));
	private ImageIcon O = new ImageIcon(this.getClass().getClassLoader().getResource("O.png"));
	private Marks mark;																				// Mark in this Space
	
	/**
	 * Creates a Space that will populate the game board's grid.
	 */
	public Space()
	{
		Space space = this;

		space.setBackground(Color.WHITE);															// Change background color of Space to white
		space.mark = null;																			// Initialize mark to null
	}
	
	/**
	 * Set the border of the Space to construct the classic 3x3 Tic-Tac-Toe grid.
	 * @param index index of the Space in its array determines its styling
	 */
	public void updateStyling(int index)
	{
		if (index == 1 || index == 7)
			this.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, Color.BLACK));
		else if (index == 3 || index == 5)
			this.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.BLACK));
		else if (index == 4)																		// Center Space
			this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		else
			this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE));
	}

	/**
	 * Set the Space's mark.
	 * @param mark mark that was made
	 */
	public void setMark(Marks mark)
	{
		this.mark = mark;
		
		changeIcon(this.mark);
		
		this.setEnabled(false);
	}
	
	/**
	 * Get the Space's mark.
	 * @return the Space's mark
	 */
	public Marks getMark()
	{
		return mark;
	}
	
	/**
	 * Change the Space's icon to represent the player's mark.
	 * @param mark mark to be displayed in the space
	 */
	private void changeIcon(Marks mark)
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
