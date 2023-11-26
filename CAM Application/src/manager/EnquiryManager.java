package manager;

import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Enquiry;
import database.CampDatabase;
import database.UserDatabase;
import user.Student;
import user.User;
import user.UserManager;

/**
 * Handles the logic regarding the current enquiry. Only one enquiry can be active at any time. Holds the reference to the current enquiry object.
 */
public class EnquiryManager {
    /**
     * The current enquiry selected.
     */
    private static Enquiry enquiry;

    /**
     * Sets the new enquiry to be selected.
     * @param index The index of the enquiry.
     */
    public static void setEnquiry(int index) {
        EnquiryManager.enquiry = CampManager.getCamp().getEnquiries().get(index);
    }

    /**
     * Gets the current enquiry object selected.
     * @return the current enquiry.
     */
    public static Enquiry getEnquiry() {
        return enquiry;
    }

    /**
     * Adds an enquiry into the current camp.
     * @param enq The contents of the enquiry.
     */
    public static void addEnquiry(String enq) { 
        User user = UserManager.getUser();
        Camp c = CampManager.getCamp();
        Enquiry eqr = new Enquiry(enq, user.getName(), user.getUserID());
        c.addEnquiry(eqr);
        CampDatabase.getInstance().update();
    }

    /**
     * Edits the content of the current enquiry.
     * @param enq The new content of the enquiry.
     * @return False if the enquiry has already been answered, true if not.
     */
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

    /**
     * Deletes the selected enquiry from the camp.
     * @param enquiryNum Index of the enquiry.
     * @return False if the enquiry has already been answered, true if not.
     * 
     */
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

    /**
     * Adds a reply to the current enquiry as a staff or a committee. If a committee replies, they wiil receive one point.
     * @param reply The reply to the enquiry.
     * @return False if the enquiry has already been answered, true if not.
     * 
     */
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
                UserManager.setUser(student);
                UserDatabase.getInstance().update();  //Update the database of the points.
            }
            CampDatabase.getInstance().update();
            return true;
        }
    }


    /**
     * Prints all the enquiries in the camp made by the current user in the current camp.
     * @return a list of indexes of the enquiries.
     * 
     */
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

    /**
     * Prints all the enquiries in the current camp.
     * @return a list of indexes of the enquiries.
     * 
     */
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
