package camppackage;


/**
 * Represents an suggestion related to a specific camp.
 * A committee makes an enquiry and the Staff-In-Charge can accept or reject it.
 */
public class Suggestion extends Message{
	
    /**
     * Whether a suggestion has been approved. True if approved, false if rejected.
     */
    private boolean status;
    
    /**
     * Creates a new Suggestion with the content, name and userID of the committee who submitted the suggestion.
     * Used in database loading, where the status is known.
     * @param content This suggestion's content.
     * @param name The name of the committee.
     * @param userID The userID of the committee.
     * @param status Whether the suggestion has been approved.
     */
    public Suggestion(String content, String name, String userID, boolean status) {
        super(content, name, userID);
        this.status = status;
    }

    /**
     * Creates a new Suggestion with the content, name and userID of the committee who submitted the suggestion.
     * The status is set to false by default.
     * @param content This suggestion's content.
     * @param name The name of the committee.
     * @param userID The userID of the committee.
     */
    public Suggestion(String content, String name, String userID) {
        super(content, name, userID);
        status = false;
    }

    /**
     * Gets the status of the suggestion.
     * @return whether the suggestion is rejected or approved.
     */
    public boolean getStatus() { 
        return status; 
    }

    /**
     * Sets the status of the suggestion. True if approved, false if rejected.
     * @param status Whether the suggestion has been approved.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
