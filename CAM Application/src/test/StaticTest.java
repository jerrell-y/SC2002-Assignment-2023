package test;

import java.util.ArrayList;
import java.util.List;

import camppackage.Camp;

public class StaticTest {
    private static Camp camp;
    private static List<Integer> x;

    public static void setCamp(Camp camp) {
        StaticTest.camp = camp;
    }

    public static void setX(ArrayList<Integer> x) {
        StaticTest.x = x;
    }

    public static List<Integer> getX() {
        return x;
    }

    public static String getString() {
        return camp.toString();
    }
}
