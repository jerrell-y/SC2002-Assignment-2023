package view;

public class ViewAllCamps implements ViewCamps {
	public static void displayCamps(ArrayList<Camp> c) {
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i).getCampID() + c.get(i).getCampName());
        }
    }
}
