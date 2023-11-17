package camppackage;

public class Enquiry {
    private String title;
    private String content;
    private String studentName;
    private boolean answered;
    private String reply;

    public Enquiry(String title, String content, String studentName) {
        this.title = title;
        this.content = content;
        this.studentName = studentName;
        this.answered = false;
        this.reply = "";
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
