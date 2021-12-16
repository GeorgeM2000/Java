import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task_2B {
	
	private static final int[] fibonacciCache = new int[200];
	private static final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	// fibonacci()
	static int fibonacci(int x) {
		if (x == 0) return 0;
	    if (x == 1) return 1;
	    return fibonacci(x - 1) + fibonacci(x - 2);
	}
	
	// fibonacci() memoization
    static int fibonacciMemoization(int x) {
        System.out.println(x);
        if (x == 0) return 0;
        if (x == 1) return 1;

        if (fibonacciCache[x] > -1) {
            return fibonacciCache[x];
        } else {
            fibonacciCache[x] = fibonacciMemoization(x - 1) + fibonacciMemoization(x - 2);
            return fibonacciCache[x];
        }
    }
	
	// fibonacciSum()
	static long fibonacciSum(int N){
		long sum = 0;
		for(int i=0; i<N; i++) {
		    sum = sum + fibonacci(i);
		}
		return sum;
	}
	
	
	// MyCallable class for thread pool
	public static class MyCallable implements Runnable {
		private static long sum = 0;
		private int current_loop;

        public MyCallable(int loop) {
        	this.current_loop = loop;
        }
        
        public void run( ) {
            try {
            	long k = fibonacci(this.current_loop);
            	lock.writeLock().lock();
            	MyCallable.sum += k;
            } catch (Exception e){
                e.printStackTrace();
            } finally {
            	lock.writeLock().unlock();
            }
            System.out.println("Task for current fibonnaci number " + this.current_loop + " and sum: " + MyCallable.sum + " is finished in thread" + Thread.currentThread().getName());
        }
    }
	
	// RunnableEx class for threads
	public static class RunnableEx implements Runnable {
		private static long sum = 0;
		private int current_loop;
		
		public RunnableEx(int loop) {
			this.current_loop = loop;
		}

        public void run() {  
        	try {
        		System.out.println(Thread.currentThread().getName() +  " started running");   
            	long k = fibonacci(this.current_loop);   	  
            	lock.writeLock().lock();
            	RunnableEx.sum += k;
    	        System.out.println("\nCurrent fibonacci number:  " + this.current_loop + "  and sum: " + RunnableEx.sum);
        	} finally {
        		lock.writeLock().unlock();
        		System.out.println(Thread.currentThread().getName() +  " finished running");	
        	}
        	
	        
	        		
        }
    }
	
	// Function to calculate fibonacci using threads
	static void fibonacciMultipleThreads(int N) throws InterruptedException {
		Thread[] threads = new Thread[N];
		
        for (int i = 0; i < N; i++ ) {
            RunnableEx r = new RunnableEx(i);
            threads[i] = new Thread(r);
            threads[i].start();
        }

        for (int i = 0; i < N; i++) {
            threads[i].join();
        }
	}

	
	// Main ---------------------------------------------------------------
	public static void main(String[] args) throws InterruptedException {
		// Scanner object
		Scanner input = new Scanner(System.in);
		
		// Main prompt
		System.out.println("For normal serial execution press 1:\nTo create N thrads press 2:\nTo create a thread pool press 3:\n");
		int c = Integer.parseInt(input.nextLine());
		
			
		// Loops for fibonacci
		System.out.println("Enter number of loops for fibonacci");
		int loops = Integer.parseInt(input.nextLine());
			
			
		switch(c) {
		// Normal serial execution --------------------------------
		case 1:
			Instant start_serial_instant = Instant.now();			
			System.out.println("Fibonacci sum for " + loops + " loops is: " + fibonacciSum(loops));			
			Instant end_serial_instant = Instant.now();
			
			System.out.println("Difference: " + Duration.between(end_serial_instant, start_serial_instant));
			
			break;
			
		// Execution with N threads ------------------------------
		case 2:
			System.out.println("Thread number is equal to number of loops.");
			
			Instant start_thread_instant = Instant.now();	        
	        fibonacciMultipleThreads(loops);
	        Instant end_thread_instant = Instant.now();
	       
	        System.out.println("Difference: " + Duration.between(end_thread_instant, start_thread_instant));	        
	        break;
	        
	    // Execution with thread pool -----------------------------
		case 3:
			System.out.println("Enter pool size: ");
			int T = Integer.parseInt(input.nextLine());
			
			Instant start_pool_instant = Instant.now();		
			
			ExecutorService pool = Executors.newFixedThreadPool(T);
	        for (int i = 0; i < loops; i++) {
	            MyCallable task = new MyCallable(i);
	            pool.submit(task);
	        }
	        pool.shutdown();
	        pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);	
	        
	        Instant end_pool_instant = Instant.now();
	        
	        System.out.println("Difference: " + Duration.between(end_pool_instant, start_pool_instant));	        
	        break;
		}
			
		
		input.close();

	}

}
