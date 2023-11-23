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
                break;
            } else {
                System.out.println("Invalid userID or password");
            }
        }
        System.out.println("Login Succesfull");
        return loggedInUser;
    }
}
