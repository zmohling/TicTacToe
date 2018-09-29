package zachary.mohling.tictactoe;
/**
 * A Mark can be a player or a physical mark. For example, "Player X" and "the player which marked an 'X' on the game board" refer to the same player
 * @author Zachary Mohling
 * @version 1.5
 */
public enum Marks {
	X, O;
	
	private static Marks[] vals = values();
	
	/**
	 * Return the next mark in the enumeration.
	 * @return next mark in the enumeration
	 */
	public Marks next()
	{
		return vals[(ordinal() + 1) % vals.length];
	}
}
