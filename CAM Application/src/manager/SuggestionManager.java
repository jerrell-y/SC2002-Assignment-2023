package manager;


import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Suggestion;
import database.CampDatabase;
import database.UserDatabase;
import user.Student;
import user.User;
import user.UserManager;

/**
 * Handles the logic regarding the current suggestion. Only one suggestion can be active at any time. Holds the reference to the current suggestion object.
 */
public class SuggestionManager {
/**
     * The current suggestion selected.
     */
    private static Suggestion suggestion;

    /**
     * Sets the new suggestion to be selected.
     * @param suggestNum The index of the suggestion.
     */
    public static void setSuggestion(int suggestNum) {
        Camp c = CampManager.getCamp();
        SuggestionManager.suggestion = c.getSuggestions().get(suggestNum);
    }

    /**
     * Gets the current suggestion object selected.
     * @return the current suggestion.
     */
    public static Suggestion getSuggestion() {
        return suggestion;
    }
    
    /**
     * Adds a suggestion to the current camp. 1 point is awarded to the committee who added the suggestion.
     * @param sug The content of the suggestion.
     */
    public static void addSuggestion(String sug) { 
        User user = UserManager.getUser();
        Camp c = CampManager.getCamp();
        Suggestion sgn = new Suggestion(sug, user.getName(), user.getUserID());
        c.addSuggestion(sgn);
        Student user2 = (Student) user; 
        user2.addPoints();
        UserDatabase.getInstance().update();
        CampDatabase.getInstance().update();
    }
    
    /**
     * Replaces the content of the current suggestion.
     * @param sug The content of the suggestion.
     * @return false if suggestion has already been approved, true if not.
     */
    public static boolean editSuggestion(String sug) { 
        if (suggestion.getStatus()) {      
            System.out.println("The suggestion has already been approved!\n");
            return false;
        }
        else{
            suggestion.setContent(sug);
            CampDatabase.getInstance().update();
            return true;
        }
       
    }

    /**
     * Deletes the current suggestion from the camp.
     * @param suggestNum The index of the suggestion.
     * @return false if suggestion has already been approved, true if not.
     */
    public static boolean deleteSuggestion(int suggestNum) { 
        if (suggestion.getStatus()) {      
            System.out.println("The suggestion has already been approved!\n");
            return false;
        }
        Camp c = CampManager.getCamp();
        SuggestionManager.suggestion = null;
        c.removeSuggestion(suggestNum);
        CampDatabase.getInstance().update();
        return true;
    }

    /**
     * Approves the current suggestion. 1 point is awarded to the attendee who submitted the suggestion.
     */
    public static void approveSuggestion() {
        if (suggestion.getStatus()) {
            System.out.println("The suggestion has already been approved!\n");
            return;
        }
        else{
            suggestion.setStatus(true);
            User user = UserDatabase.getInstance().getUserByID(suggestion.getUserID());
            Student user2 = (Student) user;
            user2.addPoints();
            UserDatabase.getInstance().update();
            CampDatabase.getInstance().update();
        }
    }

    /**
     * Prints the list of suggestions made by the current commitee in the current camp.
     * @return a list of indexes for the suggestions.
     */
    public static ArrayList<Integer> printUserSuggestions() { 
        Camp c = CampManager.getCamp();
        User user = UserManager.getUser();
        ArrayList<Suggestion> sug = c.getSuggestions();
        ArrayList<Integer> userSuggestions = new ArrayList<Integer>();
        int counter=1;

        for (int i=0; i<sug.size(); i++) {
            if (user.getUserID() == sug.get(i).getUserID()) {
                System.out.println(counter + ". Name: " + sug.get(i).getName() + "Content: " + sug.get(i).getContent());
                userSuggestions.add(i);
                counter++;
            }
        }
        return userSuggestions; 
    }

    /**
     * Prints a list of all suggestions in the current camp.
     * @return a list of indexes for the suggestions.
     */
    public static ArrayList<Integer> printAllSuggestions() { 
        Camp c = CampManager.getCamp();
        ArrayList<Suggestion> sug = c.getSuggestions();
        ArrayList<Integer> allSuggestions = new ArrayList<Integer>();
        int counter=1;

        for (int i=0; i<sug.size(); i++) {
            System.out.println(counter + ". Name: " + sug.get(i).getName() + "Content: " + sug.get(i).getContent());
            allSuggestions.add(i);
            counter++;
        }
        return allSuggestions; 
    }
}
