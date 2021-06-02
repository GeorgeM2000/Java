package overloading;

class SomeClass {

	public int aMethod(int x, int y) {
		System.out.println("int int");
		return 1;
	}
	
  	public float aMethod(float x, float y) {
  		System.out.println("float float");
  		return 1;
	}
	
  	public double aMethod(double x, double y) {
  		System.out.println("double double");
  		return 1;
  	}		
}

public class OverloadingExample {
	public static void main(String args[]) {
		SomeClass anObject = new SomeClass();
		anObject.aMethod(1,1);
	}
}
