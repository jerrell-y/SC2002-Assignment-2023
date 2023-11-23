package test;

import java.util.ArrayList;
import java.util.List;

public class testStatic {
    public static void show() {

        System.out.println("In testStatic: " + StaticTest.getString());
        System.out.println();
        List<Integer> y = StaticTest.getX();
        y.add(1);

        List<Integer> z = StaticTest.getX();

        System.out.println(y);
        System.out.println(z);
    }
}
