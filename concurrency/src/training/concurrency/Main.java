package training.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		Logger logger = new Logger();
		pool.execute(logger);

		for (int i = 0; i < 10; i++) {
			Fibonacci fib = new Fibonacci(logger);
			pool.execute(fib);
			fib.tell(Fibonacci.Message.FIB);
		}

//		pool.shutdown();
//		pool.awaitTermination(10, TimeUnit.SECONDS);
//
//		System.out.println("done");
	}

}
