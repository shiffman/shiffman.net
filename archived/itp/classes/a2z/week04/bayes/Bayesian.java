/* Daniel Shiffman               */
/* Bayesian Spam Filter Example  */
/* Programming from A to Z       */
/* Spring 2007                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Main driver program

package bayes;

import java.io.*;
import a2z.*;

public class Bayesian {
  public static void main (String args[]) {
    try {

      // Create a new SpamFilter Object
      SpamFilter filter = new SpamFilter();

      // Train spam with a file of spam e-mails
      filter.trainSpam("spam.txt");
      // Train spam with a file of regular e-mails
      filter.trainGood("good.txt");
      // We are finished adding words so finalize the results
      filter.finalizeTraining();


      for (int i = 1; i < 4; i++) {
        // Read in a text file
        A2ZFileReader fr = new A2ZFileReader("messages/mail" + i + ".txt");
        String stuff = fr.getContent();

        // Ask the filter to analyze it
        boolean spam = filter.analyze(stuff);

        // Print results
        if (spam) System.out.println("I do believe this message is spam!");
        else System.out.println("I do believe this is a genuine message!");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
