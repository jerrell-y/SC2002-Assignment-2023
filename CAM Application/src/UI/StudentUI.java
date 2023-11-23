package UI;

import java.util.Scanner;

public class StudentUI {
	
    public void start() {
    	Scanner scan = new Scanner(System.in);    	
    	int x = scan.nextInt();
    	int y;
    	
    	switch (x) {
    	case 1: //view available camps
    		//visibility == true, check if faculty matches user's faculty
			//show list of camps
				//1. Choose a specific camp
					//print camp details
					//1. register
					//2. viewenquiry
						//add enquiry, edit, delete enquiry
					//3. go back
					
    		break;
    	
    	case 2: //view registered camps
    		//show list of camps, need a method to iterate thru camp database and search
    		//array list of all the members in that camp
    		y = scan.nextInt(); //choose a specific camp
    	//	generateReport(); print camp info
    		break;
    	}
    	
    	case 3:
    		break;
    	
		//attendee
        //2. View registered camps
            // Show list of registered camps and their roles
            // 1. Choose a specific camp
                // print camp details
                // 1. Withdraw
                    //showWithdraw(camp)
                // 2. ViewEnquiry
					//1. Add
					//2. Edit
					//3. Delete
				// 3. exit
        //3. Exit

		//committee
        //2. View registered camps
            // Show list of registered camps and their roles
            // 1. Choose a specific camp
                // print camp details
                // 1. View enquiry
					//1. Add
					//2. Edit
					//3. Delete
                // 2. View suggestions
					//1. Add
					//2. Edit
					//3. Delete
				// 3. exit
        //3. Exit
    }
}
