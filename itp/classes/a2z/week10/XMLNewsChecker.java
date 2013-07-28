/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Searching for an XML Element

import java.io.IOException;

import a2z.ConsoleReader;

public class XMLNewsChecker {
    public static void main (String argv []) throws IOException {
        CheckThread thread = new CheckThread(60000);
        
        // Create the console reader
        ConsoleReader console = new ConsoleReader(System.in);
        System.out.println("Starting news checker app.");
        System.out.print("Would you like to start checking (y/n)? ");
        
        // Blocking occurs here (i.e. the program sits and waits for the user to enter something)
        String s = console.readLine().toLowerCase();
        
        // If the user replies with a yes, start the thread
        if (s.equals("y")) {
            thread.start();
        // Otherwise, just quit
        } else {
            System.out.println("Ok quitting. . .");
            System.exit(0);
        }
        
        System.out.println("Type q and hit enter to quit at any point.");
        s = "";
        
        // Ok, the thread is off and running, let's sit and wait for the user to type in 'q' to enter
        while (!s.equals("q")) {
          s = console.readLine().toLowerCase();
        }
        thread.quit();
   }
}