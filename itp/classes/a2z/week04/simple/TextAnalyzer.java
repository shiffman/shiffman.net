/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Main driver program

package analysis;

import java.io.IOException;

import a2z.A2ZFileReader;
import a2z.A2ZFileWriter;


public class TextAnalyzer {
	public static void main (String args[]) {
		WordMap concordance = new WordMap();
		
		try {
			
			// Read in an input file
			A2ZFileReader fr = new A2ZFileReader("test.txt");
			String content = fr.getContent();
			concordance.parseString(content);
			
			// Grab report of content from concordance object
			String report = concordance.getReport();
			A2ZFileWriter fw = new A2ZFileWriter("report.txt");
			fw.writeContent(report);
			System.out.println(report);
			
			// Grab report of analysis from concordance object
			String analysis = concordance.analyze();
			A2ZFileWriter fw2 = new A2ZFileWriter("analysis.txt");
			fw2.writeContent(analysis);
			System.out.println(analysis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
