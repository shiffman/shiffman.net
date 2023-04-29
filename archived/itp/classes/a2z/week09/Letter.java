/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* Letter class                  */

// Each letter is a character
// and has an array which describes a list of probabilities
// that other characters will appear afterwards

public class Letter {
    
    char c;
    float[] probs;
    
    public Letter(char c_) {
        c = c_;
        probs = new float[127]; // We are allowing for 127 other possibilities
    }
    
    // Arbitrary function that fills the probabilities according to alphabetical position
    // What kind of system could you design?
    // Could you calculate probabilities based on real world text examples?
    // Could you try this with words instead of characters?
    // How about the probability of a character appearing after two characters (not one)
    public void calcprob() {
        for (int i = 0; i < probs.length; i++) {
            // only considering lower case letters
            if (i > 96 && i < 123) {
                // if it's  before in the alphabet
                if (i <= c) probs[i] = 0.01f;
                // if it's within 4 letters after
                else if (i-c < 4) probs[i] = (float) (4-(c-i))*10f;
                // anything else
                else probs[i] = 0.1f;
            }
            else {
                probs[i] = 0.0f;
            }
        }
        // normalize all values
        normalize(probs);
    }
    
    // An algorithm for picking the next character based on probability map
    public char pickNext() {
        // Have we found one yet
        boolean foundone = false;
        int hack = 0;  // let's count just so we don't get stuck in an infinite loop by accident
        while (!foundone && hack < 10000) {
            // Pick two random numbers
            float r1 = (float) Math.random();
            float r2 = (float) Math.random();
            int index = (int) (r1*probs.length); // random spot in the array
            // If the second random number is less than the probability in the array
            // Then we've found an OK answer
            if (r2 < probs[index]) {
                foundone = true;
                return (char) index;
            }
            hack++;
        }
        // Hack in case we run into a problem (need to improve this)
        return ' ';
    }
    
    
    // Divide each value by the sum of all values
    public void normalize(float[] arr) {
        float total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] /= total;
        }
    }
    
}
