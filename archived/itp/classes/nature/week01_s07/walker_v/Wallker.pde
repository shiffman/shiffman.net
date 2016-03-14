// Daniel Shiffman
// The Nature of Code
// ITP, Spring 2006
// http://www.shiffman.net/

// A random walker object!

class Walker {
  Vector3D loc;

  Walker() {
    loc = new Vector3D(width/2,height/2);
  }

  void render() {
    stroke(255);
    fill(100);
    rectMode(CENTER);
    rect(loc.x,loc.y,40,40);
  }

  // Randomly move up, down, left, right, or stay in one place
  void walk() {
    Vector3D vel = new Vector3D(random(-2,2),random(-2,2));
    loc.add(vel);
    
    // Stay on the screen
    loc.x = constrain(loc.x,0,width-1);
    loc.y = constrain(loc.y,0,height-1);
  }
}
