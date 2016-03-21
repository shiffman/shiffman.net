Thing t;
boolean showVectors = true;

void setup() {
  size(320,480);
  background(0);
  colorMode(RGB,255,255,255,100);
  Vector2D a = new Vector2D(0.0,0.0);
  Vector2D v = new Vector2D(0.0,0.0);
  Vector2D l = new Vector2D(10,0);
  t = new Thing(a,v,l,1.5);
}

void draw() {
  background(0);

  /*ADD GRAVITY TO THING*/
  /*This isn't "real" gravity, I am making up a force vector on my own*/
  Vector2D grav = new Vector2D(0,0.1);
  t.add_force(grav);

  /*ADD WIND TO THING*/
  /*Again, just making up a force vector*/
  Vector2D wind = new Vector2D(0.02,-0.05);
  t.add_force(wind);

  /*ADD DRAG TO THING*/
  float liq_start    = 150; //liquid location
  float liq_height   = 150; //liquid height
  //test if thing is in liquid
  if ((t.getLoc().y() > liq_start) && (t.getLoc().y() < liq_start + liq_height)) {
    //drag is always calculated as force vector in the negative direction of velocity
    float c = -0.02f;                           //drag coefficient
    Vector2D thingVel = t.getVel();             //velocity of our thing
    Vector2D force = Vector2D.mult(thingVel,c); //following the formula above
    t.add_force(force);                         //adding the force to our object, which will ultimately affect its acceleration
  }

  t.go();

  //Draw the "liquid"
  /*Note it would probably be a good idea to make a liquid class and take a lot of this code out of the main loop*/
  noStroke();
  fill(0,0,255,50);
  rectMode(CORNER);
  rect(0,liq_start,width,liq_height);
}


/*Our draw vector function*/
/*It might be nice to improve this to have different vectors colored differently*/
void drawVector(Vector2D v, Vector2D loc, float scayl) {
  push();
  float arrowsize = 10;
  //translate to location to render vector
  translate(loc.x(),loc.y());
  stroke(255);
  //call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading());
  //calculate length of vector & scale it to be bigger or smaller if necessary
  float len = v.magnitude()*scayl;
  //draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  pop();
}

void mousePressed() {
  showVectors = !showVectors;
}

