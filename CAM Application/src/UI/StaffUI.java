package UI;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("enter camp name");
                    String campname = sc.nextLine();
                    System.out.println("Enter a start date (format yyyy-MM-dd):");
                    String dateString = sc.nextLine();
                    Date startdate = util.DateHelper.stringToDate(dateString);
                    System.out.println("Enter an end date (format yyyy-MM-dd):");
                    dateString = sc.nextLine();
                    Date enddate = util.DateHelper.stringToDate(dateString);
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
                    System.out.println("Enter description");
                    String description = sc.nextLine();
                    System.out.println("Enter staff in charge");
                    String staffInCharge = sc.nextLine();
                    boolean visibility;
                    System.out.println("Enter 1 if you want the camp to be visible");
                    int v = sc.nextInt();
                    if (v == 1) {
                        visibility = true;
                    } else visibility = false;
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
                            System.out.println("Enter Camp ID of camp you wish to delete");
                            int ID = sc.nextInt();
                            s.DeleteCamp(ID);
                            break;
                        }
                        case 3: {
                            Camp c=null;
                            s.ViewEnquiries(c);
                            break;
                        }
                        case 4: {
                            s.ViewSuggestions();
                            break;
                        }
                        case 5: {
                            s.GenerateReport();
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
                    s.ViewCreatedCamps();
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
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }while(choice!=4);
    }
}
