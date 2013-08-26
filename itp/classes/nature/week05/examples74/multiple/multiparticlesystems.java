import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class multiparticlesystems extends PApplet {
ArrayList psystems;

public void setup() {
  size(600,400);
  colorMode(RGB,255,255,255,100);
  psystems = new ArrayList();
  smooth();
}

public void draw() {
  background(0);

  for (int i = psystems.size()-1; i >= 0; i--) {
    ParticleSystem psys = (ParticleSystem) psystems.get(i);
    psys.run();

    if (psys.dead()) {
      psystems.remove(i);
    }
  }

  if (random(1) < 0.01f) {
    psystems.add(new ParticleSystem(PApplet.toInt(random(5,25)),new Vector3D(random(width),random(height),0)));
  }

}

public void mousePressed() {
  psystems.add(new ParticleSystem(PApplet.toInt(random(5,25)),new Vector3D(mouseX,mouseY,0)));
}

/*A class to describe a one singular particle*/

class CrazyParticle extends Particle {

  //just adding one new variable to a CrazyParticle
  //it still has all the variables from "Particle", and we don't have to retype them!
  float theta;

  // The CrazyParticle constructor can call the parent class (super class) constructor
  CrazyParticle(Vector3D l) {
    //super says do everything from the constructor in Particle
    super(l);
    //one more line of code to deal with the new variable, theta
    theta = 0.0f;

  }

  /*Notice we don't have the method "run" here b/c it inherits it
  from the class Particle*/

  // This update() method overrides the parent class update() method
  void update() {
    //do what update does
    super.update();
    //and then do some more stuff
    float theta_vel = (vel.x() * vel.magnitude()) / 10.0f;
    theta += theta_vel;
  }

  //totally rewrite timer for this class
  void timer() {
    timer -= 0.2f;
  }

  //function to display
  void render() {
    //render the ellipse just like in a regular particle
    super.render();

    //then do some fancy other stuff
    //to add a rotating line
    push();
    translate(loc.x(),loc.y());
    rotate(theta);
    stroke(0,255,255,timer);
    line(0,0,25,0);
    pop();
  }
}


/*A class to describe a one singular particle*/
//this could is really basic, just like our "thing" class in earlier examples
//here we could make more interesting particles using images, different shapes, more advanced behaviors, etc.

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
    acc = new Vector3D(0,0.01f,0);
    vel = new Vector3D(random(-1,1),random(-2,0),0);
    loc = l.copy();
    r = 10.0f;
    timer = 100.0f;
  }


  void run() {
    update();
    timer();
    render();
  }

  //function to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
  }
  
  void timer() {
    timer -= 1.0f;
  }

  //function to display
  void render() {
    ellipseMode(CENTER);
    noStroke();
    fill(0,100,255,timer);
    ellipse(loc.x(),loc.y(),r,r);
  }
  
  //if the timer has reached 0 this function
  //will tell us we don't need this particle anymore
  boolean dead() {
    if (timer <= 0.0f) {
      return true;
    } else {
      return false;
    }
  }
}



/*A class to describe a group of Particles*/
//this class does very little.
//it doesn't care what the particles do, it's just for managing the arraylist of particles

class ParticleSystem {
  
  //this particle system has an origin point, and an ArrayList of particles
  ArrayList particles;
  Vector3D origin;

  //The Constructor (called when the object is first created)
  ParticleSystem(int num, Vector3D v) {
    //create the arraylist and set the origin point
    particles = new ArrayList();
    origin = v.copy();
    
    //add an initial group of particles to the ArrayList
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin));
      //there is a 10% chance we will add a "crazy particle" into the system
      if (random(1) < 0.1f) {
        particles.add(new CrazyParticle(origin));
      }
    }
  }

  void run() {
  
    //Notice how we are going backwards through the arraylist here
    //This is very important, we can't go forwards if we are deleting!!
    for (int i = particles.size()-1; i >= 0; i--) {
      //***POLYMORPHISM HERE!!!  Even though some of the particles in the ArrayList are of type "Particle"
      //and some are of type "CrazyParticle", we can treat them all as just a regular "Particle"
      //and not have to worry about managing what is what      
      Particle p = (Particle) particles.get(i);
      p.run();
      //if the particle is no longer useful, delete it.
      if (p.dead()) {
        particles.remove(i);
      }
    }
  }
  
  void addParticle() {
    particles.add(new Particle(origin));
  }
  
  void addCrazyParticle() {
    particles.add(new CrazyParticle(origin));
  }
  
  //a method to say if the system has no more particles left and can be discarded
  boolean dead() {
    if (particles.size() < 1) {
      return true;
    } else {
      return false;
    }
  }

}

}