import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class steering6 extends PApplet {
//An instance of Boid
Boid b;

public void setup() {
  size(480,360);
  colorMode(RGB,255,255,255,100);
  b = new Boid(new Vector3D(width/2,height/2),5.0f,0.1f);
  smooth();
}

public void draw() {
  background(0);
  //Draw an ellipse at the mouse location
  int mx = mouseX;
  int my = mouseY;
  fill(100);
  noStroke();
  ellipse(mx,my,30,30);
  
  //call some behaviors on our agent
  //b.wander();
  //b.seek(new Vector3D(mx,my));
  b.arrive(new Vector3D(mx,my));
  b.run();
}

//our Boid extends Particle, has a few more properties

class Boid extends Particle {

  float wandertheta;          //the "wander" angle
  float maxforce,maxspeed;    //maxforce for steering, maxspeed for vel

  Boid(Vector3D l, float ms, float mf) {
    super(l);
    r = 4.0f;
    wandertheta = 0.0f;
    maxspeed = ms;
    maxforce = mf;
  }
  
  void run() {
    update();
    borders();
    render();
  }
  
  //function to update location
  void update() {
    vel.add(acc);
    vel.limit(maxspeed);
    loc.add(vel);
    ///***DRAW STEERING VECTOR****//
    //drawVector(acc,loc,250.0f);
    acc.setXYZ(0,0,0);
  }

  void seek(Vector3D target) {
    acc.add(steer(target,false));
  }
 
  void arrive(Vector3D target) {
    acc.add(steer(target,true));
  }

  void wander() {
    float wanderR = 40.0f;           //radius for our "wander circle"
    float wanderD = 80.0f;           //distance for our "wander circle"
    float change = 0.5f;
    wandertheta += random(-change,change);     //randomly change wander theta

    //now we have to calculate the new location to steer towards on the wander circle
    Vector3D circleloc = vel.copy();  //start with velocity
    circleloc.normalize();    //normalize to get heading
    circleloc.mult(wanderD);  //multiply by distance
    circleloc.add(loc);       //make it relative to boid's location
    //calc the offset on the wander circle
    float actualtheta = wandertheta + vel.heading2D(); //adjust wandertheta to circle direction
    Vector3D circleOffSet = new Vector3D(wanderR*cos(actualtheta),wanderR*sin(actualtheta));
    Vector3D target = Vector3D.add(circleloc,circleOffSet);
    acc.add(steer(target,false));  //steer towards it
    
    //DEBUG DRAWING STUFF ASSOCIATED WITH WANDERING
    //drawWanderStuff(loc,circleloc,target,wanderR);
    
  }
  
  //a method that calculates a steering vector towards a target
  //takes a second argument, if true, it slows down as it approaches the target
  Vector3D steer(Vector3D target, boolean slowdown) {
    Vector3D steer;  //the steering vector
    Vector3D desired = Vector3D.sub(target,loc);  //a vector pointing from the location to the target
    float d = desired.magnitude(); //distance from the target is the magnitude of the vector
    //if the distance is greater than 0, calc steering (otherwise return zero vector)
    if (d > 0) {
      //normalize desired
      desired.normalize();
      //two options for magnitude (1 -- based on distance, 2 -- maxspeed)
      if ((slowdown) && (d < 100.0f)) desired.mult(maxspeed*(d/100.0f));
      else desired.mult(maxspeed);
      //STEERING VECTOR IS DESIREDVECTOR MINUS VELOCITY
      steer = Vector3D.sub(desired,vel);
      steer.limit(maxforce);  //limit to maximum steering force
    } else {
      steer = new Vector3D(0,0);
    }
    return steer;
  }
  
  void render() {
    //draws a triangle rotated in the direction of velocity
    float theta = vel.heading2D() + radians(90);
    fill(200);
    stroke(255);
    push();
    translate(loc.x(),loc.y());
    rotateZ(theta);
    beginShape(TRIANGLES);
    vertex(0, -r*2);
    vertex(-r, r*2);
    vertex(r, r*2);
    endShape();
    pop();
  }

  void borders() {
    if (loc.x() < -r) loc.setX(width+r);
    if (loc.y() < -r) loc.setY(height+r);
    if (loc.x() > width+r) loc.setX(-r);
    if (loc.y() > height+r) loc.setY(-r);
  }

}

/*A class to describe a one singular particle*/

class Particle {
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float r;
  float timer;

  //The Constructor (called when the object is first created)
  Particle(Vector3D a, Vector3D v, Vector3D l, float r_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    r = r_;
    timer = 100.0f;
  }
  
  //look, we can have more than one constructor!
  Particle(Vector3D l) {
    acc = new Vector3D(0,0.05f,0);
    //ooooh, bad random, random bad
    vel = new Vector3D(random(-1,1),random(-2,0),0);
    loc = l.copy();
    r = 10.0f;
    timer = 100.0f;
  }


  void run() {
    update();
    render();
  }

  //function to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
    timer -= 1.0f;
  }

  //function to display
  void render() {
    ellipseMode(CENTER);
    noStroke();
    fill(0,100,255,timer);
    ellipse(loc.x(),loc.y(),r,r);
  }
  
  //is the particle still useful?
  boolean dead() {
    if (timer <= 0.0f) {
      return true;
    } else {
      return false;
    }
  }
  
}



void drawVector(Vector3D v, Vector3D loc, float scayl) {
  push();
  if (v.magnitude() > 0.0f) {
    float arrowsize = 5;
    //translate to location to render vector
    translate(loc.x(),loc.y());
    stroke(255);
    //call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
    rotate(v.heading2D());
    //calculate length of vector & scale it to be bigger or smaller if necessary
    float len = v.magnitude()*scayl;
    //draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
    line(0,0,len,0);
    line(len,0,len-arrowsize,+arrowsize/2);
    line(len,0,len-arrowsize,-arrowsize/2);
  }
  pop();
}

void drawWanderStuff(Vector3D loc, Vector3D circle, Vector3D target, float R) {
  stroke(255); noFill();
  ellipseMode(CENTER);
  ellipse(circle.x(),circle.y(),R*2,R*2);
  ellipse(target.x(),target.y(),4,4);
  line(loc.x(),loc.y(),circle.x(),circle.y());
  line(circle.x(),circle.y(),target.x(),target.y());
}
}