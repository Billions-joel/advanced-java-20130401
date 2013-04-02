package training.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor implements Runnable {

	private final BlockingQueue<Object> messages = new LinkedBlockingQueue<Object>();

	protected abstract void receive(Object message);

	public final void tell(Object message) {
		try {
			messages.put(message);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public final void run() {
		try {
			while (true) {
				receive(messages.take());
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
