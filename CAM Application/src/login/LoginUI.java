package login;

import java.util.Scanner;

import user.User;

public class LoginUI {
    /* public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("=============================================");
            System.out.println("                CAMs Login                   ");
            System.out.println("=============================================");
            System.out.println("(1) Login");
            System.out.println("(2) Exit Program");
            System.out.print("Select an option: ");
            choice = sc.nextInt();
            if(choice == 1){
                displayLoginForm();
            }
        } while (choice != 2);
        sc.close();
    } */
    private static boolean changedDefaultPassword = false;

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
						newPass1 = sc.next();
						System.out.print("Enter your new password again: ");
						newPass2 = sc.next();
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

    public static boolean getChangedDefaultPassword() {
        return changedDefaultPassword;
    }

    public static void setChangedDefaultPassword(boolean changedDefaultPassword) {
        LoginUI.changedDefaultPassword = changedDefaultPassword;
    }

}
