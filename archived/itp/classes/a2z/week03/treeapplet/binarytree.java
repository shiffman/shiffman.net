import processing.core.*; import java.util.regex.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class binarytree extends PApplet {
Tree tree;
PFont f;

boolean go = false;
Pattern p;

public void setup() {
  size(640,480);
  tree = new Tree();
  //tree.print();
  f = loadFont("Arial.vlw");
  p = Pattern.compile("[a-z]");

}


public void draw() {

  background(100);
  textFont(f);
  textAlign(CENTER);
  translate(width/2,20);
  if (go) {
    tree.render(this);
    textAlign(LEFT);
    fill(255);
    text("type a-z (no caps) to add letters",-width/2+20,0);
  } 
  else {
    text("a simple demo of an unbalanced binary tree\n click anywhere to begin",0,height/2-20);
  }
}


public void keyPressed() {
  if (go) {
    String input = "" + key;
    Matcher m = p.matcher(input);
    if (m.find()) {
      tree.insert(input);
    }
  }
}

public void mousePressed() {
  go = true;
}
static public void main(String args[]) {   PApplet.main(new String[] { "binarytree" });}}