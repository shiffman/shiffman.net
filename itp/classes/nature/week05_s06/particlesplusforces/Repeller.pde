// Simple Particle System
// Daniel Shiffman <http://www.shiffman.net>

// A very basic Repeller class
class Repeller {

  // Location
  Vector3D loc;

  Repeller(float x, float y)  {
    loc = new Vector3D(x,y);
  }

  void display() {
    stroke(255);
    noFill();
    ellipse(loc.x,loc.y,20,20);
  }

  // Calculate a force to push particle away from repeller
  Vector3D pushParticle(Particle p) {
    Vector3D dir = Vector3D.sub(loc,p.loc);      // Calculate direction of force
    float d = dir.magnitude();                   // Distance between objects
    dir.normalize();                             // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = -150.0 / (d * d);              // Repelling force is inversely proportional to distance
    dir.mult(force);                             // Get force vector --> magnitude * direction
    return dir;
  }  

}
