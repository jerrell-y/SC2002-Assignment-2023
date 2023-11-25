package UI;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import database.CampDatabase;
import login.*;
import camppackage.Camp;

import manager.CampManager;
import manager.EnquiryManager;
import manager.SuggestionManager;
import user.Faculty;
import user.Staff;
import user.User;
import user.UserManager;
import util.DateHelper;
import view.*;


public class StaffUI {
    public static void start(){
        int choice;
        do {
            Staff s = (Staff) UserManager.getUser();
            Scanner sc = new Scanner(System.in);
            System.out.println("Select Function");
            System.out.println("1. Create camp");
            System.out.println("2. View Created camps");
            System.out.println("3. View all camps");
            System.out.println("4. Change password");
            System.out.println("5. Logout");
            try {
                choice = sc.nextInt();
            } 
            catch (Exception e) {
                choice = -1;
            }
            sc.nextLine();
            switch (choice) {
                case 1: {
                    String dateString;
                    System.out.println("enter camp name");
                    String campname = sc.nextLine();
                    int c=0;
                    Date startdate, enddate;
                    do {
                        if(c>0){
                            System.out.println("start date cannot be after end date");
                        }
                        System.out.println("Enter a start date (format yyyy-MM-dd):");
                        dateString = sc.nextLine();
                        startdate = util.DateHelper.stringToDate(dateString);
                        System.out.println("Enter an end date (format yyyy-MM-dd):");
                        dateString = sc.nextLine();
                        enddate = util.DateHelper.stringToDate(dateString);
                        c++;
                    }while(startdate.compareTo(enddate)>0);
                    System.out.println("Enter a reg end date (format yyyy-MM-dd):");
                    dateString = sc.nextLine();
                    Date regenddate = DateHelper.stringToDate(dateString);
                    System.out.println("Enter Faculty");
                    String faculty = sc.nextLine();
                    System.out.println("Enter location");
                    String location = sc.nextLine();
                    System.out.println("Enter total slots");
                    int totalSlots = sc.nextInt();
                    System.out.println("Enter total camp committee slots");
                    int campCommitteeSlots = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter description");
                    String description = sc.nextLine();
                    boolean visibility;
                    System.out.println("Enter 1 if you want the camp to be visible");
                    int v = sc.nextInt();
                    if (v == 1) {
                        visibility = true;
                    } else visibility = false;
                    String staffInCharge = s.getUserID();
                    boolean x = s.CreateCamp(campname, startdate, enddate, regenddate, Faculty.valueOf(faculty.toUpperCase()), location, totalSlots, campCommitteeSlots, description, staffInCharge, visibility);
                    if (x) {
                        System.out.println("Added successfully");
                    } else {
                        System.out.println("error");
                    }
                    break;
                }
                case 2: {
                    ViewCreatedCamps v= new ViewCreatedCamps();
                    ArrayList<Integer> CreatedCamps = v.displayCamps();
                    System.out.println();
                    int ch,subch;
                    do {
                        System.out.println("Choose a camp: ");
                        ch = sc.nextInt();
                        if (ch > CreatedCamps.size() || ch <= 0) {
                            System.out.println("Please enter a valid camp number! \n");
                        }
                    } while (ch > CreatedCamps.size() || ch <= 0);
                    CampManager.setCamp(CreatedCamps.get(ch-1));
                    CampManager.printDetails();
                    
                    do{
                    System.out.println("Select Function");
                    System.out.println("1. Edit Camp");
                    System.out.println("2. Delete Camp");
                    System.out.println("3. View and reply enquiries");
                    System.out.println("4. View suggestions");
                    System.out.println("5. Generate report");
                    System.out.println("6. Go back");
                    subch = sc.nextInt();
                    switch (subch) {
                        case 1: {
                            s.EditCamp();
                            break;
                        }
                        case 2: {
                            int ID = sc.nextInt();
                            CampDatabase.getInstance().deleteCamp(ID);
                            break;
                        }
                        case 3: {
                            EnquiryManager.printAllEnquiry();
                            System.out.println("Select the enquiry you wish to reply to: ");
                            int enquiryNum = sc.nextInt();
                            sc.nextLine();
                            EnquiryManager.setEnquiry(enquiryNum);
                            String reply;
                            System.out.println("Enter reply");
                            reply=sc.nextLine();
                            EnquiryManager.replyEnquiry(reply);
                            break;
                        }
                        case 4: {
                            SuggestionManager.printAllSuggestions();
                            System.out.println("Select the suggestion you wish accept or reject to: ");
                            int sugg = sc.nextInt();
                            sc.nextLine();
                            SuggestionManager.setSuggestion(sugg);
                            System.out.println("enter 1 to accept");
                            int x=sc.nextInt();
                            sc.nextLine();
                            if(x==1){
                                SuggestionManager.approveSuggestion();
                            }

                            break;
                        }
                        case 5: {
                            int a;
                            do{
                                System.out.println("1. Generate student report");
                                System.out.println("2. Generate committee report");
                                a=sc.nextInt();
                            }
                            while(a<0 || a>2);
                            if(a==1){
                                CampManager.generateStudentReport();
                            }
                            else{
                                CampManager.generateCommitteeReport();

                            }
                            break;
                        }
                        case 6: {
                            break;
                        }
                        default:
                            throw new IllegalStateException("Unexpected value: " + choice);
                    }
                    break;
                }while(subch<=0 && subch>6);
                    break;
                }
                case 3: {
                    ViewAllCamps v= new ViewAllCamps();
                    ArrayList<Integer> AllCamps = v.displayCamps();
                    System.out.println();
                    break;
                }
                case 4: {
                    String newPW;
                    System.out.println("Enter new password");
                    newPW = sc.nextLine();
                    LoginManager.changePassword(s, newPW);
                    break;
                }
                case 5: {
                    break;
                }
                default:
                    System.out.println("Please enter a valid option!");
            }
        }while(choice!=5);
    }
}
