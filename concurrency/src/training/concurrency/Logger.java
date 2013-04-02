package training.concurrency;

import java.util.concurrent.atomic.AtomicReference;

import training.generics.ImmutableQueue;
import training.generics.ImmutableQueue.DequeueResult;

public class Logger implements Runnable {

	private final AtomicReference<ImmutableQueue<String>> messagesRef =
			new AtomicReference<ImmutableQueue<String>>(ImmutableQueue.<String>empty());

	public void log(String message) {
		ImmutableQueue<String> oldMessages, newMessages;
		do {
			oldMessages = messagesRef.get();
			newMessages = oldMessages.enqueue(message);
		} while (!messagesRef.compareAndSet(oldMessages, newMessages));
	}

	@Override
	public void run() {
		while (true) {
			ImmutableQueue<String> oldMessages = messagesRef.get();
			if (!oldMessages.isEmpty()) {
				DequeueResult<String> result = oldMessages.dequeue();
				String message = result.head;
				ImmutableQueue<String> newMessages = result.tail;
				if (messagesRef.compareAndSet(oldMessages, newMessages))
					System.out.println(message);
			}
		}
	}

}
