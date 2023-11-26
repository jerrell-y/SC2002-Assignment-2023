package camppackage;

import java.util.ArrayList;
import java.util.Date;

import user.Faculty;
/**
 * Represents a camp that exists in the database.
 * A camp can have many campAttendees and campCommittes.
 */
public class Camp{
    /**
     * The unique ID of this camp.
     */
    private int campID;

    /**
     * The name of the camp.
     */
    private String campName;

    /**
     * The starting date of the camp.
     */
    private Date startDate;

    /**
     * The ending date of the camp.
     */
    private Date endDate;

    /**
     * The latest date to register for the camp.
     */
    private Date regEndDate;

    /**
     * The faculty that this camp is open to. NTU represents open to all students.
     */
    private Faculty faculty;

    /**
     * The location where this camp is taking place in.
     */
    private String location;

    /**
     * The remaining number of camp attendee slots left.
     */
    private int campAttendeeSlots;

    /**
     * The remaining number of camp committee slots left.
     */
    private int campCommitteeSlots;

    /**
     * A simple description of the camp.
     */
    private String description;

    /**
     * The userID of the Staff-In-Charge
     */
    private String staffInCharge;

    /**
     * Whether the camp is visible to students or not. True represents visible, while false represents not visible.
     */
    private boolean visibility;

    /**
     * A list of userID of all student attendees for the camp.
     */
    private ArrayList<String> campAttendees;

    /**
     * A list of userID of all student committees for the camp.
     */
    private ArrayList<String> campCommittees;

    /**
     * A list of userID of all students who have withdrawn from the camp.
     */
    private ArrayList<String> blacklist;

    /**
     * A list of enquiries related to the camp.
     */
    private ArrayList<Enquiry> enquiries;

    /**
     * A list of suggestions related to the camp.
     */
    private ArrayList<Suggestion> suggestions;

    /**
     * The number of total camps generated so far.
     */
    private static int totalCamps;

    /**
     * Creates a new camp with the given camp information. 
     * The list of attendees, committees, blacklist, enquiries and suggestions are all default to an empty list.
     * A new campID is given based on the totalCamps variable.
     * @param campName This camp's name.
     * @param startDate This camp's start date.
     * @param endDate This camp's end date.
     * @param regEndDate This camp's registration end date.
     * @param faculty This camp's faculty.
     * @param location This camp's location.
     * @param campAttendeeSlots This camp's camp attendee slots.
     * @param campCommitteeSlots This camp's camp committee slots.
     * @param description This camp's description.
     * @param staffInCharge This camp's Staff-In-Charge.
     * @param visibility This camp's visibility.
     */
    public Camp (String campName, Date startDate, Date endDate, Date regEndDate, Faculty faculty, String location, int campAttendeeSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility) {
        this.campID = ++totalCamps;
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regEndDate = regEndDate;
        this.faculty = faculty;
        this.location = location;
        this.campAttendeeSlots = campAttendeeSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.visibility = visibility;
        campAttendees = new ArrayList<String>();
        campCommittees = new ArrayList<String>();
        blacklist = new ArrayList<String>();
        enquiries = new ArrayList<Enquiry>();
        suggestions = new ArrayList<Suggestion>();
    }

    /**
     * Creates a new camp with the given camp information.
     * Used during database loading, when the campID, list of attendees, committees, blacklist, enquiries and suggestions are not empty.
     * @param campID This camp's unique ID.
     * @param campName This camp's name.
     * @param startDate This camp's start date.
     * @param endDate This camp's end date.
     * @param regEndDate This camp's registration end date.
     * @param faculty This camp's faculty.
     * @param location This camp's location.
     * @param campAttendeeSlots This camp's camp attendee slots.
     * @param campCommitteeSlots This camp's camp committee slots.
     * @param description This camp's description.
     * @param staffInCharge This camp's Staff-In-Charge.
     * @param visibility This camp's visibility.
     * @param campAttendees This camp's list of attendees.
     * @param campCommittees This camp's list of committees.
     * @param blacklist This camp's blacklist.
     * @param enquiries This camp's list of enquiries.
     * @param suggestions This camp's list of suggestions.
     */
    public Camp (int campID, String campName, Date startDate, Date endDate, Date regEndDate, Faculty faculty, String location, int campAttendeeSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility, ArrayList<String> campAttendees, ArrayList<String> campCommittees, ArrayList<String> blacklist, ArrayList<Enquiry> enquiries, ArrayList<Suggestion> suggestions) {
        this.campID = campID;
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regEndDate = regEndDate;
        this.faculty = faculty;
        this.location = location;
        this.campAttendeeSlots = campAttendeeSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.visibility = visibility;
        this.campAttendees = campAttendees;
        this.campCommittees = campCommittees;
        this.blacklist = blacklist;
        this.enquiries = enquiries;
        this.suggestions = suggestions;
    }

    /**
     * Sets the total number of camps generated so far.
     * Used only during initialisation of database.
     * @param count The new total number of camps generated.
     */
    public static void setTotalCamps(int count) {
        Camp.totalCamps = count;
    }

    /**
     * Sets the unique campID of the camp.
     * Should not be used except during initialisation.
     * @param campID The camp's new unique ID;
     */
    public void setCampID(int campID) {
        this.campID = campID;
    }

    /**
     * Gets the unique campID for the camp.
     * @return this camp's unique ID.
     */
    public int getCampID() {
        return campID;
    }

    /**
     * Sets the name of the camp.
     * Used for updating.
     * @param campName This camp's new name.
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Gets the name of the camp.
     * @return this camp's name.
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Sets the start date of the camp. The start date should not be later than the end date.
     * @param startDate This camp's new start date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the start date of the camp.
     * @return this camp's start date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the end date of the camp. The end date should not be earlier than the start date. The end date should also not be earlier than the current date.
     * @param endDate This camp's new end date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the end date of the camp.
     * @return this camp's end date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the registration end date of the camp. The registration end date should not be earlier than the current date.
     * @param regEndDate This camp's new registration end date
     */
    public void setRegEndDate(Date regEndDate) {
        this.regEndDate = regEndDate;
    }

    /**
     * Gets the registration end date of the camp.
     * @return this camp's registration end date.
     */
    public Date getRegEndDate() {
        return regEndDate;
    }

    /**
     * Sets the faculty that the camp is open to. Uses an enum called Faculty that contains the list of faculties.
     * @param faculty This camp's new faculty.
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * Gets the faculty of the camp.
     * @return this camp's faculty.
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Sets the location that the camp is located in. 
     * @param location This camp's new location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the location that the camp is located in.
     * @return this camp's location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the remaining number of attendee slots left. Must be 0 or positive number.
     * @param campAttendeeSlots This camp's new camp attendee slots.
     */
    public void setCampAttendeeSlots(int campAttendeeSlots) {
        this.campAttendeeSlots = campAttendeeSlots;
    }

    /**
     * Gets the remaining number of attendee slots left. 
     * @return this camp's attendee slots.
     */
    public int getCampAttendeeSlots() {
        return campAttendeeSlots;
    }

    /**
     * Sets the remaining number of committee slots left. Must be between 1 and 10.
     * @param campCommitteeSlots This camp's new camp committee slots.
     */
    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    /**
     * Gets the remaining number of committee slots left.
     * @return this camp's committee slots.
     */
    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    /**
     * Sets a simple description of the camp.
     * @param description This camp's new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the simple description of the camp.
     * @return this camp's new description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Staff-In-Charge of the camp. Default set to the userID of the staff that created the camp.
     * @param staffInCharge This camp's new Staff-In-Charge
     */
    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    /**
     * Gets the Staff-In-Charge of the camp.
     * @return this camp's Staff-In-Charge
     */
    public String getStaffInCharge() {
        return staffInCharge;
    }

    /**
     * Sets the visibility of the camp to students. True for visible, false for not.
     * @param visibility this camp's new visibility.
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * Gets the visibility of the camp to students.
     * @return this camp's visibility.
     */
    public boolean getVisibility() {
        return visibility;
    }

    /**
     * Gets the list of userID of attendees of the camp.
     * @return this camp's attendees list.
     */
    public ArrayList<String> getCampAttendees() {
        return campAttendees;
    }

    /**
     * Sets the list of userID of attendees of the camp.
     * @param campAttendees This camp's new attendees list.
     */
    public void setCampAttendees(ArrayList<String> campAttendees) {
        this.campAttendees = campAttendees;
    }

    /**
     * Gets the list of userID of committees of the camp.
     * @return this camp's committee list.
     */
    public ArrayList<String> getCampCommittees() {
        return campCommittees;
    }

    /**
     * Sets the list of userID of committees of the camp.
     * @param campCommittees This camp's new committees list
     */
    public void setCampCommittees(ArrayList<String> campCommittees) {
        this.campCommittees = campCommittees;
    }

    /**
     * Gets the list of userID of students who have withdrawn from the camp. They are not allowed to register again.
     * @return this camp's blacklist.
     */
    public ArrayList<String> getBlacklist() {
        return blacklist;
    }

    /**
     * Sets the list of userID of students who have withdrawn from the camp.
     * @param blacklist This camp's blacklist.
     */
    public void setBlacklist(ArrayList<String> blacklist) {
        this.blacklist = blacklist;
    }

    /**
     * Gets the list of enquiries related to the camp.
     * @return this camp's enquiries.
     */
    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }

    /**
     * Sets the list of enquiries related to the camp.
     * @param enquiries This camp's enquiries.
     */
    public void setEnquiries(ArrayList<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }

    /**
     * Gets the list of suggestions related to the camp.
     * @return this camp's suggestions.
     */
    public ArrayList<Suggestion> getSuggestions() {
        return suggestions;
    }

    /**
     * Sets the list of suggestions related to this camp.
     * @param suggestions This camp's suggestions.
     */
    public void setSuggestions(ArrayList<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    /**
     * Adds an attendee into the camp. The userID is added into the list of attendees, and the attendee slots is reduced by 1.
     * @param userID The attendee's userID.
     */
    public void addCampAttendee(String userID) {
        campAttendees.add(userID);
        campAttendeeSlots--;
    }

    /**
     * Removes an attendee from the camp. The userID is removed from the list of attendees, and the attendee slots is increased by 1.
     * @param index The index of the attendee in the list.
     */
    public void removeCampAttendee(int index) {
        campAttendees.remove(index);
        campAttendeeSlots++;
    }

    /**
     * Adds an committee into the camp. The userID is added into the list of committees, and the committee slots is reduced by 1.
     * @param userID The committee's userID.
     */
    public void addCampCommittee(String userID) {
        campCommittees.add(userID);
        campCommitteeSlots--;
    }

    /**
     * Adds a student into the blacklist. The userID is added into the blacklist.
     * @param userID The student's userID.
     */
    public void addBlacklist(String userID) {
        blacklist.add(userID);
    }

    /**
     * Adds an enquiry into the list of enquiries.
     * @param enquiry The enquiry to be added.
     */
    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    /**
     * Removes an enquiry from the list of enquiries.
     * @param index The index of the enquiry to be removed.
     */
    public void removeEnquiry(int index) {
        enquiries.remove(index);
    }

    /**
     * Adds an suggestion into the list of suggestions.
     * @param suggestion The suggestion to be added.
     */
    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    /**
     * Removes an suggestion from the list of suggestions.
     * @param index The index of the suggestion to be removed.
     */
    public void removeSuggestion(int index) {
        suggestions.remove(index);
    }
}
