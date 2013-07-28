/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Simple Text Concordance       */
/* Uses Binary Tree              */

import java.io.*;

import a2z.*;
import java.util.regex.*;

public class Concordance
{
  public static void main(String[] args)
  {
  
    // Print out some basic info about the program
    System.out.println("\n\n Welcome to the java text concordance program.");
    System.out.println(" This program treats all characters as ") ;
    System.out.println(" delimiters with the following exceptions:       ") ;
    System.out.println("   Letters A-Z and a-z                           ") ;
    System.out.println("   Numbers 0-9                                   ") ;
    System.out.println("   An apostrophe                                 ") ;
    System.out.println(" All delimiters are thrown away and not kept as part of the concordance.      ") ;
    System.out.println(" In addition, this program is case sensitive and treats uppercase letters as  ") ;
    System.out.println(" alphabetically lower than lowercase ones.") ;
    System.out.println(" Also note this program was designed for use w/ Unix formatted text files.\n\n") ;
    String path = null;
    
    try
    {
      // Step 1, read the input file
      path   = args[0];
      A2ZFileReader fr = new A2ZFileReader(path);
      String content = fr.getContent();
      
      // Step 2, create an empty Tree
      Tree tree = new Tree();
      
      // Step 3, break input file up into words
      // We are doing this with split and a regular expression
      String regex = "\\b";
      String words[] = content.split(regex);
      
      // We'll use a regular exrpession to match words with only characters and apostrophes
      // Throwing away all the punctuation (we could do this with a different split regex too)
      Pattern p = Pattern.compile("[a-z']+",Pattern.CASE_INSENSITIVE);
      
      // For every word
      for (int i = 0; i < words.length; i++) 
      {
        // If it matches our regex, insert it in the tree
        Matcher m = p.matcher(words[i]);
        if (m.matches()) tree.insert(words[i]);
      }
      
      // We're done, print out contents of Tree!
      System.out.println("Here are the contents of your tree:");
      System.out.println();
      tree.print();
    
    // Look Ma, Error Handling!
    
    //check for IO problem with bad file
    } catch (IOException e) {
      System.out.println("There was a problem with the filename you entered.");
    
    //check for no filename entered at command prompt
    } catch (ArrayIndexOutOfBoundsException e)
    {
      System.out.println("Please include a filename when running this program.");
    }
  }
}

