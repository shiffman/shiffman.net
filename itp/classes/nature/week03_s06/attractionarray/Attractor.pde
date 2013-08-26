// Attraction
// Daniel Shiffman <http://www.shiffman.net>

// A class for a draggable attractive body in our world

class Attractor {
  float mass;    // Mass, tied to size
  float G;       // Gravitational Constant
  Vector3D loc;  // Location
  boolean dragging = false; // Is the object being dragged?
  boolean rollover = false; // Is the mouse over the ellipse?
  Vector3D drag;  // holds the offset for when object is clicked on

  Attractor(Vector3D l_,float m_, float g_) {
    loc = l_;
    mass = m_;
    G = g_;
    drag = new Vector3D(0.0,0.0);
  }

  void go() {
    render();
    drag();
  }

  Vector3D calcGravForce(Thing t) {
    Vector3D dir = Vector3D.sub(loc,t.getLoc());      // Calculate direction of force
    float d = dir.magnitude();                        // Distance between objects
    d = constrain(d,5.0f,25.0f);                      // Limiting the distance to eliminate "extreme" results for very close or very far objects
    dir.normalize();                                  // Normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = (G * mass * t.getMass()) / (d * d); // Calculate gravitional force magnitude
    dir.mult(force);                                  // Get force vector --> magnitude * direction
    return dir;
  }

  // Method to display
  void render() {
    ellipseMode(CENTER);
    noStroke();
    if (dragging) fill (255,90);
    else if (rollover) fill(200,90);
    else fill(100,90);
    ellipse(loc.x,loc.y,mass*2,mass*2);
  }

  // The methods below are for mouse interaction
  void clicked(int mx, int my) {
    float d = dist(mx,my,loc.x,loc.y);
    if (d < mass) {
      dragging = true;
      drag.x = loc.x-mx;
      drag.y = loc.y-my;
    }
  }

  void rollover(int mx, int my) {
    float d = dist(mx,my,loc.x,loc.y);
    if (d < mass) {
      rollover = true;
    } else {
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

