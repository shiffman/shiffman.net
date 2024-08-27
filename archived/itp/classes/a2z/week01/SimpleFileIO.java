// Simple File Input and Output using "new" I/O
// Daniel Shiffman
// Programming from A to Z, Spring 2006
// Based off of code from Java Regular Expressions by Mehran Habibi

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class SimpleFileIO {
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
    //bb.rewind(); //bb.flip();
    String content = new String(bb.array());

    
    // Conceivably we would now mess with the string here
    // Doing all sorts of fun stuff
    
    // Create an output stream and file channel
    // Using second argument as file name to write out
    FileOutputStream fos = new FileOutputStream(args[1]);
    FileChannel outfc = fos.getChannel();
    
    // Convert content String into ByteBuffer and write out to file
    bb = ByteBuffer.wrap(content.getBytes());
    //outfc.position(0);
    outfc.write(bb);
    outfc.close();

  }
}