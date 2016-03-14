// G ---> gravitational constant
// m1 --> mass of object #1
// m2 --> mass of object #2
// d ---> distance between objects
// F = (G*m1*m2)/(d*d)

class Attractor {
  float mass;    //Mass, tied to size
  float G;       //Gravitational Constant
  Vector2D loc;  //Location
  boolean dragging = false;
  Vector2D drag;  //holds the offset for when object is clicked on

  Attractor(Vector2D l_,float m_, float g_) {
    loc = l_;
    mass = m_;
    G = g_;
    drag = new Vector2D(0.0,0.0);
  }

  void go() {
    render();
    drag();
  }

  Vector2D calcGravForce(Thing t) {
    Vector2D dir = Vector2D.sub(loc,t.getLoc());      //calculate direction of force
    float d = dir.magnitude();                        //distance between objects
    dir.normalize();                                  //normalize vector (distance doesn't matter here, we just want this vector for direction)
    float force = (G * mass * t.getMass()) / (d * d); //calculate gravitional force magnitude
    dir.mult(force);                                  //get force vector --> magnitude * direction
    return dir;
  }

  //function to display
  void render() {
    rectMode(CENTER);
    noStroke();
    if (dragging) {
      fill(255,0,0);
    } else {
      fill(0,255,0);
    }
    ellipse(loc.x(),loc.y(),mass*2,mass*2);
  }

  /*THE FUNCTIONS BELOW ARE FOR MOUSE INTERACTION*/
  void clicked(int mx, int my) {
    float d = dist(mx,my,loc.x(),loc.y());
    if (d < mass) {
      dragging = true;
      drag.setX(loc.x()-mx);
      drag.setY(loc.y()-my);
    }
  }

  void stopDragging() {
    dragging = false;
  }

  void drag() {
    if (dragging) {
      loc.setX(mouseX + drag.x());
      loc.setY(mouseY + drag.y());
    }
  }

}

