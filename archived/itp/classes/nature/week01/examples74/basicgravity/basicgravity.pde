Thing t;

//this may prove useful later depending on whether or not we want to display vectors
boolean showVectors = true;

void setup() {
  size(200,480);
  background(0);

  Vector2D a = new Vector2D(0.0,0.125);
  Vector2D v = new Vector2D(0.0,0.0);
  Vector2D l = new Vector2D(width/2,0);
  t = new Thing(a,v,l);
}

void draw() {
  background(0);
  t.go();
}




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


