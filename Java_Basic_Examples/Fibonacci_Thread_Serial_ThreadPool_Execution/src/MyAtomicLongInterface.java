import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyAtomicLongInterface {
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private long longNumber;
	
	// set()
	void set(long newValue) {
		try {
            lock.writeLock().lock();
            longNumber = newValue;
        } finally {
            lock.writeLock().unlock();
        }
	}
	
	// incrementAndGet()
	long incrementAndGet() {
		try {
            lock.writeLock().lock();
            longNumber += 1;
            return longNumber;
        } finally {
            lock.writeLock().unlock();
        }
	}
	
	
	// addAndGet()
	long addAndGet(long value) {
		try {
            lock.writeLock().lock();
            longNumber += value;
            return longNumber;
        } finally {
            lock.writeLock().unlock();
        }
	}
	
	// getValue()
	long getValue() {
		try {
            lock.readLock().lock();
            return longNumber;
        } finally {
            lock.readLock().unlock();
        }
	}
}
