package database;

import user.User;
import user.Faculty;
import camppackage.Camp;
import util.DateHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Database {
    // testing if users are entered correctly
    public static void main(String[] args) {
        ArrayList<User> users = getUsers();
        
        // Print all users
        for (User user : users) {
            System.out.println("Name: " + user.getName() + " userID: " + user.getUserID() + " Faculty: " + user.getFaculty() + " Password: " + user.getPassword());
        }

        List<Camp> camps = getCamp();

        for (Camp camp : camps) {
            System.out.println(camp.toString());
        }
    }

    public static ArrayList<User> getUsers() {

        ArrayList<User> users = new ArrayList<>();

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

    public static ArrayList<Camp> getCamp() {

        ArrayList<Camp> camp = new ArrayList<>();

        // Import CampInfo
        String camps = "../data/camp_info.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(camps))) {
            br.readLine(); // Skip Header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int campID = values[0]
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
                Boolean visibility;
                if (values[11].equals("True")) {
                    visibility = true;
                }
                else {
                    visibility = false;
                }
                camp.add(new Camp(campID, campName, startDate, endDate, regEndDate, faculty, location, totalSlots, campCommiteeSlots, description, staffInCharge, visibility));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return camp;

    }
}
