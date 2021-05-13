package hashtables;
/*
    * HashTable Example
*/

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class HashTableExmaple1 {
    public static void main(String[] args) {
        // HashTable declaration
        Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
  
        // HashTable initialization
        ht.put(1, "Thessaloniki");
        ht.put(2, "Athens");
        ht.put(3, "Patra");
        ht.put(4, "Volos");
  
        // Print size of HashTable
        System.out.println("Size of ht is: " + ht.size());
        System.out.println(ht+"\n");

        // HashTable iteration 
        // Method 1
        Set<Integer> set = ht.keySet();
        for (Integer key: set) {
        	System.out.print("key="+key);
        	System.out.println("\t value="+ht.get(key));
        }
        System.out.println();

        // HashTable iteration
        // Method 2
        Enumeration en = ht.keys();
        while (en.hasMoreElements()) {
        	Integer key = (Integer)en.nextElement();
        	System.out.print("key="+key);
        	System.out.println("\t value="+ht.get(key));
        }
        System.out.println();
        
        
        if (ht.containsKey(2)) {
            String value = ht.get(2);
            System.out.println("value for key" + " \"2\" is: " + value);
        }
        
        if (ht.containsValue("Patra")) {
            System.out.println("The value exists inside hashtable");
        }
        
        ht.remove(3);
        System.out.println(ht+"\n");
        
        ht.replace(4, new String("Kastoria"));
        System.out.println(ht+"\n");
    }
}
