package camppackage;

/**
 * The enquiry class for camps
 */
public class Enquiry extends Message {

    private String reply;
    private boolean answered;

    public Enquiry(String content, String name, String userID, String reply, boolean answered) {
        super(content, name, userID);
        this.reply = reply;
        this.answered = answered;
    }
    public Enquiry(String content, String name, String userID){
        super(content, name, userID);
        reply = "";
        answered = false;
    }

    public String getReply() { 
        return reply; 
    }

    public boolean isAnswered() {
         return answered; 
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
