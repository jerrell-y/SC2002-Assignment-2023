package manager;

import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Enquiry;
import database.CampDatabase;
import database.UserDatabase;
import user.Student;
import user.User;
import user.UserManager;

public class EnquiryManager {
    private static Enquiry enquiry;

    public static void setEnquiry(int index) {
        EnquiryManager.enquiry = CampManager.getCamp().getEnquiries().get(index);
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

    public static boolean editEnquiry(String enq) { 
        if (enquiry.isAnswered()) {
            System.out.println("The enquiry has already been answered!\n");
            return false;
        }
        else {
            enquiry.setContent(enq);
            CampDatabase.getInstance().update();
            return true;
        }
    }

    public static boolean deleteEnquiry(int enquiryNum) { 
        if (enquiry.isAnswered()) {
            System.out.println("The enquiry has already been answered!\n");
            return false;
        }

        Camp c = CampManager.getCamp();
        EnquiryManager.enquiry = null;
        c.removeEnquiry(enquiryNum);
        CampDatabase.getInstance().update();
        return true;
    }

    public static boolean replyEnquiry(String reply) {          
        User user = UserManager.getUser();
        if (enquiry.isAnswered()) {
            System.out.println("The enquiry has already been answered!\n");
            return false;
        }
        else{
            enquiry.setReply(reply);
            enquiry.setAnswered(true);
            if (user instanceof Student) {
                Student student = (Student) user;
                student.addPoints();
                UserDatabase.getInstance().update();  //Update the database of the points.
                UserManager.setUser(student);
            }
            CampDatabase.getInstance().update();
            return true;
        }
    }


    public static ArrayList<Integer> printUserEnquiry() { 
        Camp c = CampManager.getCamp();
        User user = UserManager.getUser();
        ArrayList<Enquiry> eqr = c.getEnquiries();
        ArrayList<Integer> userEnquiries = new ArrayList<Integer>();
        int counter=1;

        for (int i=0; i<eqr.size(); i++) {
            Enquiry enquiry = eqr.get(i);
            //System.out.println("\n\n" + user.getUserID() + "," + eqr.get(i).getUserID());
            if (user.getUserID().equals(enquiry.getUserID())) {
                System.out.println(counter + ". Name: " + enquiry.getName() + " Content: " + enquiry.getContent());
                if (enquiry.isAnswered()){
                    System.out.println("Reply: " + enquiry.getReply());
                }
                userEnquiries.add(i);
                counter++;
            }
        }
        return userEnquiries; 
    }

    //returns an ArrarList<Integer> of all the indexes of the current list being looked at, to be used later 
    public static ArrayList<Integer> printAllEnquiry() { 
        Camp c = CampManager.getCamp();
        ArrayList<Enquiry> eqr = c.getEnquiries();
        ArrayList<Integer> allEnquiries = new ArrayList<Integer>();
        int counter=1;

        for (int i=0; i<eqr.size(); i++) {
            Enquiry enquiry = eqr.get(i);
            System.out.println(counter + ". Name: " + enquiry.getName() + " Content: " + enquiry.getContent());
            if (enquiry.isAnswered()){
                   System.out.println("Reply: " + enquiry.getReply());
            }
            allEnquiries.add(i);
            counter++;
        }
        return allEnquiries; 
    }
}
