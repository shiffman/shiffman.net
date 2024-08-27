/* Daniel Shiffman               */
/* Bayesian Spam Filter Example  */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// This class describes a word
// and the various probabilities associated with that word

package bayes;

public class Word {
  private String word;    // The String itself
  private int countBad;   // The total times it appears in "bad" messages
  private int countGood;  // The total times it appears in "good" messages
  private float rBad;     // bad count / total bad words
  private float rGood;    // good count / total good words
  private float pSpam;    // probability this word is Spam

  // Create a word, initialize all vars to 0
  public Word(String s) {
    word = s;
    countBad = 0;
    countGood = 0;
    rBad = 0.0f;
    rGood = 0.0f;
    pSpam = 0.0f;
  }

  // Increment bad counter
  public void countBad() {
    countBad++;
  }

  // Increment good counter
  public void countGood() {
    countGood++;
  }

  // Computer how often this word is bad
  public void calcBadProb(int total) {
    if (total > 0) rBad = countBad / (float) total;
  }

  // Computer how often this word is good
  public void calcGoodProb(int total) {
    if (total > 0) rGood = 2*countGood / (float) total; // multiply 2 to help fight against false positives (via Graham)
  }

  // Implement bayes rules to computer how likely this word is "spam"
  public void finalizeProb() {
    if (rGood + rBad > 0) pSpam = rBad / (rBad + rGood);
    if (pSpam < 0.01f) pSpam = 0.01f;
    else if (pSpam > 0.99f) pSpam = 0.99f;
  }


  // The "interesting" rating for a word is
  // How different from 0.5 it is
  public float interesting() {
    return Math.abs(0.5f - pSpam);
  }

  // Some getters and setters
  public float getPGood() {
    return rGood;
  }

  public float getPBad() {
    return rBad;
  }

  public float getPSpam() {
    return pSpam;
  }

  public void setPSpam(float f) {
    pSpam = f;
  }

  public String getWord() {
    return word;
  }

}
