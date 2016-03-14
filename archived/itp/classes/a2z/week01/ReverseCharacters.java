// Reverse Characters
// Daniel Shiffman
// Programming from A to Z, Spring 2006

import java.io.*;
import java.nio.*;
import java.nio.channels.*;


public class ReverseCharacters {
  public static void main (String[] args) throws IOException {

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
    
    System.out.println("Read " + content.length() + " characters from " + args[0]);
    StringBuffer reverse = new StringBuffer();
    for (int i = content.length()-1; i >= 0; i--) {
       char c = content.charAt(i);
       reverse.append(c);
    }
    
    String output = reverse.toString();
    
    // Create an output stream and file channel to write out a report
    // (Also print out report to screen)
    FileOutputStream fos = new FileOutputStream(args[1]);
    FileChannel outfc = fos.getChannel();
    
    // Convert content String into ByteBuffer and write out to file
    bb = ByteBuffer.wrap(output.getBytes());
    outfc.write(bb);
    outfc.close();
    
    System.out.println("Reversed text written to " + args[1]);

  }
}
