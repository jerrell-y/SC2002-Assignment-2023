package view;

import java.util.ArrayList;

/**
 * A helper function to get and print a specific list of camps in the database.
 */
public interface ViewCamps{
	/**
	 * Prints the specific list of camps in the database.
	 * @return a list of indexes for the specific camps.
	 */
	public ArrayList<Integer> displayCamps();
}
