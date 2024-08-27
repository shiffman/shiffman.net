Pendulum p;

void setup() {
  size(400,400);
  background(0);
  colorMode(RGB,255,255,255,100);
  
  //make a new Pendulum with an origin location and armlength
  p = new Pendulum(new Vector2D(width/2,height/2),150.0f);
  smooth();
}

void draw() {
  background(0);
  p.go();
}

void mousePressed() {
  p.clicked(mouseX,mouseY);
}

void mouseReleased() {
  p.stopDragging();
}




