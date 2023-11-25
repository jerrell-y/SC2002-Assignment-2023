package UI;

import java.util.ArrayList;
import java.util.Scanner;

import camppackage.Enquiry;
import camppackage.Suggestion;
import login.LoginManager;
import manager.CampManager;
import manager.EnquiryManager;
import manager.SuggestionManager;
import user.UserManager;
import view.ViewAvailableCamps;
import view.ViewRegisteredCamps;

public class StudentUI {
	
    public static void start() {
		int mainChoice, subChoice;
		do {
			Scanner scan = new Scanner(System.in);    	
			System.out.println("================================");
			System.out.println("List of options:");
			System.out.println("1. View available camps");
			System.out.println("2. View registered camps");
			System.out.println("3. Change password");
			System.out.println("4. Logout");
			System.out.println("================================");

			System.out.print("Choose an option: ");
			System.out.println();
			mainChoice = scan.nextInt();
			switch (mainChoice) {
				case 1:
					do {
						int campChoice;
						ViewAvailableCamps vac = new ViewAvailableCamps();
						ArrayList<Integer> availableCamps = vac.displayCamps();
						System.out.println();
						if (availableCamps.size() == 0) {
							break;
						}

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

					if (registeredCamps.size() == 0) {
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
						System.out.println("2. Add new enquiry");
						System.out.println("3. View enquiries");
						System.out.println("4. Go back");

						subChoice = scan.nextInt();
						switch (subChoice) {
							case 1:
								CampManager.withdraw();
								System.out.println("Successfully removed from camp");
								break;

							case 2:
								System.out.println("Enter your enquiry: ");
								scan.nextLine();
								String enqy = scan.nextLine();
								EnquiryManager.addEnquiry(enqy);
								break;
					
							case 3:
								ArrayList<Enquiry> userEnquiries = EnquiryManager.printUserEnquiry();
								if (userEnquiries.size() == 0) {
									System.out.println("You have no enquiries!\n");
									break;
								}

								System.out.println("Select an enquiry: ");
								int enquiryNum = scan.nextInt();
								EnquiryManager.setEnquiry(userEnquiries.get(enquiryNum-1));
								System.out.println("1. Edit enquiry");
								System.out.println("2. Delete enquiry");
								System.out.println("3. Go back");
								int subsubChoice = scan.nextInt();
								switch(subsubChoice) {
									case 1:
										System.out.println("Enter new enquiry: ");
										scan.nextLine();
										enqy = scan.nextLine();
										EnquiryManager.editEnquiry(enqy);
										break;
									case 2:
										EnquiryManager.deleteEnquiry(enquiryNum-1);
										break;
									case 3:
										break;
								}

								
								
								break;

							case 4:   //go back
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
								ArrayList<Suggestion> s = CampManager.getSuggestByUser();   //not implemented yet
								if (s == null) {
									System.out.println("1. Add suggestion");
									System.out.println("2. Go back");
									int x = scan.nextInt();
									switch(x) {
										case 1:
											System.out.println("Enter suggestion: ");
											String sgn = scan.next();
											SuggestionManager.addSuggestion(sgn);
											break;
										case 2:
											break;
									}

								}
								else {
									SuggestionManager.printUserSuggestions();
									System.out.println("Select an option: ");
									int sugChooser = scan.nextInt();
									System.out.println("1. Add suggestion");
									System.out.println("2. Edit suggestion");
									System.out.println("3. Delete suggestion");
									System.out.println("4. Go back");
									int choicer = scan.nextInt();
									switch(choicer) {
										case 1:
											System.out.println("Enter suggestion: ");
											String sgn = scan.next();
											SuggestionManager.addSuggestion(sgn);
											break;
										case 2:
											System.out.println("Enter new suggestion: ");
											sgn = scan.next();
											SuggestionManager.editSuggestion(sgn);
											break;
										case 3:
											SuggestionManager.deleteSuggestion(sugChooser);
											break;
										case 4:
											break;
									}
								}
								
								break;

							case 2:
								ArrayList<Enquiry> allEnquiries = EnquiryManager.printAllEnquiry();

								if (allEnquiries.size() == 0) {
									System.out.println("There are no enquiries!\n");
									break;
								}

								System.out.println("Select an enquiry: ");
								int enquiryNum = scan.nextInt();
								EnquiryManager.setEnquiry(allEnquiries.get(enquiryNum-1));

								System.out.println("1. Reply enquiry");
								System.out.println("2. Go back");
								int choicee = scan.nextInt();
								switch(choicee) {
									case 1:
										System.out.println("Enter reply: ");
										scan.nextLine();
										String reply = scan.nextLine();
										EnquiryManager.replyEnquiry(reply);
										break;
									case 2:
										break;
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
					mainChoice = 4;
					break;
				
				case 4:           //logout
					break;

				default:
					System.out.println("Please enter a valid option!");
			}
		} while (mainChoice != 4);
    }
}
