package training.concurrency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// create thread pool and actor list
		ExecutorService pool = Executors.newCachedThreadPool();
		List<Actor> actors = new ArrayList<Actor>();

		// create the logger
		Logger logger = new Logger();
		actors.add(logger);

		// create the fibonacci actors, and tell them FIB
		for (int i = 0; i < 10; i++) {
			Fibonacci fib = new Fibonacci(logger);
			fib.tell(Fibonacci.Message.FIB);
			actors.add(fib);
		}

		// run all the actors
		for (Actor actor : actors) {
			pool.execute(actor);
		}

		// wait for keystroke and shut down actors
		System.in.read();
		for (Actor actor : actors) {
			actor.tell(Actor.Message.SHUTDOWN);
		}

		// shut down executor service
		pool.shutdown();
		pool.awaitTermination(5, TimeUnit.SECONDS);
		System.out.println("done");
	}

}
