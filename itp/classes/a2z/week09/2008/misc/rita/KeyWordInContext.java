/* Daniel Shiffman               
 * Programming from A to Z       
 * Spring 2008                   
 * http://www.shiffman.net       
 * daniel.shiffman@nyu.edu       
 * 
 * Demos RiTa's KeyWordInContext
 * http://www.rednoise.org/rita/documentation/
 * */

package misc.rita;

import java.io.IOException;

import a2z.A2ZFileReader;
import rita.RiKWICker;

public class KeyWordInContext {
	public static void main(String[] args) throws IOException {

		RiKWICker kwic = new RiKWICker(null);

		A2ZFileReader fr = new A2ZFileReader("obama.txt");
		String[] sentences = fr.getContent().split("[.?!]");
		kwic.addLines(sentences);

		String[] result = kwic.lookup("hope");
		for (int i = 0; i < result.length; i++) {	
			System.out.println(result[i]);
		}

	}
}
