package view;

public class ViewRegisteredCamps implements ViewCamps {
	public static void displayCamps(ArrayList<Camp> c) {
        for (int i = 0; i < c.size(); i++) {
            if (CampManager.isAttendee(c.get(i)) || CampManager.isCommittee(c.get(i))) {
                System.out.println(c.get(i).getCampID() + c.get(i).getCampName());
            }
        }
    }
}
