/* Daniel Shiffman               */
/* Binary Tree Class             */
/* Programming from A to Z       */
/* ITP, Spring 2006              */

// Simple Class to describe a word and count

public class Word
{
  private String word;
  private int count;

  public Word(String s) {
     word = s;
     count = 1;
  }
  
  public void count() {
    count++;
  }
  
  public int getCount() {
    return count;
  }
  
  public String getWord() {
    return word;
  }
}



