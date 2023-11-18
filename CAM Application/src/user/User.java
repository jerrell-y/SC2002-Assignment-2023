package user;

public class User {
    private final String userID, password, name;
    private final Faculty faculty;

    public User(String userID, String password, String name, Faculty faculty) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.faculty = faculty;
    }

    public String getUserID() { return userID; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public Faculty getFaculty() { return faculty; }
}