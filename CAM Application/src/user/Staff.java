package user;
import database.CampDatabase;
import manager.*;
import camppackage.*;
import java.util.*;
import login.*;
import util.DateHelper;

import java.text.SimpleDateFormat;
import database.*;
public class Staff extends User{
    public Staff(String userID, String password, String name, Faculty faculty) {
        super(userID, password, name, faculty);
    }

    public boolean CreateCamp(String campName, Date startDate, Date endDate, Date regEndDate, Faculty faculty, String location, int campAttendeeSlots, int campCommiteeSlots, String description, String staffInCharge, boolean visibility){
        Camp c = new Camp(campName, startDate, endDate, regEndDate, faculty, location, campAttendeeSlots, campCommiteeSlots, description, staffInCharge,visibility);
        boolean x=CampDatabase.getInstance().addCamp(c);
        return x;
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
    /*public void DeleteCamp (int ID) {

        CampDatabase.getInstance().deleteCamp(c);
    }

     */
    public void ViewEnquiries(Camp camp){
        System.out.println(camp.getEnquiries());
    }
    //public void GenerateReport(){}
    public void ViewSuggestions(){
        SuggestionManager.printAllSuggestions();
    }
}
