package database;

import user.Faculty;
import camppackage.Camp;
import util.DateHelper;
import camppackage.Enquiry;
import camppackage.Suggestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class CampDatabase implements Database<Camp> {

    private static CampDatabase instance;
    private ArrayList<Camp> camps;

    private CampDatabase() {
        camps = new ArrayList<>();
    }

    public static synchronized CampDatabase getInstance() {
        if (instance == null) {
            instance = new CampDatabase();
        }
        return instance;
    }

    public ArrayList<Camp> load() {

        // Import CampInfo
        String campInfo = "../data/camp_info.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(campInfo))) {
            br.readLine(); // Skip Header
            String line;
            int max = 0;
            while ((line = br.readLine()) != null) {
                // Split by commas that are not inside quotes
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                int campID = Integer.parseInt(values[0]);
                String campName = values[1];
                Date startDate = DateHelper.stringToDate(values[2]);
                Date endDate = DateHelper.stringToDate(values[3]);
                Date regEndDate = DateHelper.stringToDate(values[4]);
                Faculty faculty = Faculty.valueOf(values[5]);
                String location = values[6];
                int campAttendeeSlots = Integer.parseInt(values[7]);
                int campCommiteeSlots = Integer.parseInt(values[8]);
                String description = values[9];
                String staffInCharge = values[10];
                boolean visibility = "True".equalsIgnoreCase(values[11]);

                // Parsing CampAttendees and Campcommitees
                ArrayList<String> campAttendees = new ArrayList<>(
                        Arrays.asList(values[12].replaceAll("\\[|\\]", "").split(",")));
                ArrayList<String> campcommitees = new ArrayList<>(
                        Arrays.asList(values[13].replaceAll("\\[|\\]", "").split(",")));

                // Parsing Enquiries
                ArrayList<Enquiry> enquiries = new ArrayList<>();
                String[] enquiryData = values[14].replaceAll("\\[|\\]", "").split("\\],\\[");
                for (String enquiry : enquiryData) {
                    String[] parts = enquiry.split(",", -1);
                    // Assuming the constructor Enquiry(String content, String name, String userID,
                    // String reply, boolean answered)
                    String reply = parts.length > 3 ? parts[3] : "";
                    boolean answered = parts.length > 4 && "True".equalsIgnoreCase(parts[4]);
                    enquiries.add(new Enquiry(parts[0], parts[1], parts[2], reply, answered));
                }

                // Parsing Suggestions
                ArrayList<Suggestion> suggestions = new ArrayList<>();
                String[] suggestionData = values[15].replaceAll("\\[|\\]", "").split("\\],\\[");
                for (String suggestion : suggestionData) {
                    String[] parts = suggestion.split(",");
                    // Assuming the constructor Suggestion(String content, String name, String
                    // userID, int status)
                    boolean status = parts.length > 3 && "True".equalsIgnoreCase(parts[3]);
                    suggestions.add(new Suggestion(parts[0], parts[1], parts[2], status));
                }

                if (max < campID) {
                    max = campID;
                }
                camps.add(
                        new Camp(campID, campName, startDate, endDate, regEndDate, faculty, location, campAttendeeSlots,
                                campCommiteeSlots, description, staffInCharge, visibility, campAttendees,
                                campcommitees, enquiries, suggestions));
            }
            Camp.setTotalCamps(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return camps;

    }

    public void update() {
        String campInfo = "../data/camp_info.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(campInfo))) {
            // Write the header
            bw.write(
                    "CampID,CampName,StartDate,EndDate,RegEndDate,Faculty,Location,CampAttendeeSlots,CampCommiteeSlots,Description,StaffInCharge,Visibility,CampAttendees,Campcommitees,Enquiries,Suggestions\n");
            for (Camp camp : camps) {
                StringBuilder sb = new StringBuilder();
                sb.append(camp.getCampID()).append(",");
                sb.append(camp.getCampName()).append(",");
                sb.append(DateHelper.dateToString(camp.getStartDate())).append(",");
                sb.append(DateHelper.dateToString(camp.getEndDate())).append(",");
                sb.append(DateHelper.dateToString(camp.getRegEndDate())).append(",");
                sb.append(camp.getFaculty().name()).append(",");
                sb.append(camp.getLocation()).append(",");
                sb.append(camp.getCampAttendeeSlots()).append(",");
                sb.append(camp.getCampCommiteeSlots()).append(",");
                sb.append(camp.getDescription()).append(",");
                sb.append(camp.getStaffInCharge()).append(",");
                sb.append(camp.getVisibility() ? "True" : "False").append(",");

                // Serialize CampAttendees and enclose outer brackets in quotes
                sb.append("\"").append("[");
                sb.append(String.join(",", camp.getCampAttendees()));
                sb.append("]").append("\",");

                // Serialize CampCommitees and enclose outer brackets in quotes
                sb.append("\"").append("[");
                sb.append(String.join(",", camp.getCampCommitees()));
                sb.append("]").append("\",");

                // Serialize Enquiries and enclose outer brackets in quotes
                sb.append("\"").append("[");
                for (Enquiry enquiry : camp.getEnquiries()) {
                    sb.append("[");
                    sb.append(enquiry.getContent()).append(",");
                    sb.append(enquiry.getName()).append(",");
                    sb.append(enquiry.getUserID()).append(",");
                    // Check for empty replies and enclose in double quotes
                    sb.append(enquiry.getReply().isEmpty() ? "\"\"" : enquiry.getReply()).append(",");
                    sb.append(enquiry.isAnswered() ? "True" : "False");
                    sb.append("],");
                }
                if (!camp.getEnquiries().isEmpty()) {
                    sb.setLength(sb.length() - 1); // Remove the last comma
                }
                sb.append("]").append("\",");

                // Serialize Suggestions and enclose outer brackets in quotes
                sb.append("\"").append("[");
                for (Suggestion suggestion : camp.getSuggestions()) {
                    sb.append("[");
                    sb.append(suggestion.getContent()).append(",");
                    sb.append(suggestion.getName()).append(",");
                    sb.append(suggestion.getUserID()).append(",");
                    sb.append(suggestion.getStatus() ? "True" : "False");
                    sb.append("],");
                }
                if (!camp.getSuggestions().isEmpty()) {
                    sb.setLength(sb.length() - 1); // Remove the last comma
                }
                sb.append("]").append("\"");

                // Write the serialized camp to the file
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Camp> getCamps() {
        return camps;
    }

    public Camp getCampByID(int campID) {
        for (Camp camp : camps) {
            if (camp.getCampID() == campID) {
                return camp;
            }
        }
        return null;
    }

    public boolean addCamp(Camp camp) {
        camps.add(camp);
        CampDatabase.getInstance().update();
        return true;
    }

    public void deleteCamp(Camp camp) {
        camps.remove(camp);
        CampDatabase.getInstance().update();
    }
}
