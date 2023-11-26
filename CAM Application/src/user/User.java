package user;

/**
 * User class which stores the information of the current user. It is the parent class for student and staff.
 */
public class User {
    /**
     * Unique ID of the user.
     */
    private final String userID;

    /**
     * Name of the user.
     */
    private final String name;

    /**
     * Faculty of the user.
     */
    private final Faculty faculty;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Creates a new user with userID, password in SHA-256, name and faculty of the user.
     * @param userID The user's userID.
     * @param password The user's hashed password.
     * @param name The user's name.
     * @param faculty The user's faculty.
     */
    public User(String userID, String password, String name, Faculty faculty) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.faculty = faculty;
    }

    /**
     * Gets the unique ID of the user.
     * @return the user's unique ID.
     */
    public String getUserID() { return userID; }

    /**
     * Gets the hashed password of the user.
     * @return the user's hashed password.
     */
    public String getPassword() { return password; }

    /**
     * Gets the name of the user.
     * @return the user's name.
     */
    public String getName() { return name; }

    /**
     * Gets the faculty of the user.
     * @return the user's faculty.
     */
    public Faculty getFaculty() { return faculty; }


    /**
     * Sets the new hashed password of the user.
     * @param password The new hashed password.
     */
    public void setPassword(String password){
        this.password = password;
    }
}