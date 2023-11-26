package user;
import database.CampDatabase;
import manager.*;
import camppackage.*;
import java.util.*;

import UI.StaffUI;

/*
 * class containing the staff information, as well as the methods that a staff is able of using. It is the child class of user
 */

public class Staff extends User{
    /*
     * constructor that creates the staff object
     * @param userID is the current userID
     * @param password is the password of the staff
     * @param name is the real name of the staff
     * @faculty is the faculty the staff belongs to
     */
    public Staff(String userID, String password, String name, Faculty faculty) {
        super(userID, password, name, faculty);
    }
    /*
     * function creates camps by taking in the camp information as the parameters
     */
    public boolean CreateCamp(String campName, Date startDate, Date endDate, Date regEndDate, Faculty faculty, String location, int campAttendeeSlots, int campCommiteeSlots, String description, String staffInCharge, boolean visibility){
        Camp c = new Camp(campName, startDate, endDate, regEndDate, faculty, location, campAttendeeSlots, campCommiteeSlots, description, staffInCharge,visibility);
        boolean x=CampDatabase.getInstance().addCamp(c);
        return x;
    }
    /*
     * function for editing camps
     */
    public void EditCamp () {
        Camp c = CampManager.getCamp();
        if (c.getCampAttendees().size() != 0 || c.getCampCommittees().size() != 0) {
            System.out.println("You cannot edit this camp because there are existing attendees/committee members!\n");
            return;
        }
        Scanner sc= new Scanner(System.in);
        int ch;
        do {
            System.out.println("================================");
            System.out.println("1. Camp Name");
            System.out.println("2. Start Date");
            System.out.println("3. End Date");
            System.out.println("4. Registration end date");
            System.out.println("5. Faculty");
            System.out.println("6. Location");
            System.out.println("7. Camp attendee slots");
            System.out.println("8. Camp committee slots");
            System.out.println("9. Description");
            System.out.println("10. Visibility");
            System.out.println("11. Go back");
            System.out.println("================================");

            System.out.print("Enter the attribute you wish to edit: ");
            ch = StaffUI.checkValidInput(sc);
            System.out.println();
            switch (ch) {
                case 1:
                    String campName;
                    do {
                        System.out.print("Enter new camp name: ");
                        campName = sc.nextLine();
                        if (campName.isEmpty()) {
                            System.out.println("Camp name cannot be empty!\n");
                        }
                    } while (campName.isEmpty());

                    c.setCampName(campName);
                    CampDatabase.getInstance().update();
                    System.out.println("Successfully changed camp name to \"" + campName + "\".");
                    break;
                
                case 2:
                    String startDateStr;
                    Date startDate;
                    do {
                        System.out.print("Enter new start date: ");
                        startDateStr=sc.nextLine();
                        startDate = util.DateHelper.stringToDate(startDateStr);
                        if (startDate.compareTo(c.getEndDate()) > 0) {
                            System.out.println("Start date cannot be after end date! \n");
                        }
                    } while (startDate.compareTo(c.getEndDate()) > 0);
                    c.setStartDate(startDate);
                    CampDatabase.getInstance().update();
                    System.out.println("Successfully changed start date to \"" + startDateStr + "\".");
                    break;
                
                case 3:
                    String endDateStr;
                    Date endDate;
                    do {
                        System.out.print("Enter new end date: ");
                        endDateStr=sc.nextLine();
                        endDate = util.DateHelper.stringToDate(endDateStr);
                        if (c.getStartDate().compareTo(endDate) > 0) {
                            System.out.println("End date cannot be before start date! \n");
                        }
                        else if (endDate.compareTo(new Date()) < 0) {
                            System.out.println("End date cannot be earlier than today! \n");
                        }
                    } while (c.getStartDate().compareTo(endDate) > 0 || endDate.compareTo(new Date()) < 0);

                    c.setEndDate(endDate);
                    CampDatabase.getInstance().update();
                    System.out.println("Successfully changed end date to \"" + endDateStr + "\".");
                    break;
                
                case 4:
                    String date;
                    Date regEndDate;
                    do {    
                        System.out.print("Enter new registration end date: ");
                        date = sc.nextLine();
                        regEndDate = util.DateHelper.stringToDate(date);
                        if (regEndDate.compareTo(new Date()) < 0) {
                            System.out.println("Registration end date cannot be earlier than today! \n");
                        }
                    } while (regEndDate.compareTo(new Date()) < 0);

                    c.setRegEndDate(regEndDate);
                    CampDatabase.getInstance().update();
                    System.out.println("Successfully changed registration end date to \"" + date + "\".");
                    break;
                
                case 5:
                    String facultyString = "";
                    Faculty faculty = Faculty.NTU;
                    do {
                        try {
                            System.out.print("Enter new faculty: ");
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

                    c.setFaculty(faculty);
                    CampDatabase.getInstance().update();
                    System.out.println("Successfully changed faculty to \"" + facultyString + "\".");
                    break;
                
                case 6:
                    String location;
                    do {
                        System.out.print("Enter new location: ");
                        location = sc.nextLine();
                        if (location.isEmpty()) {
                            System.out.println("Location cannot be empty!\n");
                        }
                    } while (location.isEmpty());

                    c.setLocation(location);
                    CampDatabase.getInstance().update();
                    System.out.println("Successfully changed location to \"" + location + "\".");
                    break;
                
                case 7:
                    int campAttendeeSlots;
                    do {
                        System.out.print("Enter new camp attendee slots: ");
                        campAttendeeSlots = StaffUI.checkValidInput(sc);
                        if (campAttendeeSlots <= 0) {
                            System.out.println("Please enter a valid input! \n");
                        }
                    } while (campAttendeeSlots <= 0);

                    c.setCampAttendeeSlots(campAttendeeSlots);
                    CampDatabase.getInstance().update();
                    System.out.println("Successfully changed camp attendee slots to " + campAttendeeSlots + ".");
                    break;
                
                case 8:
                    int campCommitteeSlots;
                    do {
                        System.out.print("Enter new camp committee slots: ");
                        campCommitteeSlots = StaffUI.checkValidInput(sc);
                        if (campCommitteeSlots <= 0) {
                            System.out.println("Please enter a valid input! \n");
                        }
                        else if (campCommitteeSlots > 10) {
                            System.out.println("The maximum number of camp committee slots is 10!\n");
                        }
                    } while (campCommitteeSlots <= 0 || campCommitteeSlots > 10);

                    c.setCampCommitteeSlots(campCommitteeSlots);
                    CampDatabase.getInstance().update();
                    System.out.println("Successfully changed camp committee slots to " + campCommitteeSlots + ".");
                    break;
                
                case 9:
                    String description;
                    do {
                        System.out.print("Enter new description: ");
                        description = sc.nextLine();
                        if (description.isEmpty()) {
                            System.out.println("Description cannot be empty! \n");
                        }
                    } while (description.isEmpty());

                    c.setDescription(description);
                    CampDatabase.getInstance().update();
                    System.out.println("Successfully changed description to \"" + description + "\".");
                    break;
                
                case 10:
                    String visiString;
                    do {
                        System.out.print("Enter new visibility (Y/N): ");
                        visiString = sc.nextLine();
                        if (visiString.equals("Y")) {
                            c.setVisibility(true);
                            System.out.println("Successfully changed visibility to true.");
                        }
                        else if (visiString.equals("N")) {
                            c.setVisibility(false);
                            System.out.println("Successfully changed visibility to false.");
                        }
                        else {
                            System.out.println("Please enter a valid input! \n");
                        }
                    } while (!(visiString.equals("Y") || visiString.equals("N")));
                    CampDatabase.getInstance().update();
                    break;
                
                case 11:
                    break;

                default:
                    System.out.println("Please enter a valid option! \n");
            }
        } while (ch != 11);
    }
   /* public void DeleteCamp (int ID) {
        Camp c= CampManager.getCamp();
        CampDatabase.getInstance().deleteCamp(c.getCampID());

    }

    */

    public void ViewEnquiries(Camp camp){
        System.out.println(camp.getEnquiries());
    }



    public void GenerateReport(){}
    //public void GenerateReport(){}
    public void ViewSuggestions(){
        SuggestionManager.printAllSuggestions();
    }
}
