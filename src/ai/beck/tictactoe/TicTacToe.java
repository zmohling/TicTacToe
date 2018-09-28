package ai.beck.tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The game of Tic-Tac-Toe.
 * @author Zachary Mohling
 * @version 1.1
 */
@SuppressWarnings("serial")
public class TicTacToe extends JFrame implements ActionListener {
	
	private static int ROWS = 3; private static int COLS = 3;
	
	private JPanel gameBoard = new JPanel();
	private JLabel label = new JLabel();
	
	private Space[] spaces = new Space[ROWS * COLS];								// Array of nine spaces, blocks of a grid
	
	/* A Mark can be a player or a physical mark. For example, "Player X" and "the player which marked an 'X' on the game board" refer to the same player */
	public static enum Marks {
		X, O;
		
		private static Marks[] vals = values();
		public Marks next()
		{
			return vals[(ordinal() + 1) % vals.length];
		}
	}
	
	public Marks player = Marks.X;													// This Marks variable tracks the turn of the players

	public static void main(String[] args) 
	{
		new TicTacToe();
	}
	
	
	public TicTacToe()
	{
		super();
		
		this.setTitle("Tic-Tac-Toe");
		
		this.setSize(600, 625);														// 600 by 600 game board + 25 for southern JLabel
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		gameBoard.setLayout(new GridLayout(ROWS, COLS));
		
		for (int i = 0; i < (ROWS * COLS); i++)										// Populate array with spaces and add them to the GridLayout
		{
			spaces[i] = new Space();
			
			spaces[i].addActionListener(this);										// The Space class extends JButton. When pressed, our action listener will call actionPerformed()  
			
			gameBoard.add(spaces[i]);
		}
		
		this.add(gameBoard);														// Add gameBoard to the JFrame
		
		label.setText("It is player X's turn to play.");
		this.add(label, BorderLayout.SOUTH);										// Add label to the JFrame
				
		this.setVisible(true);														// Set Frame Visible
		
	}

	/**
	 * The necessary steps after each mark
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Space space = (Space) e.getSource();										// Get the space that was marked
		
		space.setEnabled(false);													// Disable space so that it is considered an ineligible space
		space.changeIcon(player);													// Change the space's icon to the appropriate mark
		space.markSpace(player);													// Change the <code>mark<code> data field of the space

		
		if (checkWin(player))
		{
			label.setText("Player " + player.toString() + " wins!");
			
			for (Space s : spaces)
				s.setEnabled(false);
			
			return;
		}
		
		
		player = player.next();														// Next turn
		label.setText("It is player " + player.toString() + "'s turn to play.");
	}
	
	/**
	 * Check if most recent mark wins the game
	 * @param player Player to have marked
	 * @return Returns true if player wins the game
	 */
	public boolean checkWin(Marks player)
	{
		/* Check horizontal win */
		
		for (int i = 0; i < spaces.length; i += 3)
			if (spaces[i].getMark() == player && spaces[i + 1].getMark() == player && spaces[i + 2].getMark() == player)
				return true;
		
		/* Check vertical win */
		for (int i = 0, j = 3, k = 6; k < spaces.length;)
		{
			if (spaces[i].getMark() == player && spaces[j].getMark() == player && spaces[k].getMark() == player)
				return true;
			
			i++; j++; k++;
		}
		
		/* Check diagonal win */
		if (spaces[0].getMark() == player && spaces[4].getMark() == player && spaces[8].getMark() == player)
			return true;
		if (spaces[2].getMark() == player && spaces[4].getMark() == player && spaces[6].getMark() == player)
			return true;
		
		
		return false;
	}
	
	@Override
	public String toString()
	{
		String result = "";
		
		result += "[";
		
		for (int i = 0; i < spaces.length; i++)
		{
			result += spaces[i].toString();
			
			if (i < (spaces.length - 1))
				result += ", ";
		}
		
		result += "]";
		
		return result;
	}
}
