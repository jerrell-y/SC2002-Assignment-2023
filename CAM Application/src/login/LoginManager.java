package login;

import user.User;
import database.CampDatabase;
import database.UserDatabase;
import format.CampFormatter;

import java.util.ArrayList;

import camppackage.Camp;
import camppackage.Enquiry;
import camppackage.Suggestion;

/**
 * The manager that handles the methods regarding logins.
 */
public class LoginManager {
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

        // TESTING
        ArrayList<Camp> camps = campDB.getCamps();
        if (!camps.isEmpty()) {
            System.out.println("CampDatabase has been initialized and loaded correctly!");
            for (Camp camp : camps) {
                CampFormatter cf = new CampFormatter();
                System.out.println(cf.formatFull(camp));
                ArrayList<Enquiry> e = camp.getEnquiries();
                for (Enquiry i : e) {
                    System.out.println(i.getContent() + "," + i.getName() + "," + i.getUserID() + "," + i.getReply() + "," + i.isAnswered());
                }

                ArrayList<Suggestion> s = camp.getSuggestions();
                for (Suggestion j : s){
                    System.out.println(j.getContent() + "," + j.getName() + "," + j.getUserID() + "," + j.getStatus());
                }
            }
        } else {
            System.out.println("CampDatabase is empty or not initialized correctly.");
        }
        // TESTING

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
