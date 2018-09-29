package zachary.mohling.tictactoe;

import java.awt.event.*;

/**
 * The game of Tic-Tac-Toe.
 * @author Zachary Mohling
 * @version 1.5
 */
public class TicTacToe implements ActionListener {	
	
	/**
	 * Tic-Tac-Toe's Game Board.
	 */
	public GameBoard gameBoard;
	
	/**
	 * This <code>Marks</code> variable tracks the turn of the players.
	 */
	public Marks player = Marks.X;
	
	/**
	 * Array of nine spaces, blocks of a grid.
	 */
	private static Space[] spaces = new Space[Constants.ROWS * Constants.COLS];
	
	/**
	 * Counter for marks on the game board.
	 */
	private int markCounter = 0;

	public static void main(String[] args) 
	{
		new TicTacToe();
	}
	
	/**
	 * Creates the classic game of Tic-Tac-Toe.
	 */
	public TicTacToe()
	{
		gameBoard = new GameBoard();
		instantiateGameBoard(gameBoard);
	}

	/**
	 * Update game state after user initiated a mark.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/* Get the source of the event. (i.e. Get the space which was marked) */
		Space space = (Space) e.getSource();
		
		/* Mark the Space */
		space.setMark(player);
		
		/* Check the grid for a winner or end the game in a draw if nine or more moves had been made */
		if (checkWin(player))
		{
			endGame("Player " + player.toString() + " wins!");

			return;
		}
		else if (markCounter >= 9)
		{
			endGame("Draw!");
			
			return;
		}		
		
		nextTurn();
	}
	
	/**
	 * Instantiate the game board by populating the grid with empty Spaces.
	 * @param board The game board
	 */
	private void instantiateGameBoard(GameBoard board)
	{
		/* Populate spaces[] array with Spaces and add them to the grid */
		for (int i = 0; i < (Constants.ROWS * Constants.COLS); i++)
		{
			spaces[i] = new Space();
			spaces[i].updateStyling(i);		// Change border properties of the Space to construct the classic 3x3 grid
			spaces[i].scaleIconToGameBoard(board);
			
			spaces[i].addActionListener(this);		// The Space class extends JButton. When pressed, our action listener will call actionPerformed()  
			
			board.grid.add(spaces[i]);
		}
		
		board.isInstantiated = true;		// Grid is now instantiated
		board.update();		// Update the GameBoard the represent the changes
	}
	
	/**
	 * Transition to next player's turn.
	 */
	private void nextTurn()
	{
		player = player.next();
		gameBoard.setLabel("It is player " + player.toString() + "'s turn to play.");
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
		/* Disable all Spaces */
		for (Space s : spaces)
			s.setEnabled(false);
		
		String restartGameString = "Restart game to play again.";
		
		/* Display end-game text */
		gameBoard.setLabel(string + " " + restartGameString);
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
