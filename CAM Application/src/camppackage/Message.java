package camppackage;

/**
 * Represents a message made by a student.
 * Superclass of Enquiry and Suggestion.
 */
public abstract class Message {
    /**
     * The content of the message.
     */
    private String content;

    /**
     * The name of the user who submitted the message.
     */
    private String name;

    /**
     * The userID of the user who submitted the message.
     */
    private String userID;

    /**
     * Creates a new message with the content, name and userID of the student who submitted the message.
     * @param content The content of the message.
     * @param name The name of the user.
     * @param userID The userID of the user.
     */
    public Message(String content, String name, String userID){
        this.content = content;
        this.name = name;
        this.userID = userID;
    }

    /**
     * Gets the content of the message.
     * @return the content of the message.
     */
    public String getContent() { return content; }

    /**
     * Gets the name of the user who submitted the messaage.
     * @return the name of the user.
     */
    public String getName() { return name; }

    /**
     * Gets the userID of the user who submitted the message.
     * @return the userID of the user.
     */
    public String getUserID() { return userID; }
    
    /**
     * Changes the content of the message. The content cannot be empty.
     * @param content The content of the message.
     */
    public void setContent(String content) { this.content = content; }
}
