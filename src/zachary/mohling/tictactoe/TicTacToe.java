package zachary.mohling.tictactoe;

import java.awt.event.*;

/**
 * The game of Tic-Tac-Toe.
 * @author Zachary Mohling
 * @version 1.4
 */
public class TicTacToe implements ActionListener {	
	
	public GameBoard board;															// The Game Board
	public Marks player = Marks.X;													// This <code>Marks<code> variable tracks the turn of the players
	
	private Space[] spaces = new Space[Constants.ROWS * Constants.COLS];			// Array of nine spaces, blocks of a grid
	private int markCounter = 0;													// Counter for marks on the game board

	public static void main(String[] args) 
	{
		new TicTacToe();
	}
	
	/**
	 * Creates the classic game of Tic-Tac-Toe.
	 */
	public TicTacToe()
	{
		board = new GameBoard();
		instantiateGameBoard(board);
	}

	/**
	 * Update game state after user initiated a mark.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Space space = (Space) e.getSource();										// Get the Space that was marked
		space.setMark(player);														// Mark the space
		
		if (checkWin(player))														// Check to see if player made a winning mark
		{
			endGame("Player " + player.toString() + " wins!");

			return;
		}
		else if (markCounter >= 9)													// If 9 or more moves have occurred without a win, the game will result in a draw
		{
			endGame("Draw!");
			
			return;
		}		
		
		nextTurn();
	}
	
	/**
	 * Instantiate the game board by populating the board with empty Spaces.
	 * @param board The game board
	 */
	private void instantiateGameBoard(GameBoard board)
	{
		for (int i = 0; i < (Constants.ROWS * Constants.COLS); i++)					// Populate array with Spaces and add them to the GridLayout
		{
			spaces[i] = new Space();
			spaces[i].updateStyling(i);												// Change border properties of the Space to construct the classic 3x3 grid
			
			spaces[i].addActionListener(this);										// The Space class extends JButton. When pressed, our action listener will call actionPerformed()  
			
			board.gameBoard.add(spaces[i]);
		}
		
		board.update();																// Update the board the represent the changes
	}
	
	/**
	 * Transition to next player's turn.
	 */
	private void nextTurn()
	{
		player = player.next();
		board.setLabel("It is player " + player.toString() + "'s turn to play.");
	}
	
	/**
	 * Check if most recent mark wins the game.
	 * @param player Player to have marked
	 * @return true if player wins the game
	 */
	private boolean checkWin(Marks player)
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
	
	/**
	 * End the game.
	 * @param string Text to display the outcome of the game
	 */
	private void endGame(String string)
	{
		for (Space s : spaces)														// Disable all Spaces
			s.setEnabled(false);
		
		String restartGameString = "Restart game to play again.";
		
		board.setLabel(string + " " + restartGameString);							// Display end-game text
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
