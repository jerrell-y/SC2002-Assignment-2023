package user;

import camppackage.Camp;

public class Student extends User{
	private int points;
	private int[] registeredCamps;
	
	enum role {
		COMMITTEE,
		ATTENDEE
	}

	public Student(String userID, String password, String name, Faculty faculty) {
		super(userID, password, name, faculty);
		this.points = 0;
	}
	
	public void viewCamps(campDatabase c) {
		for (int i=0;i<c.camps.length();i++) {
			if (checkRole(c.camps[i]) != 2 && c.camps[i].getVisiblity() == true) {
				c.camps[i].printInfo();       //can have a method in camp class to print out all the camp details
			}
		}
	}
	
	public void register(Camp c, role r) {
		if (checkRole(c) == 2) {    //check that student not in camp
			if (r == role.COMMITTEE) {
				for (int i=0;i<c.campCommittees.length();i++) {
					if (c.campCommittees[i] == null) {
						c.campCommittees[i] = name;
						break;
					}
				}
			}
			else {
				for (int i=0;i<c.campAttendees.length();i++) {
					if (c.campAttendees[i] == null) {
						c.campAttendees[i] = name;
						break;
					}
				}
			}
		}
	}
	
	public void withdraw(Camp c) {
		if (checkRole(c) == 1) {
			System.out.println("Committee cannot withdraw.");
		}
		
		else {
			for (int i=0;i<c.campAttendees.length();i++) {
				if (c.campAttendees[i] == name) {
					c.campAttendees[i] = null;
				}
			}
			for (int i=0;i<c.campAttendees.length();i++) {  //cover the missing slot
				if (c.campAttendees[i] == null) {
					c.campAttendees[i] = c.campAttendees[i+1];
					c.campAttendees[i+1] = null;
				}
			}
		}
		
	}
	
	public int checkRole(Camp c) {        //will return 1 if student is committee for that camp, 0 if attendee
		for (int i=0;i<c.campCommittees.length();i++) {
			if (name == c.campCommittees[i]) {
				return 1;
			}
		}
		for (int i=0;i<c.campAttendees.length();i++) {
			if (name == c.campAttendees[i]) {
				return 0;
			}
		}
		
	//	System.out.println("Student not registered for any camp yet.");
		return 2;
	}
}
