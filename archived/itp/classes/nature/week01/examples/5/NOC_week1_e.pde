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

void loop() {
  background(0);
  t.go(showVectors,this);
  if (mousePressed) {
    //compute direction vector between mouse and object location
    //3 steps -- (1) Get Mouse Location, (2) Get Difference Vector, (3) Normalize difference vector
    Vector2D m = new Vector2D(mouseX,mouseY);
    Vector2D diff = Vector2D.sub(m,t.getLoc());
    diff.normalize();
    float factor = 2.0;
    diff.mult(factor);
    //object accelerates towards mouse
    t.setAcc(diff);
  } else {
    t.setAcc(new Vector2D(0,0));
  }
}

void keyPressed() {
  showVectors = !showVectors;
}
