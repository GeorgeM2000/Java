package hashsets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class HashSetExample1 {

	public static void main(String args[]) {
		HashSet<Object> mySet = new HashSet<Object>();
		ArrayList<String> al = new ArrayList<String>();
		al.add("John");
		al.add("Jim");
		Integer n = 15;
		Double d = 23.45;
		mySet.add(al);
		mySet.add(n);
		mySet.add(d);
		

		Iterator it = mySet.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}