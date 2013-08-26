
/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
class Thing {
  //*using private now to encapsulate data*//
  private Vector2D loc;
  private Vector2D vel;
  private Vector2D acc;
  private float maxvel;

  //The Constructor (called when the object is first created)
  Thing(Vector2D a, Vector2D v, Vector2D l) {
    acc = a;
    vel = v;
    loc = l;
    maxvel = 7;
  }

  /*Add functions to our thing object to access the location, velocity and acceleration from outside the class*/
  Vector2D getVel() {
    return vel;
  }

  Vector2D getAcc() {
    return acc;
  }

  Vector2D getLoc() {
    return loc;
  }

  void setLoc(Vector2D v) {
    loc = v;
  }

  void setVel(Vector2D v) {
    vel = v;
  }

  void setAcc(Vector2D v) {
    acc = v;
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
    //limit speed to max
    vel.limit(maxvel);
    /*if (vel.magnitude() > maxvel) {
      vel.normalize();
      vel.mult(maxvel);
    }*/
  }

  void borders() {
    if ((loc.y() > height) || (loc.y() < 0))  {
      vel.setY(-vel.y());
    }
    if ((loc.x() < 0) || (loc.x() > width)) {
      vel.setX(-vel.x());
    }
  }

  //function to display
  void render() {
    rectMode(CENTER);
    noStroke();
    fill(0,0,255);
    rect(loc.x(),loc.y(),20,20);

    if (showVectors) {
      //draws both velocity and acceleration vectors, this could be improved to differentiate by color, etc.
      drawVector(vel,loc,10);
      drawVector(acc,loc,50);
    }
  }

}
