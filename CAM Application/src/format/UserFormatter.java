package format;

import user.User;

public class UserFormatter implements iFormatter<User>{
    private static UserFormatter instance;
    
    public static synchronized UserFormatter getInstance() {
        if (instance == null) {
            instance = new UserFormatter();
        }
        return instance;
    }

    public String formatFull(User user) {
        return  "UserID = " + user.getUserID() +
                "\nName = " + user.getName() + 
                "\nFaculty = " + user.getFaculty();
    }
}
