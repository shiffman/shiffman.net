/* Daniel Shiffman               
 * Programming from A to Z       
 * Spring 2008                   
 * http://www.shiffman.net       
 * daniel.shiffman@nyu.edu       
 * 
 * Markov Demonstration
 * Uses RiTa to generate sentences from
 * two source texts 
 * */

package markov;

import java.io.IOException;
import a2z.A2ZFileReader;
import rita.*;

public class Markov {
	public static void main(String[] args) throws IOException {

		// Create a new markov model with n=3
		RiMarkov markov = new RiMarkov(null, 3);  

		// Load in Hamlet
		A2ZFileReader fr = new A2ZFileReader("data/hamlet.txt");
		markov.loadString(fr.getContent(),1);
		
		// Load in The Seagull (with a weight of 3!)
		fr = new A2ZFileReader("data/seagull.txt");
		markov.loadString(fr.getContent(),3);
		
		// Set maximum sentence length
		markov.setMaxSentenceLength(16);
		
		// Display some info about the markov model
		System.out.println("\nAbout the markov model: ");
		System.out.println(markov.getInfo());
		System.out.println();
		
		// Generate 8 lines
		String[] lines = markov.generate(8);
		System.out.println("Here are 8 generated lines: + \n");
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}
		
		

	}
}