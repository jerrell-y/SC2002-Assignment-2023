package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import manager.CampManager;
import user.Staff.*;

public class ViewCreatedCamps implements ViewCamps {
    public ArrayList<Integer> displayCamps() {
        int count = 1;
        CampFormatter cf = CampFormatter.getInstance();
        ArrayList<Integer> CreatedCamps = new ArrayList<Integer>();
        ArrayList<Camp> campList = CampDatabase.getInstance().getCamps();
        int i;
        for (i = 0; i != campList.size(); i++) {
            Camp camp = campList.get(i);

            if (CampManager.isInCharge(camp)) {
                System.out.println( cf.formatShort(camp));
                campList.add(camp);
                count++;
            }
        }
        return CreatedCamps;
    }
}
