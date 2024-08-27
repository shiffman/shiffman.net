/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* Class to read an input URL    */
/* and return a String           */

package a2z;

import java.net.*;
import java.io.*;

public class A2ZUrlReader
{
	// Stings to hold url as well as url's source content
	private String urlPath;
	private String content;
	
	public A2ZUrlReader(String name) throws IOException {
		urlPath = name;
		readContent();
	}
	
	
	public void readContent() throws IOException {
		// Create an empty StringBuffer
		StringBuffer stuff = new StringBuffer();
		try {
			// Call the openStream method (from below)
			InputStream stream = createInputStream(urlPath);
			//Create a BufferedReader from the InputStream
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			
			// Read the stream line by line and append to StringBuffer
			String line;
			while (( line = reader.readLine()) != null) {
				stuff.append(line + "\n");
			}
			// Close the reader 
			reader.close();
		} catch (IOException e) {
			System.out.println("Ooops!  Something went wrong! " + e);
		}
		// Convert the StringBuffer to a String
		content = stuff.toString();
	}
		
	// A method to create an InputStream from a URL
	public static InputStream createInputStream(String urlpath) {
		InputStream stream = null;
		try {
			URL url = new URL(urlpath);
			stream = url.openStream();
			return stream;
		} catch (MalformedURLException e) {
			System.out.println("Something's wrong with the URL:  "+ urlpath + " " + e);
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("there's a problem downloading from:  "+ urlpath + " " + e);
			// e.printStackTrace();
		}
		return stream;
	}
	
	public String getContent() {
		return content;
	}
}



