/* Daniel Shiffman               
 * Programming from A to Z       
 * Spring 2008                   
 * http://www.shiffman.net       
 * daniel.shiffman@nyu.edu       
 * 
 * Context Free Grammar
 * This example writes a "haiku" grammar file using a source text
 * Using RiTa it counts syllables to place words properly in the grammar file 
 * */

package grammar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import rita.RiAnalyze;

import a2z.A2ZFileReader;
import a2z.A2ZFileWriter;
import a2z.Concordance;
import a2z.Word;

public class GrammarMaker {

	public static void main(String[] args) throws IOException {

		// Start of file (this could be more efficient)
		A2ZFileWriter fw = new A2ZFileWriter("data/generated_grammar.g");
		fw.appendLine("#  This grammar file is based on Daniel Howe's Haiku grammar");
		fw.appendLine("#  Which is based on a grammar by G.B. Kaminaga");
		fw.appendLine("# line-breaks are noted by '%' sign\n\n");

		fw.appendLine("{");
		fw.appendLine("<start>");
		fw.appendLine("<5-line> % <7-line> % <5-line>");
		fw.appendLine("}\n");

		fw.appendLine("{");
		fw.appendLine("<5-line>");
		fw.appendLine(" <1> <4>  |  <1> <3> <1>  |  <1> <1> <3>  |  <1> <2> <2>  |  <1> <2> <1> <1>  |  <1> <1> <2> <1>  |  <1> <1> <1> <2>  |  <1> <1> <1> <1> <1>  |  <2> <3>  |  <2> <2> <1>  |  <2> <1> <2>  |  <2> <1> <1> <1>  |  <3> <2>  |  <3> <1> <1>  |  <4> <1>  |  <5>");
		fw.appendLine("}\n");

		fw.appendLine("{");
		fw.appendLine("<7-line>");
		fw.appendLine("<1> <1> <5-line>  |  <2> <5-line>  |  <5-line> <1> <1>  |  <5-line> <2> ");
		fw.appendLine("}\n");
		
		// Make a concordance of all words in a source text
		Concordance concordance = new Concordance();
		A2ZFileReader fr = new A2ZFileReader("data/obama_short.txt");
		concordance.addContent(fr.getContent());
		
		// Create 5 ArrayLists to store words of different syllable counts
		ArrayList[] wordsBySyllable = new ArrayList[5];
		for (int i = 0; i < wordsBySyllable.length; i++) {
			wordsBySyllable[i] = new ArrayList();
		}
		
		// Go through all the words
		Iterator iter = concordance.getWords();
		while (iter.hasNext()) {
			Word w = (Word) iter.next();
			String s = w.getWord();
			// Use RiTa's Analyzer to determine syllable count
			RiAnalyze pa = new RiAnalyze(null, s);
			String syllables = pa.getSyllables();
			// Syllables are separated with colons
			int count = syllables.split(":").length;
			if (count < 6) {
				// Add the word to the appropriate ArrayList
				// Assuming it has between 1 and 5 syllables
				System.out.println(s + " " + count);
				wordsBySyllable[count-1].add(s);
			}
		}
		
		
		System.out.println("Finishing file");
		
		// Finish up the file by writing production rules
		// for 1-5 syllable words
		for (int i = 0; i < 5; i++) {
			fw.appendLine("\n{");
			fw.appendLine("<"+ (i+1) + ">");
			for (int j = 0; j < wordsBySyllable[i].size(); j++) {
				String s = (String) wordsBySyllable[i].get(j);
				fw.append(s + " | ");
			}
			fw.appendLine("\n}");
		}

	}
}
