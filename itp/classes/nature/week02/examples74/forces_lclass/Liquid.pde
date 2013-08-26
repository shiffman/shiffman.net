class Liquid {
  float start;          //liquid location
  float h;              //liquid height
  float c;              //drag coefficient

  Liquid(float start_, float h_, float c_) {
    start = start_;
    h = h_;
    c = c_;
  }

  boolean intersect(Thing t) {
    if ((t.getLoc().y() > start) && (t.getLoc().y() < start + h)) {
      return true;
    } else {
      return false;
    }

  }

  /*ADD DRAG TO THING*/
  //test if thing is in liquid
  Vector2D calcDrag(Thing t) {
    //drag is always calculated as force vector in the negative direction of velocity
    Vector2D thingVel = t.getVel();             //velocity of our thing
    Vector2D force = Vector2D.mult(thingVel,c); //following the formula above
    return force;
  }

  void render() {
    noStroke();
    fill(0,0,255,50);
    rectMode(CORNER);
    rect(0,start,width,h);
  }

}
