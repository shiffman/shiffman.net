/* Daniel Shiffman               */
/* HashMap Example               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

import java.util.*;

public class HashMapTest {
  public static void main(String[] args) {
    
    // Some Text to play with
    String text = "This example demonstrates using a HashMap";  
    
    // An empty hashmap
    HashMap words = new HashMap();
    
    // Add every word to the hashmap
    String[] tokens = text.split("\\W");
    for (int i = 0; i <  tokens.length; i++) {
        String word = tokens[i];
        // Note we are using the same String for both Key and Value
        // Conceivably, the value could be some new-fangled object
        words.put(word,word);
    }
    
    // Search the hash table for various words
    if (words.containsKey("example")) {
      System.out.println("I found the word example!");
    } else {
      System.out.println("I did not find the word example!");
    }
    
    if (words.containsKey("floopy")) {
      System.out.println("I found the word floopy!");
    } else {
      System.out.println("I did not find the word floopy!");
    }
    
  } 
}