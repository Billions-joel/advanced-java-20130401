package training.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class Logger implements Runnable {

	private final Queue<String> messages = new LinkedList<String>();

	public void log(String message) {
		// TODO
	}

	@Override
	public void run() {
		// run forever:
		//   process log messages when they are available
	}

}
