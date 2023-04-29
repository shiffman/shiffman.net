// Daniel Shiffman
// The Nature of Code
// ITP, Spring 2006
// http://www.shiffman.net/

// A random walker object!

class Walker {
  float x,y;

  Walker() {
    x = width/2;
    y = height/2;
  }

  void render() {
    stroke(255);
    fill(100);
    rectMode(CENTER);
    rect(x,y,40,40);
  }

  // Randomly move up, down, left, right, or stay in one place
  void walk() {
    float vx = random(-2,2);
    float vy = random(-2,2);
    x += vx;
    y += vy;
    
    // Stay on the screen
    x = constrain(x,0,width-1);
    y = constrain(y,0,height-1);
  }
}
