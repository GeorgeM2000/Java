package inheritance.shapes;

public class TestShapes {

	public static void main(String args[]) {
		Circle c = new Circle();
		Rectangle r = new Rectangle();

		r.setPosition(1, 2);
		r.setDimensions(50, 50);
		r.name = "Rectangle";
		
		c.setPosition(3, 4);
		c.setRadius(10);
		c.name = "Circle";
		
		System.out.println(r);
		System.out.println(c);
		
		ColoredCircle cc = new ColoredCircle();
		cc.setRadius(14);
		cc.setPosition(22, 28);
		cc.setColor("yellow");
		System.out.println(cc.toString());
	}

}
