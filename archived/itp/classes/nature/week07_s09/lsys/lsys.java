import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class lsys extends PApplet {

/* Daniel Shiffman               */
/* The Nature of Code            */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

LSystem lsys;
Turtle turtle;

public void setup() {
  size(200,200);
  /*
  // Create an empty ruleset
  Rule[] ruleset = new Rule[2];
  // Fill with two rules (These are rules for the Sierpinksi Gasket Triangle)
  ruleset[0] = new Rule('F',"F--F--F--G");
  ruleset[1] = new Rule('G',"GG");
  // Create LSystem with axiom and ruleset
  lsys = new LSystem("F--F--F",ruleset);
  turtle = new Turtle(lsys.getSentence(),width*2,TWO_PI/3);
  */
  
  Rule[] ruleset = new Rule[1];
  ruleset[0] = new Rule('F',"F[F]-F+F[--F]+F-F");
  lsys = new LSystem("F-F-F-F",ruleset);
  turtle = new Turtle(lsys.getSentence(),width-1,PI/2);
  smooth();
}

public void draw() {
  background(255);  
  translate(0,height-1);
  turtle.render();
  noLoop();
}

public void mousePressed() {
  lsys.generate();
  turtle.setToDo(lsys.getSentence());
  turtle.changeLen(0.3333f);
  redraw();
}

/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

/* LSystem Class                 */

// An LSystem has a starting sentence
// An a ruleset
// Each generation recursively replaces characteres in the sentence
// Based on the rulset

class LSystem {

  String sentence;     // The sentence (a String)
  Rule[] ruleset;      // The ruleset (an array of Rule objects)
  int generation;      // Keeping track of the generation #

  // Construct an LSystem with a startin sentence and a ruleset
  LSystem(String axiom, Rule[] r) {
    sentence = axiom;
    ruleset = r;
    generation = 0;
  }

  // Generate the next generation
  public void generate() {
    // An empty StringBuffer that we will fill
    StringBuffer nextgen = new StringBuffer();
    // For every character in the sentence
    for (int i = 0; i < sentence.length(); i++) {
      // What is the character
      char curr = sentence.charAt(i);
      // We will replace it with itself unless it matches one of our rules
      String replace = "" + curr;
      // Check every rule
      for (int j = 0; j < ruleset.length; j++) {
        char a = ruleset[j].getA();
        // if we match the Rule, get the replacement String out of the Rule
        if (a == curr) {
          replace = ruleset[j].getB();
          break; 
        }
      }
      // Append replacement String
      nextgen.append(replace);
    }
    // Replace sentence
    sentence = nextgen.toString();
    // Increment generation
    generation++;
  }

  public String getSentence() {
    return sentence; 
  }

  public int getGeneration() {
    return generation; 
  }


}



/* Daniel Shiffman               */
/* http://www.shiffman.net       */

/* A Class to describe an
 LSystem Rule                  */

class Rule {
  char a;
  String b;

  Rule(char a_, String b_) {
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


/* Daniel Shiffman               */
/* http://www.shiffman.net       */

class Turtle {

  String todo;
  float len;
  float theta;

  Turtle(String s, float l, float t) {
    todo = s;
    len = l; 
    theta = t;
  } 

  public void render() {
    stroke(0);
    for (int i = 0; i < todo.length(); i++) {
      char c = todo.charAt(i);
      if (c == 'F' || c == 'G') {
        line(0,0,len,0);
        translate(len,0);
      } 
      else if (c == '+') {
        rotate(theta);
      } 
      else if (c == '-') {
        rotate(-theta);
      } 
      else if (c == '[') {
        pushMatrix();
      } 
      else if (c == ']') {
        popMatrix();
      }
    } 
  }

  public void setLen(float l) {
    len = l; 
  } 

  public void changeLen(float percent) {
    len *= percent; 
  }

  public void setToDo(String s) {
    todo = s; 
  }

}



  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "lsys" });
  }
}
