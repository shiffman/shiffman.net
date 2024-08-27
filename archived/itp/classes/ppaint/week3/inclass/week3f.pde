
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
  rotate(x);
  rect(width/2,height/2,50,50);
}

