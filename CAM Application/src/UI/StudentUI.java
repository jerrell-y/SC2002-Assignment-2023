package UI;

import java.util.Scanner;

public class StudentUI {
	
    public void start() {
    	Scanner scan = new Scanner(System.in);    	
    	int x = scan.nextInt();
    	int y;  //another scanner
		System.out.println("Select Function");
        System.out.println("1. View available camps");
        System.out.println("2. View registered camps");
        System.out.println("3. Change password");
        System.out.println("4. Logout");
    	
    	switch (x) {
    	case 1:
			viewManager.viewAvailableCamps();
    		break;
    	
    	case 2:
    		viewManager.viewRegisteredCamps();
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
