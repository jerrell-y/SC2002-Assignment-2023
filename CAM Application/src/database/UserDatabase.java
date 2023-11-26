package database;

import user.User;
import user.Student;
import user.Staff;
import user.Faculty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserDatabase implements Database<User> {

    private static UserDatabase instance;
    private ArrayList<User> users;

    private UserDatabase() {
        users = new ArrayList<>();
    }

    public static synchronized UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public ArrayList<User> load() {

        // Import Students
        String students = "../data/student_list.csv";
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
                users.add(new Student(userID, password, name, faculty, points));
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

    public void update() {
        String studentFile = "../data/student_list.csv";
        String staffFile = "../data/staff_list.csv";
        try (PrintWriter studentWriter = new PrintWriter(new FileWriter(studentFile));
                PrintWriter staffWriter = new PrintWriter(new FileWriter(staffFile))) {

            // Writing headers
            studentWriter.println("Name,Email,Faculty,Password,Points");
            staffWriter.println("Name,Email,Faculty,Password");

            for (User user : users) {
                StringBuilder sb = new StringBuilder();
                String email;
                if (user instanceof Student) {
                    email = user.getUserID() + "@e.ntu.edu.sg";
                } else {
                    email = user.getUserID() + "@NTU.EDU.SG";
                }
                sb.append(user.getName()).append(",");
                sb.append(email).append(",");
                sb.append(user.getFaculty().name()).append(",");
                sb.append(user.getPassword());

                if (user instanceof Student) {
                    Student student = (Student) user;
                    sb.append(",").append(student.getPoints());
                    studentWriter.println(sb.toString());
                } else if (user instanceof Staff) {
                    staffWriter.println(sb.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public ArrayList<User> getUsers() {
        return users;
    }
}
