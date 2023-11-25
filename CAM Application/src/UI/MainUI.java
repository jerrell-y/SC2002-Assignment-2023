package UI;

import user.Staff;
import user.Student;
import user.User;
import user.UserManager;
import login.LoginUI;

public class MainUI {
    private static User user;

    public static void main(String[] args) {
        // Keeps running as once you exit StudentUI/StaffUI you come back to the main
        // menu screen.
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("       WELCOME TO CAMS");
            System.out.println("-----------------------------");

            // LoginUI here
            user = LoginUI.displayLoginForm();
            if (!LoginUI.getChangedDefaultPassword()) {
                UserManager.setUser(user);
                if (user instanceof Student) {
                    System.out.println("Student logged in successfully. \nWelcome back, " + user.getUserID() + ".\n");
                    StudentUI.start();
                } else if (user instanceof Staff) {
                    // show StaffUI
                    System.out.println("Staff logged in successfully. \nWelcome back, " + user.getUserID() + ".\n");
                    StaffUI.start();
                }
            } else {
                LoginUI.setChangedDefaultPassword(false);
            }

        }
    }
}
