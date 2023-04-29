/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* A Class to describe an
 LSystem Rule                  */


public class Rule {
    private char a;
    private String b;
    
    public Rule(char a_, String b_) {
        a = a_;
        b = b_; 
    }
    
    public char getA() {
        return a;
    }
    
    public String getB() {
        return b;
    }
    
}
