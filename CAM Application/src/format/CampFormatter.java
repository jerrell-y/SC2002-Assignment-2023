package format;

import java.text.SimpleDateFormat;

import camppackage.Camp;

public class CampFormatter implements iFormatter<Camp>{

    public String formatShort(Camp camp) {
        return "Camp Name: " + camp.getCampName();
    }

    public String formatFull(Camp camp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return  "Camp Name: " + camp.getCampName() +
                "\nStart Date: " + dateFormat.format(camp.getStartDate().getTime()) +
                "\nEnd Date: " + dateFormat.format(camp.getEndDate().getTime()) +
                "\nRegistration End Date: " + dateFormat.format(camp.getRegEndDate().getTime()) +
                "\nFaculty: " + camp.getFaculty() +
                "\nLocation: " + camp.getLocation() +
                "\nTotal Slots: " + camp.getTotalSlots() +
                "\nCamp Commitee Slots: " + camp.getCampCommiteeSlots() +
                "\nDescription: " + camp.getDescription() +
                "\nStaff in charge: " + camp.getStaffInCharge();
    }

}
