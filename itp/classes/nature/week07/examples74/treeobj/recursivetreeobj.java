import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class recursivetreeobj extends PApplet {//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

/*randomly draws trees at random locations on the screen
  we achieve this by not erasing the background, but could
  get more interesting results by using an ArrayList.
  This program excessively uses random(), and could easily
  be improved*/


public void setup() {
  size(320,240);
  background(0);
  smooth();
}

public void draw() {
  if (random(1) < 0.01f) {
    Tree t = new Tree(random(width),random(height+100));
    t.render();
  }
}

public void mousePressed() {
  Tree t = new Tree(mouseX,mouseY);
  t.render();
}


/*a class to represent a recursively drawn tree structure (resembling a simple L-System)
//each object has an x,y point (starting location), a starting branch length
//and an angle of rotation for each subsequent branch

//we could do a better job and add many more parameters (i.e. instance variables) to this class*/

class Tree {

  float theta;
  float x,y;
  float len;

  Tree(float x_, float y_) {
    theta = radians(random(45));
    x = x_;
    y = y_;
    len = random(50) + 10;
  }

  void render() {
    push();
    stroke(255);
    translate(x,y);      //go to start point
    line(0,0,0,-len);    //draw a vertical line
    translate(0,-len);   //go to end of line
    branch(len);         //start the recursive branching process
    pop();
  }

  void branch(float h) {

    //every branch will be 2/3rds the size of the previous one
    h *= 0.66f;

    //All recursive functions must have an exit condition!!!!
    //here, ours is when the length of the branch is 2 pixels or less
    if (h > 2) {
      push();          //save the current state of transformation (i.e. where are we now)
      rotate(theta);   //rotate by theta
      line(0,0,0,-h);  //draw the branch
      translate(0,-h); //move to the end of the branch
      branch(h);       //ok, now call myself to draw two new branches!!
      pop();           //whenever we get back here, we "pop" in order to restore the location we were at previously

      //repeat the same thing, only branch off to the "left" this time!
      push();
      rotate(-theta);
      line(0,0,0,-h);
      translate(0,-h);
      branch(h);
      pop();

    }
  }
}
}