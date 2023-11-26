package format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import camppackage.Camp;

/**
 * Formats a camp into a more readable String for printing purposes.
 * Also used in generating the report.
 */
public class CampFormatter implements iFormatter<Camp>{
    /**
     * The instance of this class.
     */
    private static CampFormatter instance;

    /**
     * Gets the instance of this class. If not created yet, create a new instance.
     * @return this class' instance.
     */
    public static synchronized CampFormatter getInstance() {
        if (instance == null) {
            instance = new CampFormatter();
        }
        return instance;
    }



    /**
     * Formats a camp into a short string containing the camp name.
     * @param camp The camp object.
     * @return a String containing the camp name.
     */
    public String formatShort(Camp camp) {
        return "Camp Name: " + camp.getCampName();
    }

    /**
     * Creates a CSV file containing the student report of a camp.
     * @param camp The camp object.
     */
    public void formatStudentReport(Camp camp){
        ArrayList<String> attendees ;
        ArrayList<String> committee ;
        attendees=camp.getCampAttendees();
        committee=camp.getCampCommittees();
        String csvFile = "Student.csv";
        String x=CampFormatter.getInstance().formatFull(camp);

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
            bw.write(x);
            bw.newLine();
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

    /**
     * Creates a CSV file containing the committee report of a camp.
     * @param camp The camp object.
     */
    public void formatCommitteeReport(Camp camp){
        ArrayList<String> committee ;
        committee=camp.getCampCommittees();
        String inputPath = "../data/student_list.csv";
        String outputPath = "CommitteeReport.csv";
        String x=CampFormatter.getInstance().formatFull(camp);
        try (FileWriter fw = new FileWriter(outputPath, false)) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
             PrintWriter pw = new PrintWriter(new FileWriter(outputPath,true))) {
            pw.write(x);
           pw.println();
           pw.write("NAME "+"   POINTS");
           pw.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<committee.size();i++) {

            int checkColumnIndex = 1; // index of the column to check
            int valueColumnIndex = 4; // index of the value column

            try (BufferedReader br = new BufferedReader(new FileReader(inputPath));
                 PrintWriter pw = new PrintWriter(new FileWriter(outputPath,true))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(","); // adjust delimiter if necessary
                    System.out.println(Arrays.toString(values));
                    System.out.println(values[checkColumnIndex]+committee.get(i));

                    if (values[checkColumnIndex].contains(committee.get(i))) {
                        pw.println(committee.get(i) + " , " + values[valueColumnIndex]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("SUCCESS");
    }

    /**
     * Formats a camp into a long string containing all the information of the camp.
     * @param camp The camp object.
     * @return a String containing all information of the camp.
     */
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
