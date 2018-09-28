package zachary.mohling.tictactoe;

import java.awt.*;
import javax.swing.*;

/**
 * The Tic-Tac-Toe game board.
 * @author Zachary Mohling
 * @version 1.4
 */
public class GameBoard extends JFrame {
	private static final long serialVersionUID = 6535414081209674855L;
	
	public JPanel gameBoard = new JPanel();
	public JLabel dialogLabel = new JLabel();
	
	/**
	 * Creates a Tic-Tac-Toe game board with predetermined dimensions and properties.
	 */
	public GameBoard()
	{
		this.setTitle("Tic-Tac-Toe");												// Set window's title
		
		ImageIcon icon = new ImageIcon(
				this.getClass().getClassLoader().getResource("icon.png")			// Set window's icon
				);
		this.setIconImage(icon.getImage());
		
		
		this.setSize(600, 625);														// 600 by 600 game board + 25 for southern JLabel
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		gameBoard.setLayout(new GridLayout(Constants.ROWS, Constants.COLS));
		this.add(gameBoard);														// Add gameBoard to the JFrame
		
		dialogLabel.setText("It is player X's turn to play.");
		dialogLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(dialogLabel, BorderLayout.SOUTH);									// Add label to the JFrame
				
		this.setVisible(true);														// Set Frame Visible
	}
	
	/**
	 * Set the text of the dialog label.
	 * @param s string of text to display in dialog label
	 */
	public void setLabel(String newText)
	{
		dialogLabel.setText(newText);
	}
	
	/**
	 * Display visual updates by setting all components to visible.
	 */
	public void update()
	{
		this.setVisible(true);
	}

}
