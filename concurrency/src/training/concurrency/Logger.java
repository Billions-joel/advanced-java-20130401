package training.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

public class Logger implements Runnable {

	private final AtomicReference<Queue<String>> messagesRef =
			new AtomicReference<Queue<String>>(new LinkedList<String>());

	public void log(String message) {
		Queue<String> oldMessages, newMessages;
		do {
			oldMessages = messagesRef.get();
			newMessages = new LinkedList<String>(oldMessages);
			newMessages.add(message);
		} while (!messagesRef.compareAndSet(oldMessages, newMessages));
	}

	@Override
	public void run() {
		while (true) {
			Queue<String> oldMessages = messagesRef.get();
			if (!oldMessages.isEmpty()) {
				Queue<String> newMessages = new LinkedList<String>(oldMessages);
				String message = newMessages.remove();
				if (messagesRef.compareAndSet(oldMessages, newMessages))
					System.out.println(message);
			}
		}
	}

}
