import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class bodies extends PApplet {
/*In this example, there is no fixed Attractive body
  Each object asserts a gravitational force on all objects*/

//establish an array of MAX Things
final int MAX = 6;
Thing[] t = new Thing[MAX];
boolean showVectors = true;

public void setup() {
  size(480,360);
  background(0);
  reset();
  smooth();
}

public void draw() {
  //framerate(10);
  background(0);
  for (int i = 0; i < t.length; i++) {          //for every Thing t[i]
    for (int j = 0; j < t.length; j++) {        //for every Thing t[j]
      if (i != j) {                             //make sure we are not calculating gravtional pull on oneself
        Vector2D f = t[i].calcGravForce(t[j]);  //use the function we wrote above
        t[i].add_force(f);                      //add the force to the object to affect its acceleration
      }
    }
    t[i].go();                                  //implement the rest of the object's functionality
  }
}

/*A reset function that initializes all the objects*/
void reset() {
  for (int i = 0; i < t.length; i++) {
    Vector2D ac = new Vector2D(0.0f,0.0f);
    Vector2D ve = new Vector2D(0.0f,0.0f);
    Vector2D lo = new Vector2D(random(width),random(height));
    t[i] = new Thing(ac,ve,lo,random(5,10),0.2f);
  }

}

public void mousePressed() {
  reset();
}

public void keyPressed() {
  showVectors = !showVectors;
}

public void drawVector(Vector2D vel, Vector2D loc, float scayl) {
  push();
  float arrowsize = 10;
  //translate to location to render vector
  translate(loc.x(),loc.y());
  stroke(255);
  //call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(vel.heading());
  //calculate length of vector & scale it to be bigger or smaller if necessary
  float len = vel.magnitude()*scayl;
  //draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  pop();
}

// G ---> gravitational constant
// m1 --> mass of object #1
// m2 --> mass of object #2
// d ---> distance between objects
// F = (G*m1*m2)/(d*d)

// Force = Mass * Acceleration
// Acceleration = Force / Mass

/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
/*now we add "mass" to this thing as well and a function to add forces*/

class Thing {
  Vector2D loc;
  Vector2D vel;
  Vector2D acc;
  float mass;
  float max_vel;
  float G;
  float sz;

  //The Constructor (called when the object is first created)
  Thing(Vector2D a, Vector2D v, Vector2D l, float m_, float g_) {
    acc = a;
    vel = v;
    loc = l;
    mass = m_;
    G = g_;
    max_vel = 5.0f;
    sz = mass*5;
  }

  /*we should probably make getters and setters for all of our instance variables
  but for this example, i'm just making the ones we are using*/
  Vector2D getLoc() {
    return loc;
  }

  float getMass() {
    return mass;
  }

  void add_force(Vector2D force) {
    acc.add(force);
  }


  void go() {
    update();
    //borders();
    render();
  }
  
  /*In case we want wraparound, but won't really help us here*/
  void borders() {
    if (loc.x() < 0) loc.setX(width);
    if (loc.x() > width) loc.setX(0);
    if (loc.y() < 0) loc.setY(height);
    if (loc.y() < height) loc.setY(0);
  }

  Vector2D calcGravForce(Thing t) {
    Vector2D dir = Vector2D.sub(t.getLoc(),loc);      //calculate direction of force
    float d = dir.magnitude();                        //distance between objects
    d = constrain(d,20.0f,100.0f);                    //Limiting the distance to eliminate "extreme" results for very close or very far objects
    dir.normalize();                                  //normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = (G * mass * t.getMass()) / (d * d); //calculate gravitional force magnitude
    dir.mult(force);                                  //get force vector --> magnitude * direction
    return dir;
  }

  //function to update location
  void update() {
    vel.add(acc);
    vel.limit(max_vel);
    loc.add(vel);
    if (showVectors) drawVector(acc,loc,2500);        //we have to draw acceleration before we set it to 0
    acc.setX(0.0f);
    acc.setY(0.0f);
  }

  //function to display
  void render() {
    if (showVectors) drawVector(vel,loc,50);  
    rectMode(CENTER);
    noStroke();
    fill(0,0,255,150);
    rect(loc.x(),loc.y(),sz,sz);
  }

}

}