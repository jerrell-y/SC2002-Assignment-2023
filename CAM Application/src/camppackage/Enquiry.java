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

    public String getStudentName() {
        return studentName;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
