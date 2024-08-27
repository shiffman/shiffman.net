/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Simple Thread Driver

public class ThreadDriver {
    public static void main (String argv []) {
        SimpleThread thread1 = new SimpleThread(1000,"cat");
        SimpleThread thread2 = new SimpleThread(1500,"dog");
        
        thread1.start();
        thread2.start();
        
  }
}