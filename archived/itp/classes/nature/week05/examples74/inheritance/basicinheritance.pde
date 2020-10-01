
Square s;
Circle c;

void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  smooth();
  
  s = new Square(75,75,10);
  c = new Circle(125,125,20,color(0,0,200));
}

void draw() {
  background(0);
  
  c.jiggle();
  s.jiggle();
   
  c.render();
  s.render();
}

