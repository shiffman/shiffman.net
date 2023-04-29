/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// A Simple Thread example

public class SimpleThread extends Thread {
    
    private boolean running;           // Is the thread running?  Yes or no?
    private int wait;                  // How many milliseconds should we wait in between executions?
    private String id;                 // Thread name
    private int count;                 // counter
    
    // Constructor, create the thread
    // It is not running by default
    public SimpleThread (int w, String s){
        wait = w;
        running = false;
        id = s;
        count = 0;
    }
    
    // Overriding "start()"
    public void start ()
    {
        // Set running equal to true
        running = true;
        // Print messages
        System.out.println("Starting thread (will execute every " + wait + " milliseconds.)"); 
        // Do whatever start does in Thread, don't forget this!
        super.start();
    }
    
    
    // We must implement run, this gets triggered by start()
    public void run ()
    {
        while (running && count < 10){
            System.out.println(id + ": " + count);
            count++;
            // Ok, let's wait for however long we should wait
            try {
                sleep((long)(wait));
            } 
            catch (Exception e) {
            }
        }
        System.out.println(id + " thread is done!");  // The thread is done when we get to the end of run()
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
        interrupt();
    }
}
