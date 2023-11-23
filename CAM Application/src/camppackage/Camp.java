package camppackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import user.Faculty;
import user.Student;

public class Camp{
    private String campName;
    private Calendar startDate;
    private Calendar endDate;
    private Calendar regEndDate;
    private Faculty faculty;
    private String location;
    private int totalSlots;
    private int campCommiteeSlots;
    private String description;
    private String staffInCharge;
    private boolean visibility;
    private ArrayList<Student> campAttendees;
    private ArrayList<Student> campCommitees;
    private ArrayList<Enquiry> enquiries;
    private ArrayList<Suggestion> suggestions;

    public Camp (String campName, Calendar startDate, Calendar endDate, Calendar regEndDate, Faculty faculty, String location, int totalSlots, int campCommiteeSlots, String description, String staffInCharge, boolean visibility) {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regEndDate = regEndDate;
        this.faculty = faculty;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommiteeSlots = campCommiteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.visibility = visibility;
        campAttendees = new ArrayList<Student>();
        campCommitees = new ArrayList<Student>();
        enquiries = new ArrayList<Enquiry>();
        suggestions = new ArrayList<Suggestion>();
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampName() {
        return campName;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setRegEndDate(Calendar regEndDate) {
        this.regEndDate = regEndDate;
    }

    public Calendar getRegEndDate() {
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

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setCampCommiteeSlots(int campCommiteeSlots) {
        this.campCommiteeSlots = campCommiteeSlots;
    }

    public int getCampCommiteeSlots() {
        return campCommiteeSlots;
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

    //Returns the camp infomation in a single string. (Excluding visibility)
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return  "Camp Name: " + campName +
                "\nStart Date: " + dateFormat.format(startDate.getTime()) +
                "\nEnd Date: " + dateFormat.format(endDate.getTime()) +
                "\nRegistration End Date: " + dateFormat.format(regEndDate.getTime()) +
                "\nFaculty: " + faculty +
                "\nLocation: " + location +
                "\nTotal Slots: " + totalSlots +
                "\nCamp Commitee Slots: " + campCommiteeSlots +
                "\nDescription: " + description +
                "\nStaff in charge: " + staffInCharge;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void addAttendee(Student student) {
        campAttendees.add(student);
    }

    public void addCommitee(Student student) {
        campCommitees.add(student);
    }

    public void deleteAttendee(Student student) {
        int i;
        for (i = 0; i != campAttendees.size(); i++) {
            if (campAttendees.get(i).equals(student)) {
                campAttendees.remove(i);
                break;
            }
        }
    }

    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    public void deleteEnquiry(int index) {
        enquiries.remove(index);
    }

    public ArrayList<Enquiry> getEnquiries() {return enquiries;}


    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    public String generateReport() {
        return toString();
        //add student infomation / camp commitee information
    }

    public boolean isAttendee(Student student) {
        int i;
        for (i = 0; i != campAttendees.size(); i++) {
            if (campAttendees.get(i).getUserID() == student.getUserID()) {
                return true;
            }
        }
        return false;
    }

    public boolean isCommitee(Student student) {
        int i;
        for (i = 0; i != campCommitees.size(); i++) {
            if (campAttendees.get(i).getUserID() == student.getUserID()) {
                return true;
            }
        }
        return false;
    }
}
