// Collisions
// Daniel Shiffman <http://www.shiffman.net>

// A Thing class for idealized collision

class Thing {

  PVector loc;
  PVector vel;
  float bounce = 1.0;
  float r = 20;
  boolean colliding = false;

  Thing(PVector v, PVector l) {
    vel = v.get();
    loc = l.get();
  }

  // Main method to operate object
  void go() {
    update();
    borders();
    render();
  }

  // Method to update location
  void update() {
    loc.add(vel);
  }

  // Check for bouncing off borders
  void borders() {
    if (loc.y > height) {
      vel.y *= -bounce;
      loc.y = height;
    } else if (loc.y < 0) {
      vel.y *= -bounce;
      loc.y = 0;
    } 
    if (loc.x > width) {
      vel.x *= -bounce;
      loc.x = width;
    }  else if (loc.x < 0) {
      vel.x *= -bounce;
      loc.x = 0;
    } 
  }  

  // Method to display
  void render() {
    ellipseMode(CENTER);
    stroke(0);
    fill(175,200);
    ellipse(loc.x,loc.y,r*2,r*2);
    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

  void collideEqualMass(Thing t) {
    float d = PVector.dist(loc,t.loc);
    float sumR = r + t.r;
    // Are they colliding?
    if (!colliding && d < sumR) {
      // Yes, make new velocities!
      colliding = true;
      // Direction of one object another
      PVector n = PVector.sub(t.loc,loc);
      n.normalize();
      
      // Difference of velocities so that we think of one object as stationary
      PVector u = PVector.sub(vel,t.vel);
      
      // Separate out components -- one in direction of normal
      PVector un = componentVector(u,n);
      // Other component
      u.sub(un);
      // These are the new velocities plus the velocity of the object we consider as stastionary
      vel = PVector.add(u,t.vel);
      t.vel = PVector.add(un,t.vel);
    } 
    else if (d > sumR) {
      colliding = false;
    }

  }


}


PVector componentVector (PVector vector, PVector directionVector) {
  //--! ARGUMENTS: vector, directionVector (2D vectors)
  //--! RETURNS: the component vector of vector in the direction directionVector
  //-- normalize directionVector
  directionVector.normalize();
  directionVector.mult(vector.dot(directionVector));
  return directionVector;
}



