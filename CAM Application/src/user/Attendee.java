package user;
/*
import java.util.Scanner;

public class Attendee {
	
	public void viewEnquiry(Camp c) {
		for (int i=0;i<c.enquiries.length();i++) {
			if (name == c.enquiries[i].studentName) {
				System.out.println(i + ". Title: " + c.enquiries[i].title + "Content: " + c.enquiries[i].content);
				
				if (c.enquiries[i].answered == true) {
					System.out.println("Reply: " + c.enquiries[i].reply);
				}
			}
		}
	}
	
	public void submitEnquiry(Camp c, String title, String content) {
		for (int i=0;i<c.enquiries.length();i++) {
			if (c.enquiries[i].title == null) {
				c.enquiries[i] = new Enquiry(title,content,name);
				break;
			}
		}
	}
	
	public void deleteEnquiry(Camp c) {
		viewEnquiry(c);
	//	System.out.println("Select an entry to delete.");
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();      //they pick which enquiry to remove
		int y = 1;                   //counter for how many times the student's enquiry is called so far
		
		for (int i=0;i<c.enquiries.length();i++) {
			if (name == c.enquiries[i].getStudentName()) {
				if (x == y) {
					c.deleteEnquiry(i);
				}
				
				else {
					y++;
				}
			}
		}
		
	}
	
	public void editEnquiry(Camp c, String title, String content) {
		viewEnquiry(c);
	//	System.out.println("Select an entry to edit.");
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();      //they pick which enquiry to remove
		int y = 1;                   //counter for how many times the student's enquiry is called so far
		
		for (int i=0;i<c.enquiries.length();i++) {
			if (name == c.enquiries[i].studentName) {
				if (x == y) {
					c.enquiries[i].title = title;
					c.enquiries[i].content = content;
					break;
				}
				
				else {
					y++;
				}
			}
		}
	}
}
*/