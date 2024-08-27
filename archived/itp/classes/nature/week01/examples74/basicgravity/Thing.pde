
/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
class Thing {
  Vector2D loc;
  Vector2D vel;
  Vector2D acc;
  
  //The Constructor (called when the object is first created)
  Thing(Vector2D a, Vector2D v, Vector2D l) {
    acc = a;
    vel = v;
    loc = l;
  }

  //main function to operate object)
  void go() {
    update();
    render();
  }
  
  //function to update location
  void update() {
    if (loc.y() > height) {
      vel.setY(-vel.y());
      loc.setY(height);
    }
    vel.add(acc);
    loc.add(vel);
  }

  //function to display
  void render() {
    rectMode(CENTER);
    noStroke();
    fill(0,0,255);
    rect(loc.x(),loc.y(),20,20);

    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

}

