//a simple class to keep track of one point
//similar to our Vector3D class, but without all the fancy stuff. . .

class Point {
  float x,y;
  
  Point(float x_, float y_) {
    x = x_;
    y = y_;
  }
  
  Point copy() {
    return new Point(x,y);
  }
}
