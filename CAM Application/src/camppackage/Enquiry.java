package camppackage;

/**
 * Represents an enquiry related to a specific camp.
 * An attendee makes an enquiry and the Staff-In-Charge or a camp committee can reply to it.
 */
public class Enquiry extends Message {

    /**
     * The reply for the enquiry.
     */
    private String reply;

    /**
     * Whether the enquiry has been answered. True if answered, false if not.
     */
    private boolean answered;

    /**
     * To create a new enquiry with the content, name and userID of the attendee.
     * The reply is set to empty, and the answered status is set to false.
     * @param content This enquiry's content.
     * @param name The name of the attendee.
     * @param userID The userID of the attendee,
     */
    public Enquiry(String content, String name, String userID){
        super(content, name, userID);
        reply = "";
        answered = false;
    }

    /**
     * To create a new enquiry with the content, name and userID of the attendee.
     * Used during database loading, when the reply and answered status is known.
     * @param content This enquiry's content.
     * @param name The name of the attendee.
     * @param userID The userID of the attendee.
     * @param reply The reply to the enquiry.
     * @param answered Whether the enquiry has been answered.
     */
    public Enquiry(String content, String name, String userID, String reply, boolean answered) {
        super(content, name, userID);
        this.reply = reply;
        this.answered = answered;
    }

    /**
     * Gets the reply for the enquiry.
     * @return a String of the reply for the enquiry.
     */
    public String getReply() { 
        return reply; 
    }

    /**
     * Gets the answered status of the enquiry.
     * @return whether the enquiry has been answered.
     */
    public boolean isAnswered() {
         return answered; 
    }

    /**
     * Sets the reply for the enquiry. The reply cannot be empty.
     * @param reply The reply for the enquiry.
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * Sets the answered status of the enquiry.
     * @param answered Whether the enquiry has been answered.
     */
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
