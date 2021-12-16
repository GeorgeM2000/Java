import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProdConsPipeline {
	
	//implement a simple pipeline of three steps

    // step 1 (generator): produces random numbers in the range of [100, 10000] and puts it in queue 1
    // step 2 (filter): consumes from queue 1, if the number is even it ignores it, if it is odd, adds it in queue 2
    // step 3 (processor): consumes from queue 2, for each 'x' it consumes, it sleeps for 'x' milliseconds

    // experiment with range, number of threads for different threads to improve throughput

	static BlockingQueue<Integer> queue_1 = new LinkedBlockingQueue<>();
	static BlockingQueue<Integer> queue_2 = new LinkedBlockingQueue<>();

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Random random = new Random();
                    int m = random.nextInt(10000 - 100) + 100;
                    queue_1.put(m);
                    System.out.println("Random number selected " + m + " in queue 1.");
                    //System.out.println(Thread.currentThread().getName() + ": queue size: " + queue.size() + " and produced message: " + m);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer_1 implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Integer m = queue_1.take();
                    if(m % 2 != 0) {
                    	queue_2.put(m);
                    	System.out.println("Number " + m + " is in queue 2.");
                    }
                    //System.out.println(Thread.currentThread().getName() + ": queue size: " + queue.size() + " and consumed message: " + m);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    static class Consumer_2 implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Integer m = queue_2.take();
                    System.out.println("Sleep for " + m);
                    Thread.sleep(m);
                    //System.out.println(Thread.currentThread().getName() + ": queue size: " + queue.size() + " and consumed message: " + m);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread producerThread = new Thread(new Producer());
        Thread consumerThread_1 = new Thread(new Consumer_1());
        Thread consumerThread_2 = new Thread(new Consumer_2());

        producerThread.start();
        consumerThread_1.start();
        consumerThread_2.start();

        producerThread.join();
        consumerThread_1.join();
        consumerThread_2.start();

    }

}
