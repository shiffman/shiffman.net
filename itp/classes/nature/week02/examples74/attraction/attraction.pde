Thing t;
Attractor a;                //here we are creating an entirely separate object that exerts gravitational pull on other objects
boolean showVectors = true;

void setup() {
  size(320,240);
  background(0);

  Vector2D ac = new Vector2D(0.0,0.0);
  Vector2D ve = new Vector2D(0.0,-0.2);
  Vector2D lo = new Vector2D(300,200);
  //create new thing with some initial settings
  t = new Thing(ac,ve,lo,10);
  //create an attractive body
  a = new Attractor(new Vector2D(width/2,height/2),10,0.2);
}

void draw() {
  background(0);
  //calculate the force, the "attractor" exerts on the "thing"
  Vector2D f = a.calcGravForce(t);
  //apply that force to the thing
  t.add_force(f);
  //update and render the positions of both objects
  t.go();
  a.go();
}

void mousePressed() {
  a.clicked(mouseX,mouseY);
}

void mouseReleased() {
  a.stopDragging();
}

void keyPressed() {
  showVectors = !showVectors;
}


