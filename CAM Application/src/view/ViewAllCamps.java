package view;

public class ViewAllCamps implements ViewCamps {
	public static void displayCamps(ArrayList<Camp> c) {
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i).getCampName() + c.get(i).getCampID());
        }
    }
}
