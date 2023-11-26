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
 * The manager that handles the methods regarding suggestions.
 */

public class SuggestionManager {
    /**
     * variable to store a private suggestion within the manager
     */
    private static Suggestion suggestion;

    /**
     * method to set the suggestion inside of the manager
     * @param suggestion to be set
     */
    public static void setSuggestion(int suggestNum) {
        Camp c = CampManager.getCamp();
        SuggestionManager.suggestion = c.getSuggestions().get(suggestNum);
    }

    /**
     * method to retrieve the suggestion from the manager
     */
    public static Suggestion getSuggestion() {
        return suggestion;
    }
    
    /**
     * method to add in a suggestion to the list of suggestions inside of the current camp
     * @param sug is the content of the suggestion to be added in
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
     * method to edit the already existing suggestion by overriding the content with the new content
     * @param sug is the new content to be added in
     * @return the value is true if it successfully updates the content, false if it is unable to do so due to the suggestion already being approved
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
     * method to delete the suggestion from the current camp
     * @param suggestNum is the index passed into removeSuggestion() which deletes that specific suggestion
     * @return the value is true if it successfully deletes the suggestion, false if it is unable to do so due to the suggestion already being approved
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
     * method to approve the suggestion for the current camp
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
     * method to print out all of the suggestions made by the committee member
     * @return arraylist of integers for userSuggestions, which help to index the suggestions
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
     * method to print out all of the suggestions for that camp
     * @return array list of integers for allsuggestions, which help to index the suggestions
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
