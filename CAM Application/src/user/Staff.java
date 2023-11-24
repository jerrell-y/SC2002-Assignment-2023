package user;
import manager.*;
import camppackage.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class Staff extends User{


    public Staff(String userID, String password, String name, Faculty faculty) {
        super(userID, password, name, faculty);
    }
    public void CreateCamp() {
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
        boolean visibility;
        System.out.println("Enter 1 if you want the camp to be visible");
        int v=sc.nextInt();
        if(v==1){
            visibility=true;
        }
        else visibility=false;
        Camp c = new Camp(campname, startdate, enddate, regdate, Faculty.valueOf(faculty.toUpperCase()), location, totalSlots, campCommitteeSlots, description, staffInCharge,visibility);
        OldCampManager.addCamp(c);

    }

    public void EditCamp () {
        String campName;
        System.out.println("enter camp name you wish to edit");
        campName=sc.nextLine();
        OldCampManager.EditCamp(campName);

    }

    public void DeleteCamp () {
        Camp c;
        OldCampManager.deleteCamp(c);
    }

    public void ViewCamps (){
        campName.generateReport();
    }

    public void GenerateStudentReport (Camp campName) {
        campName.generateReport();
    }

    public static void GenerateCommitteeReport(Camp campName) {
        campName.generateReport();
    }
    public void ViewEnquiries(Camp camp){
        System.out.println(camp.getEnquiries());
    }
    public void Answer(Camp camp){
        camp.answerEnquiry();
    }

}
