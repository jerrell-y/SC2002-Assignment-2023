package database;

import user.User;
import user.Faculty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    // testing if users are entered correctly
    public static void main(String[] args) {
        List<User> users = getUsers();
        
        // Print all users
        for (User user : users) {
            System.out.println("Name: " + user.getName() + " userID: " + user.getUserID() + " Faculty: " + user.getFaculty() + " Password: " + user.getPassword());
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
}
