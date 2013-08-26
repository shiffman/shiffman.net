int MAX = 5;
Thing[] t = new Thing[5];   //identical to the last program, but using an array instead
Attractor a;                //here we are creating an entirely separate object that exerts gravitational pull on other objects
boolean showVectors = true;

void setup() {
  size(480,360);
  background(0);
  colorMode(RGB,255,255,255,100);
  for (int i = 0; i < t.length; i++) {
    Vector2D ac = new Vector2D(0.0,0.0);
    Vector2D ve = new Vector2D(random(-1,1),random(-1,1));
    Vector2D lo = new Vector2D(random(width),random(height));
    t[i] = new Thing(ac,ve,lo,random(8,16));
  }
  //create an attractive body
  a = new Attractor(new Vector2D(width/2,height/2),20,0.4);
  smooth();
}

void draw() {
  background(0);
  for (int i = 0; i < t.length; i++) {
    //calculate the force, the "attractor" exerts on the "thing"
    Vector2D f = a.calcGravForce(t[i]);
    //apply that force to the thing
    t[i].add_force(f);
    //update and render the positions of both objects
    t[i].go();
  }
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

