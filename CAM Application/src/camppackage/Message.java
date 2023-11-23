package camppackage;

public abstract class Message {
    private String content, name, userID;

    public Message(String content, String name, String userID){
        this.content = content;
        this.name = name;
        this.userID = userID;
    }

    public String getContent() { return content; }
    public String getName() { return name; }
    public String getUserID() { return userID; }
    
    public void setContent(String content) { this.content = content; }
}
