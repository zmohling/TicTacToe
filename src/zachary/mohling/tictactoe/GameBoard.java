package zachary.mohling.tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The Tic-Tac-Toe game board.
 * @author Zachary Mohling
 * @version 1.5
 */
public class GameBoard extends JFrame {
	private static final long serialVersionUID = 6535414081209674855L;
	
	/**
	 * The grid of the GameBoard.
	 */
	public JPanel grid = new JPanel();
	
	/**
	 * The dialog label of the GameBoard.
	 */
	public JLabel dialogLabel = new JLabel();
	
	/**
	 * The grid is populated with Spaces.
	 */
	public boolean isInstantiated = false;
	
	/**
	 * Creates a Tic-Tac-Toe game board with predetermined dimensions and properties.
	 */
	public GameBoard()
	{
		/* Set GameBoard's title and icon */
		this.setTitle("Tic-Tac-Toe");
		ImageIcon icon = new ImageIcon(
				this.getClass().getClassLoader().getResource("icon.png")
				);
		this.setIconImage(icon.getImage());
		
		
		/* Manage initial GameBoard dimensions */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(screenSize.height * 0.95), (int)(screenSize.height * 0.95));		// Set GameBoard size to a square with lengths as long as screen height * 0.95
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		/* Set GameBoard's layout manager to GridLayout() */
		grid.setLayout(new GridLayout(Constants.ROWS, Constants.COLS));
		this.add(grid);
		
		
		/* Set Dialog Label's initial values */
		dialogLabel.setText("It is player X's turn to play.");
		dialogLabel.setHorizontalAlignment(SwingConstants.CENTER);		// Center text in middle of dialogLabel
		this.add(dialogLabel, BorderLayout.SOUTH);
		
		
		Toolkit.getDefaultToolkit().setDynamicLayout(false);		// Disable dynamic layout
		
		
		/* Add a component listener that'll run the componentResized() method every time the JFrame's dimensions change. */
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e)
			{
				Rectangle window = e.getComponent().getBounds();
				e.getComponent().setBounds(window.x, window.y, window.width, window.width);		// Maintain aspect ratio of 1x1
				
				/* If grid has been instantiated prior to GameBoard's resize, reflect those changes onto the components within grid */
				if (isInstantiated)
				{
					Space[] Spaces = new Space[9];
					
					for (int i = 0; i < Spaces.length; i++)
					{
						Spaces[i] = (Space) grid.getComponent(i);		// Get array of Spaces from components within grid
						Spaces[i].scaleIconToGameBoard((GameBoard) e.getComponent());		// Scale the marks
						
						ImageIcon imageIcon;
						
						if(Spaces[i].getMark() == Marks.X)
							imageIcon = Spaces[i].X;
						else if(Spaces[i].getMark() == Marks.O)
							imageIcon = Spaces[i].O;
						else
							continue;
						
						Spaces[i].setIcon(imageIcon);		// Set the scaled marks in the Space
						Spaces[i].setDisabledIcon(imageIcon);

					}
				}
				
				/* Repaint the two components in GameBoard to reflect changes */
				grid.repaint();
				dialogLabel.repaint();
			}
		});
				
		this.setVisible(true);		// Set Frame Visible
	}
	
	/**
	 * Set the text of the dialog label.
	 * @param newText string of text to display in dialog label
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
