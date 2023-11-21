package camppackage;

public class Suggestion {
	
    private String title;
    private String suggestion;
    private String campCommiteeName;
    private boolean status;

    public Camp getCamp() {
	    return camp;
	}
    
    public Suggestion(String title, String suggestion, String campCommiteeName) {
        this.title = title;
        this.suggestion = suggestion;
        this.campCommiteeName = campCommiteeName;
        this.status = false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void viewSuggestion(String suggestion) {
	System.out.println("Here is the suggestion submitted: " + suggestion);
	}
    
    public String editSuggestion(String suggestion) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Please enter the new suggestion: ");
	String newSuggestion = sc.nextLine();
	return newSuggestion;
    }
    
    public String getSuggestion() {
	return suggestion;
	}
    
    public void setSuggestion(String suggestion) {
	this.suggestion = suggestion;
	}
    
}
