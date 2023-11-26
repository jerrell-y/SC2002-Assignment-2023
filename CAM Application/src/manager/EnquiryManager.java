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
 * The manager that handles the methods regarding enquiries.
 */
public class EnquiryManager {
    /**
     * a variable that stores a specific enquiry into the manager
     */
    private static Enquiry enquiry;

    /**
     * method that sets a specific enquiry into the manager
     * @param index gives us the index to store that specific enquiry within the camp
     */
    public static void setEnquiry(int index) {
        EnquiryManager.enquiry = CampManager.getCamp().getEnquiries().get(index);
    }

    /**
     * method that returns the specific enquiry stored in the manager
     * @return the enquiry stored inside
     */
    public static Enquiry getEnquiry() {
        return enquiry;
    }

    /**
     * method to add enquiry into a camp
     * @param enq the string that will be added into the content of the camp
     */
    public static void addEnquiry(String enq) { 
        User user = UserManager.getUser();
        Camp c = CampManager.getCamp();
        Enquiry eqr = new Enquiry(enq, user.getName(), user.getUserID());
        c.addEnquiry(eqr);
        CampDatabase.getInstance().update();
    }

    /**
     * method to edit the enquiry from the camp
     * @param enq is the string that is used to overwrite the current content in that specific enquiry
     * @return false if the enquiry has already been answered, and true if it has not yet been answered
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
     * method to delete enquiry from the camp
     * @param enquiryNum gives us the index to remove the enquiry from the camp
     * @return false if the enquiry has already been answered, and true if it has not
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
     * method to reply the enquiry as a staff or a committee. If a committee replies, then they will recieve points
     * @param reply is the string that is entered to reply to that enquiry
     * @return false if the enquiry has already been answered, and true if it has not
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
     * the method to print out all the current user's enquiries in that camp
     * @return an ArrarList<Integer> of all the indexes of the current list being looked at, to be used later 
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
     * method to print out all the enquiries in that camp
     * @return an ArrarList<Integer> of all the indexes of the current list being looked at, to be used later 
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
