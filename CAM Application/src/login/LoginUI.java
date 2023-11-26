package login;

import java.util.Scanner;

import user.User;

/**
 * The LoginUI class handles the user interface for login operations.
 * It prompts the user for login credentials and communicates with the LoginManager
 * to authenticate users. It also enforces password change if the default password is detected.
 */
public class LoginUI {
    private static boolean changedDefaultPassword = false;

    /**
     * Displays a login form to the user, prompts for user ID and password, and performs the login operation.
     * If the user is logged in with a default password, it forces a password change before proceeding.
     *
     * @return The authenticated User object if login is successful, null otherwise.
     */
    public static User displayLoginForm() {
        Scanner sc = new Scanner(System.in);
        String userID, password;
        User loggedInUser;
        while (true) {
            System.out.print("Enter userID: ");
            userID = sc.nextLine();
            System.out.print("Enter Password: ");
            password = sc.nextLine();
            loggedInUser = LoginManager.login(userID, password);
            if(loggedInUser != null) {
                if (LoginManager.isDefaultPassword(loggedInUser)) {
                    System.out.println("You are using the default password. You must change your password now.");
                    String newPass1, newPass2;
                    do {
						System.out.print("Enter your new password: ");
						newPass1 = sc.nextLine();
						System.out.print("Enter your new password again: ");
						newPass2 = sc.nextLine();
						if (!newPass1.equals(newPass2)) {
							System.out.println("Please enter the same password!\n");
						}
					} while (!newPass1.equals(newPass2));
                    LoginManager.changePassword(loggedInUser, newPass1);
                    System.out.println("Your password has been changed successfully. Please login again");
                    changedDefaultPassword = true;
                }
                break;
            } else {
                System.out.println("Invalid userID or password");
            }
        }
        return loggedInUser;
    }

    /**
     * Retrieves the status of whether the default password has been changed during the current session.
     *
     * @return true if the default password was changed, false otherwise.
     */
    public static boolean getChangedDefaultPassword() {
        return changedDefaultPassword;
    }

    /**
     * Sets the status of whether the default password has been changed during the current session.
     *
     * @param changedDefaultPassword The new status to set.
     */
    public static void setChangedDefaultPassword(boolean changedDefaultPassword) {
        LoginUI.changedDefaultPassword = changedDefaultPassword;
    }

}
