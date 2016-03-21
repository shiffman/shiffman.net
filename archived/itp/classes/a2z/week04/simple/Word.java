/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// This class describes a word

package analysis;

public class Word {
	private String word;    // The String itself
	private int count;      // The number of times it appears
	
	// Create a word, initialize all vars to 0
	public Word(String s) {
		word = s;
	}
	
	// Increment bad counter
	public void count() {
		count++;
	}
	
	public int getCount() {
		return count;
	}

	public String getWord() {
		return word;
	}
	
}
