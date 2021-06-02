package inheritance.shapes;

public class Rectangle extends Shape {

	private int height, width;
	
	public void setDimensions(int h, int w) {
		height = h;
		width = w;
	}
	
	public String toString() {
		return super.toString() + ": Rectangle(" + height + " x " + width + ")";
	}

}
