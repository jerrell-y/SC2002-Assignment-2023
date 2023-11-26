package UI;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.View;

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

	public static int checkValidInput(Scanner scan) {
		int returnValue;
		try {
			returnValue = Integer.parseInt(scan.nextLine());
		}
		catch (NumberFormatException e) {
			returnValue = -1;
		}
		return returnValue;
	}
	
    public static void start() {
		int mainChoice, subChoice = 1;
		Scanner scan = new Scanner(System.in);
		String confirmString;
		do {  	
			System.out.println("================================");
			System.out.println("List of options:");
			System.out.println("1. View available camps");
			System.out.println("2. View registered camps");
			System.out.println("3. Change password");
			System.out.println("4. Logout");
			System.out.println("================================");

			System.out.print("Choose an option: ");
			mainChoice = checkValidInput(scan);
			System.out.println();
			switch (mainChoice) {
				case 1:
					do {
						int campChoice;
						System.out.println("================================");
						ArrayList<Integer> availableCamps = ViewAvailableCamps.getInstance().displayCamps();
						if (availableCamps.size() == 0) {
							System.out.println("There are currently no camps available for you! \n");
							break;
						}
						System.out.println("================================");

						do {
							System.out.print("Choose a camp (Enter 0 to go back): ");
							campChoice = checkValidInput(scan);
							if (campChoice == 0) {
								break;
							}
							else if (campChoice > availableCamps.size() || campChoice <= 0) {
								System.out.println("Please enter a valid camp number! \n");
							}
						} while (campChoice > availableCamps.size() || campChoice <= 0);

						System.out.println();
						if (campChoice == 0) {
							break;
						}
						System.out.println("================================");
						CampManager.setCamp(availableCamps.get(campChoice-1));
						CampManager.printDetails();

						do {
							System.out.println("================================");
							System.out.println("1. Register as attendee");
							System.out.println("2. Register as commitee");
							System.out.println("3. Go back");
							System.out.println("================================");

							System.out.print("Choose an option: ");
							subChoice = checkValidInput(scan);
							System.out.println();
							boolean success;
							switch (subChoice) {
								case 1:
									success = CampManager.registerAttendee();
									if (success) {
										System.out.println("Successfully registered as an attendee! \n");
									}
									break;
										
								case 2:
									success = CampManager.registerCommitee();
									if (success) {
										System.out.println("Successfully registered as a commitee member! \n");
									}
									break;

								case 3:   //go back
									break;

								default:
									System.out.println("Please enter a valid option! \n");
							}
						} while (subChoice <= 0 && subChoice > 3);
					} while (subChoice == 3);

					break;

				case 2:
					do {
						int campChoice;
						System.out.println("================================");
						ArrayList<Integer> registeredCamps = ViewRegisteredCamps.getInstance().displayCamps();
						if (registeredCamps.size() == 0) {
							System.out.println("You are currently not registered in any camps! \n");
							break;
						}
						System.out.println("================================");

						do {
							System.out.print("Choose a camp (Enter 0 to go back): ");
							campChoice = checkValidInput(scan);
							if (campChoice == 0) {
								break;
							}
							if (campChoice > registeredCamps.size() || campChoice <= 0) {
								System.out.println("Please enter a valid camp number! \n");
							}
						} while (campChoice > registeredCamps.size() || campChoice <= 0);

						System.out.println();
						if (campChoice == 0) {
							break;
						}

						System.out.println("================================");
						CampManager.setCamp(registeredCamps.get(campChoice-1));
						CampManager.printDetails();
						
						if (CampManager.isAttendee()) {
							int subsubChoice = 2;
							do {
								System.out.println("================================");
								System.out.println("1. Withdraw from camp");
								System.out.println("2. Add new enquiry");
								System.out.println("3. View enquiries");
								System.out.println("4. Go back");
								System.out.println("================================");
								
								System.out.print("Choose an option: ");
								subChoice = checkValidInput(scan);
								System.out.println();
								switch (subChoice) {
									case 1:
										System.out.print("Are you sure you want to withdraw from this camp? (Enter CONFIRM to continue): ");
										confirmString = scan.nextLine();
										System.out.println();
										if (confirmString.equals("CONFIRM")) {
											CampManager.withdraw();
											System.out.println("Successfully removed from camp\n");
											subChoice = 4;
											subsubChoice = 4;
										}
										else {
											System.out.println("Aborted the withdrawal process.\n");
										}
										break;

									case 2:
										System.out.print("Enter your enquiry: ");
										String enqy = scan.nextLine();
										System.out.println();
										EnquiryManager.addEnquiry(enqy);
										System.out.println("Enquiry successfully added.\n");
										break;
							
									case 3:
										System.out.println("================================");
										ArrayList<Integer> userEnquiries = EnquiryManager.printUserEnquiry();
										System.out.println("================================");
										if (userEnquiries.size() == 0) {
											System.out.println("You have no enquiries! \n");
											break;
										}

										int enquiryNum;
										do {
											System.out.print("Select an enquiry (Enter 0 to go back): ");
											enquiryNum = checkValidInput(scan);
											if (enquiryNum == 0) {
												break;
											}
											if (enquiryNum > userEnquiries.size() || enquiryNum <= 0) {
												System.out.println("Please enter a valid enquiry number! \n");
											}
										} while (enquiryNum > userEnquiries.size() || enquiryNum <= 0);

										System.out.println();
										if (enquiryNum == 0) {
											break;
										}

										EnquiryManager.setEnquiry(userEnquiries.get(enquiryNum-1));

										do {
											System.out.println("================================");
											System.out.println("1. Edit enquiry");
											System.out.println("2. Delete enquiry");
											System.out.println("3. Go back");
											System.out.println("================================");

											System.out.print("Choose an option: ");
											subsubChoice = checkValidInput(scan);
											System.out.println();
											switch(subsubChoice) {
												case 1:
													System.out.print("Enter your new enquiry: ");
													enqy = scan.nextLine();
													if (EnquiryManager.editEnquiry(enqy)) {
														System.out.println("Your enquiry was successfully edited! \n");
														subsubChoice = 3;
													}
													break;

												case 2:
													System.out.print("Are you sure you want to delete this enquiry? (Enter CONFIRM to continue): ");
													confirmString = scan.nextLine();
													System.out.println();

													if (confirmString.equals("CONFIRM")) {
														if (EnquiryManager.deleteEnquiry(userEnquiries.get(enquiryNum-1))) {
															System.out.println("Your enquiry was successfully deleted! \n");
															subsubChoice = 3;
														}
													}
													else {
														System.out.println("Aborted the deletion process.\n");
													}
													break;

												case 3:
													break;

												default:
													System.out.println("Please enter a valid option! \n");
											}
										} while (subsubChoice != 3);
										break;

									case 4:   //go back
										subsubChoice = 4;
										subChoice = 4;
										break;

									default:
										System.out.println("Please enter a valid option! \n");
								}
							} while (subsubChoice != 4);
						}

						else if (CampManager.isCommitee()) {
							int subsubChoice = 2;
							do {
								System.out.println("================================");
								System.out.println("1. View suggestions");
								System.out.println("2. Add new suggestion");
								System.out.println("3. View enquiries");
								System.out.println("4. Generate report");
								System.out.println("5. Go back");
								System.out.println("================================");

								System.out.print("Choose an option: ");
								subChoice = checkValidInput(scan);
								System.out.println();

								switch (subChoice) {
									case 1:
										System.out.println("================================");
										ArrayList<Integer> userSuggestions = SuggestionManager.printUserSuggestions();
										System.out.println("================================");

										if (userSuggestions.size() == 0) {
											System.out.println("You have no suggestions! \n");
											break;
										}
										
										int suggestionNum;
										do {
											System.out.print("Select an suggestion (Enter 0 to go back): ");
											suggestionNum = checkValidInput(scan);
											if (suggestionNum == 0) {
												break;
											}
											if (suggestionNum > userSuggestions.size() || suggestionNum <= 0) {
												System.out.println("Please enter a valid suggestion number! \n");
											}
										} while (suggestionNum > userSuggestions.size() || suggestionNum <= 0);

										System.out.println();
										if (suggestionNum == 0) {
											subsubChoice = 4;
											break;
										}

										SuggestionManager.setSuggestion(userSuggestions.get(suggestionNum-1));

										do {
											System.out.println("================================");
											System.out.println("1. Edit suggestion");
											System.out.println("2. Delete suggestion");
											System.out.println("3. Go back");
											System.out.println("================================");


											System.out.print("Choose an option: ");
											subChoice = checkValidInput(scan);
											System.out.println();
											switch (subsubChoice) {
												case 1:
													System.out.println("Enter your new suggestion: ");
													String sgn = scan.next();
													if (SuggestionManager.editSuggestion(sgn)) {
														System.out.println("Your suggestion was successfully edited! \n");
														subsubChoice = 3;
													}
													break;

												case 2:
													System.out.print("Are you sure you want to delete this suggestion? (Enter CONFIRM to continue): ");
													confirmString = scan.nextLine();
													System.out.println();

													if (confirmString.equals("CONFIRM")) {
														if (SuggestionManager.deleteSuggestion(userSuggestions.get(suggestionNum-1))) {
															System.out.println("Your suggestion was successfully deleted! \n");
															subsubChoice = 3;
														}
													}
													else {
														System.out.println("Aborted the deletion process.\n");
													}
													break;

												case 3:
													break;

												default:
													System.out.println("Please enter a valid option! \n");
											}
										} while (subsubChoice != 3);
										break;

									case 2:
										System.out.print("Enter your suggestion: ");
										String sug = scan.nextLine();
										System.out.println();
										SuggestionManager.addSuggestion(sug);
										System.out.println("Suggestion successfully added.\n");
										break;

									case 3:
										System.out.println("================================");
										ArrayList<Integer> allEnquiries = EnquiryManager.printAllEnquiry();
										System.out.println("================================");

										if (allEnquiries.size() == 0) {
											System.out.println("There are no enquiries!\n");
											break;
										}

										int enquiryNum;
										do {
											System.out.print("Select an enquiry (Enter 0 to go back): ");
											enquiryNum = checkValidInput(scan);
											if (enquiryNum == 0) {
												break;
											}
											if (enquiryNum > allEnquiries.size() || enquiryNum <= 0) {
												System.out.println("Please enter a valid enquiry number! \n");
											}
										} while (enquiryNum > allEnquiries.size() || enquiryNum <= 0);

										System.out.println();
										if (enquiryNum == 0) {
											subsubChoice = 4;
											break;
										}

										EnquiryManager.setEnquiry(allEnquiries.get(enquiryNum-1));
										do {
											System.out.println("================================");
											System.out.println("1. Reply to enquiry");
											System.out.println("2. Go back");
											System.out.println("================================");

											System.out.print("Choose an option: ");
											subsubChoice = checkValidInput(scan);
											System.out.println();

											switch(subsubChoice) {
												case 1:
													System.out.println("Enter your reply: ");
													String reply = scan.nextLine();
													if (EnquiryManager.replyEnquiry(reply)) {
														System.out.println("Successfully replied to enquiry! \n");
														subsubChoice = 2;
													}
													break;

												case 2:
													break;

												default:
													System.out.println("Please enter a valid option! \n");
											}
										} while (subsubChoice != 2);
										break;

									case 4:   //generate report
										break;


									case 5:   //go back
										subsubChoice = 4;
										break;
									default:
										System.out.println("Please enter a valid option! \n");
								} 
							} while (subsubChoice != 4);
						} 
					} while (subChoice == 4);
					break;

				case 3:
					String newPass1, newPass2;
					do {
						System.out.print("Enter your new password: ");
						newPass1 = scan.nextLine();
						System.out.print("Enter your new password again: ");
						newPass2 = scan.nextLine();
						if (!newPass1.equals(newPass2)) {
							System.out.println("Please enter the same password!\n");
						}
					} while (!newPass1.equals(newPass2));
					LoginManager.changePassword(UserManager.getUser(), newPass1);
					System.out.println("Your password has been changed successfully. Please login again");
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
