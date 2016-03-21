class Circle extends Shape {

  //inherits all instance variables from parent + adding one
  color c;

  Circle(float x_, float y_, float r_, color c_) {
    super(x_,y_,r_);  // call the parent constructor
    c = c_;           // also deal with this new instance variable
  }

  //call the parent jiggle, but do some more stuff too
  void jiggle() {
    super.jiggle();
    r += random(-1,1);
    r = constrain(r,0,100);
  }

   //adds a new render method
  void render() {
    ellipseMode(CENTER);
    fill(c);
    noStroke();
    ellipse(x,y,r,r);
  }
}
