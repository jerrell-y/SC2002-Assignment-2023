package format;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

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
        committee=camp.getCampCommittees();
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
        committee=camp.getCampCommittees();
        for(int i=0;i<committee.size();i++) {
                    String inputPath = "../data/camp_info.csv";
                    String outputPath = "CommitteeReport.csv";
                    int checkColumnIndex = 0; // index of the column to check
                    int valueColumnIndex = 4; // index of the value column

                    try (BufferedReader br = new BufferedReader(new FileReader(inputPath));
                         PrintWriter pw = new PrintWriter(new FileWriter(outputPath))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] values = line.split(","); // adjust delimiter if necessary

                            if (values.length > valueColumnIndex && values[checkColumnIndex].equals(committee.get(i))) {
                                pw.println(values[checkColumnIndex] + "," + values[valueColumnIndex]);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
                "\nCamp Attendee Slots: " + camp.getCampAttendeeSlots() +
                "\nCamp Committee Slots: " + camp.getCampCommitteeSlots() +
                "\nDescription: " + camp.getDescription() +
                "\nStaff in charge: " + camp.getStaffInCharge() + 
                "\n======================================";
    }

}
