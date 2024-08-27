// The HTML Tag Remover
// Daniel Shiffman
// Programming from A to Z, Spring 2006
// Demonstrates regular expressions in Java

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.regex.*;

public class HTMLTagRemover{
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

    // Replace anything between script or style tags
    // We should do some extra work to allow for white spaces in the HTML
    String scriptregex = "<(script|style)[^>]*>[^<]*</(script|style)>";
    Pattern p1 = Pattern.compile(scriptregex,Pattern.CASE_INSENSITIVE);
    Matcher m1 = p1.matcher(content);
    // For displaying results
    int count = 0;
    while (m1.find()) {
      //System.out.println(m1.group());
      count++;
    }
    System.out.println("Removed " + count + " script & style tags");
    // Replace any matches with nothing
    content = m1.replaceAll("");

    // A Regex to match anything in between <>
    // Reads as: Match a "<"
    // Match one or more characters that are not ">"
    // Match "<";
    String tagregex = "<[^>]*>";
    Pattern p2 = Pattern.compile(tagregex);
    Matcher m2 = p2.matcher(content);
    count = 0;
    // Just counting all the tags first
    while (m2.find()) {
      //System.out.println(m.group());
      count++;
    }

    // Replace any matches with nothing
    content = m2.replaceAll("");
    System.out.println("Removed " + count + " other tags.");

    // Oh what the hey, let's get rid of a lot of extra carriage returns
    // Matches one or two line breaks, 
    // followed any number of sequences of white spaces and line breaks
    String multiplenewlines = "(\\n{1,2})(\\s*\\n)+"; 
    // Replace with the original one or two new lines
    // Backreference not exactly necessary, but a nice demonstration
    content = content.replaceAll(multiplenewlines,"$1");

    // Create an output stream and file channel to write out a report
    // (Also print out report to screen)
    FileOutputStream fos = new FileOutputStream(args[1]);
    FileChannel outfc = fos.getChannel();

    // Convert content String into ByteBuffer and write out to file
    bb = ByteBuffer.wrap(content.getBytes());
    outfc.write(bb);
    outfc.close();
  }
}