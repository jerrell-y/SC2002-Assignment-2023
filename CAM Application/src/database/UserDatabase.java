package database;

import user.User;
import user.Student;
import user.Staff;
import user.Faculty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserDatabase implements Database<User>{

    private static ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> load() {

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
                int points = Integer.parseInt(values[4]);
                users.add(new Student(userID, password, name, faculty,points));
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
                users.add(new Staff(userID, password, name, faculty));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void update(){
        //
    };

    public ArrayList<User> getUsers() { return users; }
}
