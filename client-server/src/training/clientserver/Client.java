package training.clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Client {

	public static void main(String[] args) {
		try {
			Socket server = new Socket(InetAddress.getLocalHost(), 31337);
			try {
				ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
				try {
					out.writeObject(new Random().nextInt(1000));
					ObjectInputStream in = new ObjectInputStream(server.getInputStream());
					try {
						System.out.println(in.readObject());
					} catch (ClassNotFoundException e) {
					} finally {
						in.close();
					}
				} finally {
					out.close();
				}
			} finally {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
