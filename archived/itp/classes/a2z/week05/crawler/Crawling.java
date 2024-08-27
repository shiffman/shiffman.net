/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Web Crawler driver program

package a2z;

import java.io.IOException;

public class Crawling {
  
  public static void main (String args[]) throws IOException {
    
    // A URL to start wtih
    String url = "http://www.slashdot.com";
    
    // Create a crawler object
    Crawler crawler = new Crawler();
    
    // Put the URL into the crawler object
    crawler.addUrl(url);
    
    // Since this crawler isn't particularly polite: http://www.robotstxt.org/wc/guidelines.html
    // I'm limited it to viewing 100 url requests
    int count = 0; int limit = 100;
    
    // Start crawling! (this should likely be its own thread.)
    while (!crawler.queueEmpty()) {
      crawler.crawl();
      count++;
      if (count > limit) break;
    }
    
    // If we end up here, there must be nothing left to crawl.
    if (count < limit) System.out.println("I ran out of websites to crawl.");
    else System.out.println("I'm tired and don't feel like crawling anymore.");
  }
}
