/* Daniel Shiffman               
 * Programming from A to Z       
 * Spring 2008                   
 * http://www.shiffman.net       
 * daniel.shiffman@nyu.edu       
 * 
 * Demos RiTa's Analyzer
 * http://www.rednoise.org/rita/documentation/
 * */

package misc.rita;

import java.io.IOException;
import java.util.Set;

import rita.RiAnalyze;

public class Analyze {
	public static void main(String[] args) throws IOException {

		String text = "The cat in the hat";
		// Create an Analyze object
	    RiAnalyze pa = new RiAnalyze(null, text);
	    
	    // Phonemes
	    System.out.println("I am now going to analyze: " + text);
	    String phonemes = pa.getPhonemes();   
	    System.out.println("Phonemes: " + phonemes);

	    // Stresses
	    String stresses = pa.getStresses();
	    System.out.println("Stresses: " + stresses);

	    // Syllables
	    String syllables = pa.getSyllables();
	    System.out.println("Syllables: " + syllables);

	    pa.dumpFeatures();
	
	}
}
