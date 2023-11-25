package manager;

import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Enquiry;
import database.UserDatabase;
import user.User;
import user.UserManager;

public class EnquiryManager {
    private static Enquiry enquiry;

    public static void setEnquiry(int enquiryNum) {
        Camp c = CampManager.getCamp();
        EnquiryManager.enquiry = c.getEnquiries().get(enquiryNum);
    }

    public static Enquiry getEnquiry() {
        return enquiry;
    }

    public static void addEnquiry(String enq) { 
        User user = UserManager.getUser();
        Camp c = CampManager.getCamp();
        Enquiry eqr = new Enquiry(enq, user.getName(), user.getUserID());
        c.addEnquiry(eqr);
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

    public static int printUserEnquiry() { 
        Camp c = CampManager.getCamp();
        User user = UserManager.getUser();
        ArrayList<Enquiry> eqr = c.getEnquiries();

        for (int i=1; i<=eqr.size(); i++) {
            if (user.getUserID() == eqr.get(i).getUserID()) {
                System.out.println(i + ". Name: " + eqr.get(i).getName() + "Content: " + eqr.get(i).getContent());
                if (eqr.get(i).isAnswered()){
                    System.out.println("Reply: " + eqr.get(i).getReply());
                }
            }
        }
        return eqr.size(); 
    }

        public static void printAllEnquiry() { 
        Camp c = CampManager.getCamp();
        ArrayList<Enquiry> eqr = c.getEnquiries();

        for (int i=1; i<=eqr.size(); i++) {
            System.out.println(i + ". Name: " + eqr.get(i).getName() + "Content: " + eqr.get(i).getContent());
            if (eqr.get(i).isAnswered()){
                   System.out.println("Reply: " + eqr.get(i).getReply());
            }
        }
        return; 
    }
}
