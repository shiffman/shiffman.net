// Attraction
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a thing in our world, has vectors for location, velocity, and acceleration
// Also includes scalar values for mass, maximum velocity, and elasticity

class Thing {
  PVector loc;
  PVector vel;
  PVector acc;
  float mass;
  float max_vel;
  float bounce = 1.0f; // How "elastic" is the object
  float G;             // Universal gravitational constant
    
  Thing(PVector a, PVector v, PVector l, float m_) {
    acc = a.get();
    vel = v.get();
    loc = l.get();
    mass = m_;
    max_vel = 5.0;
    G = 0.2;
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
  
  PVector calcGravForce(Thing t) {
    PVector dir = PVector.sub(t.getLoc(), loc);        // Calculate direction of force
    float d = dir.mag();                               // Distance between objects
    d = constrain(d,20.0,50.0);                        // Limiting the distance to eliminate "extreme" results for very close or very far objects
    dir.normalize();                                   // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = (G * mass * t.getMass()) / (d * d);  // Calculate gravitional force magnitude
    dir.mult(force);                                   // Get force vector --> magnitude * direction
    return dir;
  }
  
  void applyForce(PVector force) {
    force.div(mass);
    acc.add(force);
    if (showVectors) {
      drawVector(force,loc,5000);
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
    acc.mult(0);
  }
  
  // Method to display
  void render() {
    ellipseMode(CENTER);
    stroke(0);
    fill(100,127);
    ellipse(loc.x,loc.y,mass*2,mass*2);
    if (showVectors) {
      drawVector(vel,loc,20);
    }
  }
}

