package user;

public class Student extends User{
	private int points;
	private int commiteeCampID;

	public Student(String userID, String password, String name, Faculty faculty, int points, int commiteeCampID) {
		super(userID, password, name, faculty);
		this.points = points;
		this.commiteeCampID = commiteeCampID;
	}

	public int getPoints() { return points; }
	
	public void addPoints() {
		points++;
	}

	public void setCommiteeCampID(int commiteeCampID) {
		this.commiteeCampID = commiteeCampID;
	}
	public int getCommiteeCampID() {
		return commiteeCampID;
	}
}

