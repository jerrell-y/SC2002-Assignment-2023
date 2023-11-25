package manager;

import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Enquiry;
import database.CampDatabase;
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
        CampDatabase.getInstance().update();
    }

    public static void editEnquiry(String enq) { 
        if (enquiry.isAnswered()) {
            System.out.println("The enquiry has already been answered!\n");
            return;
        }
        enquiry.setContent(enq);
        CampDatabase.getInstance().update();
    }

    public static void deleteEnquiry(int enquiryNum) { 
        Camp c = CampManager.getCamp();
        EnquiryManager.enquiry = null;
        c.removeEnquiry(enquiryNum);
        CampDatabase.getInstance().update();
    }

    public static void replyEnquiry(String reply) {
        if (enquiry.isAnswered()) {
            System.out.println("The enquiry has already been answered!\n");
            return;
        }
        enquiry.setReply(reply);
        CampDatabase.getInstance().update();
    }


    public static int printUserEnquiry() { 
        Camp c = CampManager.getCamp();
        User user = UserManager.getUser();
        ArrayList<Enquiry> eqr = c.getEnquiries();
        int counter=1;

        for (int i=0; i<eqr.size(); i++) {
            if (user.getUserID() == eqr.get(i).getUserID()) {
                System.out.println(counter + ". Name: " + eqr.get(i).getName() + "Content: " + eqr.get(i).getContent());
                if (eqr.get(i).isAnswered()){
                    System.out.println("Reply: " + eqr.get(i).getReply());
                }
                counter++;
            }
        }
        return eqr.size(); 
    }

        public static void printAllEnquiry() { 
        Camp c = CampManager.getCamp();
        ArrayList<Enquiry> eqr = c.getEnquiries();
        int counter=1;

        for (int i=0; i<eqr.size(); i++) {
            System.out.println(counter + ". Name: " + eqr.get(i).getName() + "Content: " + eqr.get(i).getContent());
            if (eqr.get(i).isAnswered()){
                   System.out.println("Reply: " + eqr.get(i).getReply());
            }
        }
        return; 
    }
}
