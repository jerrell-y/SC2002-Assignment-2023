package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import user.User;
import user.UserManager;

public class ViewAvailableCamps implements ViewCamps {

    public ArrayList<Integer> displayCamps() {
        int count = 0;
        CampFormatter cf = new CampFormatter();
        ArrayList<Camp> campList = CampDatabase.getCamps(); 
        ArrayList<Integer> availableCamps = new ArrayList<Integer>();
        User user = UserManager.getUser();
        int i;
        for (i = 0; i != campList.size(); i++) {
            Camp camp = campList.get(i);
            if (camp.getVisibility() && camp.getFaculty() == user.getFaculty()) {
                System.out.println(count + ". " + cf.formatShort(camp) + "\n");
                availableCamps.add(camp.getCampID());
                count++;
            }
        }
        return availableCamps;
    }
}
