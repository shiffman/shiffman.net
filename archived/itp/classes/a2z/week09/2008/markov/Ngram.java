/* Daniel Shiffman               
 * Programming from A to Z       
 * Spring 2008                   
 * http://www.shiffman.net       
 * daniel.shiffman@nyu.edu       
 * 
 * N-gram Demonstration
 * Gets all the n-grams for a source sentence 
 * */

package markov;

import java.util.ArrayList;

public class Ngram {
	
	public static void main (String[] args) {
		
		// A sentence
		String s = "All your n-grams belong to us.";	
		
		// Function to return list of N-grams
		ArrayList grams = nGrams(s,3);
		for (int i = 0; i < grams.size(); i++) {
			System.out.println((String) grams.get(i));
		}
	}
	
	
	public static ArrayList nGrams (String sentence,int n) {
		// Split sentence up into words
		String[] words = sentence.split("\\W+");
        // Total number of n-grams we will have
		int total  = words.length - n;
		ArrayList grams = new ArrayList();
        // Loop through and create all sequences
		for(int i=0;i<=total;i++) {
        	String seq = "";
        	for (int j = i; j < i+n; j++) {
        	  seq += words[j] + " ";
        	}
        	// Add to ArrayList
            grams.add(seq);
        }
        return grams;
    }

}
