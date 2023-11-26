package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import manager.CampManager;

/**
 * A helper function to get and print all camps in the database that the student registered for.
 */
public class ViewRegisteredCamps implements ViewCamps {
    /**
     * The instance of this class.
     */
    private static ViewRegisteredCamps instance;

    /**
     * Gets the instance of this class. If not created yet, create a new instance.
     * @return this class' instance.
     */
    public static synchronized ViewRegisteredCamps getInstance() {
        if (instance == null) {
            instance = new ViewRegisteredCamps();
        }
        return instance;
    }

    /**
     * Prints the name of all camps in the database that the student registered for.
     * @return an list of indexes for the registered camps.
     */
    public ArrayList<Integer> displayCamps() {
        int count = 1;
        CampFormatter cf = CampFormatter.getInstance();
        ArrayList<Camp> campList = CampDatabase.getInstance().getCamps(); 
        ArrayList<Integer> registeredCamps = new ArrayList<Integer>();
        int i;
        for (i = 0; i != campList.size(); i++) {
            Camp camp = campList.get(i);

            if (CampManager.isAttendee(camp)) {
                System.out.println(count + ". " + cf.formatShort(camp));
                System.out.println("Registered as an attendee. \n");
                registeredCamps.add(camp.getCampID());
                count++;
            }

            else if (CampManager.isCommittee(camp)) {
                System.out.println(count + ". " + cf.formatShort(camp));
                System.out.println("Registered as an committee member. \n");
                registeredCamps.add(camp.getCampID());
                count++;
            }
        }
        return registeredCamps;
    }
}
