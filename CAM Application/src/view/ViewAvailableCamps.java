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
 * function to view all the available camps to the student and display it in the UI
 */

public class ViewAvailableCamps implements ViewCamps {

    /**
     * variable to store the object of ViewAvailableCamps
     */
    private static ViewAvailableCamps instance;

    /**
     * method to veiw all the available camps
     * @return ViewAvailableCamps object
     * 
     */
    public static synchronized ViewAvailableCamps getInstance() {
        if (instance == null) {
            instance = new ViewAvailableCamps();
        }
        return instance;
    }

    /**
     * displays all the available camps
     * @return an ArrarList<Integer> of all the indexes of available camps being looked at, to be used later 
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
