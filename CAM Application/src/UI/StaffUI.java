import java.lang.String;
import java.util.Scanner;
import user.Staff;
public class StaffUI {
    public void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Select Function");
        System.out.println("1. Create camp");
        System.out.println("2. Edit camp");
        System.out.println("3. Delete camp");
        System.out.println("4. View camps");
        System.out.println("5. Generate student report");
        System.out.println("6.Generate committee report");
        choice = sc.nextInt();
        switch (choice) {
            case 1: {
                Staff.CreateCamp();
                break;
            }
            case 2: {
                String campName;
                Staff.EditCamp(Camp campName);
                break;
            }
            case 3: {
                Staff.DeleteCamp();
                break;
            }
            case 4: {
                String campName;
                Staff.ViewCamp(Camp campName);
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
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
