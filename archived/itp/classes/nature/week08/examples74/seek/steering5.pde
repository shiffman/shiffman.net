
//An instance of Boid
Boid b;

void setup() {
  size(600,400);
  colorMode(RGB,255,255,255,100);
  b = new Boid(new Vector3D(width/2,height/2),5.0f,0.1f);
  smooth();
}

void draw() {
  background(0);
  //Draw an ellipse at the mouse location
  int mx = mouseX;
  int my = mouseY;
  fill(100);
  noStroke();
  ellipse(mx,my,30,30);
  
  //call some behaviors on our agent
  //b.wander();
  b.seek(new Vector3D(mx,my));
  //b.arrive(new Vector3D(mx,my));
  b.run();
}
