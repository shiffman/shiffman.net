
/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
public class Thing {
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
    maxvel = 15;
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
  void go(boolean show,BApplet b) {
    update();
    borders(b);
    render(b,show);
  }

  //function to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
    //limit speed to max
    if (vel.magnitude() > maxvel) {
      vel.normalize();
      vel.mult(maxvel);
    }
  }

  void borders(BApplet b) {
    if ((loc.y() > b.height) || (loc.y() < 0))  {
      vel.setY(-vel.y());
    }
    if ((loc.x() < 0) || (loc.x() > b.width)) {
      vel.setX(-vel.x());
    }
  }

  //function to display
  void render(BApplet b, boolean show) {
    b.rectMode(b.CENTER_DIAMETER);
    b.noStroke();
    b.fill(0,0,255);
    b.rect(loc.x(),loc.y(),20,20);

    if (show) {
      //draws both velocity and acceleration vectors, this could be improved to differentiate by color, etc.
      vel.drawVector(b,loc,5);
      acc.drawVector(b,loc,25);
    }
  }

}