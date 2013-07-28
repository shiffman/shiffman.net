/* Asterisk and Java
 * Daniel Shiffman
 * Big Screens
 * ITP, Fall 2007
 * example modified from Shawn Van Every's Redial class
 * http://itp.nyu.edu/~sve204/redial/
 */

import java.io.*;

public class JEAGIClient
{
	// Process ID so that we can open up the audio stream.
	// Unfortunately, Java doesn't let us know this so we  have to wrap this app in a shell script that will pass it in
	// We aren't using the pid in this example, but we will need it when we do sound processing on the call
	int pid = 0;

	// EAGI Streams
	BufferedReader bin; // Input stream from asterisk
	OutputStream out;   // Communication with asterisk
	OutputStream err;   // Communication with asterisk

	String id = "";     // The phone number for this call

	Client client;      // A Client to connect to the JEAGIServer

	public static void main(String[] args)
	{
		if (args.length > 2) {
			JEAGIClient jeagi = new JEAGIClient(Integer.parseInt(args[0]),args[1],Integer.parseInt(args[2]));
			jeagi.waitForDigits();
		} else if (args.length > 0) {
			JEAGIClient jeagi = new JEAGIClient(Integer.parseInt(args[0]),"localhost",9001);
			jeagi.waitForDigits();
		} else {
			System.out.println("Need to pass in the EAGI process id");
		}
	}


	public JEAGIClient(int pid_, String serverIPAddress, int serverPort) {

		// EAGI Streams
		out = System.out;
		err = System.err;
		bin = new BufferedReader(new InputStreamReader(System.in));
		pid = pid_;
		// Create the client and launch its thread
		client = new Client(serverIPAddress, serverPort);
		client.start();
	}

	public void waitForDigits() {
		try {
			// For Debugging, we could write out to a file
			// File outtFile = new File("/home/dts204/asterisk_log/theoutput.txt");
			// OutputStream tfsos = new FileOutputStream(outtFile);  

			// Get started waiting for digits, we will wait pretty much forever
			String wait = "WAIT FOR DIGIT -1\n";
			out.write(wait.getBytes());
			boolean loop = true;
			String line = null;
			while ((line = bin.readLine()) != null && loop) {
				//tfsos.write(("RAW: " + line+"\n").getBytes());

				String agi_callerid = "agi_callerid:";
				int index = line.indexOf(agi_callerid);
				// See if we can get the phone number (for a new call)
				if (index > -1) {
					id = line.substring(index + agi_callerid.length()+1,line.length());
					client.send(id + ",newcall");
					// tfsos.write(("Number: " + number+"\n").getBytes());
				}
				// The message we want starts with 200, but does not = 1
				else if (line.indexOf("200") == 0 && !line.equals("200 result=1")) {
					// Parse out what digit was pressed
					String result = "200 result=";
					int i = line.indexOf(result);
					if (i > -1) {
						String key = line.substring(i+result.length(),line.length());
						// -1 means hangup
						if (key.equals("-1")) {
							client.send(id+",hangup");
							client.quit();
							loop = false;
						} else {
							// If this doesn't work we'll send "error"
							String c = "error";
							try {
								// Convert to integer
								c = "" + (char) Integer.parseInt(key);
							} catch (Exception e) {
								// oops
							}
							client.send(id+","+c);
							// tfsos.write(("Message: "+id+","+c+"\n").getBytes());
						}
					}
					out.write(wait.getBytes());
				}
			}
		} catch (Exception e) {
			// There was some sort of error
			System.out.println("SAY ALPHA ERROR \"*#\"");
			System.out.println(e);
			e.printStackTrace();
		}
		client.quit();
	}
}
