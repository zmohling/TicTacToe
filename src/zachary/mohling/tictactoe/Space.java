package zachary.mohling.tictactoe;

import java.awt.*;
import javax.swing.*;

/**
 * The standardized Space of Tic-Tac-Toe. Space stores the appropriate null, X, or O data value and acts as a button for sending action events.
 * @author Zachary Mohling
 * @version 1.5
 */
public class Space extends JButton {
	private static final long serialVersionUID = -6034787174030724679L;
	
	/**
	 * Reference to the image X.
	 */
	public ImageIcon X = new ImageIcon(this.getClass().getClassLoader().getResource("X.png"));
	
	/**
	 * Reference to the image O.
	 */
	public ImageIcon O = new ImageIcon(this.getClass().getClassLoader().getResource("O.png"));
	
	/**
	 * Mark of this Space.
	 */
	private Marks mark;
	
	/**
	 * Creates a Space that will populate the game board's grid.
	 */
	public Space()
	{
		Space space = this;

		/* Set Space's initial values */
		space.setBackground(Color.WHITE);
		space.mark = null;
		space.setIcon(null);
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
		else if (index == 4)		// Center Space
			this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		else
			this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
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
	
	/**
	 * Resize icons to fit the grid of the GameBoard.
	 * @param board GameBoard to format icon dimensions
	 */
	public void scaleIconToGameBoard(GameBoard board)
	{
		/* Reference the images of the marks */
		Image imgX = X.getImage();
		Image imgO = O.getImage();
		
		/* Scale the images to fit within the grid. Required since display resolutions will vary */
		ImageIcon imageIconX = new ImageIcon(imgX.getScaledInstance((int) (board.getHeight() / 4.0 ), -1, java.awt.Image.SCALE_SMOOTH));
		ImageIcon imageIconO = new ImageIcon(imgO.getScaledInstance((int) (board.getHeight() / 4.0 ), -1, java.awt.Image.SCALE_SMOOTH));

		/* Update the images of the marks to our scaled images */
		X = imageIconX;
		O = imageIconO;
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
