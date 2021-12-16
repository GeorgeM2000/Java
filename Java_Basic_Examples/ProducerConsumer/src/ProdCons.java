import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProdCons {
	//implement a simple producer -> consumer

    // producer creates a random number 'x' in the range of [10,45]
    // Consumer takes the number 'x' and computes & prints the fibonacci(x)
    // experiment with the range values and the number of parallel consumers to improve throughput
	
	
	static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
	
	
	static int fibonacci(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        return fibonacci(x -1) + fibonacci(x -2 );
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Random random = new Random();
                    int m = random.nextInt(45 - 10) + 10;
                    queue.put(m);
                    System.out.println("Random number selected: " + m);
                    //System.out.println(Thread.currentThread().getName() + ": queue size: " + queue.size() + " and produced message: " + m);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Integer m = queue.take();
                    System.out.println("For number " + m + " fibonacci(" + m + ") is: " + fibonacci(m));
                    //System.out.println(Thread.currentThread().getName() + ": queue size: " + queue.size() + " and consumed message: " + m);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

    }

}
