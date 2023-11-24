package manager;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import user.User;
import user.UserManager;

public class CampManager {
    private static Camp camp;

    public static void setCamp(int campID) {
        CampManager.camp = CampDatabase.getCampsByID(campID);
    }
    
    public static boolean isAttendee() {
        return isAttendee(CampManager.camp);
    }

    public static boolean isAttendee(Camp camp) {
        int i;
        User user = UserManager.getUser();
        ArrayList<String> campAttendees = camp.getCampAttendees();
        for (i = 0; i != campAttendees.size(); i++) {
            if (campAttendees.get(i).equals(user.getUserID())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCommitee() {
        return isCommitee(CampManager.camp);
    }

    public static boolean isCommitee(Camp camp) {
        int i;
        User user = UserManager.getUser();
        ArrayList<String> campCommitees = camp.getCampCommitees();
        for (i = 0; i != campCommitees.size(); i++) {
            if (campCommitees.get(i).equals(user.getUserID())) {
                return true;
            }
        }
        return false;
    }

    public static void printDetails() {
        CampFormatter cf = new CampFormatter();
        System.out.println(cf.formatFull(camp));
    }

    public static boolean registerAttendee() {
        User user = UserManager.getUser();
        if (camp.getCampAttendeeSlots() <= camp.getCampAttendees().size()) {
            return false;
        }
        camp.addCampAttendee(user.getUserID());
        return true;
    }

    public static boolean registerCommitee() {
        User user = UserManager.getUser();
        if (camp.getCampCommiteeSlots() <= camp.getCampCommitees().size()) {
            return false;
        }
        camp.addCampCommitee(user.getUserID());
        return true;
    }
}
