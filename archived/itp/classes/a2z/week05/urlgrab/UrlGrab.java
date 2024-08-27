/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Simple example demonstrating
// Grabbing content of a URL

package a2z;

import java.io.IOException;

public class UrlGrab {
  
  public static void main (String args[]) throws IOException {
    String url = "http://www.yahoo.com";
    A2ZUrlReader urlr = new A2ZUrlReader(url);
    String stuff = urlr.getContent();
    //System.out.println(stuff);
    
    String filename = "stuff.html";
    A2ZFileWriter output = new A2ZFileWriter(filename);
    output.writeContent(stuff);
    System.out.println("Wrote the contents of '" + url + "' to '" + filename + "'"); 
  }
}
