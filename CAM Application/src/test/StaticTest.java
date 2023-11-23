package test;

import camppackage.Camp;

public class StaticTest {
    private static Camp camp;

    public static void setCamp(Camp camp) {
        StaticTest.camp = camp;
    }

    public static String getString() {
        return camp.toString();
    }
}
