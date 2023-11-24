package manager;

import java.util.ArrayList;

import camppackage.Camp;
import user.Faculty;
import user.Student;

public class OldCampManager {
    private static ArrayList<Camp> campList;

    //public CampManager(ArrayList<Camp> campList) {
    //    this.campList = campList;
    //}

    public static ArrayList<Camp> getRegisteredCamps(Student student) {
        ArrayList<Camp> regList = new ArrayList<Camp>();
        int i;
        for (i = 0; i != campList.size(); i++) {
            if (campList.get(i).isAttendee(student)) {
                regList.add(campList.get(i));
            }
        }
        return regList;
    }

    public ArrayList<Camp> getAvailableCamps(Faculty faculty) {
        ArrayList<Camp> availList = new ArrayList<Camp>();
        int i;
        for (i = 0; i != campList.size(); i++) {
            if (campList.get(i).getFaculty() == faculty || campList.get(i).getFaculty() == Faculty.NTU) {
                if (campList.get(i).getVisibility()) {
                    availList.add(campList.get(i));
                }
            }
        }
        return campList;
    }

    public static void addCamp(Camp camp) {
        campList.add(camp);
        //Database.update();
    }

    public static void deleteCamp(Camp camp) {
        int i;
        for (i = 0; i != campList.size(); i++) {
            if (campList.get(i).equals(camp)) {
                campList.remove(i);
                //Database.update();
                break;
            }
        }
    }
}
