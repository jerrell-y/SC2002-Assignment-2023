package login;

import user.User;
import database.Database;

import java.util.List;

public class LoginManager {
    public static boolean login(String userID, String password) {
        List<User> users = Database.getUsers();
        for (User user : users) {
            if (user.getUserID().equals(userID) && PasswordManager.checkPassword(user.getPassword(), password)) {
                return true; // Successful login
            }
        }
        return false; // Login failed

    } 

    public static void changePassword() {

    }
}
