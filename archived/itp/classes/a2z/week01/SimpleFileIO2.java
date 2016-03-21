// Simple File Input and Output using "old" I/O
// Daniel Shiffman
// Programming from A to Z, Spring 2006

import java.io.*;

public class SimpleFileIO2 {
  public static void main (String[] args) throws IOException {
  
    // Read the file into a String, character by character
    // (We could read it line by line with BufferedReader)
    FileReader in = new FileReader(new File(args[0]));
    String content = "";
    int c;
    while ((c = in.read()) != -1)  {
      content += (char) c;
    }
    in.close();
	
	
    // Do our fancy string editing stuff here
	
    // Write out a file with the content, character by character
    FileWriter out = new FileWriter(new File(args[1]));
    for (int i = 0; i < content.length(); i++) {
      out.write(content.charAt(i));
    }
    out.close();
  }
}
