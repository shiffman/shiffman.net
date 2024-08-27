// Particles + Forces
// Daniel Shiffman <http://www.shiffman.net>

// A very basic Repeller class
class Repeller {
  
  // Gravitational Constant
  float G = 100;

  // Location
  PVector loc;

  float r = 10;

  // For mouse interaction
  boolean dragging = false; // Is the object being dragged?
  boolean rollover = false; // Is the mouse over the ellipse?
  PVector drag;  // holds the offset for when object is clicked on

  Repeller(float x, float y)  {
    loc = new PVector(x,y);
    drag = new PVector(0,0);
  }

  void display() {
    stroke(0);
    if (dragging) fill (0);
    else if (rollover) fill(150);
    else noFill();
    ellipse(loc.x,loc.y,r*2,r*2);
  }

  // Calculate a force to push particle away from repeller
  PVector pushParticle(Particle p) {
    PVector dir = PVector.sub(loc,p.loc);      // Calculate direction of force
    float d = dir.mag();                       // Distance between objects
    dir.normalize();                           // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    d = constrain(d,5,100);                     // Keep distance within a reasonable range
    float force = -1 * G / (d * d);            // Repelling force is inversely proportional to distance
    dir.mult(force);                           // Get force vector --> magnitude * direction
    return dir;
  }  

  // The methods below are for mouse interaction
  void clicked(int mx, int my) {
    float d = dist(mx,my,loc.x,loc.y);
    if (d < r) {
      dragging = true;
      drag.x = loc.x-mx;
      drag.y = loc.y-my;
    }
  }

  void rollover(int mx, int my) {
    float d = dist(mx,my,loc.x,loc.y);
    if (d < r) {
      rollover = true;
    } 
    else {
      rollover = false;
    }
  }

  void stopDragging() {
    dragging = false;
  }

  void drag() {
    if (dragging) {
      loc.x = mouseX + drag.x;
      loc.y = mouseY + drag.y;
    }
  }


}


