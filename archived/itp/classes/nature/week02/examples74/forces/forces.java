import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class forces extends PApplet {Thing t;
boolean showVectors = true;

public void setup() {
  size(320,480);
  background(0);
  colorMode(RGB,255,255,255,100);
  Vector2D a = new Vector2D(0.0f,0.0f);
  Vector2D v = new Vector2D(0.0f,0.0f);
  Vector2D l = new Vector2D(10,0);
  t = new Thing(a,v,l,1.5f);
}

public void draw() {
  background(0);

  /*ADD GRAVITY TO THING*/
  /*This isn't "real" gravity, I am making up a force vector on my own*/
  Vector2D grav = new Vector2D(0,0.1f);
  t.add_force(grav);

  /*ADD WIND TO THING*/
  /*Again, just making up a force vector*/
  Vector2D wind = new Vector2D(0.02f,-0.05f);
  t.add_force(wind);

  /*ADD DRAG TO THING*/
  float liq_start    = 150; //liquid location
  float liq_height   = 150; //liquid height
  //test if thing is in liquid
  if ((t.getLoc().y() > liq_start) && (t.getLoc().y() < liq_start + liq_height)) {
    //drag is always calculated as force vector in the negative direction of velocity
    float c = -0.02f;                           //drag coefficient
    Vector2D thingVel = t.getVel();             //velocity of our thing
    Vector2D force = Vector2D.mult(thingVel,c); //following the formula above
    t.add_force(force);                         //adding the force to our object, which will ultimately affect its acceleration
  }

  t.go();

  //Draw the "liquid"
  /*Note it would probably be a good idea to make a liquid class and take a lot of this code out of the main loop*/
  noStroke();
  fill(0,0,255,50);
  rectMode(CORNER);
  rect(0,liq_start,width,liq_height);
}


/*Our draw vector function*/
/*It might be nice to improve this to have different vectors colored differently*/
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



// Force = Mass * Acceleration
// Acceleration = Force / Mass

/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
/*now we add "mass" to this thing as well and a function to add forces*/

class Thing {
  Vector2D loc;
  Vector2D vel;
  Vector2D acc;
  float mass;
  float sz;
  float max_vel;
  float bounce = 1.0f; //how "elastic" is the object

  //The Constructor (called when the object is first created)
  Thing(Vector2D a, Vector2D v, Vector2D l, float m_) {
    acc = a;
    vel = v;
    loc = l;
    mass = m_;
    sz = mass;
    max_vel = 20.0f;
  }

  /*we should probably make getters and setters for all of our instance variables
  but for this example, i'm just making the ones we are using*/
  Vector2D getLoc() {
    return loc;
  }

  Vector2D getVel() {
    return vel;
  }
  float getMass() {
    return mass;
  }

  void add_force(Vector2D force) {
    force.div(mass);
    acc.add(force);
    if (showVectors) {
      drawVector(force,loc,1000);
    }    
  }

  void go() {
    update();
    borders();
    render();
  }

  //function to update location
  void update() {
    vel.add(acc);
    vel.limit(max_vel);
    loc.add(vel);
    acc.setXY(0.0f,0.0f);
  }

  void borders() {
    if (loc.y() > height-sz/2) {
      loc.setY(height-sz/2);
      vel.setY(-bounce*vel.y());
    }
    if ((loc.x() > width-sz/2) || (loc.x() < sz/2)) {
      vel.setX(-bounce*vel.x());
    }    
    //if (loc.x() < 0)     loc.setX(width);
    //if (loc.x() > width) loc.setX(0);
  }

  //function to display
  void render() {
    rectMode(CENTER);
    noStroke();
    fill(0,100,255,75);
    rect(loc.x(),loc.y(),20,20);

    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

}

}