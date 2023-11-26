package user;
import database.CampDatabase;
import manager.*;
import camppackage.*;
import java.util.*;

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
        Camp c = CampManager.getCamp();
        Scanner sc= new Scanner(System.in);
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
        sc.nextLine();
        switch(ch){
            case 1:{
                System.out.println("Enter new name");
                String name=sc.nextLine();
                c.setCampName(name);
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
            case 2:{
                System.out.println("Enter new start date");
                String date=sc.nextLine();
                Date d=util.DateHelper.stringToDate(date);
                c.setStartDate(d);
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
            case 3:{
                System.out.println("Enter new end date");
                String date=sc.nextLine();
                Date d=util.DateHelper.stringToDate(date);
                c.setEndDate(d);
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
            case 4:{
                System.out.println("Enter new registration end date");
                String date=sc.nextLine();
                Date d=util.DateHelper.stringToDate(date);
                c.setRegEndDate(d);
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
            case 5:{
                System.out.println("Enter new faculty");
                String f=sc.nextLine();
                c.setFaculty(Faculty.valueOf(f.toUpperCase()));
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
            case 6:{
                System.out.println("Enter new location");
                String location=sc.nextLine();
                c.setLocation(location);
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
            case 7:{
                System.out.println("Enter new total slots");
                int i =sc.nextInt();
                sc.nextLine();
                c.setCampAttendeeSlots(i);
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
            case 8:{
                System.out.println("Enter new camp committee slots");
                int i=sc.nextInt();
                sc.nextLine();
                c.setCampCommiteeSlots(i);
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
            case 9:{
                System.out.println("Enter new description");
                String des=sc.nextLine();
                c.setDescription(des);
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
            case 10:{
                System.out.println("Enter new visibility: 1 for on, 0 for off");
                int x= sc.nextInt();
                sc.nextLine();
                if(x==1) {
                    c.setVisibility(true);
                }
                else c.setVisibility(false);
                CampDatabase.getInstance().update();
                System.out.println("DONE");
                break;
            }
        }
    }
    public void DeleteCamp (int ID) {
        Camp c= CampManager.getCamp();
        CampDatabase.getInstance().deleteCamp(c.getCampID());

    }

    public void ViewEnquiries(Camp camp){
        System.out.println(camp.getEnquiries());
    }



    public void GenerateReport(){}
    //public void GenerateReport(){}
    public void ViewSuggestions(){
        SuggestionManager.printAllSuggestions();
    }
}
