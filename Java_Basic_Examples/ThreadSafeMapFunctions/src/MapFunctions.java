import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MapFunctions {
	// implement the following thread safe functions for a Map<Integer, String>
	private HashMap<Integer, String> map =  new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    // return the value from key or null if key does not exist
    String lookup(int key) {
    	try {
            lock.readLock().lock();
            if(map.containsKey(key)) {
            	return map.get(key);
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    // add the value and return true if the value was already present
    boolean put(int key, String value) {
        // ...
    	try {
    		lock.writeLock().lock();
    		if(map.containsKey(key) && map.containsValue(value)) {
    			return true;
    		}
    		map.put(key, value);
    		return false;
    	} finally {
    		lock.writeLock().unlock();
    	}       
    }

    // try to remove the key from the map. return true if key was deleted, false if it did not exist
    boolean removeKey(int key) {
        // ...
    	try {
    		lock.writeLock().lock();
    		if(!map.containsKey(key)) {
    			return false;
    		}
    		map.remove(key);
    		if(!map.containsKey(key)) {
    			return true;
    		}
    		return false;
    	} finally {
    		lock.writeLock().unlock();
    	}     
    }


    // add [key, value] in the map, return true if it was added, false if key was already in
    boolean addOnlyIfKeyNotPresent(int key, String value) {
        // ...
    	try {
    		lock.writeLock().lock();
    		if(map.containsKey(key)) {
    			return false;
    		}
    		map.put(key, value);
    		if(map.containsKey(key)) {
    			return true;
    		}
    		return false;
    	} finally {
    		lock.writeLock().unlock();
    	}
    }

    // if key exists, replace its value, return true if replaced
    boolean replaceKeyWithValue(int key, String value) {
        // ...
    	try {
    		lock.writeLock().lock();
    		if(!map.containsKey(key)) {
    			return false;
    		} 
    		String temp_value = map.get(key);
    		map.put(key, value);
    		if(temp_value != map.get(key)) {
    			return true;
    		}
    		
    		return false;
    	} finally {
    		lock.writeLock().unlock();
    	}
    }

    public static void main(String[] args) throws InterruptedException {

        MapFunctions hashTable = new MapFunctions();

        System.out.println(hashTable.put(1, "one"));
    }
}
