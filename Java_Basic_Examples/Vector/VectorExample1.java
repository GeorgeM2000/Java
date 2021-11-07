package vectors;

import java.util.ArrayList;
import java.util.Vector;

public class VectorExample1 {

	public static void main(String[] args) {
        Vector<String> v = new Vector<>(); 
        v.addElement("John");
        v.addElement("Jim");
        v.addElement("Jim");
        v.addElement("Jim");
        
        v.add(1, "Jack"); 
        System.out.println("Initial Vector " + v); 
        
        
        v.set(1, "Nick");
        
        if (v.contains("Nick"))
        	System.out.println("Vector contains Nick");
        
        
        for (int i = 0; i < v.size(); i++) { 
            System.out.print(v.get(i) + " "); 
        } 
        System.out.println(); 
    
        
        for (String str : v) 
            System.out.print(str + " ");         
        
        
        v.remove(1); 
        System.out.println("\nAfter the Index Removal " + v); 
    
        v.remove("John"); 
        System.out.println("After the Object Removal " + v);
        
        v.clear();
        System.out.println("After clear " + v);
        v.addElement("Jim");
        v.addElement("George");
        v.addElement("Jack");
        v.addElement("George");
        v.addElement("George");
        v.addElement("Maria");
        System.out.println("Before ArrayList " + v);
        ArrayList<String> al = new ArrayList<>();
        al.add("George");
        al.add("Helen");
        al.add("Jack");
        
        v.removeAll(al);
        System.out.println("After removeAll " + v);
	}

}
