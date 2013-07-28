/* Asterisk and Java
 * Daniel Shiffman
 * Big Screens
 * ITP, Fall 2007
 * Server Thread for a connection
 * Modified from Dan O'Sullivan Server example
 */

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	Socket connection;
	BufferedReader brin;
	PrintWriter pout;
	String uniqueName;  // We're not using this, but we could
	
	boolean stillRunning = true;

	ServerThread(Socket _connection) {
		connection = _connection;
		uniqueName = "Conn" + _connection.getRemoteSocketAddress();
		
		// Trade the standard byte input stream for a fancier one that allows for more than just bytes
		try {
			brin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			pout = new PrintWriter(connection.getOutputStream(),true);
		} catch (IOException e) {
			System.out.println("couldn't get streams" + e);
		}
	}

	// All the thread does is read in and broadcast out
	public void run() {
		while (stillRunning) {
			String text = null;
			try {
				text = brin.readLine(); 
				System.out.println("reading: " + text);
			} catch (IOException e) {
				killMe();
				System.out.println("couldn't listen" + e);
				break;
			}
			// If we disconnected
			if (text == null){
				killMe();
				break;
			}
			// Go through all the other connections and relay this input to them
			JEAGIServer.tellEveryone(text);
		}
	}

	// Kill the thread and take it out of the Server's list
	public void killMe(){
		System.out.println("Removing Connection" );
		stillRunning = false;
		JEAGIServer.removeMe(this);
	}

	// If we wanted to send to only this client
	// We're not using this
	synchronized public void sendToThisClient(String _what) {
		pout.println(_what);
		pout.flush();
	}
}


