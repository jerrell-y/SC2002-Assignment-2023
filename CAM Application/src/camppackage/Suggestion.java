package camppackage;


/**
 * The suggestion class for the camps
 */
public class Suggestion extends Message{
	
    private boolean status;
    
    public Suggestion(String content, String name, String userID, boolean status) {
        super(content, name, userID);
        this.status = status;
    }

    public Suggestion(String content, String name, String userID) {
        super(content, name, userID);
        status = false;
    }

    public boolean getStatus() { 
        return status; 
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
