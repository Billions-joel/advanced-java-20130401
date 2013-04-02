package training.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 20; i++) {
			pool.submit(new Fibonacci());
		}

		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);

		System.out.println("done");
	}

}
