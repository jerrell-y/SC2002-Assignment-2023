package UI;

import user.Staff;
import user.Student;
import user.User;
import user.UserManager;
import login.LoginUI;

public class MainUI {
    private static User user;
    public static void main(String[] args) {
        //Keeps running as once you exit StudentUI/StaffUI you come back to the main menu screen.
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("       WELCOME TO CAMS");
            System.out.println("-----------------------------");
            
            //LoginUI here
            user = LoginUI.displayLoginForm();
            UserManager.setUser(user);
            if (user instanceof Student) {
                System.out.println("Student logged in");
                StudentUI.start();
            }
            else if (user instanceof Staff) {
                //show StaffUI
                System.out.println("Staff logged in");
                StaffUI.start();
            }
        }
    }
}
