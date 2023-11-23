package UI;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import camppackage.Camp;
import manager.CampManager;
import user.Faculty;
import user.Staff;
import user.User;

public class StaffUI {

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Function");
        System.out.println("1. Create camp");
        System.out.println("2. Edit camp");
        System.out.println("3. Delete camp");
        System.out.println("4. View camps");
        System.out.println("5. Generate student report");
        System.out.println("6.Generate committee report");
        System.out.println("7. View suggestions");
        System.out.println("8. View enquiries");
        choice = sc.nextInt();
        switch (choice) {
            case 1: {
                s.CreateCamp();
                break;
            }
            case 2: {
                s.EditCamp();
                break;
            }
            case 3: {
                s.DeleteCamp();
                break;
            }
            case 4: {
                s.ViewCamps();
                break;
            }
            case 5: {
                Staff.GenerateStudentReport();
                break;
            }
            case 6: {
                Staff.GenerateCommitteeReport();
                break;
            }
            case 7:{

            }
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}
