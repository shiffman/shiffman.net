/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2008                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* 
 * This Replacer class has some static methods that take in an input word
 * and find a replacement from wordnet.  It also retains (mostly) capitalization
 * 
 * The replacement algorithm is arbitrary and just a demonstration of what is possible.
 * 
 */

package examples;

import java.io.IOException;
import java.util.HashMap;
import a2z.A2ZFileReader;
import rita.wordnet.RiWordnet;

public class Replacer {

	// Wordnet reference
	static RiWordnet wordnet;
	// We may want to not replace some words and so store them in a HashMap
	static HashMap skip;

	public static String replace(String word) throws IOException {
		// The first time this method is called we initialize the objects
		if (wordnet == null) {
			// Would pass in a PApplet normally, but we don't need to here
			wordnet = new RiWordnet(null);
			skip = new HashMap();
			// Read in words from a text file to skip
			A2ZFileReader fr = new A2ZFileReader("skip.txt");
			String[] skipWords = fr.getContent().split("\\n");
			for (int i = 0; i < skipWords.length; i++) {
				skip.put(skipWords[i], skipWords[i]);
			}
		}

		String search = word.toLowerCase();
		// If it's not a word, not in wordnet or a word we should skip then just pass it back
		if (!search.matches("\\w+")|| !wordnet.exists(search) || skip.containsKey(search)) {
			return word;
		// Otherwise, go forward and find a replacement!
		} else {
			String replacement = pickReplacement(search);
			// Retain any case
			replacement = retainCase(replacement,word);
			return replacement;
		}
	}

	// This is completely arbitrary, just showing how to find something
	// in wordnet to replace.  
	private static String pickReplacement(String word) {
		String pos = wordnet.getBestPos(word);        
		if (pos != null) {
			// First check and see if there are any antonyms
			// If so, pick a random one and return
			String[] antonyms = wordnet.getAllAntonyms(word,pos);
			if (antonyms != null) {
				String replacement = antonyms[(int)Math.random()*antonyms.length];
				System.out.println(word + " ==> " + replacement + "  (antonym " + pos + ")");
				return replacement;
			}
			// Then check and see if there are any hyponyms
			// If so, pick a random one and return
			String[] hyponyms = wordnet.getAllHyponyms(word,pos);
			if (hyponyms != null) {
				String replacement = hyponyms[(int)Math.random()*hyponyms.length];
				System.out.println(word + " ==> " + replacement + "  (hyponym " + pos + ")");
				return replacement;
			}
			// Same thing for synonyms
			String[] synonyms = wordnet.getAllSynonyms(word,pos);
			if (synonyms != null) {
				String replacement = synonyms[(int)Math.random()*synonyms.length];
				System.out.println(word + " ==> " + replacement + "  (synonym " + pos + ")");
				return replacement;
			}
		}
		return word;
	}
	
	private static String retainCase(String replacement, String word) {
		// If last character is upper case then all upper case
		if (Character.isUpperCase(word.charAt(word.length()-1))) {
			return replacement.toUpperCase();
		} else if (Character.isUpperCase(word.charAt(0))) {
			char fc = replacement.charAt(0);
			return Character.toUpperCase(fc)+replacement.substring(1);
		}
		return replacement;
	}

}
