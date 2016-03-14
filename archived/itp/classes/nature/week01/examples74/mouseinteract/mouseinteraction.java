import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class mouseinteraction extends PApplet {Thing t;

//this may prove useful later depending on whether or not we want to display vectors
boolean showVectors = true;

public void setup() {
  size(640,480);
  background(0);
  Vector2D v1 = new Vector2D(0,0);
  Vector2D v2 = new Vector2D(0,0);
  Vector2D v3 = new Vector2D(width/2,height/2);
  t = new Thing(v1,v2,v3);
}


public void draw() {
  background(0);
  t.go();
  if (mousePressed) {
    //compute direction vector between mouse and object location
    //3 steps -- (1) Get Mouse Location, (2) Get Difference Vector, (3) Normalize difference vector
    Vector2D m = new Vector2D(mouseX,mouseY);
    Vector2D diff = Vector2D.sub(m,t.getLoc());
    diff.normalize();
    float factor = 1.0f;
    diff.mult(factor);
    //object accelerates towards mouse
    t.setAcc(diff);
  } else {
    t.setAcc(new Vector2D(0,0));
  }
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

public void keyPressed() {
  showVectors = !showVectors;
}




/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
class Thing {
  //*using private now to encapsulate data*//
  private Vector2D loc;
  private Vector2D vel;
  private Vector2D acc;
  private float maxvel;

  //The Constructor (called when the object is first created)
  Thing(Vector2D a, Vector2D v, Vector2D l) {
    acc = a;
    vel = v;
    loc = l;
    maxvel = 7;
  }

  /*Add functions to our thing object to access the location, velocity and acceleration from outside the class*/
  Vector2D getVel() {
    return vel;
  }

  Vector2D getAcc() {
    return acc;
  }

  Vector2D getLoc() {
    return loc;
  }

  void setLoc(Vector2D v) {
    loc = v;
  }

  void setVel(Vector2D v) {
    vel = v;
  }

  void setAcc(Vector2D v) {
    acc = v;
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
    //limit speed to max
    vel.limit(maxvel);
    /*if (vel.magnitude() > maxvel) {
      vel.normalize();
      vel.mult(maxvel);
    }*/
  }

  void borders() {
    if ((loc.y() > height) || (loc.y() < 0))  {
      vel.setY(-vel.y());
    }
    if ((loc.x() < 0) || (loc.x() > width)) {
      vel.setX(-vel.x());
    }
  }

  //function to display
  void render() {
    rectMode(CENTER);
    noStroke();
    fill(0,0,255);
    rect(loc.x(),loc.y(),20,20);

    if (showVectors) {
      //draws both velocity and acceleration vectors, this could be improved to differentiate by color, etc.
      drawVector(vel,loc,10);
      drawVector(acc,loc,50);
    }
  }

}
}