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
    borders();
    render();
  }
  
  //function to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
  }
  
  void borders() {
    if (loc.x() > width ) loc.setX(0     );
    if (loc.x() < 0     ) loc.setX(width );
    if (loc.y() > height) loc.setY(0     );
    if (loc.x() < 0     ) loc.setY(height);
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



