
Tree tree;
PFont f;
import java.util.regex.*;
boolean go = false;
Pattern p;

void setup() {
  size(640,480);
  tree = new Tree();
  //tree.print();
  f = loadFont("Arial.vlw");
  p = Pattern.compile("[a-z]");

}


void draw() {

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


void keyPressed() {
  if (go) {
    String input = "" + key;
    Matcher m = p.matcher(input);
    if (m.find()) {
      tree.insert(input);
    }
  }
}

void mousePressed() {
  go = true;
}
