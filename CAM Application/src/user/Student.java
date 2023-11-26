package user;

/*
 * class containing the student information, which is a subclass of user class
 */
public class Student extends User{
	/*
	 * The total points obtained by the student
	 */
	private int points;

	/*
	 * the constructor class to create the student object
	 * @param userID is the userID used to login
	 * @param password is the user's password used to login
	 * @param name is the name of the user
	 * @param faculty is the faculty of the user
	 * @param points is the total points of the user
	 */

	public Student(String userID, String password, String name, Faculty faculty, int points) {
		super(userID, password, name, faculty);
		this.points = points;
	}

	/*
	 * The function that retrieves the points from the student class
	 * @return the points for the student
	 */

	public int getPoints() { return points; }
	
	/*
	 * function that adds points to the student class
	 */

	public void addPoints() {
		points++;
	}
}

