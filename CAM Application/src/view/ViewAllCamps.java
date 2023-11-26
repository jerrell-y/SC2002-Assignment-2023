package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;

/**
 * function to view all the camps and display it in the UI
 */

public class ViewAllCamps implements ViewCamps {
    /**
     * variable to store the object of ViewAllCamps
     */
    private static ViewAllCamps instance;

    /**
     * method to veiw all the camps
     * @return ViewAllCamps object
     * 
     */
    public static synchronized ViewAllCamps getInstance() {
        if (instance == null) {
            instance = new ViewAllCamps();
        }
        return instance;
    }

    /**
     * displays all the camps
     * @return an ArrarList<Integer> of all the indexes of camps being looked at, to be used later 
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
