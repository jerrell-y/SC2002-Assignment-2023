package manager;


import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Suggestion;
import database.CampDatabase;
import user.User;
import user.UserManager;

public class SuggestionManager {
    private static Suggestion suggestion;

    public static void setSuggestion(int suggestNum) {
        Camp c = CampManager.getCamp();
        SuggestionManager.suggestion = c.getSuggestions().get(suggestNum);
    }

    public static Suggestion getSuggestion() {
        return suggestion;
    }

    public static void addSuggestion(String sug) { 
        User user = UserManager.getUser();
        Camp c = CampManager.getCamp();
        Suggestion sgn = new Suggestion(sug, user.getName(), user.getUserID());
        c.addSuggestion(sgn);
        CampDatabase.getInstance().update();
    }

    public static void editSuggestion(String sug) { 
        if (suggestion.getStatus()) {          //need implement this
            System.out.println("The suggestion has already been answered!\n");
            return;
        }
        else{
            suggestion.setContent(sug);
            CampDatabase.getInstance().update();
        }
       
    }

    public static void deleteEnquiry(int enquiryNum) { 
        Camp c = CampManager.getCamp();
        EnquiryManager.enquiry = null;
        c.removeEnquiry(enquiryNum);
        CampDatabase.getInstance().update();
    }

    public static void approveSuggestion() {
        if (suggestion.getStatus()) {
            System.out.println("The suggestion has already been approved!\n");
            return;
        }
        else{
            suggestion.setStatus(true);
            CampDatabase.getInstance().update();
        }
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
