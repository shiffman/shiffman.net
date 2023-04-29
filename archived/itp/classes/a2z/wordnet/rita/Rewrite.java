/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2008                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* This example reads in a text file and rewrites it by replacing words
 * from wordnet (arbitrarily choosing antonyms, hyponyms, synonyms, etc.)
 * 
 * A class Replacer with static methods is used to find the new words.
 * 
 */

package examples;

import java.io.IOException;
import a2z.A2ZFileReader;
import a2z.A2ZFileWriter;

public class Rewrite {

	public static void main (String[] args) throws IOException {
		
		A2ZFileReader fr = new A2ZFileReader("test.txt");
		String text = fr.getContent();
		
		// Crazy regex to break everything apart, but keep apostrophes!
		// Uses lookahead and lookbehind
		String[] tokens = text.split("(?<!')\\b(?!')");
		
		// This is our new ouptut text
		StringBuffer newtext = new StringBuffer();
		
		// For every token, get a replacement and append
		for (int i = 0; i < tokens.length; i++) {
			String replace = Replacer.replace(tokens[i]);
			newtext.append(replace);
		}
		
		// Write the output file
		A2ZFileWriter fw = new A2ZFileWriter("output.txt");
		String output = newtext.toString();
		fw.writeContent(output);
		
		//System.out.println(output);
	}
}
