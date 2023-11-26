package manager;

import java.util.ArrayList;
import java.util.Date;

import camppackage.Camp;
import database.CampDatabase;
import database.UserDatabase;
import format.CampFormatter;
import format.UserFormatter;
import user.User;
import user.UserManager;


/**
 * Handles the logic regarding the current particular camp. Only one camp can be active at any time. Holds the reference to the current camp object.
 */
public class CampManager {
    /**
     * The current camp selected.
     */
    private static Camp camp;

    /**
     * Sets the new camp to be selected. Calls the database for the camp object given the unique campID.
     * @param campID The unique ID of the camp.
     */
    public static void setCamp(int campID) {
        CampManager.camp = CampDatabase.getInstance().getCampByID(campID);
    }

    /**
     * Gets the current camp selected.
     * @return the current camp object.
     */
    public static Camp getCamp() {
        return camp;
    }
    
    /**
     * Checks if the current user is an attendee of the current camp.
     * @return true if attendee, false if not.
     */
    public static boolean isAttendee() {
        return isAttendee(CampManager.camp);
    }

    /**
     * Check if the current user is an attendee of the specific camp.
     * @param camp The specific camp object.
     * @return true if attendee, false if not.
     */
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

    /**
     * Checks if the current student is an committee of the current camp.
     * @return true if committee, false if not.
     */
    public static boolean isCommittee() {
        return isCommittee(CampManager.camp);
    }

    /**
     * Checks if the current student is an committee of the specific camp.
     * @param camp The specific camp object.
     * @return true if committee, false if not.
     */
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

    /**
     * Checks if the current staff is in charge of the specific camp.
     * @param camp The specific camp object.
     * @return true if in charge, false if not.
     */
    public static boolean isInCharge(Camp camp) {
        User user = UserManager.getUser();
        String st = camp.getStaffInCharge();
        if(st.equals(user.getUserID())){
            return true;
        }
        return false;
    }

    /**
     * Generates a student report based on the current camp.
     */
    public static void generateStudentReport(){
        CampFormatter cf= CampFormatter.getInstance();
        cf.formatStudentReport(camp);
    }

    /**
     * Generates a committee report based on the current camp.
     */
    public static void generateCommitteeReport(){
        CampFormatter cf= CampFormatter.getInstance();
        cf.formatCommitteeReport(camp);
    }

    /**
     * Prints the details of the current camp.
     */
    public static void printDetails() {
        CampFormatter cf = CampFormatter.getInstance();
        System.out.println(cf.formatFull(camp));
    }

    /**
     * Registers the current student as an attendee of the current camp.
     * The student is not able to register as an attendee if he or she has blacklisted, the registration deadline has passed or there are no more attendee slots available.
     * @return true if successful, false if not.
     */
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

    /**
     * Registers the current student as a committee of the current camp.
     * The student is not able to register as a committee if he or she has blacklisted or is a committee of another camp, the registration deadline has passed or there are no more committee slots available.
     * @return true if successful, false if not.
     */
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

    /**
     * Withdraws the current student from the current camp.
     * He or she is then added into the blacklist.
     */
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

    /**
     * Prints the details of the attendees and committees of the current camp. Prints no registered members if there are no members currently registered.
     */
    public static void printStudents() {
        int i;
        ArrayList<String> campAttendees = camp.getCampAttendees();
        ArrayList<String> campCommittees = camp.getCampCommittees();
        if (campAttendees.size() == 0 && campCommittees.size() == 0) {
            System.out.println("There are currently no registered members! \n");
        }
        if (campAttendees.size() != 0) {
            System.out.println("List of camp attendees:\n");
            for (i = 0; i != campAttendees.size(); i++) {
                User user = UserDatabase.getInstance().getUserByID(campAttendees.get(i));
                System.out.println(i+1 + ".\n" + UserFormatter.getInstance().formatFull(user) + "\n");
            }
        }
        if (campCommittees.size() != 0) {
            System.out.println("List of camp committees:");
            for (i = 0; i != campCommittees.size(); i++) {
                User user = UserDatabase.getInstance().getUserByID(campCommittees.get(i));
                System.out.println(i+1 + ".\n" + UserFormatter.getInstance().formatFull(user) + "\n");
            }
        }
    }
}
