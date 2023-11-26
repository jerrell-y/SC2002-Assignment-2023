package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;

/**
 * A helper function to get and print all camps in the database.
 */
public class ViewAllCamps implements ViewCamps {
    /**
     * The instance of this class.
     */
    private static ViewAllCamps instance;

    /**
     * Gets the instance of this class. If not created yet, create a new instance.
     * @return this class' instance.
     */
    public static synchronized ViewAllCamps getInstance() {
        if (instance == null) {
            instance = new ViewAllCamps();
        }
        return instance;
    }

    /**
     * Prints the name of all camps in the current database.
     * @return an list of indexes for the camps in the database.
     */
	public ArrayList<Integer> displayCamps() {
        CampFormatter cf = CampFormatter.getInstance();
        ArrayList<Camp> campList = CampDatabase.getInstance().getCamps();
        ArrayList<Integer> allCamps = new ArrayList<Integer>();
        int i, count = 1;
        for (i = 0; i != campList.size(); i++) {
            Camp camp = campList.get(i);
            System.out.println(count + ". " + cf.formatShort(camp));
            allCamps.add(camp.getCampID());
            count++;
        }   
        if (count == 1) {
            System.out.println("There are currently no camps available.");
        }
        
        return allCamps;
    }
}
