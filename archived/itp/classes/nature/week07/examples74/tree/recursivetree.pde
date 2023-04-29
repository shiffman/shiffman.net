//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

float theta;   //angle at which each branch turns (+ for "right", - for "left")

void setup() {

  size(200,200);
  smooth();
}

void draw() {
  background(0);
  stroke(255);
  //let's pick an angle 0 to 90 degrees based on the mouse position
  float a = (mouseX / (float) width) * 90f;
  //convert it to radians
  theta = radians(a);
  //start the tree from the bottom of the screen
  translate(width/2,height);
  //draw a line 60 pixels
  line(0,0,0,-60);
  //move to the end of that line
  translate(0,-60);
  //start the recursive branching!
  branch(60);

}

void branch(float h) {
  //every branch will be 2/3rds the size of the previous one
  h *= 0.66f;
  
  //All recursive functions must have an exit condition!!!!
  //here, ours is when the length of the branch is 2 pixels or less
  if (h > 2) {
    push();          //save the current state of transformation (i.e. where are we now)
    rotate(theta);   //rotate by theta
    line(0,0,0,-h);  //draw the branch
    translate(0,-h); //move to the end of the branch
    branch(h);       //ok, now call myself to draw two new branches!!
    pop();           //whenever we get back here, we "pop" in order to restore the location we were at previously
    
    //repeat the same thing, only branch off to the "left" this time!
    push();
    rotate(-theta);
    line(0,0,0,-h);
    translate(0,-h);
    branch(h);
    pop();
  }
}
