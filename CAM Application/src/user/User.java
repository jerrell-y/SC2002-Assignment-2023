package user;

/*
 * user class which stores the information of the current user. It is the parent class for student and staff.
 */

public class User {
    /*
     * strings containing the userID and name of the user
     */
    private final String userID, name;
    /*
     * stores the faculty of the user
     */
    private final Faculty faculty;
    /*
     * stores password of the user
     */
    private String password;

    /*
     * constructor that creates the user class
     * @param userID is the userID of the user
     * @param password is the password of the user
     * @param name is the name of the user
     * @param faculty is the faculty of the user
     */
    public User(String userID, String password, String name, Faculty faculty) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.faculty = faculty;
    }

    /*
     * getter methods to obtain the information from the user class
     */

    public String getUserID() { return userID; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public Faculty getFaculty() { return faculty; }

    /*
     * setter method to set the password of the student
     */
    public void setPassword(String password){
        this.password = password;
    }
}