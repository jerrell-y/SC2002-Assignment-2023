package camppackage;

import java.util.ArrayList;
import java.util.Date;

import user.Faculty;
/*
 * The camp class containing camp information for 1 camp
 */
public class Camp{
    private int campID;
    private String campName;
    private Date startDate;
    private Date endDate;
    private Date regEndDate;
    private Faculty faculty;
    private String location;
    private int campAttendeeSlots;
    private int campCommitteeSlots;
    private String description;
    private String staffInCharge;
    private boolean visibility;
    private ArrayList<String> campAttendees;
    private ArrayList<String> campCommittees;
    private ArrayList<String> blacklist;
    private ArrayList<Enquiry> enquiries;
    private ArrayList<Suggestion> suggestions;

    private static int totalCamps;

    /**
     * 
     * @param campName
     * @param startDate
     * @param endDate
     * @param regEndDate
     * @param faculty
     * @param location
     * @param campAttendeeSlots
     * @param campCommitteeSlots
     * @param description
     * @param staffInCharge
     * @param visibility
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

    public static void setTotalCamps(int count) {
        Camp.totalCamps = count;
    }

    public void setCampID(int campID) {
        this.campID = campID;
    }

    public int getCampID() {
        return campID;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampName() {
        return campName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setRegEndDate(Date regEndDate) {
        this.regEndDate = regEndDate;
    }

    public Date getRegEndDate() {
        return regEndDate;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setCampAttendeeSlots(int campAttendeeSlots) {
        this.campAttendeeSlots = campAttendeeSlots;
    }

    public int getCampAttendeeSlots() {
        return campAttendeeSlots;
    }

    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public ArrayList<String> getCampAttendees() {
        return campAttendees;
    }

    public void setCampAttendees(ArrayList<String> campAttendees) {
        this.campAttendees = campAttendees;
    }

    public ArrayList<String> getCampCommittees() {
        return campCommittees;
    }

    public void setCampCommittees(ArrayList<String> campCommittees) {
        this.campCommittees = campCommittees;
    }

    public ArrayList<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(ArrayList<String> blacklist) {
        this.blacklist = blacklist;
    }

    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(ArrayList<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }

    public ArrayList<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(ArrayList<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public void addCampAttendee(String userID) {
        campAttendees.add(userID);
        campAttendeeSlots--;
    }

    public void removeCampAttendee(int index) {
        campAttendees.remove(index);
        campAttendeeSlots++;
    }

    public void addCampCommittee(String userID) {
        campCommittees.add(userID);
        campCommitteeSlots--;
    }

    public void addBlacklist(String userID) {
        blacklist.add(userID);
    }
    
    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    public void removeEnquiry(int index) {
        enquiries.remove(index);
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    public void removeSuggestion(int index) {
        suggestions.remove(index);
    }
}
