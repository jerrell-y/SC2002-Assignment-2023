package UI;

import java.util.ArrayList;

import camppackage.Camp;
import user.Staff;
import user.Student;
import user.User;

public class MainUI {
    private User user;
    public static void main(String[] args) {
        //Import from database

        //Keeps running as once you exit StudentUI/StaffUI you come back to the main menu screen.
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("       WELCOME TO CAMS");
            System.out.println("-----------------------------");
            
            //LoginUI here
            //user = get from LoginUI
            
            if (user instanceof Student) {
                //show StudentUI
            }
            else if (user instanceof Staff) {
                //show StaffUI
            }
        }
    }
}
