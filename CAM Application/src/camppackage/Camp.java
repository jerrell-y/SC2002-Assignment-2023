package camppackage;

import java.util.ArrayList;
import java.util.Calendar;

import user.Faculty;
import user.Student;

public class Camp extends CampInfo{
    private boolean visibility;
    private ArrayList<Student> campAttendees;
    private ArrayList<Student> campCommitees;
    private ArrayList<Enquiry> enquiries;
    private ArrayList<Suggestion> suggestions;

    public Camp (String campName, Calendar startDate, Calendar endDate, Calendar regEndDate, Faculty faculty, String location, int totalSlots, int campCommiteeSlots, String description, String staffInCharge, boolean visibility) {
        super(campName, startDate, endDate, regEndDate, faculty, location, totalSlots, campCommiteeSlots, description, staffInCharge);
        this.visibility = visibility;
        campAttendees = new ArrayList<Student>();
        campCommitees = new ArrayList<Student>();
        enquiries = new ArrayList<Enquiry>();
        suggestions = new ArrayList<Suggestion>();
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

    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    public void deleteEnquiry(int index) {
        enquiries.remove(index);
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    public String generateReport() {
        return super.toString();
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
