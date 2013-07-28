/* Daniel Shiffman               
 * Programming from A to Z       
 * Spring 2008                   
 * http://www.shiffman.net       
 * daniel.shiffman@nyu.edu       
 * 
 * Demos RiTa's Lexicon
 * http://www.rednoise.org/rita/documentation/
 * */

package misc.rita;

import java.io.IOException;

import rita.RiLexicon;

public class Rhymer {
	public static void main(String[] args) throws IOException {

		// Create a lexicon
		RiLexicon lexicon = new RiLexicon(null); 
		// Get an array of words that rhyme with a word
		String[] result = lexicon.getSimpleRhymes("cat");	  	
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	
	}
}
