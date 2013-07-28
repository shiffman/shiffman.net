/* Asterisk and Java
 * Daniel Shiffman
 * Big Screens
 * ITP, Fall 2007
 * Thanks to Shawn Van Every
 */

// A Simple Threaded Client to communicate with the JEAGIServer

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client extends Thread {

	Socket connection = null;   // Make the connection
	PrintWriter out = null;	    // To talk out
	BufferedReader in = null;   // To read in

	String host;				// Host IP
	int port;					// Port

	boolean running = false;

	// Make the client
	public Client(String host_, int port_) {
		host = host_;
		port = port_;
	}

	// Should only be called via start()
	public void run() {
		try {
			while (running) {
				// Actually, we don't want to read in, but we could
				// System.out.println(in.readLine());
				// Should we sleep?
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			out.close();
			in.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			// Set up the socket
			connection = new Socket(host,port);
			out = new PrintWriter(connection.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		running = true;
		super.start();
	}

	// Write out a message
	public void send(String msg) {
		out.println(msg);		
	}

	// Quit the thread
	public void quit() {
		System.out.println("Quitting.");
		running = false; // Setting running to false ends the loop in run()
		interrupt(); // In case the thread is waiting. . .
	}
}