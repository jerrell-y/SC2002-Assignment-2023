package UI;

import java.util.Scanner;

public class StudentUI {
	
    public void start() {
    	Scanner scan = new Scanner(System.in);    	
    	int x = scan.nextInt();
		System.out.println("Select Function");
        System.out.println("1. View available camps");
        System.out.println("2. View registered camps");
        System.out.println("3. Change password");
        System.out.println("4. Logout");
    	
    	switch (x) {
    	case 1:
			ViewAvailableCamps.displayCamps();
			System.out.println("Enter a campID: ");
			x = scan.nextInt();
			campDatabase.searchCampID(x);   //get that specific camp

			System.out.println("1. Register as committee");
			System.out.println("2. Register as attendee");
			System.out.println("3. Go back");

			x = scan.nextInt();
			switch(x){
				case 1:
					camp.toString();
					camp.registerCommittee();
					break;
						
				case 2:
					camp.toString();
					camp.registerAttendee();
					break;

				case 3:   //go back
					break;
			}

    		break;
    	
    	case 2:
			ViewRegisteredCamps.displayCamps();
			System.out.println("Enter a campID: ");
			x = scan.nextInt();
			campDatabase.searchCampID(x);     //find the camp

			System.out.println("1. Withdraw from camp");    //havent checked if its committee or attendee yet
			System.out.println("2. View enquiry");
			System.out.println("3. Go back");

			x = scan.nextInt();
			switch(x){
				case 1:
					camp.withdraw();
					break;

				case 2:
					camp.enquirylist();   //need to add more here
					break;

				case 3:   //go back
					break;
			}

    		break;
    	
    	case 3:
			System.out.println("Enter new password:")
			String newPass = scan.next();
			user.setPassword(newPass);
    		break;
    	
		case 4:           //logout
			break;
    }
}
