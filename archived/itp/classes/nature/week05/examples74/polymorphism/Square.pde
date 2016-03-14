//A subclass of shape

class Square extends Shape {

  //inherits all instance variables from parent
  //we could add variables for only Square here if we want to

  Square(float x_, float y_, float r_) {
    super(x_,y_,r_);
  }

  //inherits jiggle method from parent
 
  //overwrites the render method from parent
  void render() {
    rectMode(CENTER);
    fill(255);
    noStroke();
    rect(x,y,r,r);
  }
}
