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
			Camp c = campDatabase.getCampsByID(x);   //is this campID supposed to be str or int?

			System.out.println("1. Register as committee");
			System.out.println("2. Register as attendee");
			System.out.println("3. Go back");

			x = scan.nextInt();
			switch(x){
				case 1:
					c.toString();     //print details
					c.registerCommittee();
					break;
						
				case 2:
					c.toString();     //print details
					c.registerAttendee();
					break;

				case 3:   //go back
					break;
			}

    		break;
    	
    	case 2:
			ViewRegisteredCamps.displayCamps();     //if got no camp, can just exit here? If not must check for committee also
			System.out.println("Enter a campID: ");
			x = scan.nextInt();
			Camp c = campDatabase.getCampsByID(x);

			if (c.isAttendee){                            //IMPLEMENTATION FOR ATTENDEE

			System.out.println("1. Withdraw from camp");
			System.out.println("2. View enquiry");
			System.out.println("3. Go back");

			x = scan.nextInt();
			switch(x){
				case 1:
					c.withdraw();     //not implemented yet
					System.out.println("Successfully removed from camp");
					break;

				case 2:
					ArrayList<Enquiry> e = campManager.getEnquiryByID(c);   //not implemented yet
					if (e == null) {
						System.out.println("1. Add enquiry");
						System.out.println("2. Go back");
						x = scan.nextInt();
						switch(x) {
							case 1:
								campManager.addEnquiry(e);
								break;
							case 2:
								break;
						}

					}
					else {
						e.printAllEnquiries();

						System.out.println("1. Add enquiry");
						System.out.println("2. Edit enquiry");
						System.out.println("3. Delete enquiry");
						System.out.println("4. Go back");
						switch(x) {
							case 1:
								campManager.addEnquiry(e);
								break;
							case 2:
								campManager.editEnquiry(e);
								break;
							case 3:
								campManager.deleteEnquiry(e);
								break;
							case 4:
								break;
						}

					}

					break;

				case 3:   //go back
					break;
			}

    		break;
			}

		else {                                           //IMPLEMENTATION FOR COMMITTEE
			System.out.println("1. View suggestion");
			System.out.println("2. View enquiry");
			System.out.println("3. Generate report");
			System.out.println("4. Go back");

			x = scan.nextInt();
			switch(x){
				case 1:
					ArrayList<Suggestion> s = campManager.getSuggestionByID(c);   //not implemented yet
					if (s == null) {
						System.out.println("1. Add suggestion");
						System.out.println("2. Go back");
						x = scan.nextInt();
						switch(x) {
							case 1:
								campManager.addSuggestion(s);
								break;
							case 2:
								break;
						}

					}
					else {
						s.printAllSuggestions();

						System.out.println("1. Add suggestion");
						System.out.println("2. Edit suggestion");
						System.out.println("3. Delete suggestion");
						System.out.println("4. Go back");
						switch(x) {
							case 1:
								campManager.addSuggestion(s);
								break;
							case 2:
								campManager.editSuggestion(s);
								break;
							case 3:
								campManager.deleteSuggestion(s);
								break;
							case 4:
								break;
						}
					}

					break;

				case 2:
					ArrayList<Enquiry> e = campManager.getAllEnquiry(c);   //not implemented yet

					e.printAllEnquiries();

					System.out.println("1. Reply enquiry");
					System.out.println("2. Go back");
					switch(x) {
						case 1:
							campManager.replyEnquiry(e);
							break;
						case 2:
							break;
					}

					}

					break;

				case 3:   //generate report
					break;


				case 4:   //go back
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
