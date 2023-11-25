package format;

import java.text.SimpleDateFormat;

import camppackage.Camp;

public class CampFormatter implements iFormatter<Camp>{
    private static CampFormatter instance;

    public static synchronized CampFormatter getInstance() {
        if (instance == null) {
            instance = new CampFormatter();
        }
        return instance;
    }

    

    public String formatShort(Camp camp) {
        return "Camp Name: " + camp.getCampName();
    }

    public String formatFull(Camp camp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return  "Camp Name: " + camp.getCampName() +
                "\nStart Date: " + dateFormat.format(camp.getStartDate().getTime()) +
                "\nEnd Date: " + dateFormat.format(camp.getEndDate().getTime()) +
                "\nRegistration End Date: " + dateFormat.format(camp.getRegEndDate().getTime()) +
                "\nFaculty: " + camp.getFaculty() +
                "\nLocation: " + camp.getLocation() +
                "\nCamp Attendee Slots: " + camp.getCampAttendeeSlots() +
                "\nCamp Commitee Slots: " + camp.getCampCommiteeSlots() +
                "\nDescription: " + camp.getDescription() +
                "\nStaff in charge: " + camp.getStaffInCharge();
    }

}
