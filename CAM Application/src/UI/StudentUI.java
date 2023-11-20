package UI;

import java.util.Scanner;

public class StudentUI {

    //public void showWithdraw() {

   // }
	
    public void start() {
    	Scanner scan = new Scanner(System.in);    	
    	int x = scan.nextInt();
    	int y;
    	
    	switch (x) {
    	case 1: //view available camps
    		//visibility == true, faculty matches user's faculty
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
    		
    	//Switch case for all functions
        //1. View available camps
        //2. View registered camps
            // Show list of camps
            // 1. Choose a specific camp
                // print camp details
                // 1. Withdraw
                    //showWithdraw(camp)
                // 2. AddSuggestions
                // 3. AddEnquiry
                // 4. ViewEnquiry
        //3. Exit
    }
}
