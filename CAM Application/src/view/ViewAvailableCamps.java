package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import manager.CampManager;
import user.Faculty;
import user.User;
import user.UserManager;

/*
 * function to view all the available camps to the student and display it in the UI
 */

public class ViewAvailableCamps implements ViewCamps {
    private static ViewAvailableCamps instance;

    public static synchronized ViewAvailableCamps getInstance() {
        if (instance == null) {
            instance = new ViewAvailableCamps();
        }
        return instance;
    }

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
