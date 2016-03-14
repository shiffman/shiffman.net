/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2008                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* Demos the various hyponym methods and how they differ (same for hypernyms)
 * Example directly adapted from Daniel Howe's
 * http://www.rednoise.org/rita/examples/VariousHyponyms/VariousHyponyms.pde
 */

package howe;

import rita.wordnet.*;

public class Hyponyms {

	public static void main (String[] args) {
		
		// Start with the word cat as a noun
		String word = "cat";
		String pos = "n";
		
		// An array for any results we get
		String[] result;  

		RiWordnet wordnet = new RiWordnet(null);

		// First we can look at all of the senses
		int[] ids = wordnet.getSenseIds(word, pos);
		System.out.println("\n\n====================== getSenseIds('cat', 'n'):getDescription(id) ============================");

		// And their descriptions
		for (int j = 0; j < ids.length; j++) {
			System.out.print("#"+ids[j]+":   ");
			System.out.println(wordnet.getDescription(ids[j]));
		}

		// This gets Hyponyms for first sense
		result = wordnet.getHyponyms(word, pos);
		System.out.println("\n\n======== getHyponyms('cat', 'n') =======");
		for (int i = 0; i < result.length; i++) {
			System.out.println("(first-sense)      "+result[i]);
		}

		// Hyponyms for all senses
		result = wordnet.getAllHyponyms(word, pos);
		System.out.println("\n\n====== getAllHyponyms('cat', 'n') ======");
		System.out.println("(all-senses)      ");
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}

		// Hyponyms per sense
		System.out.println("\n\n======== getHyponyms(id) =======");
		for (int j = 0; j < ids.length; j++) {
			result = wordnet.getHyponyms(ids[j]); 
			System.out.println("#"+ids[j]+": ");
			if (result == null) continue; 
			for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			}
		}

		// Hyponyms tree per sense
		System.out.println("\n\n======== getHyponymTree(id) ======");
		for (int j = 0; j < ids.length; j++) {
			result = wordnet.getHyponymTree(ids[j]);
			if (result == null) continue;
			System.out.println("#"+ids[j]+":");
			for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);    
			}
		}

	}
}
