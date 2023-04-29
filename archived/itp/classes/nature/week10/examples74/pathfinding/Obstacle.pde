//Daniel Shiffman, Nature of Code, 3/29/2005
//http://stage.nyu.edu/nature

//A class for an obstacle, just a simple rectangle that is drawn
//and can check if a creature touches it

class Obstacle {

  Rectangle r;

  Obstacle(int x, int y, int w, int h) {
    r = new Rectangle(x,y,w,h);
  }

  void render() {
    noStroke();
    fill(100);
    rect(r.x,r.y,r.width,r.height);
  }

  boolean contains(Vector3D spot) {
    if (r.contains((int)spot.x(),(int)spot.y())) {
      return true;
    } else {
      return false;
    }
  }

}
