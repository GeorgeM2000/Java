package hashmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapExmaple1 {
    public static void main(String[] args) {
        // Create an empty hash map
        HashMap<String, String> map = new HashMap<>();
  
        // Add elements to the map
        map.put("Greece", "Thessaloniki");
        map.put("Greece", "Athens");
        map.put("England", "London");
        map.put("Germany", "Berlin");
  
        // Εμφάνιση του μεγέθους και του περιεχομένου
        System.out.println("Size of map is: " + map.size());
        System.out.println(map+"\n");
        // Διάσχιση του HashMap (Τρόπος 1)
        Set<String> set = map.keySet();
        for (String key: set) {
        	System.out.print("key="+key);
        	System.out.println("\t value="+map.get(key));
        }
        System.out.println();
        // Διάσχιση του HashMap (Τρόπος 2)
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
        	String key = iterator.next();
        	System.out.print("key="+key);
        	System.out.println("\t value="+map.get(key));
        }
        System.out.println();
        // Έλεγχος εάν υπάρχει το κλειδί
        if (map.containsKey("England")) {
            String value = map.get("England");
            System.out.println("value for key" + " \"England\" is: " + value);
        }
        
        HashMap<String, ArrayList> cityMap = new HashMap<String, ArrayList>();
        ArrayList<String> al1 = new ArrayList();
        al1.add("Athens");
        al1.add("Thessaloniki");
        al1.add("Patra");
        cityMap.put("Greece", al1);

        ArrayList<String> al2 = new ArrayList();
        al2.add("Berlin");
        al2.add("Munchen");
        al2.add("Hamburg");
        cityMap.put("Germany", al2);
        
        System.out.println("cityMap size = "+cityMap.size());
        
        Set<String> set2 = cityMap.keySet();
        for (String key: set2) {
        	System.out.print("key="+key);
        	System.out.println("\t value="+cityMap.get(key));
        	ArrayList<String> tmp = cityMap.get(key);
        	for (int i=0; i<tmp.size(); i++) {
        		System.out.println(tmp.get(i));
        	}
        }
        

    }
}
