package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import manager.CampManager;
import user.Faculty;
import user.User;
import user.UserManager;

/**
 * A helper function to get and print all available camps to the current user in the database.
 */
public class ViewAvailableCamps implements ViewCamps {

    /**
     * The instance of this class.
     */
    private static ViewAvailableCamps instance;

    /**
     * Gets the instance of this class. If not created yet, create a new instance.
     * @return this class' instance.
     */
    public static synchronized ViewAvailableCamps getInstance() {
        if (instance == null) {
            instance = new ViewAvailableCamps();
        }
        return instance;
    }

    /**
     * Prints the name of all available camps in the current database for the current student.
     * @return an list of indexes for the available camps.
     */
    public ArrayList<Integer> displayCamps() {
        CampFormatter cf = CampFormatter.getInstance();
        ArrayList<Camp> campList = CampDatabase.getInstance().getCamps();
        ArrayList<Integer> availableCamps = new ArrayList<Integer>();
        User user = UserManager.getUser();
        int i, count = 1;
        for (i = 0; i != campList.size(); i++) {
            Camp camp = campList.get(i);
            if (camp.getVisibility() && !CampManager.isAttendee(camp) && !CampManager.isCommittee(camp)) {
                if (camp.getFaculty() == Faculty.NTU || camp.getFaculty() == user.getFaculty()) {
                    System.out.println(count + ". " + cf.formatShort(camp));
                    availableCamps.add(camp.getCampID());
                    count++;
                }
            }
        }
        return availableCamps;
    }
}
