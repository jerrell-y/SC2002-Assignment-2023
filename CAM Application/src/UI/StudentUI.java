package UI;

import java.util.ArrayList;
import java.util.Scanner;

import camppackage.Enquiry;
import login.LoginManager;
import manager.CampManager;
import manager.EnquiryManager;
import user.UserManager;
import view.ViewAvailableCamps;
import view.ViewRegisteredCamps;

public class StudentUI {
	
    public static void start() {
		int mainChoice, subChoice;
		do {
			Scanner scan = new Scanner(System.in);    	
			System.out.println("Select Function");
			System.out.println("1. View available camps");
			System.out.println("2. View registered camps");
			System.out.println("3. Change password");
			System.out.println("4. Logout");
			System.out.println();

			System.out.print("Choose an option: ");
			mainChoice = scan.nextInt();
			switch (mainChoice) {
				case 1:
					do {
						int campChoice;
						ViewAvailableCamps vac = new ViewAvailableCamps();
						ArrayList<Integer> availableCamps = vac.displayCamps();

						if (availableCamps.isEmpty()) {
							System.out.println("There are currently no available camps.\n");
							break;
						}
						System.out.println();

						do {
							System.out.println("Choose a camp: ");
							campChoice = scan.nextInt();
							if (campChoice > availableCamps.size() || campChoice <= 0) {
								System.out.println("Please enter a valid camp number! \n");
							}
						} while (campChoice > availableCamps.size() || campChoice <= 0);

						CampManager.setCamp(availableCamps.get(campChoice-1));
						CampManager.printDetails();

						do {
							System.out.println();
							System.out.println("1. Register as committee");
							System.out.println("2. Register as attendee");
							System.out.println("3. Go back");
							System.out.println();

							System.out.print("Choose an option: ");
							subChoice = scan.nextInt();
							boolean success;
							switch (subChoice) {
								case 1:
									success = CampManager.registerCommitee();
									if (success) {
										System.out.println("Successfully registered as a commitee member! \n");
									}
									break;
										
								case 2:
									success = CampManager.registerAttendee();
									if (success) {
										System.out.println("Successfully registered as an attendee! \n");
									}
									break;

								case 3:   //go back
									break;

								default:
									System.out.println("Please enter a valid option!");
							}
						} while (subChoice <= 0 && subChoice > 3);
					} while (subChoice == 3);
					break;

				case 2:
					int campChoice;
					ViewRegisteredCamps vrc = new ViewRegisteredCamps();
					ArrayList<Integer> registeredCamps = vrc.displayCamps();
					System.out.println();

					if (registeredCamps.isEmpty()) {
						System.out.println("You are currently not registered in any camps.\n");
						break;
					}

					do {
						System.out.println("Choose a camp: ");
						campChoice = scan.nextInt();
						if (campChoice > registeredCamps.size() || campChoice <= 0) {
							System.out.println("Please enter a valid camp number! \n");
						}
					} while (campChoice > registeredCamps.size() || campChoice <= 0);

					CampManager.setCamp(registeredCamps.get(campChoice-1));
					CampManager.printDetails();
					
					if (CampManager.isAttendee()) {
						System.out.println("1. Withdraw from camp");
						System.out.println("2. View enquiry");
						System.out.println("3. Go back");

						subChoice = scan.nextInt();
						switch (subChoice) {
							case 1:
								CampManager.withdraw();
								System.out.println("Successfully removed from camp");
								break;

							case 2:
								ArrayList<Enquiry> enq = CampManager.getEnquiryByUser();
								if (enq == null) {
									System.out.println("1. Add enquiry");
									System.out.println("2. Go back");
									x = scan.nextInt();
									switch(x) {
										case 1:
											System.out.println("Enter your enquiry: ");
											String enqy = scan.next();
											EnquiryManager.addEnquiry(enqy);
											break;
										case 2:
											break;
									}

								}
								else {
									EnquiryManager.printAllEnquiry();

									System.out.println("1. Add enquiry");
									System.out.println("2. Edit enquiry");
									System.out.println("3. Delete enquiry");
									System.out.println("4. Go back");
									switch(x) {
										case 1:
											EnquiryManager.addEnquiry();
											break;
										case 2:
											EnquiryManager.editEnquiry();
											break;
										case 3:
											EnquiryManager.deleteEnquiry();
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

					else if (CampManager.isCommitee()) {                                           //IMPLEMENTATION FOR COMMITTEE
						System.out.println("1. View suggestion");
						System.out.println("2. View enquiry");
						System.out.println("3. Generate report");
						System.out.println("4. Go back");

						subChoice = scan.nextInt();
						switch(subChoice){
							case 1:
								/* 
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
								*/
								break;

							case 2:
								/* 
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
								*/
								break;

							case 3:   //generate report
								break;


							case 4:   //go back
								break;
						}

						break;
					}


				
				case 3:
					String newPass1, newPass2;
					do {
						System.out.print("Enter your new password: ");
						newPass1 = scan.next();
						System.out.print("Enter your new password again: ");
						newPass2 = scan.next();
						if (!newPass1.equals(newPass2)) {
							System.out.println("Please enter the same password!\n");
						}
					} while (!newPass1.equals(newPass2));

					LoginManager.changePassword(UserManager.getUser(), newPass1);
					break;
				
				case 4:           //logout
					break;

				default:
					System.out.println("Please enter a valid option!");
			}
		} while (mainChoice != 4);
    }
}
