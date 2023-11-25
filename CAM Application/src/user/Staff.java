package user;
import manager.*;
import camppackage.*;
import java.util.*;
import util.DateHelper;
import view.*;
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
        Date startdate= util.DateHelper.stringToDate(dateString);
        System.out.println("Enter an end date (format yyyy-MM-dd):");
        dateString = sc.nextLine();
        Date enddate= util.DateHelper.stringToDate(dateString);
        System.out.println("Enter a reg end date (format yyyy-MM-dd):");
        dateString = sc.nextLine();
        Date regenddate= DateHelper.stringToDate(dateString);
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
        Camp c = new Camp(campname, startdate, enddate, regenddate, Faculty.valueOf(faculty.toUpperCase()), location, totalSlots, campCommitteeSlots, description, staffInCharge,visibility);
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

    public void ViewCreateCamps (){
        view.ViewCreatedCamps();
        Scanner sc= new Scanner(System.in);
        int ch= sc.nextInt();


    }
    public void ViewEnquiries(Camp camp){
        System.out.println(camp.getEnquiries());
    }
    public void ViewSuggestions(){
        Camp camp;
    }

    
}
