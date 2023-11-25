package user;
import database.CampDatabase;
import manager.*;
import camppackage.*;
import java.util.*;
import login.*;
import util.DateHelper;
import view.*;
import java.text.SimpleDateFormat;
import database.*;
public class Staff extends User{
    static ArrayList<Camp> createdCampList=new ArrayList<>();
    public Staff(String userID, String password, String name, Faculty faculty) {
        super(userID, password, name, faculty);
    }
    Staff s;
    public static void CreateCamp() {
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
        createdCampList.add(c);
        CampDatabase.getInstance().addCamp(c);


    }
    public void EditCamp () {
        int campID;
        Scanner sc= new Scanner(System.in);
        System.out.println("enter camp ID you wish to edit");
        campID=sc.nextInt();
        System.out.println("Enter the attribute you wish to edit");
        System.out.println("1. name");
        System.out.println("2. Start Date");
        System.out.println("3. End Date");
        System.out.println("4. Registration end date");
        System.out.println("5. Faculty");
        System.out.println("6. Location");
        System.out.println("7. Total slots");
        System.out.println("8. Camp committee slots");
        System.out.println("9. description");
        System.out.println("10. Visibility");
        System.out.println("11. Go back");
        int ch=sc.nextInt();
        switch(ch){
            case 1:{
                System.out.println("Enter new name");
            }
            case 2:{
                System.out.println("Enter new start date");
            }
            case 3:{
                System.out.println("Enter new end date");
            }
            case 4:{
                System.out.println("Enter new registration end date");
            }
            case 5:{
                System.out.println("Enter new faculty");
            }
            case 6:{
                System.out.println("Enter new location");
            }
            case 7:{
                System.out.println("Enter new total slots");
            }
            case 8:{
                System.out.println("Enter new camp committee slots");
            }
            case 9:{
                System.out.println("Enter new description");
            }
            case 10:{
                System.out.println("Enter new visibility: 1 for on, 0 for off");
            }
        }
    }
    public void DeleteCamp (Camp c) {
        CampDatabase.getInstance().deleteCamp(c);
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
