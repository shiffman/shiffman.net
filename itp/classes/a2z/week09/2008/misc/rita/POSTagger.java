/* Daniel Shiffman               
 * Programming from A to Z       
 * Spring 2008                   
 * http://www.shiffman.net       
 * daniel.shiffman@nyu.edu       
 * 
 * Demos RiTa's POSTagger
 * http://www.rednoise.org/rita/documentation/
 * */

package misc.rita;

import java.io.IOException;

import a2z.A2ZFileReader;

import rita.RiPosTagger;

public class POSTagger {
	public static void main(String[] args) throws IOException {

		// A part of speech tagger object
		// http://www.rednoise.org/rita/documentation/ripostagger_class_ripostagger.htm
		// http://citeseer.ist.psu.edu/56508.html
		RiPosTagger postagger = new RiPosTagger(null);

		// Source text
		A2ZFileReader fr = new A2ZFileReader("obama.txt");
		String content = fr.getContent();

		// Split into words
		String[] words = content.split("\\W+");

		// Display POS
		for (int i = 0; i < words.length; i++) {
			String pos = postagger.tag(words[i]);
			System.out.println(words[i] + " " + pos);
		}

	}
}
