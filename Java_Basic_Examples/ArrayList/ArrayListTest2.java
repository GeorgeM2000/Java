package arraylists;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest2 {

	public static void main(String[] args) {
		ArrayList <String> al = new ArrayList<>();  
		al.add("Mat");
		al.add("Mike");
		al.add("John");
		al.add("Don");
		
		for (String str: al)  
			System.out.println(str);
		System.out.println();

		for (int i=0; i<al.size(); i++)  
			System.out.println(al.get(i));
		System.out.println();
		
		Iterator iterator = al.iterator();
		while (iterator.hasNext()) 
			System.out.println(iterator.next());
 
	}
}
