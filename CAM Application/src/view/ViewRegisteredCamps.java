package view;

public class ViewRegisteredCamps implements ViewCamps {
	public static void displayCamps(ArrayList<Camp> c) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).isCommittee || c.get(i).isAttendee){
                System.out.println(c.get(i).getCampName() + c.get(i).getCampID());
            }
        }
    }
}
