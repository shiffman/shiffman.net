/*A class to describe a one singular particle*/
//this could is really basic, just like our "thing" class in earlier examples
//here we could make more interesting particles using images, different shapes, more advanced behaviors, etc.

class Particle {
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float r;
  float timer;

  //The Constructor (called when the object is first created)
  Particle(Vector3D a, Vector3D v, Vector3D l, float r_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    r = r_;
    timer = 100.0;
  }
  
  //look, we can have more than one constructor!
  Particle(Vector3D l) {
    acc = new Vector3D(0,0.01,0);
    vel = new Vector3D(random(-1,1),random(-2,0),0);
    loc = l.copy();
    r = 10.0;
    timer = 100.0;
  }


  void run() {
    update();
    timer();
    render();
  }

  //function to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
  }
  
  void timer() {
    timer -= 1.0;
  }

  //function to display
  void render() {
    ellipseMode(CENTER);
    noStroke();
    fill(0,100,255,timer);
    ellipse(loc.x(),loc.y(),r,r);
  }
  
  //if the timer has reached 0 this function
  //will tell us we don't need this particle anymore
  boolean dead() {
    if (timer <= 0.0) {
      return true;
    } else {
      return false;
    }
  }
}


