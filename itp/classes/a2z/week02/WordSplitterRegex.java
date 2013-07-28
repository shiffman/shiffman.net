import java.util.regex.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
	

public class WordSplitterRegex {
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

    String regex = "\\W";  // Use any "non-word character" as a delimiter
    String[] words = content.split(regex);
    for (int i = 0; i < words.length; i++) {
      System.out.println(words[i]);
    }
    System.out.println("Total words: " + words.length);
  } 
}