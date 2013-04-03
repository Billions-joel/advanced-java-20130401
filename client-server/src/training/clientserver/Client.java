package training.clientserver;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Throwable {
		Socket server = new Socket(InetAddress.getLocalHost(), 31337);
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		out.println("hello, server");
		server.close();
	}

}
