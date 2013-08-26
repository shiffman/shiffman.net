// Obstacle Avoidance
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009


class Obstacle {

  // Obstacle has location and radius
  PVector loc;
  float radius;
  int c;

  Obstacle(float x, float y, float r) {
    radius = r;
    loc = new PVector(x,y);
    c = color(225);
  }

  // Draw the obstacle
  void display() {
    stroke(0);
    fill(c);
    ellipse(loc.x,loc.y,radius*2,radius*2);
  }

  // Highlight it for debugging
  void highlight(boolean b) {
    if (b) c = color(255,0,0);
    else c = color(175);
  }

}





