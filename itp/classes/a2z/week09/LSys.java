/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* LSystem Driver Program        */

public class LSys {
    
    public static void main(String[] args) {
        System.out.println("Starting LSystem Sentence Generator. . .\n");
        
        // Create an empty ruleset
        Rule[] ruleset = new Rule[2];
        // Fill with two rules (These are rules for the Sierpinksi Gasket Triangle)
        ruleset[0] = new Rule('F',"F--F--F--G");
        ruleset[1] = new Rule('G',"GG");
        // Create LSystem with axiom and ruleset
        LSystem lsys = new LSystem("F--F--F",ruleset);
        
        // Display sentence results
        System.out.println("Generation #" + lsys.getGeneration());
        System.out.println(lsys.getSentence());
        // Regenerate 5 times
        for (int i = 0; i < 5; i++) {
            lsys.generate();
            System.out.println();
            System.out.println("Generation #" + lsys.getGeneration());
            System.out.println(lsys.getSentence());
        }
        
    }
}
