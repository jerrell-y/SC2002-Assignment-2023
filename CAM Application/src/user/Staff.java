package user;
import manager.CampManager;
import camppackage.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class Staff {
    public Staff(){};
    ArrayList<Camp> campDatabase = new ArrayList<>();

    public void CreateCamp () {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter camp name");
        String campname = sc.nextLine();
        System.out.println("Enter a start date (format yyyy-MM-dd):");
        String dateString = sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startdate = null;
        try {
            Date date = sdf.parse(dateString);
            System.out.println("Date entered: " + date);
            startdate = Calendar.getInstance();
            startdate.setTime(date);
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
        System.out.println("Enter an end date (format yyyy-MM-dd):");
        String enddateString = sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar enddate = null;
        try {
            Date date = sdf.parse(enddateString);
            System.out.println("Date entered: " + date);
            enddate = Calendar.getInstance();
            enddate.setTime(date);
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
        System.out.println("Enter a reg end date (format yyyy-MM-dd):");
        String regdateString = sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar regdate = null;
        try {
            Date date = sdf.parse(regdateString);
            System.out.println("Date entered: " + date);
            regdate = Calendar.getInstance();
            regdate.setTime(date);
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
        System.out.println("Enter Faculty");
        String faculty= sc.nextLine();
        System.out.println("Enter location");
        String location= sc.nextLine();
        System.out.println("Enter total slots");
        int totalSlots= sc.nextInt();
        System.out.println("Enter total camp committee slots");
        int campCommitteeSlots= sc.nextInt();
        System.out.println("Enter description");
        String description= sc.nextLine();
        System.out.println("Enter staff in charge");
        String staffInCharge= sc.nextLine();
        CampInfo x = new CampInfo(campname, startdate, enddate, regdate, Faculty.valueOf(faculty.toUpperCase()), location, totalSlots, campCommitteeSlots, description, staffInCharge);
        Camp c = new Camp(x, true);
        // add camp in database;
    }

    public void EditCamp (Camp campName) {
        for(int i=0;i<campDatabase.toArray().length;i++){
            if(campDatabase[i].campName)
        }

    }

    public void DeleteCamp () {
        CampManager.deleteCamp();
    }

    public void ViewCamp (Camp campName){
        campName.generateReport();
    }

    public void GenerateStudentReport (Camp campName) {campName.generateReport();
    }

    public static void GenerateCommitteeReport(Camp campName) {campName.generateReport();
    }

}
