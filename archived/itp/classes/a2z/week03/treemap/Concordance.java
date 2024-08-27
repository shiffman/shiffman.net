/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Simple Text Concordance       */
/* This is a simplified version  */
/* Using a Java TreeMap          */

import java.io.*;
import java.util.*;

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
      // TreeMap words = new TreeMap();  // We used to say this!
      // Now we use "generics" to specify what will be in the Collection
      TreeMap words = new TreeMap();

      // Step 3, break input file up into words
      // We are doing this with split and a regular expression
      String regex = "\\b";
      String tokens[] = content.split(regex);
      
      // We'll use a regular exrpession to match words with only characters and apostrophes
      // Throwing away all the punctuation (we could do this with a different split regex too)
      Pattern p = Pattern.compile("[a-z']+",Pattern.CASE_INSENSITIVE);
            
      // For every word
      for (int i = 0; i < tokens.length; i++) 
      {
        String s = tokens[i].toLowerCase();
        // If it matches our regex, insert it in the tree
        Matcher m = p.matcher(s);
        if (m.matches()) {
           if (words.containsKey(s)) {
             Word w = (Word) words.get(s);
             w.count();
           } else {
             Word w = new Word(s);
             words.put(s,w);
           }
        }
      }
      
      // We're done, print out contents of Tree!
      System.out.println("Here are the contents of your tree:");
      Iterator iterator = words.values().iterator();
      while (iterator.hasNext()) {
	    Word word = (Word) iterator.next();
	    System.out.println(word.getWord() + " " + word.getCount());
	  }	
    
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

