package user;

/**
 * Class containing the student information, which is a subclass of user class.
 */
public class Student extends User{

	/**
	 * The total points obtained by the student.
	 */
	private int points;

	/**
	 * Creates a new student with the userID, hashed password, name, faculty and points of the student.
	 * @param userID The student's userID.
	 * @param password The student's password.
	 * @param name The student's name.
	 * @param faculty The student's faculty.
	 * @param points The sutdent's points.
	 */
	public Student(String userID, String password, String name, Faculty faculty, int points) {
		super(userID, password, name, faculty);
		this.points = points;
	}

	/**
	 * Gets the total points obtained by the student.
	 * @return the student's points.
	 */
	public int getPoints() { return points; }
	
	/**
	 * Adds one point to the total points obtained by the student.
	 */
	public void addPoints() {
		points++;
	}
}

