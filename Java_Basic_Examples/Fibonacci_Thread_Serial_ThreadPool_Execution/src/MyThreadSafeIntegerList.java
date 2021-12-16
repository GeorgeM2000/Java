import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyThreadSafeIntegerList {
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private List<Integer> list = new ArrayList<Integer>();
	
	// isEmpty()
	boolean isEmpty() {
		try {
            lock.readLock().lock();
            return list.isEmpty();
        } finally {
            lock.readLock().unlock();
        }
	}
	
	
	// addFirst()
	void addFirst(int value) {
		try {
            lock.writeLock().lock();
            list.add(0, value);
        } finally {
            lock.writeLock().unlock();
        }
	}
	
	
	// addLast()
	void addLast(int value) {
		try {
            lock.writeLock().lock();
            list.add(list.size()-1, value);
        } finally {
            lock.writeLock().unlock();
        }
	}
	
	
	// removeFirst()
	boolean removeFirst() {
		try {
            lock.writeLock().lock();
            int temp_val = list.get(0);
            list.remove(0);
            if(temp_val == list.get(0)) {
            	return true;
            } 
            return false;	
       
        } finally {
            lock.writeLock().unlock();
        }
	}
	
	// removeLast()
	boolean removeLast() {
		try {
            lock.writeLock().lock();
            int temp_val = list.get(list.size()-1);
            list.remove(list.size()-1);
            if(temp_val == list.get(list.size()-1)) {
            	return true;
            } 
            return false;	
       
        } finally {
            lock.writeLock().unlock();
        }
	}
	
	
	// removeValue()
	boolean removeValue(int value) {
		try {
            lock.writeLock().lock();
            for(int i=0; i<list.size(); i++) {
            	if(value == list.get(i)) {
            		list.remove(i);
            		if(value == list.get(i)) {
            			return true;
            		}
            		return false;
            	}
            }
            return false;
       
        } finally {
            lock.writeLock().unlock();
        }
	}
	
	// valueExists()
	boolean valueExists(int value) {
		try {
            lock.readLock().lock();
            for(int i: list) {
            	if(value == i) {
            		return true;
            	}
            }
            return false;
       
        } finally {
            lock.readLock().unlock();
        }
	}
	
	// addIfNotExists()
	boolean addIfNotExists(int value) {
		try {
            lock.writeLock().lock();
            for(int i: list) {
            	if(value == i) {
            		return false;
            	}
            }
            return list.add(value);
            
        } finally {
            lock.writeLock().unlock();
        }
	}
}
