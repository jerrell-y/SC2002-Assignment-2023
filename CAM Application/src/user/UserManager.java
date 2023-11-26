package user;

/**
 * Helper class to store the current user.
 */
public class UserManager {
    /**
     * The current user object.
     */
    private static User user;

    /**
     * Gets the current user object.
     * @return returns the user.
     */
    public static User getUser() {
        return user;
    }
    /**
     * Sets the new current user object.
     * @param user the new user object.
     */
    public static void setUser(User user) {
        UserManager.user = user;
    }
}
