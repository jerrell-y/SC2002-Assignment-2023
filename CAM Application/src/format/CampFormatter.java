package format;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import camppackage.Camp;
import manager.CampManager;
import user.*;

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
    public void formatStudentReport(Camp camp){
        ArrayList<String> attendees ;
        ArrayList<String> committee ;
        attendees=camp.getCampAttendees();
        committee=camp.getCampCommitees();
        String csvFile = "Student.csv";
        // Additional column values (including header)
        String[] additionalColumn = {"Attendee", "Committee"};

        // Add the new column to each row
        for (int i = 0; i < attendees.size(); i++) {
            String row = attendees.get(i) + "," + additionalColumn[0];
            attendees.set(i, row);
        }
        for (int i = 0; i < committee.size(); i++) {
            String row = committee.get(i) + "," + additionalColumn[1];
            committee.set(i, row);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String line : attendees) {
                bw.write(line);
                bw.newLine();
            }
            for (String line : committee) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("CSV file was created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void formatCommitteeReport(Camp camp){
        ArrayList<String> committee ;
        committee=camp.getCampCommitees();
    }


    public String formatFull(Camp camp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return  "\n======================================" +
                "\nCamp Name: " + camp.getCampName() +
                "\n======================================" +
                "\nStart Date: " + dateFormat.format(camp.getStartDate().getTime()) +
                "\nEnd Date: " + dateFormat.format(camp.getEndDate().getTime()) +
                "\nRegistration End Date: " + dateFormat.format(camp.getRegEndDate().getTime()) +
                "\nFaculty: " + camp.getFaculty() +
                "\nLocation: " + camp.getLocation() +
                "\nTotal Camp Attendee Slots: " + camp.getCampAttendeeSlots() +
                "\nRemaining Camp Attendee Slots: " + (camp.getCampAttendeeSlots() - camp.getCampAttendees().size()) +
                "\nTotal Camp Commitee Slots: " + camp.getCampCommiteeSlots() +
                "\nRemaining Camp Attendee Slots: " + (camp.getCampCommiteeSlots() - camp.getCampCommitees().size()) +
                "\nDescription: " + camp.getDescription() +
                "\nStaff in charge: " + camp.getStaffInCharge() + 
                "\n======================================";
    }

}
