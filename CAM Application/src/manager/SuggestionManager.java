package manager;


import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Suggestion;
import database.CampDatabase;
import database.UserDatabase;
import user.Student;
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
        Student user2 = (Student) user; 
        user2.addPoints();
        UserDatabase.getInstance().update();
        CampDatabase.getInstance().update();
    }

    public static boolean editSuggestion(String sug) { 
        if (suggestion.getStatus()) {      
            System.out.println("The suggestion has already been answered!\n");
            return false;
        }
        else{
            suggestion.setContent(sug);
            CampDatabase.getInstance().update();
            return true;
        }
       
    }

    public static boolean deleteSuggestion(int suggestNum) { 
        if (suggestion.getStatus()) {      
            System.out.println("The suggestion has already been answered!\n");
            return false;
        }
        Camp c = CampManager.getCamp();
        SuggestionManager.suggestion = null;
        c.removeSuggestion(suggestNum);
        CampDatabase.getInstance().update();
        return true;
    }

    public static void approveSuggestion() {     //need to implement the addpoints for the student

        if (suggestion.getStatus()) {
            System.out.println("The suggestion has already been approved!\n");
            return;
        }
        else{
            suggestion.setStatus(true);
            CampDatabase.getInstance().update();
        }
    }


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
