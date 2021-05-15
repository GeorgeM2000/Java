package inheritance.shapes;

public class Circle extends Shape {
	
	private int radius;

	public void setRadius(int r) {
		radius = r;
	}
	public String toString() {
		return super.toString() + ": Circle(" + radius + ")";
	}

}
