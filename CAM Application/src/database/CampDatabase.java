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

/**
 * The database containing all the camp data from .csv.
 */
public class CampDatabase implements Database<Camp> {

    /**
     * Singleton instance of the database
     */
    private static CampDatabase instance;

    /**
     * List holding all the camps
     */
    private ArrayList<Camp> camps;

    /**
     * Private constructor to prevent instantiation from outside, ensuring a Singleton pattern.
     */
    private CampDatabase() {
        camps = new ArrayList<>();
    }

    /**
     * Synchronized method to get the single instance of the CampDatabase class.
     * If the instance is null, it initializes a new instance.
     *
     * @return The single instance of CampDatabase.
     */
    public static synchronized CampDatabase getInstance() {
        if (instance == null) {
            instance = new CampDatabase();
        }
        return instance;
    }

    /**
     * Loads camp data from a CSV file into the application.
     * Parses each line of the file into a Camp object and adds it to the list of camps.
     *
     * @return An ArrayList of Camp objects representing all camps loaded from the CSV file.
     */
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
                int campCommitteeSlots = Integer.parseInt(values[8]);
                String description = values[9];
                String staffInCharge = values[10];
                boolean visibility = "True".equalsIgnoreCase(values[11]);

                // Parsing CampAttendees and Campcommittees
                String attendees = values[12].replaceAll("\\[|\\]|\"", "");
                ArrayList<String> campAttendees = new ArrayList<String>();
                if (!attendees.equals("")) {
                    campAttendees = new ArrayList<>(
                        Arrays.asList(values[12].replaceAll("\\[|\\]|\"", "").split(",")));
                }

                String committees = values[13].replaceAll("\\[|\\]|\"", "");
                ArrayList<String> campCommittees = new ArrayList<String>();
                if (!committees.equals("")) {
                    campCommittees = new ArrayList<>(
                        Arrays.asList(values[13].replaceAll("\\[|\\]|\"", "").split(",")));
                }

                String bl = values[14].replaceAll("\\[|\\]|\"", "");
                ArrayList<String> blacklist = new ArrayList<String>();
                if (!bl.equals("")) {
                    blacklist = new ArrayList<>(
                        Arrays.asList(values[14].replaceAll("\\[|\\]|\"", "").split(",")));
                }

                // Parsing Enquiries
                ArrayList<Enquiry> enquiries = new ArrayList<>();
                // Remove the outermost brackets
                String enquiry = values[15].substring(2, values[15].length() - 2);
                if (!enquiry.equals("")) {
                    String[] enquiryData = values[15].substring(2, values[15].length() - 2).split("\\],\\[");

                    for (int j = 0; j < enquiryData.length; j++) {
                        // If it's the first enquiry, remove the leading '['
                        if (j == 0 && enquiryData[j].startsWith("[")) {
                            enquiryData[j] = enquiryData[j].substring(1);
                        }

                        // Split each enquiry into parts
                        String[] parts = enquiryData[j].split(",", -1);
                        // Trim each part to remove any leading or trailing whitespace
                        for (int i = 0; i < parts.length; i++) {
                            parts[i] = parts[i].replaceAll("^\"|\"$", "").trim();
                        }

                        // Assuming the constructor Enquiry(String content, String name, String userID,
                        // String reply, boolean answered)
                        String content = parts[0];
                        String name = parts.length > 1 ? parts[1] : "";
                        String userID = parts.length > 2 ? parts[2] : "";
                        String reply = parts.length > 3 ? parts[3] : "";
                        boolean answered = parts.length > 4 && parts[4].equalsIgnoreCase("true");

                        // Add the new enquiry to the list
                        enquiries.add(new Enquiry(content, name, userID, reply, answered));
                    }
                }

                // Parsing Suggestions
                ArrayList<Suggestion> suggestions = new ArrayList<>();
                // Remove the very first '[' and the very last ']' from the string
                String suggestion = values[16].substring(2, values[16].length() - 2);
                if (!suggestion.equals("")) {
                    String[] suggestionData = values[16].substring(2, values[16].length() - 2).split("\\],\\[");

                    for (int j = 0; j < suggestionData.length; j++) {
                        // If it's the first suggestion, remove the leading '['
                        if (j == 0 && suggestionData[j].startsWith("[")) {
                            suggestionData[j] = suggestionData[j].substring(1);
                        }

                        // Split each suggestion into parts, accounting for possible empty fields
                        String[] parts = suggestionData[j].split(",", -1);
                        // Trim each part to remove any leading or trailing whitespace and remove quotes
                        for (int i = 0; i < parts.length; i++) {
                            parts[i] = parts[i].replaceAll("^\"|\"$", "").trim();
                        }
                        // Assuming the constructor Suggestion(String content, String name, String
                        // userID, boolean status)
                        String content = parts[0];
                        String name = parts.length > 1 ? parts[1] : "";
                        String userID = parts.length > 2 ? parts[2] : "";
                        boolean status = parts.length > 3 && parts[3].equalsIgnoreCase("true");

                        // Add the new suggestion to the list
                        suggestions.add(new Suggestion(content, name, userID, status));
                    }
                }

                if (max < campID) {
                    max = campID;
                }
                camps.add(
                        new Camp(campID, campName, startDate, endDate, regEndDate, faculty, location, campAttendeeSlots,
                                campCommitteeSlots, description, staffInCharge, visibility, campAttendees,
                                campCommittees, blacklist ,enquiries, suggestions));
            }
            Camp.setTotalCamps(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return camps;

    }

    /**
     * Updates the CSV file with current camp data.
     * Serializes each camp object into a comma-separated string and writes it to the file.
     */
    public void update() {
        String campInfo = "../data/camp_info.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(campInfo))) {
            // Write the header
            bw.write(
                    "CampID,CampName,StartDate,EndDate,RegEndDate,Faculty,Location,CampAttendeeSlots,CampCommitteeSlots,Description,StaffInCharge,Visibility,CampAttendees,Campcommittees,Enquiries,Suggestions\n");
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
                sb.append(camp.getCampCommitteeSlots()).append(",");
                sb.append(camp.getDescription()).append(",");
                sb.append(camp.getStaffInCharge()).append(",");
                sb.append(camp.getVisibility() ? "True" : "False").append(",");

                // Serialize CampAttendees and enclose outer brackets in quotes
                sb.append("\"").append("[");
                sb.append(String.join(",", camp.getCampAttendees()));
                sb.append("]").append("\",");

                // Serialize CampCommittees and enclose outer brackets in quotes
                sb.append("\"").append("[");
                sb.append(String.join(",", camp.getCampCommittees()));
                sb.append("]").append("\",");

                // Serialize CampCommittees and enclose outer brackets in quotes
                sb.append("\"").append("[");
                sb.append(String.join(",", camp.getBlacklist()));
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

    /**
     * Retrieves the list of all camps.
     *
     * @return An ArrayList of Camp objects.
     */
    public ArrayList<Camp> getCamps() {
        return camps;
    }

    /**
     * Searches for a camp by its ID and returns the corresponding Camp object.
     *
     * @param campID The ID of the camp to be retrieved.
     * @return The Camp object with the specified ID, or null if not found.
     */
    public Camp getCampByID(int campID) {
        for (Camp camp : camps) {
            if (camp.getCampID() == campID) {
                return camp;
            }
        }
        return null;
    }

    /**
     * Adds a new Camp object to the list and updates the CSV file.
     *
     * @param camp The Camp object to be added.
     * @return true if the camp was added successfully, false otherwise.
     */
    public boolean addCamp(Camp camp) {
        camps.add(camp);
        CampDatabase.getInstance().update();
        return true;
    }

    /**
     * Deletes a camp from the list based on its ID and updates the CSV file.
     * Ensures that the camp does not have any attendees or committee members before deletion.
     *
     * @param campID The ID of the camp to be deleted.
     * @return true if the camp was deleted successfully, false otherwise.
     */
    public boolean deleteCamp(int campID) {
        for (Camp camp : camps) {
            if (camp.getCampID() == campID) {
                if (camp.getCampAttendees().size() != 0 || camp.getCampCommittees().size() != 0) {
                    System.out.println("You cannot delete this camp because there are existing attendees/committee members! \n");
                    return false;
                }
                camps.remove(camp);
                CampDatabase.getInstance().update();
                break;
            }
        }
        return true;
    }
}
