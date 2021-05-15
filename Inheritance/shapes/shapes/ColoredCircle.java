package inheritance.shapes;

public class ColoredCircle extends Circle {

	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		return super.toString() + " with color " + color;
	}
}
