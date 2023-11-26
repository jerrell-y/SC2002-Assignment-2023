package login;

import user.User;
import database.CampDatabase;
import database.UserDatabase;
import java.util.ArrayList;

/**
 * The LoginManager class handles authentication and password management for users.
 * It interacts with UserDatabase to validate user credentials and manages user sessions after successful logins.
 */
public class LoginManager {
    /**
     * Attempts to log in a user with the provided userID and password.
     * It checks against the user records in the UserDatabase.
     *
     * @param userID The user's ID, typically their email or username.
     * @param password The user's password.
     * @return The User object if login is successful, null if authentication fails.
     */
    public static User login(String userID, String password) {
        //Import from database
        UserDatabase userDB = UserDatabase.getInstance();
        if (userDB.getUsers().isEmpty()) {
            userDB.load();
        }
        CampDatabase campDB = CampDatabase.getInstance();
        if (campDB.getCamps().isEmpty()) {
            campDB.load();
        }

        ArrayList<User> users = userDB.getUsers();

        String userIDLower = userID.toLowerCase();

        for (User user : users) {
            if (user.getUserID().toLowerCase().equals(userIDLower) && PasswordManager.checkPassword(user.getPassword(), password)) {
                return user; // Successful login
            }
        }
        return null; // Login failed

    } 

    /**
     * Checks if the user's password is set to the default one.
     *
     * @param user The User object to check the password for.
     * @return true if the password matches the default, false otherwise.
     */
    public static boolean isDefaultPassword(User user) {
        return "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8".equals(user.getPassword());
    }

    /**
     * Changes the user's password to a new one after encrypting it.
     * It updates the User object and the UserDatabase with the new password.
     *
     * @param user The User object whose password is to be changed.
     * @param newPassword The new password that will replace the old one.
     */
    public static void changePassword(User user, String newPassword) {
        // Logic to change the user's password
        user.setPassword(PasswordManager.encrypt(newPassword));
        UserDatabase.getInstance().update();
    }
}
