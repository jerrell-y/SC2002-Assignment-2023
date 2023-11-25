package manager;

import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Enquiry;
import user.User;
import user.UserManager;

public class EnquiryManager {
    public static void addEnquiry(String enq) { 
        User user = UserManager.getUser();
        Camp c = CampManager.getCamp();
        ArrayList<Enquiry> eqr = c.getEnquiries();
        Enquiry eqr2 = new Enquiry(enq, user.getName(), user.getUserID());
        eqr.add(eqr2);
        c.setEnquiries(eqr);
        return; 
    }

    public static void editEnquiry() { 
        return; 
    }

    public static void deleteEnquiry() { 
        return; 
    }

    public static void printAllEnquiry() { 

        return; 
    }
}
