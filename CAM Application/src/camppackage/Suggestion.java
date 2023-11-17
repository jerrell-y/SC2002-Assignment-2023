package camppackage;

public class Suggestion {
    private String title;
    private String content;
    private String campCommiteeName;
    private boolean status;

    public Suggestion(String title, String content, String campCommiteeName) {
        this.title = title;
        this.content = content;
        this.campCommiteeName = campCommiteeName;
        this.status = false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
