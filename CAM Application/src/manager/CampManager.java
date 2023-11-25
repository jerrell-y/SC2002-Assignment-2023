package manager;

import java.util.ArrayList;

import camppackage.Camp;
import database.CampDatabase;
import format.CampFormatter;
import user.User;
import user.UserManager;
import camppackage.Enquiry;
import camppackage.Suggestion;

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
        if (camp.getCampAttendeeSlots() <= camp.getCampAttendees().size()) {
            System.out.println("There are no more slots available!\n");
            return false;
        }
        camp.addCampAttendee(user.getUserID());
        CampDatabase.getInstance().update();
        return true;
    }

    public static boolean registerCommitee() {
        User user = UserManager.getUser();
        ArrayList<Camp> campList = CampDatabase.getInstance().getCamps();
        Camp currCamp;
        int i;
        for (i = 0; i != campList.size(); i++) {
            currCamp = campList.get(i);
            if (isCommitee(currCamp)) {
                System.out.println("You are already a commitee member in another camp!");
                return false;
            }
        }
        if (camp.getCampCommiteeSlots() <= camp.getCampCommitees().size()) {
            System.out.println("There are no more slots available!\n");
            return false;
        }
        camp.addCampCommitee(user.getUserID());
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
                CampDatabase.getInstance().update();
                break;
            }
        }
    }

    public static ArrayList<Enquiry> getEnquiryByUser() {
        User user = UserManager.getUser();
        ArrayList<Enquiry> eqr = camp.getEnquiries();
        ArrayList<Enquiry> eqr2 = new ArrayList<Enquiry>();
        for (int i = 0; i != eqr.size(); i++) {
            if (eqr.get(i).getUserID().equals(user.getUserID())) {
                eqr2.add(eqr.get(i));
            }
        }
        if (eqr2.size() == 0){
            return null;
        }
        else{
            return eqr2;
        }
    }

    public static ArrayList<Suggestion> getSuggestByUser() {
        User user = UserManager.getUser();
        ArrayList<Suggestion> sug = camp.getSuggestions();
        ArrayList<Suggestion> sug2 = new ArrayList<Suggestion>();
        for (int i = 0; i != sug.size(); i++) {
            if (sug.get(i).getUserID() == user.getUserID()) {
                sug2.add(sug.get(i));
            }
        }
        if (sug2.size() == 0){
            return null;
        }
        else{
            return sug2;
        }
    }
}
