package manager;

import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Enquiry;
import database.CampDatabase;
import user.Student;
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
        System.out.println("Successfully added enquiry!");
    }

    public static void editEnquiry(String enq) { 
        if (enquiry.isAnswered()) {
            System.out.println("The enquiry has already been answered!\n");
        }
        else {
            enquiry.setContent(enq);
            CampDatabase.getInstance().update();
            System.out.println("Successfully edited enquiry!");
        }
    }

    public static void deleteEnquiry(int enquiryNum) { 
        Camp c = CampManager.getCamp();
        c.removeEnquiry(enquiryNum);
        EnquiryManager.enquiry = null;
        CampDatabase.getInstance().update();
        System.out.println("Successfully deleted enquiry!");
    }

    public static void replyEnquiry(String reply) {          
     //   User user = UserManager.getUser();
    //    Student user2 = (Student) user;
        if (enquiry.isAnswered()) {
            System.out.println("The enquiry has already been answered!\n");
        }
        else{
            enquiry.setReply(reply);
       //     user2.addPoints();                         //need upcast?
            enquiry.setAnswered(true);
            CampDatabase.getInstance().update();
            System.out.println("Successfully replied enquiry!");
        }
    }


    public static int printUserEnquiry() { 
        Camp c = CampManager.getCamp();
        User user = UserManager.getUser();
        ArrayList<Enquiry> eqr = c.getEnquiries();
        int counter=1;

        System.out.println(eqr.size());
        /*
        if (eqr.size() == 0) {
            System.out.print("No enquiries found!");
            return 0;
        }
        */

        for (int i=0; i<eqr.size(); i++) {
            //System.out.println("\n\n" + user.getUserID() + "," + eqr.get(i).getUserID());
            if (user.getUserID().equals(eqr.get(i).getUserID())) {
                System.out.println(counter + ". Name: " + eqr.get(i).getName() + " Content: " + eqr.get(i).getContent());
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
            System.out.println(counter + ". Name: " + eqr.get(i).getName() + " Content: " + eqr.get(i).getContent());
            if (eqr.get(i).isAnswered()){
                   System.out.println("Reply: " + eqr.get(i).getReply());
            }
            counter++;
        }
        return; 
    }
}
