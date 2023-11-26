package test;

import java.util.ArrayList;
import java.util.Date;

import camppackage.Camp;
import format.CampFormatter;
import format.UserFormatter;
import user.Faculty;
import user.Student;

public class test {
    public static void main(String[] args) {
        Student s = new Student("zng052", "123456", "Zhuo Quan", Faculty.SCSE);
        Date x = new Date(2023, 11,28);
        Date y = new Date(2023, 11,30);
        Date z = new Date(2023, 11,27);
        Camp c = new Camp("Camp 1", x, y, z, Faculty.SCSE, "NTU", 10, 5, "Please join!", "John Adams", false);

        UserFormatter sf = new UserFormatter();
        CampFormatter cf = new CampFormatter();
        
        System.out.println(sf.formatFull(s));
        System.out.println(cf.formatShort(c));
        System.out.println(cf.formatFull(c));
        ArrayList<Integer> xx = new ArrayList<Integer>();
        xx.add(0);

        StaticTest.setCamp(c);
        StaticTest.setX(xx);
        System.out.println();
        System.out.println("In test: " + StaticTest.getString());
        System.out.println();
        testStatic.show();
    }
}
