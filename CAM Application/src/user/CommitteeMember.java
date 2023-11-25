package user;
/* 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CommiteeMember {

    private Camp camp;
    private ArrayList<Suggestion> mySuggestions = new ArrayList<Suggestion>();
    private int points;
    
    public void viewCampInfo(Camp camp) {
        camp.viewCampInfo();
    }
    
    public void submitSuggestions(Camp camp) {
        System.out.println("Please enter your suggestion for this camp: ");
        String suggestion = sc.nextLine();
        Suggestion suggestion = new Suggestion(camp, suggestion);
    	camp.addSuggestion(suggestion);
        this.Suggestion(suggestion);
       givePoints(1);
    }
        
    public void viewEnquiry(Camp camp) {
        camp.viewEnquiry();
    }
    
    public void replyCampEnquiry(Enquiry enquiry) {
        System.out.println("Please reply to this enquiry:");
        String userInputString = sc.nextLine();
        enquiry.replyEnquiry(userInputString);
        givePoints(1);
    }
	
    public void viewSuggestions() {
        System.out.println("Here is(are) your suggestion(s):");
        int i = 1;
        for (Suggestion suggestion : mySuggestions) {
            System.out.println(i + ": " + suggestion.getSuggestion());
            i++;
        }
    }
	
    public void editSuggestion(Suggestion suggestion) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please edit your submitted suggestion: ");
        String userInputString = scanner.nextLine();
        suggestion.setSuggestion(userInputString);
    }
	
    public void deleteSuggestion(Camp camp, Suggestion suggestion) {
        camp.removeSuggestion(suggestion);
        this.removemySuggestion(suggestion);
        givePoints(-1);
    }
    
    public void givePoints(int point) {
        this.points += point;
    }

    public int getPoints() {
        return this.points;
    }	
	
*/