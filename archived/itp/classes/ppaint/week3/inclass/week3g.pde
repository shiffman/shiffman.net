
float x = 0.0f;

void setup() {
  size(100,100);
}

void loop() {
  background(100);
  noStroke();
  fill(255);
  rectMode(CENTER_DIAMETER);
  x += 0.01f;
  translate(width/2,height/2);
  rotate(x);
  rect(0,0,50,50);
}

