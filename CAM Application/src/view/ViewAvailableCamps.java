package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import user.User;
import user.UserManager;

public class ViewAvailableCamps implements ViewCamps {
    public void displayCamps() {
        CampFormatter cf = new CampFormatter();
        ArrayList<Camp> campList = CampDatabase.getCamps();
        User currUser = UserManager.getUser();
        int i;
        for (i = 0; i != campList.size(); i++) {
            Camp camp = campList.get(i);
            if (camp.getVisibility() && camp.getFaculty() == currUser.getFaculty()) {
                System.out.println(cf.formatFull(camp) + "\n");
            }
        }
    }
}
