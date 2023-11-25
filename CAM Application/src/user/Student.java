package user;

public class Student extends User{
	private int points;

	public Student(String userID, String password, String name, Faculty faculty, int points) {
		super(userID, password, name, faculty);
		this.points = points;
	}

	public int getPoints() { return points; }
	
	public void addPoints() {
		points++;
	}

