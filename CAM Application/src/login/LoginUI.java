package login;

import java.util.Scanner;

public class LoginUI {
    public static void main(String[] args) {
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
    }

    private static void displayLoginForm() {
        String userID, password;
        while (true) {
            userID = inputUserID();
            password = inputPassword();
            if(LoginManager.login(userID, password)) {
                break;
            } else {
                System.out.println("Invalid userID or password");
            }
        }
        System.out.println("Login Succesfull");

    }

    private static String inputUserID() {
        Scanner sc = new Scanner(System.in);
        String userID;
        System.out.print("Enter userID: ");
        userID = sc.nextLine();
        return userID;
    }

    private static String inputPassword(){
        Scanner sc = new Scanner(System.in);
        String password;
        System.out.print("Enter Password: ");
        password = sc.nextLine();
        return password;
    }
}
