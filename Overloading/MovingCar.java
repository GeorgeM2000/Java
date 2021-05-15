package overloading;

class Car {
	private int position;
	
	public Car(){
		this.position = 0;
	}

	public Car(int position){
		this.position = position;
	}

	public void move() {
		position ++ ;
	}
	
	public void move(int delta) {
		position += delta ;
	}

	public int getPosition() {
		return position;
	}
}

public class MovingCar {
	public static void main(String args[]) {
		Car myCar1 = new Car(1);
		Car myCar2 = new Car();
		myCar1.move();
		System.out.println("Position: "+myCar1.getPosition());
		myCar2.move(-1);
		System.out.println("Position: "+myCar2.getPosition());
	}
}