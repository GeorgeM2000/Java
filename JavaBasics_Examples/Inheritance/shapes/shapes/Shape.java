package inheritance.shapes;

public class Shape {
	
	private int x, y;  // Position
	protected String name;
	
	public void setPosition(int px, int py) {
		x = px;
	    y = py;
	}
	public String toString() {
		return "Shape(" + x + ", " + y + ")";
	}

}
