// Attraction
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
  float G;             // Universal gravitational constant
    
  Thing(Vector3D a, Vector3D v, Vector3D l, float m_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    mass = m_;
    max_vel = 5.0f;
    G = 0.2f;
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
  
  Vector3D calcGravForce(Thing t) {
    Vector3D dir = Vector3D.sub(t.getLoc(), loc);     // Calculate direction of force
    float d = dir.magnitude();                        // Distance between objects
    d = constrain(d,20.0f,50.0f);                      // Limiting the distance to eliminate "extreme" results for very close or very far objects
    dir.normalize();                                  // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = (G * mass * t.getMass()) / (d * d); // Calculate gravitional force magnitude
    dir.mult(force);                                  // Get force vector --> magnitude * direction
    return dir;
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
    render();
  }
  
  // Method to update location
  void update() {
    vel.add(acc);
    vel.limit(max_vel);
    loc.add(vel);
    acc.setXY(0.0f,0.0f);
  }
  
  // Method to display
  void render() {
    ellipseMode(CENTER);
    noStroke();
    fill(200,90);
    ellipse(loc.x,loc.y,mass*2,mass*2);
    if (showVectors) {
      drawVector(vel,loc,20);
      drawVector(acc,loc,10000);
    }
  }
}

