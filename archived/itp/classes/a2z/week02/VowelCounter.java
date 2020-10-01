import java.util.regex.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
	

public class VowelCounter {
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
  
    String regex = "[aeiou]";               
    Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);   
    int vowelcount = 0;
    Matcher m = p.matcher(content);         // Create Matcher
    while (m.find()) {
      //System.out.print(m.group());
      vowelcount++;
    }
    System.out.println("Total vowels: " + vowelcount);
  } 
}