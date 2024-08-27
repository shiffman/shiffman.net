/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2008                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* Uses the console reader to receive a lookup word
 * and display information related to its "Senses"
 */

package examples;

import rita.wordnet.RiWordnet;
import a2z.ConsoleReader;

public class SensesLookup {

	public static void main(String[] args) {

		ConsoleReader reader = new ConsoleReader(System.in);

		// Would pass in a PApplet normally, but we don't need to here
		RiWordnet wordnet = new RiWordnet(null);

		while (true) {
			
			// Grab a word from the user
			System.out.println("\n\nPlease enter a word (q to quit): ");
			System.out.print("% ");
			String input = reader.readLine();
			
			// Check to see if we should quit
			if (input.equals("q")) {
				break;
			}
			// First we should make sure it's a valid wordnet word
			if (wordnet.exists(input)) {
				
				// Get parts of speech as an array
				String[] pos = wordnet.getPos(input);
				System.out.println("\nPossible parts of speech: ");
				for (int i = 0; i < pos.length; i++) {
					System.out.println(i + ": " + pos[i]);
				}
				// Ask user to select a part of speech
				System.out.println("\nPlease select a part of speech (enter index #) ");
				System.out.print("% ");
				int selection = reader.readInt();
				
				// Get all of the IDs associated with possible "senses" (synsets) of that word
				// we will use these IDs as a means for getting data about that sense
				int[] ids = wordnet.getSenseIds(input, pos[selection]);

				// Display information about the synsets that word belongs to
				System.out.println("\nHere are all the possible senses of " + input + " with pos: " + pos[selection]);
				System.out.println("==========================================");
				for (int i = 0; i < ids.length; i++) {
					// Sense ID #
					System.out.println("Sense: " + ids[i]);
					String description = wordnet.getDescription(ids[i]);
					// Sense Description (definition)
					System.out.println("Description: " + description);
					// All words that belong to this synset
					String[] words = wordnet.getSynset(ids[i]);
					if (words != null) {
					  System.out.print("Synset: ");
					  for (int j = 0; j < words.length; j++) System.out.print(words[j] + " ");
					}
					System.out.println("\n-------------------------");
				}
			} else {
				System.out.println("That word is not in wordnet.");
			}
		}

		System.out.println("Quitting. . .");
	}

}
