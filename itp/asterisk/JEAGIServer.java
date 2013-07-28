/* Asterisk and Java
 * Daniel Shiffman
 * Big Screens
 * ITP, Fall 2007
 * Server to relay calls from JEAGIStreamer to Processing
 * Modified from Dan O'Sullivan Server example
 */

package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class JEAGIServer {

	static ArrayList<ServerThread> allConnections = new ArrayList<ServerThread>();

	public static void main(String[] args) {
		
		ServerSocket server;
		int portNum = 9001;
		try {
			server = new ServerSocket(portNum);
			System.out.println("Server running at " + InetAddress.getLocalHost()+ "  " + server.getLocalPort());
			while (true) {
				System.out.println("Waiting for a new connection: ");
				// Blocking, waiting for a new connection
				Socket connection = server.accept();
				System.out.println(connection.getRemoteSocketAddress() + " connected.");
				// Farm out the connection immediately to another thread
				ServerThread newConnection = new ServerThread(connection);
				newConnection.start(); // Start the thread
				allConnections.add(newConnection); // Add it to the list of connections
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// A method to broadcast a message to all of the clients
	static public void tellEveryone(String _text){
		System.out.println("broadcasting: " + _text);
		for (int i = 0; i < allConnections.size(); i++){
			ServerThread thisConnection = (ServerThread) allConnections.get(i);
			thisConnection.sendToThisClient(_text);
		}
	}

	// Delete a client
	static public void removeMe(ServerThread _which){
		allConnections.remove(_which);
	}

}



