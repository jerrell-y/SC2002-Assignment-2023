package login;

import user.User;
import database.UserDatabase;

import java.util.List;

public class LoginManager {
    public static User login(String userID, String password) {
        //Import from database
        UserDatabase userDB = new UserDatabase();
        userDB.load();
        List<User> users = userDB.getUsers();

        for (User user : users) {
            if (user.getUserID().equals(userID) && PasswordManager.checkPassword(user.getPassword(), password)) {
                return user; // Successful login
            }
        }
        return null; // Login failed

    } 

    public static void changePassword() {

    }
}
