//**Our parent class for all shapes****/

class Shape {
  float x;
  float y;
  float r;

  Shape(float x_, float y_, float r_) {
    x = x_;
    y = y_;
    r = r_;
  }

  void jiggle() {
    x += random(-1,1);
    y += random(-1,1);
  }

}

