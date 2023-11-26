package format;

import user.User;

/**
 * Formats a user into a more readable String for printing purposes.
 */
public class UserFormatter implements iFormatter<User>{
    /**
     * The instance of this class.
     */
    private static UserFormatter instance;
    
    /**
     * Gets the instance of this class. If not created yet, create a new instance.
     * @return this class' instance.
     */
    public static synchronized UserFormatter getInstance() {
        if (instance == null) {
            instance = new UserFormatter();
        }
        return instance;
    }

    /**
     * Formats a user into a long string containing all the information of the user.
     * @param camp The user object.
     * @return a String containing all information of the user.
     */
    public String formatFull(User user) {
        return  "UserID = " + user.getUserID() +
                "\nName = " + user.getName() + 
                "\nFaculty = " + user.getFaculty();
    }
}
