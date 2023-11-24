package database;

import user.Faculty;
import camppackage.Camp;
import util.DateHelper;
import camppackage.Enquiry;
import camppackage.Suggestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class CampDatabase implements Database<Camp> {

    private static ArrayList<Camp> camps = new ArrayList<>();

    public ArrayList<Camp> load() {

        // Import CampInfo
        String campInfo = "../data/camp_info.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(campInfo))) {
            br.readLine(); // Skip Header
            String line;
            int max = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int campID = Integer.parseInt(values[0]);
                String campName = values[1]; 
                Date startDate = DateHelper.stringToDate(values[2]);
                Date endDate = DateHelper.stringToDate(values[3]);
                Date regEndDate = DateHelper.stringToDate(values[4]);
                Faculty faculty = Faculty.valueOf(values[5]);
                String location = values[6];
                int totalSlots = Integer.parseInt(values[7]);
                int campCommiteeSlots = Integer.parseInt(values[8]);
                String description = values[9];
                String staffInCharge = values[10];
                boolean visibility = "True".equalsIgnoreCase(values[11]);

                // Parsing CampAttendees and Campcommitees
                ArrayList<String> campAttendees = new ArrayList<>(Arrays.asList(values[12].replaceAll("\\[|\\]", "").split(",")));
                ArrayList<String> campcommitees = new ArrayList<>(Arrays.asList(values[13].replaceAll("\\[|\\]", "").split(",")));

                // Parsing Enquiries
                ArrayList<Enquiry> enquiries = new ArrayList<>();
                String[] enquiryData = values[14].replaceAll("\\[|\\]", "").split("\\],\\[");
                for (String enquiry : enquiryData) {
                    String[] parts = enquiry.split(",", -1);
                    // Assuming the constructor Enquiry(String content, String name, String userID, String reply, boolean answered)
                    String reply = parts.length > 3 ? parts[3] : "";
                    boolean answered = parts.length > 4 && "True".equalsIgnoreCase(parts[4]);
                    enquiries.add(new Enquiry(parts[0], parts[1], parts[2], reply, answered));
                }

                // Parsing Suggestions
                ArrayList<Suggestion> suggestions = new ArrayList<>();
                String[] suggestionData = values[15].replaceAll("\\[|\\]", "").split("\\],\\[");
                for (String suggestion : suggestionData) {
                    String[] parts = suggestion.split(",");
                    // Assuming the constructor Suggestion(String content, String name, String userID, int status)
                    boolean status = parts.length > 3 && "True".equalsIgnoreCase(parts[3]);
                    suggestions.add(new Suggestion(parts[0], parts[1], parts[2], status));
                }

                if(max < campID) { max = campID; }
                camps.add(new Camp(campID, campName, startDate, endDate, regEndDate, faculty, location, totalSlots, 
                campCommiteeSlots, description, staffInCharge, visibility, campAttendees, 
                campcommitees, enquiries, suggestions));
            }
            Camp.setTotalCamps(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return camps;

    }

    public void update(){
        //
    };

    public static ArrayList<Camp> getCamps() {
        return camps;
    }
}
