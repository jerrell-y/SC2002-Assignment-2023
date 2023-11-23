package format;

import user.Student;

public class StudentFormatter implements iFormatter<Student>{

    public String formatFull(Student s) {
        return  "UserID = " + s.getUserID() +
                "\nName = " + s.getName() + 
                "\nFaculty = " + s.getFaculty();
    }
}
