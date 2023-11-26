package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import manager.CampManager;

/**
 * A helper function to get and print all camps in the database that the current staff created.
 */
public class ViewCreatedCamps implements ViewCamps {
    /**
     * The instance of this class.
     */
    private static ViewCreatedCamps instance;

    /**
     * Gets the instance of this class. If not created yet, create a new instance.
     * @return this class' instance.
     */
    public static synchronized ViewCreatedCamps getInstance() {
        if (instance == null) {
            instance = new ViewCreatedCamps();
        }
        return instance;
    }

    /**
     * Prints the name of all camps in the current database that the current staff created.
     * @return an list of indexes for the created camps.
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
