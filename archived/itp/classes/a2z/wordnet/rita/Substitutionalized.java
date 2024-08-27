/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2008                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* Replaces words in text with similar words, same part of speech
 * Example directly adapted from Daniel Howe's
 * http://www.rednoise.org/rita/examples/Substitutionalized/Substitutionalized.pde
 */

package howe;

import rita.wordnet.*;

public class Substitutionalized {

	public static void main(String[] args) {
		
		String test = "Processing is an open source programming language and environment for people who " +
		"\nwant to program images, animation, and interactions. It is used by students, artists, " +
		"\ndesigners, researchers, and hobbyists for learning, prototyping, and production. It is " +
		"\ncreated to teach fundamentals of computer programming within a visual context and to serve " +
		"\nas a software sketchbook and professional production tool. Processing is developed by " +
		"\nartists and designers as an alternative to proprietary software tools in the same domain.";


		RiWordnet wordnet = new RiWordnet(null);

		System.out.println(test);
		String[] words = test.split("[ .,]");

		//int count =  (int) Math.random()*words.length;
		for (int i = 0; i < words.length; i++) {
			String pos = wordnet.getBestPos(words[i].toLowerCase());        
			if (pos != null) {
				String[] syns = wordnet.getSynset(words[i], pos);
				if (syns != null) {
					String newStr = syns[(int)Math.random()*syns.length];
					if (Character.isUpperCase(words[i].charAt(0))) {
						newStr = firstUpperCase(newStr); // maintain capitalilzation
					}
					test = test.replaceAll("\\b"+words[i]+"\\b", newStr);
				}
			}
		}       

		System.out.println("\n=================================\n");
		System.out.println(test);
	}


	static String firstUpperCase(String newStr) {
		char fc = newStr.charAt(0);
		String tmp = newStr.substring(1);
		return Character.toUpperCase(fc)+tmp;
	}

}
