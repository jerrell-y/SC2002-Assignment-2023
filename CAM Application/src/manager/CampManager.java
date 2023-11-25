package manager;

import java.util.ArrayList;
import java.util.Objects;

import camppackage.Camp;
import database.CampDatabase;
import database.UserDatabase;
import format.CampFormatter;
import user.Student;
import user.User;
import user.UserManager;
import camppackage.Enquiry;

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
        int i;
        User user = UserManager.getUser();
        String st = camp.getStaffInCharge();
        if(Objects.equals(st, user.getName())){
            return true;
        }
        return false;
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
        Student student = (Student) UserManager.getUser();
        if (student.getCommiteeCampID() != -1) {
            System.out.println("You are already registered as a camp commitee in another camp!\n");
            return false;
        }
        if (camp.getCampCommiteeSlots() <= camp.getCampCommitees().size()) {
            System.out.println("There are no more slots available!\n");
            return false;
        }
        camp.addCampCommitee(student.getUserID());
        student.setCommiteeCampID(camp.getCampID());
        UserManager.setUser(student);
        UserDatabase.getInstance().update();
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
            if (eqr.get(i).getUserID() == user.getUserID()) {
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
}
