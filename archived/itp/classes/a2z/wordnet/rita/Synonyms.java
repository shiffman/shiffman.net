/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2008                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* Picks a random noun and lists synonyms
 * Example directly adapted from Daniel Howe's
 * http://www.rednoise.org/rita/examples/RWN_Syns_Ex1/RWN_Syns_Ex1.pde
 */

package howe;

import java.io.IOException;
import java.util.Arrays;

import rita.wordnet.*;

public class Synonyms {

	public static void main(String[] args) throws IOException {

		// Would pass in a PApplet normally, but we don't need to here
		RiWordnet wordnet = new RiWordnet(null);

		// Get a random noun
		String word = wordnet.getRandomWord("n");
		// Get max 15 synonyms
		String[] synonyms = wordnet.getAllSynonyms(word, "n", 15);

		System.out.println("Random noun: " + word);
		if (synonyms != null) {
			// Sort alphabetically
			Arrays.sort(synonyms);
			for (int i = 0; i < synonyms.length; i++) {
				System.out.println("Synonym " + i + ": " + synonyms[i]);
			}
		} else {
			System.out.println("No synyonyms!");
		}
	}

}


