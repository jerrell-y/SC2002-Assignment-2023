package camppackage;

import java.util.ArrayList;
import java.util.Calendar;

public class Camp {
    private CampInfo campInfo;
    private boolean visibility;
    private ArrayList<Student> campAttendees;
    private ArrayList<Student> campCommitees;
    private ArrayList<Enquiry> enquiries;
    private ArrayList<Suggestion> suggestions;

    public Camp (CampInfo campInfo, boolean visibility) {
        this.campInfo = campInfo;
        this.visibility = visibility;
        campAttendees = new ArrayList<Student>();
        campCommitees = new ArrayList<Student>();
        enquires = new ArrayList<Enquiry>();
        suggestions = new ArrayList<Suggestion>();
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

    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }
}
