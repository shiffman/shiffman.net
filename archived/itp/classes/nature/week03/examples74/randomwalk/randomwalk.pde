Walker w;

void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,255);
  w = new Walker();

}

void draw() {
  noStroke();
  fill(0,0,0,1);
  rect(0,0,width,height);

  w.walk();
  w.render();
}
