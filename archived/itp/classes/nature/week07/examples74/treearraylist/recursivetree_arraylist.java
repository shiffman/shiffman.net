import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class recursivetree_arraylist extends PApplet {//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

//Recursive branching "structure" without an explicity recursive function
//Instead we have an ArrayList to hold onto N number of elements
//For every element in the ArrayList, we add 2 more elements, etc. (this is the recursion)


//an arraylist that will keep track of all current branches
ArrayList a;

public void setup() {
  size(400,400);
  colorMode(RGB,255,255,255,100);
  background(0);
  smooth();
  
  //setup the arraylist and add one branch to it
  a = new ArrayList();
  //a branch has a starting location, a starting "velocity", and a starting "timer"
  Branch b = new Branch(new Vector3D(width/2,height),new Vector3D(0f,-0.5f),200f);
  //add to arraylist
  a.add(b);
}

public void draw() {
  //let's stop when the arraylist gets too big
  if (a.size() < 1024) {
    //for every branch in the arraylist
    for (int i = a.size()-1; i >= 0; i--) {
      //get the branch, update and draw it
      Branch b = (Branch) a.get(i);
      b.update();
      b.render();
      //if it's ready to split
      if (b.timeToBranch()) {
        a.remove(i);             //delete it
        a.add(b.branch( 30f));   //add one going right
        a.add(b.branch(-25f));   //add one going left
      }
    }
  }
}



class Branch {
  //System of "branches"
  //each has a location, velocity, and timer 
  //we could implement this same idea with different data
  Vector3D loc;
  Vector3D vel;
  float timer;
  float timerstart;

  Branch(Vector3D l, Vector3D v, float n) {
    loc = l.copy();
    vel = v.copy();
    timerstart = n;
    timer = timerstart;
  }
  
  //move location
  void update() {
    loc.add(vel);
  }
  
  //draw a dot at location
  void render() {
    fill(0,0,255,50);
    noStroke();
    ellipseMode(CENTER);
    ellipse(loc.x(),loc.y(),4,4);
  }
  
  //did the timer run out?
  boolean timeToBranch() {
    timer--;
    if (timer < 0) {
      return true;
    } else {
      return false;
    }
  }

  //create a new branch at the current location, but change direction by an angle in degrees
  Branch branch(float angle) {
    //what is my current angle
    float theta = vel.heading2D();
    //what is my current speed
    float mag = vel.magnitude();
    //turn me
    theta += radians(angle);
    //look, polar coordinates to cartesian!!
    Vector3D newvel = new Vector3D(mag*cos(theta),mag*sin(theta));
    //return a new Branch
    return new Branch(loc,newvel,timerstart*0.66f);
  }
  
}
}