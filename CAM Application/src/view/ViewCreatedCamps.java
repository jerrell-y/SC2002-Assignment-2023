package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import manager.CampManager;

/*
 ** function to view all the created camps and display it in the UI
 */

public class ViewCreatedCamps implements ViewCamps {
    /**
     * variable to store the object of ViewCreatedCamps
     */
    private static ViewCreatedCamps instance;

    /**
     * method to veiw all the created camps
     * @return ViewCreatedCamps object
     * 
     */
    public static synchronized ViewCreatedCamps getInstance() {
        if (instance == null) {
            instance = new ViewCreatedCamps();
        }
        return instance;
    }

    /**
     * displays all the created camps
     * @return an ArrarList<Integer> of all the indexes of created camps being looked at, to be used later 
     */
    public ArrayList<Integer> displayCamps() {
        int count = 1;
        CampFormatter cf = CampFormatter.getInstance();
        ArrayList<Integer> createdCamps = new ArrayList<Integer>();
        ArrayList<Camp> campList = CampDatabase.getInstance().getCamps();
        int i;
        for (i = 0; i <campList.size(); i++) {
            Camp camp = campList.get(i);

            if (CampManager.isInCharge(camp)) {
                System.out.println(count + ". " + cf.formatShort(camp));
                createdCamps.add(camp.getCampID());
                count++;
            }
        }
        return createdCamps;
    }
}
