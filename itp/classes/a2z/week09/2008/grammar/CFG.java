/* Daniel Shiffman               
 * Programming from A to Z       
 * Spring 2008                   
 * http://www.shiffman.net       
 * daniel.shiffman@nyu.edu       
 * 
 * Context Free Grammar
 * Uses RiTa to generate sentences
 * from a grammar file
 * */

package grammar;

import rita.RiGrammar;

public class CFG {
	
	public static void main(String[] args) {

		// Make a grammar object
		//RiGrammar grammar = new RiGrammar(null, "simple.g");
		//RiGrammar grammar = new RiGrammar(null, "haiku.g");
		RiGrammar grammar = new RiGrammar(null, "generated_grammar.g");
		
		// Expand text from the grammar rules
		String text = grammar.expand();
		
		// In our files we'll consider % as carriage return
		text = text.replaceAll("%", "\n");
		
		// Display result
		System.out.println("\n\n " + text);
		
	}
}
