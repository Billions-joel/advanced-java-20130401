package training.concurrency;

public class Logger extends Actor {

	@Override
	protected void receive(Object message) {
		System.out.println(message);
	}

}