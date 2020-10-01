// Forces
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a thing in our world, has vectors for location, velocity, and acceleration
// Also includes scalar values for mass, maximum velocity, and elasticity

class Thing {
  PVector loc;
  PVector vel;
  PVector acc;
  float mass;
  float max_vel;
  float bounce = 1.0; // How "elastic" is the object
    
  Thing(PVector a, PVector v, PVector l, float m_) {
    acc = a.get();
    vel = v.get();
    loc = l.get();
    mass = m_;
    max_vel = 20.0;
  }
  
  PVector getLoc() {
    return loc;
  }

  PVector getVel() {
    return vel;
  }
  float getMass() {
    return mass;
  }

  void applyForce(PVector force) {
    force.div(mass);
    acc.add(force);
    if (showVectors) {
      drawVector(force,loc,1000);
    }    
  }

  // Main method to operate object
  void go() {
    update();
    borders();
    render();
  }
  
  // Method to update location
  void update() {
    vel.add(acc);
    vel.limit(max_vel);
    loc.add(vel);
    acc.mult(0);
  }
  
  // Check for bouncing off borders
  void borders() {
    if (loc.y > height) {
      vel.y *= -bounce;
      loc.y = height;
    }
    if ((loc.x > width) || (loc.x < 0)) {
      vel.x *= -bounce;
    }    
    //if (loc.x < 0)     loc.x = width;
    //if (loc.x > width) loc.x = 0;
  }  
  
  // Method to display
  void render() {
    ellipseMode(CENTER);
    stroke(0);
    fill(175,200);
    ellipse(loc.x,loc.y,mass*10,mass*10);
    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }
}

