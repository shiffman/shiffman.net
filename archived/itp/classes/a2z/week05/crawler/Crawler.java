/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Simple example of a web crawler
// URL queue: linked list
// Sites already visited: hash table

// Needs to be updated to comply with ROBOTS.TXT!

package a2z;

import java.util.*;
import java.util.regex.*;

public class Crawler {
  
  private LinkedList urlsToVisit; // A queue of URLs to visit
  private HashMap urlsVisited;    // A table of already visited URLs
  private Pattern href;           // A Pattern to match an href tag
  private String ignore;          // To be used as a regex for ignoring media files (JPG,MOV, etc.)
    
  public Crawler() {
    urlsToVisit = new LinkedList();
    urlsVisited = new HashMap();
    
    // Match URLs
    // Note using Pattern.COMMENTS flag which ignores white spaces and anything after '#' in a regex
    href = Pattern.compile( "href               # match href \n" + 
                            "\\s*=\\s*\"        # 0 or more spaces, =, 0 ore more spaces, quote \n" + 
                            "(http[^\"\\s]*)    # capture the URL itself, http followed by no spaces and no quotes \n" +
                            "\"                 # ending with a quote \n",
                            Pattern.CASE_INSENSITIVE | Pattern.COMMENTS);
    
    // We will ignore URLs ending with certain extensions
    ignore = ".*(mov|jpg|gif|pdf)$";
  }
  
  
  public void addUrl(String urlpath) {
    // Add it to both the LinkedList and the HashMap
    urlsToVisit.add(urlpath);
    urlsVisited.put(urlpath,urlpath);

  }
  
  
  // A method to determine if the queue is empty or not
  public boolean queueEmpty() {
    return urlsToVisit.isEmpty();
  }
  
  // A method to crawl one URL
  public void crawl() {
      // Get a URL in the queue
      String urlpath = (String) urlsToVisit.removeFirst();
      // Read that URL
      read(urlpath);
  }
  
  // A method to read a URL and look for other URLs
  // (and possibly do some sort of analysis)
  private void read(String urlpath) {
    
    System.out.println(urlsVisited.size() + " " + urlsToVisit.size() + " " + urlpath);
    try {
      // Grab the URL content
      A2ZUrlReader urlr = new A2ZUrlReader(urlpath);
      String stuff = urlr.getContent();
      
      // This lame example just searches for web sites with the word "valentine" in them
      // We could break up the documents and throw all the words into a concordance here
      // Or implement some other type of storage / analysis algorithm
      // (we also probably shouldn't compile a regular expresssion everytime here, but. . .)
      Pattern valenp = Pattern.compile("valentine", Pattern.CASE_INSENSITIVE);
      int valentiney = 0;
      Matcher valenm = valenp.matcher(stuff);
      while (valenm.find()) {
        valentiney++;
      }
      if (valentiney > 0) System.out.println(urlpath + ", would you be my valentine? My love for you is multipled by " + valentiney + "!");
      /************************************/
            
      // Match the URL pattern to the content
      Matcher m = href.matcher(stuff);
      // While there are URLs
      while (m.find()) {
        // Grab the captured part of the regex (the URLPath itself)
        String newurl = m.group(1);
        // If it hasn't already been visited (or if it matches the ignore pattern)
        if (!newurl.matches(ignore) && !urlsVisited.containsKey(newurl)) {
          addUrl(newurl);
        }
      }
    } catch (Exception e) {
      // System.out.println("Problem reading from " + urlpath + " " + e);
      // e.printStackTrace();
    }
  }

}
