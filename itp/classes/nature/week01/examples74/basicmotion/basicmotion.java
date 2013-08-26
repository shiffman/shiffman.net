import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class basicmotion extends PApplet {Thing t;

//this may prove useful later depending on whether or not we want to display vectors
boolean showVectors = true;

public void setup() {
  size(320,240);
  background(0);

  Vector2D a = new Vector2D(0.01f,0.001f);
  Vector2D v = new Vector2D(0.0f,0.0f);
  Vector2D l = new Vector2D(0,height/2);
  t = new Thing(a,v,l);
}

public void draw() {
  background(0);
  t.go();
}



void drawVector(Vector2D v, Vector2D loc, float scayl) {
  push();
  float arrowsize = 10;
  //translate to location to render vector
  translate(loc.x(),loc.y());
  stroke(255);
  //call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading());
  //calculate length of vector & scale it to be bigger or smaller if necessary
  float len = v.magnitude()*scayl;
  //draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  pop();
}

public void mousePressed() {
  showVectors = !showVectors;
}



/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
class Thing {
  Vector2D loc;
  Vector2D vel;
  Vector2D acc;
  
  //The Constructor (called when the object is first created)
  Thing(Vector2D a, Vector2D v, Vector2D l) {
    acc = a;
    vel = v;
    loc = l;
  }

  //main function to operate object)
  void go() {
    update();
    borders();
    render();
  }
  
  //function to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
  }
  
  void borders() {
    if (loc.x() > width ) loc.setX(0     );
    if (loc.x() < 0     ) loc.setX(width );
    if (loc.y() > height) loc.setY(0     );
    if (loc.x() < 0     ) loc.setY(height);
  }

  //function to display
  void render() {
    rectMode(CENTER);
    noStroke();
    fill(0,0,255);
    rect(loc.x(),loc.y(),20,20);
    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

}



}