package view;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;

public class ViewAllCamps implements ViewCamps {
	public ArrayList<Integer> displayCamps() {
        CampFormatter cf = CampFormatter.getInstance();
        ArrayList<Camp> campList = CampDatabase.getInstance().getCamps();
        ArrayList<Integer> allCamps = new ArrayList<Integer>();
        int i, count = 1;
        for (i = 0; i != campList.size(); i++) {
            Camp camp = campList.get(i);
            System.out.println(count + ". " + cf.formatShort(camp));
            allCamps.add(camp.getCampID());
            count++;
        }   
        if (count == 1) {
            System.out.println("There are currently no camps available.");
        }
        
        return allCamps;
    }
}
