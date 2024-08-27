
class Branch {
  //System of "branches"
  //each has a location, velocity, and timer 
  //we could implement this same idea with different data
  Vector3D loc;
  Vector3D vel;
  float timer;
  float timerstart;

  Branch(Vector3D l, Vector3D v, float n) {
    loc = l.copy();
    vel = v.copy();
    timerstart = n;
    timer = timerstart;
  }
  
  //move location
  void update() {
    loc.add(vel);
  }
  
  //draw a dot at location
  void render() {
    fill(0,0,255,50);
    noStroke();
    ellipseMode(CENTER);
    ellipse(loc.x(),loc.y(),4,4);
  }
  
  //did the timer run out?
  boolean timeToBranch() {
    timer--;
    if (timer < 0) {
      return true;
    } else {
      return false;
    }
  }

  //create a new branch at the current location, but change direction by an angle in degrees
  Branch branch(float angle) {
    //what is my current angle
    float theta = vel.heading2D();
    //what is my current speed
    float mag = vel.magnitude();
    //turn me
    theta += radians(angle);
    //look, polar coordinates to cartesian!!
    Vector3D newvel = new Vector3D(mag*cos(theta),mag*sin(theta));
    //return a new Branch
    return new Branch(loc,newvel,timerstart*0.66f);
  }
  
}
