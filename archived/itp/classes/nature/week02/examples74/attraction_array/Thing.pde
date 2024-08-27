
// Force = Mass * Acceleration
// Acceleration = Force / Mass

/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
/*now we add "mass" to this thing as well and a function to add forces*/

class Thing {
  Vector2D loc;
  Vector2D vel;
  Vector2D acc;
  float mass;
  float max_vel;

  //The Constructor (called when the object is first created)
  Thing(Vector2D a, Vector2D v, Vector2D l, float m_) {
    acc = a;
    vel = v;
    loc = l;
    mass = m_;
    max_vel = 5.0f;
  }

  /*we should probably make getters and setters for all of our instance variables
  but for this example, i'm just making the ones we are using*/
  Vector2D getLoc() {
    return loc;
  }

  float getMass() {
    return mass;
  }

  void add_force(Vector2D force) {
    acc.add(force);
  }

  void go() {
    update();
    render();
  }

  //function to update location
  void update() {
    vel.add(acc);
    vel.limit(max_vel);
    loc.add(vel);
    if (showVectors) drawVector(acc,loc,1000);        //we have to draw acceleration before we set it to 0    
    acc.setX(0.0f);
    acc.setY(0.0f);
  }

  //function to display
  void render() {
    if (showVectors) drawVector(vel,loc,50);
    rectMode(CENTER);
    noStroke();
    fill(0,0,255,75);
    rect(loc.x(),loc.y(),mass*2,mass*2);
  }

}

