package UI;

import user.Staff;
import user.Student;
import user.User;
import user.UserManager;
import login.LoginUI;

/**
 * The Main program and UI that handles the printing of the interface that the user sees.
 */
public class MainUI {

    /**
     * The current user of the program.
     */
    private static User user;

    /**
     * The main program. Called at the start of excution.
     * @param args The argument entered.
     */
    public static void main(String[] args) {
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
