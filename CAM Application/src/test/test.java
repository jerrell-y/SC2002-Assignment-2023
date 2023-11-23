package test;

import java.util.Calendar;

import camppackage.Camp;
import format.CampFormatter;
import format.StudentFormatter;
import user.Faculty;
import user.Student;

public class test {
    public static void main(String[] args) {
        Student s = new Student("zng052", "123456", "Zhuo Quan", Faculty.SCSE);
        Calendar x = Calendar.getInstance();
        Calendar y = Calendar.getInstance();
        Calendar z = Calendar.getInstance();
        x.set(2023, 11, 28);
        y.set(2023, 11, 30);
        z.set(2023, 11,27);
        Camp c = new Camp("Camp 1", x, y, z, Faculty.SCSE, "NTU", 10, 5, "Please join!", "John Adams", false);

        StudentFormatter sf = new StudentFormatter();
        CampFormatter cf = new CampFormatter();
        
        System.out.println(sf.formatFull(s));
        System.out.println(cf.formatShort(c));
        System.out.println(cf.formatFull(c));
    }
}
