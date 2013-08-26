Thing t;
Liquid liq;
boolean showVectors = true;

void setup() {
  size(320,480);
  background(0);
  colorMode(RGB,255,255,255,100);
  Vector2D a = new Vector2D(0.0,0.0);
  Vector2D v = new Vector2D(0.0,0.0);
  Vector2D l = new Vector2D(width/2,0);
  t = new Thing(a,v,l,1.5);
  liq = new Liquid(300,100,-0.02f);
}

void draw() {
  background(0);
  /*ADD GRAVITY TO THING*/
  /*This isn't "real" gravity, I am making up a force vector on my own*/
  Vector2D grav = new Vector2D(0,0.2);
  t.add_force(grav);
  if (liq.intersect(t)) {
     Vector2D f = liq.calcDrag(t);
     t.add_force(f);
  }

  liq.render();
  t.go();
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

