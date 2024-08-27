import java.util.regex.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
	

public class DoubleWords {
  public static void main(String[] args) throws IOException {
    // Create an input stream and file channel
    // Using first arguemnt as file name to read in
    FileInputStream fis = new FileInputStream(args[0]);
    FileChannel fc = fis.getChannel();
    	
    // Read the contents of a file into a ByteBuffer
    ByteBuffer bb = ByteBuffer.allocate((int)fc.size());
    fc.read(bb);
    fc.close();
	
    // Convert ByteBuffer to one long String
    String content = new String(bb.array());
  
    String regex = "\\b(\\w+)\\b\\W+\\1";   // Regex that matches double words
    Pattern p = Pattern.compile(regex);     // Compile Regex
    Matcher m = p.matcher(content);         // Create Matcher
    while (m.find()) {
      System.out.println(m.group());
    }
  } 
}