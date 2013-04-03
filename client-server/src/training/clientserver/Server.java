package training.clientserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import training.concurrency.Actor;

public class Server extends Actor {

	private static enum Message {
		ACCEPT
	}

	private final ServerSocket server;
	private final List<Actor> actors = new ArrayList<Actor>();
	private int robin = 0;

	public Server() throws IOException, InterruptedException {
		server = new ServerSocket(31337);
		server.setSoTimeout(1000);

		// create thread pool and actor list
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			actors.add(new Fibonacci());
		}

		this.tell(Message.ACCEPT);
		actors.add(this);

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

	public static void main(String[] args) throws IOException, InterruptedException {
		new Server();
	}

	@Override
	protected void receive(Object message) {
		if (message == Message.ACCEPT) {
			try {
				Socket client = server.accept();
				actors.get(robin).tell(client);
				robin = (robin + 1) % 10;
			} catch (Exception e) {
				// TODO: log
			} finally {
				tell(Message.ACCEPT);
			}
		}
	}

}
