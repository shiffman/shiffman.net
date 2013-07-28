/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Simple class to describe a collection of words

package analysis;
import java.util.*;

public class WordMap {
	
	Map words;
	// regex to split the String
	String regex;
	
	public WordMap() {
		words = new TreeMap();   // let's use a tree
		// words = new HashMap();// we could use a hashmap too
		regex = "\\W";
	}
	
	
	// Break a string up into words and add it in our map
	public void parseString(String stuff) {
		String[] tokens = stuff.split(regex);
        for (int i = 0; i < tokens.length; i++) {
			String word = tokens[i].toLowerCase();
			Word w = null;
			if (words.containsKey(word)) {
				w = (Word) words.get(word);
			} else {
				w = new Word(word);
				words.put(word,w);
			}
			w.count();
		}
	}
	
	// Dummy analyze method
	// Counts total # of words (we could do something more interesting here)
	public String analyze() {
		Iterator iter = words.values().iterator();
		int total = 0;
		while (iter.hasNext()) {
			Word w = (Word) iter.next();
			total += w.getCount();
		}
		String analysis = "I have discovered there are " + total + " total words.";
		return analysis;
	}

	

	// Join together a report of all words
	// and all counts
	public String getReport() {
		String report = "";
		Iterator iter = words.values().iterator();
		while (iter.hasNext()) {
			Word w = (Word) iter.next();
			report += w.getWord() + " " + w.getCount() + "\n";
		}
		return report;
	}
}





