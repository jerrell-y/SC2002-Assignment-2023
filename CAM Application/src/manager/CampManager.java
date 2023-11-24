package manager;

import java.util.ArrayList;

import camppackage.Camp;

public class CampManager {
    private static Camp camp;

    public static void setCamp(Camp camp) {
        CampManager.camp = camp;
    }
    
    public boolean isAttendee(String userID) {
        int i;
        ArrayList<String> campAttendees = camp.getCampAttendees();
        for (i = 0; i != campAttendees.size(); i++) {
            if (campAttendees.get(i).equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCommitee(String userID) {
        int i;
        ArrayList<String> campCommitees = camp.getCampCommitees();
        for (i = 0; i != campCommitees.size(); i++) {
            if (campCommitees.get(i).equals(userID)) {
                return true;
            }
        }
        return false;
    }
}
