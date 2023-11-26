package UI;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import database.CampDatabase;
import login.*;
import manager.CampManager;
import manager.EnquiryManager;
import manager.SuggestionManager;
import user.Faculty;
import user.Staff;
import user.UserManager;
import util.DateHelper;
import view.*;

/*
 * The UI that handles the printing of the interface that the staff user sees.
 */
public class StaffUI {

    public static int checkValidInput(Scanner scan) {
		/*
		 * @param scan takes in the input from the scanner
		 * @return returnValue will check if the input is an integer or not
		 */
		int returnValue;
		try {
			returnValue = Integer.parseInt(scan.nextLine());
		}
		catch (NumberFormatException e) {
			returnValue = -1;
		}
		return returnValue;
	}
    public static void start(){
        int choice;
        do {
            Staff s = (Staff) UserManager.getUser();
            Scanner sc = new Scanner(System.in);
            System.out.println("================================");
            System.out.println("List of options:");
            System.out.println("1. Create camp");
            System.out.println("2. View Created camps");
            System.out.println("3. View all camps");
            System.out.println("4. Change password");
            System.out.println("5. Logout");

            System.out.println("================================");

            System.out.print("Choose an option: ");
			choice = checkValidInput(sc);
			System.out.println();
            switch (choice) {
                case 1: 
                    String dateString, campName;
                    do {
                        System.out.print("Enter camp name: ");
                        campName = sc.nextLine();
                        if (campName.isEmpty()) {
                            System.out.println("Camp name cannot be empty!\n");
                        }
                    } while (campName.isEmpty());

                    Date startdate, enddate;
                    do {
                        System.out.print("Enter a start date (format yyyy-MM-dd): ");
                        dateString = sc.nextLine();
                        startdate = util.DateHelper.stringToDate(dateString);
                        System.out.print("Enter an end date (format yyyy-MM-dd): ");
                        dateString = sc.nextLine();
                        enddate = util.DateHelper.stringToDate(dateString);
                        if (startdate.compareTo(enddate) > 0) {
                            System.out.println("Start date cannot be later than end date! \n");
                        }
                        else if (enddate.compareTo(new Date()) < 0) {
                            System.out.println("End date cannot be earlier than today! \n");
                        }
                    } while (startdate.compareTo(enddate) > 0 || enddate.compareTo(new Date()) < 0);

                    Date regenddate;
                    do {
                        System.out.print("Enter a registration end date (format yyyy-MM-dd): ");
                        dateString = sc.nextLine();
                        regenddate = DateHelper.stringToDate(dateString);
                        if (regenddate.compareTo(new Date()) < 0) {
                            System.out.println("Registration end date cannot be earlier than today! \n");
                        }
                    } while (regenddate.compareTo(new Date()) < 0);

                    String facultyString = "";
                    Faculty faculty = Faculty.NTU;
                    do {
                        try {
                            System.out.print("Enter faculty: ");
                            facultyString = sc.nextLine();
                            if (facultyString.isEmpty()) {
                                System.out.println("Faculty cannot be empty! \n");
                            }
                            else {
                                faculty = Faculty.valueOf(facultyString.toUpperCase());
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Please enter a valid faculty! \n");
                            facultyString = "";
                        }
                    } while (facultyString.isEmpty());

                    String location;
                    do {
                        System.out.print("Enter location: ");
                        location = sc.nextLine();
                        if (location.isEmpty()) {
                            System.out.println("Location cannot be empty!\n");
                        }
                    } while (location.isEmpty());

                    int campAttendeeSlots;
                    do {
                        System.out.print("Enter total camp attendee slots: ");
                        campAttendeeSlots = checkValidInput(sc);
                        if (campAttendeeSlots <= 0) {
                            System.out.println("Please enter a valid input! \n");
                        }
                    } while (campAttendeeSlots <= 0);

                    int campCommitteeSlots;
                    do {
                        System.out.print("Enter total camp committee slots: ");
                        campCommitteeSlots = checkValidInput(sc);
                        if (campCommitteeSlots <= 0) {
                            System.out.println("Please enter a valid input! \n");
                        }
                        else if (campCommitteeSlots > 10) {
                            System.out.println("The maximum number of camp committee slots is 10!\n");
                        }
                    } while (campCommitteeSlots <= 0 || campCommitteeSlots > 10);

                    String description;
                    do {
                        System.out.print("Enter description: ");
                        description = sc.nextLine();
                        if (description.isEmpty()) {
                            System.out.println("Description cannot be empty! \n");
                        }
                    } while (description.isEmpty());
                    String visiString;
                    boolean visibility = true;
                    do {
                        System.out.print("Do you want the camp to be visible? (Y/N): ");
                        visiString = sc.nextLine();
                        if (visiString.equals("Y")) {
                            visibility = true;
                        }
                        else if (visiString.equals("N")) {
                            visibility = false;
                        }
                        else {
                            System.out.println("Please enter a valid input! \n");
                        }
                    } while (!(visiString.equals("Y") || visiString.equals("N")));

                    String staffInCharge = s.getUserID();
                    boolean x = s.CreateCamp(campName, startdate, enddate, regenddate, faculty, location, campAttendeeSlots, campCommitteeSlots, description, staffInCharge, visibility);
                    if (x) {
                        System.out.println("Camp added successfully! \n");
                    } else {
                        System.out.println("There was an error.");
                    }
                    break;
                
                case 2: 
                    ViewCreatedCamps v= new ViewCreatedCamps();
                    ArrayList<Integer> CreatedCamps = v.displayCamps();
                    System.out.println();
                    int ch,subch;
                    do {
                        System.out.print("Choose a camp: ");
                        ch = checkValidInput(sc);
                        if (ch > CreatedCamps.size() || ch <= 0) {
                            System.out.println("Please enter a valid camp number! \n");
                        }
                    } while (ch > CreatedCamps.size() || ch <= 0);
                    CampManager.setCamp(CreatedCamps.get(ch-1));
                    CampManager.printDetails();
                    
                    do {
                        System.out.println("================================");
                        System.out.println("1. Edit Camp");
                        System.out.println("2. Delete Camp");
                        System.out.println("3. View and reply enquiries");
                        System.out.println("4. View suggestions");
                        System.out.println("5. Generate report");
                        System.out.println("6. View Registered Students");
                        System.out.println("7. Go back");
                        System.out.println("================================");

                        System.out.print("Choose an option: ");
                        subch = checkValidInput(sc);
                        System.out.println();
                        switch (subch) {
                            case 1: 
                                s.EditCamp();
                                break;
                            
                            case 2: 
                                String confirmString;
                                System.out.print("Are you sure you want to delete this camp? (Enter CONFIRM to continue): ");
                                confirmString = sc.nextLine();
                                System.out.println();

                                if (confirmString.equals("CONFIRM")) {
                                    if (CampDatabase.getInstance().deleteCamp(CreatedCamps.get(ch-1))) {
                                        System.out.println("Your camp was successfully deleted! \n");
                                        subch = 6;
                                    }
                                }
                                else {
                                    System.out.println("Aborted the deletion process.\n");
                                }
                                break;

                            case 3: 
                                ArrayList<Integer> allEnquiries = EnquiryManager.printAllEnquiry();

                                if (allEnquiries.size() == 0) {
                                    System.out.println("There are no enquiries!\n");
                                    break;
                                }
                                
                                int enquiryNum;
                                do {
                                    System.out.print("Select the enquiry you wish to reply to: ");
                                    enquiryNum = checkValidInput(sc);
                                    if (enquiryNum > allEnquiries.size() || enquiryNum <= 0) {
                                        System.out.println("Please enter a valid enquiry number! \n");
                                    }
                                } while (enquiryNum > allEnquiries.size() || enquiryNum <= 0);

                                EnquiryManager.setEnquiry(allEnquiries.get(enquiryNum-1));
                                
                                String reply;
                                
                                do {
                                    System.out.print("Enter reply: ");
                                    reply = sc.nextLine();
                                    if (reply.isEmpty()) {
                                        System.out.println("Reply cannot be empty! \n");
                                    }
                                } while (reply.isEmpty());

                                EnquiryManager.replyEnquiry(reply);
                                break;
                            
                            case 4: 
                                ArrayList<Integer> allSuggestions = SuggestionManager.printAllSuggestions();

                                int sugg;
                                do {
                                    System.out.print("Select the suggestion you to wish accept or reject: ");
                                    sugg = checkValidInput(sc);
                                    if (sugg > allSuggestions.size() || sugg <= 0) {
                                        System.out.println("Please enter a valid suggestion number! \n");
                                    }
                                } while (sugg > allSuggestions.size() || sugg <= 0);

                                SuggestionManager.setSuggestion(allSuggestions.get(sugg-1));

                                System.out.print("Do you approve this suggestion? (Y/N): ");
                                String approval = sc.nextLine();
                                if (approval.equals("Y")) {
                                    SuggestionManager.approveSuggestion();
                                }
                                else {
                                    System.out.println("The suggestion was not approved. \n");
                                }
                                break;
                            
                            case 5: 
                                int a;
                                do {
                                    System.out.println("================================");
                                    System.out.println("1. Generate student report");
                                    System.out.println("2. Generate committee report");
                                    System.out.println("================================");

                                    System.out.print("Choose an option: ");
                                    a = checkValidInput(sc);
                                    System.out.println();

                                    if (a<0 || a>2) {
                                        System.out.println("Please enter a valid option!");
                                    }
                                } while(a<0 || a>2);

                                if(a==1){
                                    CampManager.generateStudentReport();
                                }
                                else{
                                    CampManager.generateCommitteeReport();
                                }
                                break;

                            case 6:
                                CampManager.printStudents();
                                break;

                            case 7:
                                break;
                            
                            default:
                                System.out.println("Please enter a valid option! \n");

                        }
                    } while(subch != 7);
                    break;
                

                case 3: 
                    ViewAllCamps vac = new ViewAllCamps();
                    ArrayList<Integer> AllCamps = vac.displayCamps();
                    int campChoice;

                    do {
                        System.out.print("Choose a camp (Enter 0 to go back): ");
                        campChoice = sc.nextInt();
                        if (campChoice == 0) {
                            break;
                        }
                        if (campChoice > AllCamps.size() || campChoice <= 0) {
                            System.out.println("Please enter a valid camp number! \n");
                        }
                    } while (campChoice > AllCamps.size() || campChoice <= 0);

                    System.out.println();
                    if (campChoice == 0) {
                        break;
                    }

                    CampManager.setCamp(AllCamps.get(campChoice-1));
                    CampManager.printDetails();
                    System.out.println();
                    break;
                
                case 4: 
                    String newPass1, newPass2;
                    do {
						System.out.print("Enter your new password: ");
						newPass1 = sc.next();
						System.out.print("Enter your new password again: ");
						newPass2 = sc.next();
						if (!newPass1.equals(newPass2)) {
							System.out.println("Please enter the same password!\n");
						}
					} while (!newPass1.equals(newPass2));
                    LoginManager.changePassword(s, newPass1);
                    System.out.println("Your password has been changed successfully. Please login again");
                    choice = 5;
                    break;
                
                case 5: 
                    break;
                
                default:
                    System.out.println("Please enter a valid option!");
            }
        } while(choice != 5);
    }
}
