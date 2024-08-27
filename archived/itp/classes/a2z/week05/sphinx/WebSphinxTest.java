package sphinx;

import java.net.MalformedURLException;
import websphinx.Link;

public class WebSphinxTest {
    
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        System.out.println("Testing Websphinx. . .");
        
        // Make an instance of own our crawler
        MyCrawler crawler = new MyCrawler();
        // Create a "Link" object and set it as the crawler's root
        Link link = new Link("http://blogblender.faludi.com");
        crawler.setRoot(link);
        
        // Start running the crawler!
        System.out.println("Starting crawler. . .");
        crawler.run(); // Blocking function, could implement a thread, etc.
        
    }

}
