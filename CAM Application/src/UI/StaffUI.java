package UI;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import login.*;
import camppackage.Camp;
import manager.OldCampManager;
import user.Faculty;
import user.Staff;
import user.User;
import user.UserManager;
import view.*;

public class StaffUI {
    public static void start(){
        Staff s = (Staff) UserManager.getUser();
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Function");
        System.out.println("1. Create camp");
        System.out.println("2. View Created camps");
        System.out.println("3. View all camps");
        System.out.println("4. Change password");
        System.out.println("5. Logout");
        choice = sc.nextInt();
        switch (choice) {
            case 1: {
                s.CreateCamp();
                break;
            }
            case 2: {
                s.ViewCreateCamps();
                System.out.println("Select Function");
                System.out.println("1. Edit Camp");
                System.out.println("2. Delete Camp");
                System.out.println("3. View enquiries");
                System.out.println("4. View suggestions");
                System.out.println("5. Generate report");
                System.out.println("6. Go back");
                int ch = sc.nextInt();
                switch (ch) {
                    case 1: {
                        s.EditCamp();
                        break;
                    }
                    case 2: {
                        s.DeleteCamp();
                        break;
                    }
                    case 3: {
                        s.ViewEnquiries();
                        break;
                    }
                    case 4: {
                        s.ViewSuggestion();
                        break;
                    }
                    case 5: {
                        s.generateReport();
                        break;
                    }
                    case 6: {
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + choice);
                }
                break;
            }
            case 3: {
                s.ViewCreateCamps();
                break;
            }
            case 4: {
                String newPW;
                System.out.println("Enter new password");
                newPW=sc.nextLine();
                LoginManager.changePassword(s,newPW);
                break;
            }
            case 5: {
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}
