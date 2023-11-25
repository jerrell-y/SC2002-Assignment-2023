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

    public static void setEnquiry(Enquiry enquiry) {
        EnquiryManager.enquiry = enquiry;
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


    public static ArrayList<Enquiry> printUserEnquiry() { 
        Camp c = CampManager.getCamp();
        User user = UserManager.getUser();
        ArrayList<Enquiry> eqr = c.getEnquiries();
        ArrayList<Enquiry> userEnquiries = new ArrayList<Enquiry>();
        int counter=1;

        for (int i=0; i<eqr.size(); i++) {
            Enquiry enquiry = eqr.get(i);
            //System.out.println("\n\n" + user.getUserID() + "," + eqr.get(i).getUserID());
            if (user.getUserID().equals(enquiry.getUserID())) {
                System.out.println(counter + ". Name: " + enquiry.getName() + " Content: " + enquiry.getContent());
                if (enquiry.isAnswered()){
                    System.out.println("Reply: " + enquiry.getReply());
                }
                userEnquiries.add(enquiry);
                counter++;
            }
        }
        return userEnquiries; 
    }

    public static ArrayList<Enquiry> printAllEnquiry() { 
        Camp c = CampManager.getCamp();
        ArrayList<Enquiry> eqr = c.getEnquiries();
        int counter=1;

        for (int i=0; i<eqr.size(); i++) {
            Enquiry enquiry = eqr.get(i);
            System.out.println(counter + ". Name: " + enquiry.getName() + " Content: " + enquiry.getContent());
            if (enquiry.isAnswered()){
                   System.out.println("Reply: " + enquiry.getReply());
            }
            counter++;
        }
        return eqr; 
    }
}
