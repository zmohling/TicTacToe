package ai.beck.tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The game of Tic-Tac-Toe.
 * @author Zachary Mohling
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TicTacToe extends JFrame implements ActionListener {
	
	private static int ROWS = 3; private static int COLS = 3;
	public int turn = 0;															// Turn 0 == Player X's turn, Turn 1 == Player O's turn
	
	private JPanel gameBoard = new JPanel();
	public JLabel label = new JLabel();
	
	private DynamicButton buttons[] = new DynamicButton[ROWS * COLS];
	

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
		
		for (int i = 0; i < (ROWS * COLS); i++)										// Fill array with buttons and add them to the grid
		{
			buttons[i] = new DynamicButton();
			
			buttons[i].addActionListener(this);										// Listen for button presses
			
			gameBoard.add(buttons[i]);
		}
		
		this.add(gameBoard);														// Add gameBoard to the JFrame
		
		label.setText("It is player X's turn to play.");
		this.add(label, BorderLayout.SOUTH);										// Add label to the JFrame
				
		this.setVisible(true);														// Set Frame Visible
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		DynamicButton btn = (DynamicButton) e.getSource();							// Get button that triggered action event
		
		btn.setEnabled(false);														// Disable button so that it is considered an ineligible space
		
		switch(turn)
		{
		case 0: btn.changeIcon(turn);
		
				turn = 1;
				label.setText("It is player O's turn to play.");
				
				break;
				
		case 1: btn.changeIcon(turn);
				
				turn = 0;
				label.setText("It is player X's turn to play.");
				
				break;
		}
	}	
}
