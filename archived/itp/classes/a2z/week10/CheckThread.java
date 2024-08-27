/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// A Simple Thread example
// Reads information from an XML feed every so often
// occuring to the "wait" variable


import java.util.ArrayList;

import a2z.*;

import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class CheckThread extends Thread {
    
    private boolean running;   // Is the thread running?  Yes or no?
    private int wait;                  // How many milliseconds should we wait in between executions?
    
    // Constructor, create the thread
    // It is not running by default
    public CheckThread (int w){
        wait = w;
        running = false;
    }
    
    // Overriding "start()"
    public void start ()
    {
        // Set running equal to true
        running = true;
        // Print messages
        System.out.println("Starting thread (will execute every " + (wait/1000) + " seconds.)"); 
        // Do whatever start does in Thread, don't forget this!
        super.start();
    }
    
    
    // We must implement run, this gets triggered by start()
    public void run ()
    {
        while (running){
            System.out.println("Checking."); 
            check();
            // Ok, let's wait for however long we should wait
            try {
                sleep((long)(wait));
            } 
            catch (Exception e) {
            }
        }
    }
    
    
    // Our method that quits the thread
    public void quit()
    {
        System.out.println("Quitting."); 
        running = false;  // Setting running to false ends the loop in run()
        // We used to need to call super.stop()
        // We don't any more since it is deprecated, see: http://java.sun.com/j2se/1.5.0/docs/guide/misc/threadPrimitiveDeprecation.html
        // super.stop();
        // Instead, we use interrupt, in case the thread is waiting. . .
        super.interrupt();
    }
    
    private void check() {
        // Create a URL object and open an InputStream
        A2ZXmlReader xmlreader = null;
        try {
            xmlreader = new A2ZXmlReader("http://itp.nyu.edu/icm/proxy/proxy.php?url=http://news.google.com/?output=rss");
            // Call our recursive search function to locate the element we want
            
            ArrayList headlines = new ArrayList();
            xmlreader.fillArrayList(xmlreader.getRoot(),"title",headlines);
            String report = "";
            for (int i = 0; i < headlines.size(); i++) {
                Element e = (Element) headlines.get(i);
                // As long as we find the element
                if (e != null) {
                    Node n = e.getFirstChild();
                    String headline = n.getNodeValue();
                    if (!headline.matches("Google News")) report += headline + "\n";
                }
            }
            report = report.replaceAll("&#39;","'");
            report = report.replaceAll("&amp;","&");
            System.out.println(report);
            A2ZFileWriter fw = new A2ZFileWriter("headlines.txt");
            fw.writeContent(report);
        } catch (Exception e) {
            System.out.println("Something went wrong. " + e);
        }
    }
}
