package manager;

import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Enquiry;
import database.UserDatabase;
import user.User;
import user.UserManager;

public class EnquiryManager {
    private static Enquiry enquiry;

    public static void setEnquiry(Enquiry enquiry) {
        EnquiryManager.enquiry = enquiry;
    }

    public static Enquiry getEnquiry() {
        return enquiry;
    }

    public static void addEnquiry(String enq) { 
        User user = UserManager.getUser();
        Camp c = CampManager.getCamp();
        Enquiry eqr2 = new Enquiry(enq, user.getName(), user.getUserID());
        c.addEnquiry(eqr2);
        UserDatabase.getInstance().update();
    }

    public static boolean editEnquiry(String enq) { 
        if (enquiry.isAnswered()) {
            System.out.println("The enquiry has already been answered!\n");
            return false;
        }
        enquiry.setContent(enq);
        UserDatabase.getInstance().update();
        return true; 
    }

    public static void deleteEnquiry() { 
        return; 
    }

    public static void printAllEnquiry() { 

        return; 
    }
}
