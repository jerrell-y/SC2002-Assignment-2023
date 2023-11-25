package test;

import UI.StaffUI;
import user.Faculty;
import user.Staff;
import user.UserManager;

public class testStaff {
    public static void main(String[] args) {
        Staff staff = new Staff("john", "john", "fqefej", Faculty.SCSE);
        UserManager.setUser(staff);
        StaffUI.start();
    }

}
