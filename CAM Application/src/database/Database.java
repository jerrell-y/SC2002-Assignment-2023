package database;

import user.User;
import user.Faculty;
import camppackage.Camp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Database {
    // testing if users are entered correctly
    public static void main(String[] args) {
        List<User> users = getUsers();
        
        // Print all users
        for (User user : users) {
            System.out.println("Name: " + user.getName() + " userID: " + user.getUserID() + " Faculty: " + user.getFaculty() + " Password: " + user.getPassword());
        }

        List<Camp> camps = getCamp();

        for (Camp camp : camps) {
            System.out.println(camp.toString());
        }
    }

    public static List<User> getUsers() {

        List<User> users = new ArrayList<>();

        // Import Students
        String students = "../data/student list.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(students))) {
            br.readLine(); // Skip Header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0]; 
                String email = values[1];
                String userID = email.substring(0, email.indexOf('@'));
                Faculty faculty = Faculty.valueOf(values[2]);
                String password = values[3];
                users.add(new User(userID, password, name, faculty)); // need to change to Student
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Import Staff
        String staff = "../data/staff_list.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(staff))) {
            br.readLine(); // Skip Header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0]; 
                String email = values[1];
                String userID = email.substring(0, email.indexOf('@'));
                Faculty faculty = Faculty.valueOf(values[2]);
                String password = values[3];
                users.add(new User(userID, password, name, faculty)); // need to change to Staff
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static List<Camp> getCamp() {

        List<Camp> camp = new ArrayList<>();

        // Import CampInfo
        String camps = "../data/camp_info.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(camps))) {
            br.readLine(); // Skip Header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String campName = values[0]; 
                Calendar startDate = parseDate(values[1]);
                Calendar endDate = parseDate(values[2]);
                Calendar regEndDate = parseDate(values[3]);
                Faculty faculty = Faculty.valueOf(values[4]);
                String location = values[5];
                int totalSlots = Integer.parseInt(values[6]);
                int campCommiteeSlots = Integer.parseInt(values[7]);
                String description = values[8];
                String staffInCharge = values[9];
                Boolean visibility;
                if (values[10].equals("True")) {
                    visibility = true;
                }
                else {
                    visibility = false;
                }
                camp.add(new Camp(campName, startDate, endDate, regEndDate, faculty, location, totalSlots, campCommiteeSlots, description, staffInCharge, visibility));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return camp;

    }

    private static Calendar parseDate(String dateString) {
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(dateString.substring(0, 4));
        // Subtract 1 from month because Calendar months are 0-based
        int month = Integer.parseInt(dateString.substring(4, 6)) - 1;
        int day = Integer.parseInt(dateString.substring(6, 8));
        calendar.set(year, month, day);
        return calendar;
    }
}
