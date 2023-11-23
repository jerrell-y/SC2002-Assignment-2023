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
			viewManager.viewAvailableCamps();
			System.out.println("Enter a campID: ");
			x = scan.nextInt();
			campDatabase.searchCampID(x);
			
			System.out.println("1. Register as committee");
			System.out.println("2. Register as attendee");
			System.out.println("3. Go back");
			x = scan.nextInt();
			switch(x){
				case 1:
					//get camp from the database with that ID
					camp.registerCommittee();
					break;
						
				case 2:
					//get camp from the database with that ID
					camp.registerAttendee();
					break;

				case 3:   //go back
					break;
			}

    	break;
    	
    	case 2:
    		viewManager.viewRegisteredCamps();
			System.out.println("1. Enter a campID: ");
			System.out.println("2. Go back");
			x = scan.nextInt();

			switch(x){
				case 1:

					break;
				case 2:  //go back
					break;
			}


    		break;
    	}
    	
    	case 3:
			System.out.println("Enter new password:")
			String newPass = scan.next();
			user.setPassword(newPass);
    		break;
    	
		case 4:           //logout
			break;
    }
}
