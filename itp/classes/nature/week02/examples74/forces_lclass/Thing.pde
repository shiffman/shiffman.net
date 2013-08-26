
// Force = Mass * Acceleration
// Acceleration = Force / Mass

/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
/*now we add "mass" to this thing as well and a function to add forces*/

class Thing {
  Vector2D loc;
  Vector2D vel;
  Vector2D acc;
  float mass;
  float sz;
  float max_vel;
  float bounce = 1.0f; //how "elastic" is the object

  //The Constructor (called when the object is first created)
  Thing(Vector2D a, Vector2D v, Vector2D l, float m_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    mass = m_;
    sz = mass;
    max_vel = 20.0f;
  }

  /*we should probably make getters and setters for all of our instance variables
  but for this example, i'm just making the ones we are using*/
  Vector2D getLoc() {
    return loc;
  }

  Vector2D getVel() {
    return vel;
  }
  float getMass() {
    return mass;
  }

  void add_force(Vector2D force) {
    force.div(mass);
    acc.add(force);
    if (showVectors) {
      drawVector(force,loc,1000);
    }    
  }

  void go() {
    update();
    borders();
    render();
  }

  //function to update location
  void update() {
    vel.add(acc);
    vel.limit(max_vel);
    loc.add(vel);
    acc.setXY(0.0f,0.0f);
  }

  void borders() {
    if (loc.y() > height-sz/2) {
      loc.setY(height-sz/2);
      vel.setY(-bounce*vel.y());
    }
    if ((loc.x() > width-sz/2) || (loc.x() < sz/2)) {
      vel.setX(-bounce*vel.x());
    }    
    //if (loc.x() < 0)     loc.setX(width);
    //if (loc.x() > width) loc.setX(0);
  }

  //function to display
  void render() {
    rectMode(CENTER);
    noStroke();
    fill(0,100,255,75);
    rect(loc.x(),loc.y(),20,20);

    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

}

