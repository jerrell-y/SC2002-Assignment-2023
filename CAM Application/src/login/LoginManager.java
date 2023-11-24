package login;

import user.User;
import database.UserDatabase;

import java.util.ArrayList;

public class LoginManager {
    public static User login(String userID, String password) {
        //Import from database
        UserDatabase userDB = UserDatabase.getInstance();
        if (userDB.getUsers().isEmpty()) {
            userDB.load();
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

    public static boolean isDefaultPassword(User user) {
        return "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8".equals(user.getPassword());
    }

    public static void changePassword(User user, String newPassword) {
        // Logic to change the user's password
        user.setPassword(PasswordManager.encrypt(newPassword));
        System.out.println("new password = " + user.getPassword());
        UserDatabase.getInstance().update();
    }
}
