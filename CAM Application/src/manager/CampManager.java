package manager;

import java.util.ArrayList;
import java.util.Date;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import user.User;
import user.UserManager;


/*
 * The manager that handles the methods regarding camps.
 */
public class CampManager {
    private static Camp camp;

    public static void setCamp(int campID) {
        CampManager.camp = CampDatabase.getInstance().getCampByID(campID);
    }

    public static Camp getCamp() {
        return camp;
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

    public static boolean isCommittee() {
        return isCommittee(CampManager.camp);
    }

    public static boolean isCommittee(Camp camp) {
        int i;
        User user = UserManager.getUser();
        ArrayList<String> campCommittees = camp.getCampCommittees();
        for (i = 0; i != campCommittees.size(); i++) {
            if (campCommittees.get(i).equals(user.getUserID())) {
                return true;
            }
        }
        return false;
    }
    public static boolean isInCharge(Camp camp) {
        User user = UserManager.getUser();
        String st = camp.getStaffInCharge();
        if(st.equals(user.getUserID())){
            return true;
        }
        return false;
    }
    public static void generateStudentReport(){
        CampFormatter cf= CampFormatter.getInstance();
        cf.formatStudentReport(camp);
    }
    public static void generateCommitteeReport(){
        CampFormatter cf= CampFormatter.getInstance();
        cf.formatCommitteeReport(camp);
    }
    public static void printDetails() {
        CampFormatter cf = CampFormatter.getInstance();
        System.out.println(cf.formatFull(camp));
    }

    public static boolean registerAttendee() {
        User user = UserManager.getUser();
        ArrayList<String> blacklist = camp.getBlacklist();
        int i;
        for (i = 0; i != blacklist.size(); i++) {
            if (user.getUserID().equals(blacklist.get(i))) {
                System.out.println("You cannot join a camp that you withdraw from! \n");
                return false;
            }
        }

        if (camp.getCampAttendeeSlots() == 0) {
            System.out.println("There are no more slots available!\n");
            return false;
        }

        if (camp.getRegEndDate().compareTo(new Date()) < 0) {
            System.out.println("The registration dateline has passed and you cannot register anymore! \n");
            return false;
        }

        camp.addCampAttendee(user.getUserID());
        CampDatabase.getInstance().update();
        return true;
    }

    public static boolean registerCommittee() {
        User user = UserManager.getUser();
        ArrayList<Camp> campList = CampDatabase.getInstance().getCamps();
        Camp currCamp;
        int i;

        ArrayList<String> blacklist = camp.getBlacklist();
        for (i = 0; i != blacklist.size(); i++) {
            if (user.getUserID().equals(blacklist.get(i))) {
                System.out.println("You cannot join a camp that you withdraw from! \n");
                return false;
            }
        }

        for (i = 0; i != campList.size(); i++) {
            currCamp = campList.get(i);
            if (isCommittee(currCamp)) {
                System.out.println("You are already a committee member in another camp!");
                return false;
            }
        }
        if (camp.getCampCommitteeSlots() == 0) {
            System.out.println("There are no more slots available!\n");
            return false;
        }

        if (camp.getRegEndDate().compareTo(new Date()) < 0) {
            System.out.println("The registration dateline has passed and you cannot register anymore! \n");
            return false;
        }

        camp.addCampCommittee(user.getUserID());
        CampDatabase.getInstance().update();
        return true;
    }

    public static void withdraw() {
        User user = UserManager.getUser();
        int i;
        ArrayList<String> campAttendees = camp.getCampAttendees();
        for (i = 0; i != campAttendees.size(); i++) {
            if (campAttendees.get(i).equals(user.getUserID())) {
                camp.removeCampAttendee(i);
                camp.addBlacklist(user.getUserID());
                CampDatabase.getInstance().update();
                break;
            }
        }
    }
}
