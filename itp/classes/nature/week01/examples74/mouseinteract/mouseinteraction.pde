Thing t;

//this may prove useful later depending on whether or not we want to display vectors
boolean showVectors = true;

void setup() {
  size(640,480);
  background(0);
  Vector2D v1 = new Vector2D(0,0);
  Vector2D v2 = new Vector2D(0,0);
  Vector2D v3 = new Vector2D(width/2,height/2);
  t = new Thing(v1,v2,v3);
}


void draw() {
  background(0);
  t.go();
  if (mousePressed) {
    //compute direction vector between mouse and object location
    //3 steps -- (1) Get Mouse Location, (2) Get Difference Vector, (3) Normalize difference vector
    Vector2D m = new Vector2D(mouseX,mouseY);
    Vector2D diff = Vector2D.sub(m,t.getLoc());
    diff.normalize();
    float factor = 1.0;
    diff.mult(factor);
    //object accelerates towards mouse
    t.setAcc(diff);
  } else {
    t.setAcc(new Vector2D(0,0));
  }
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

void keyPressed() {
  showVectors = !showVectors;
}


