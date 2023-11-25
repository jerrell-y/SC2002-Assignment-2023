package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import manager.CampManager;

public class ViewRegisteredCamps implements ViewCamps {
    public ArrayList<Integer> displayCamps() {
        int count = 1;
        CampFormatter cf = new CampFormatter();
        ArrayList<Camp> campList = CampDatabase.getInstance().getCamps(); 
        ArrayList<Integer> registeredCamps = new ArrayList<Integer>();
        int i;
        for (i = 0; i != campList.size(); i++) {
            Camp camp = campList.get(i);

            if (CampManager.isAttendee(camp)) {
                System.out.println(count + ". " + cf.formatShort(camp));
                System.out.println("Registered as an attendee \n");
                registeredCamps.add(camp.getCampID());
                count++;
            }

            else if (CampManager.isCommitee(camp)) {
                System.out.println(count + ". " + cf.formatShort(camp));
                System.out.println("Registered as an commitee member \n");
                registeredCamps.add(camp.getCampID());
                count++;
            }
        }
        return registeredCamps;
    }
}
