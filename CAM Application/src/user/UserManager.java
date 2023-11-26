package user;

/*
 * class that contains the getter and setter method for the user class
 */

public class UserManager {
    /*
     * stores the current user object into user
     */
    private static User user;

    /*
     * getter method
     * @return returns the user object
     */

    public static User getUser() {
        return user;
    }
    /*
     * setter method
     * @param user object to be stored inside
     */

    public static void setUser(User user) {
        UserManager.user = user;
    }
}
