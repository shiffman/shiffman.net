// Gravity 101
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a thing in our world, has vectors for location, velocity, and acceleration
// Also includes scalar values for mass, maximum velocity, and elasticity

// Created 2 May 2005

class Thing {
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float mass;
  float max_vel;
  float bounce = 1.0f; // How "elastic" is the object
    
  Thing(Vector3D a, Vector3D v, Vector3D l, float m_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    mass = m_;
    max_vel = 20.0f;
  }
  
  Vector3D getLoc() {
    return loc;
  }

  Vector3D getVel() {
    return vel;
  }
  float getMass() {
    return mass;
  }

  void add_force(Vector3D force) {
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
    acc.setXYZ(0.0f,0.0f,0.0f);
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
    noStroke();
    fill(200,90);
    ellipse(loc.x,loc.y,mass*10,mass*10);
    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }
}

