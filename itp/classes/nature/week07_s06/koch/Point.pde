// Koch Curve
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe an X,Y coordinate 

// Created 2 May 2005

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
