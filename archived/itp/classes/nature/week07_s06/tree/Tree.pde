// Recursive Tree
// Daniel Shiffman <http://www.shiffman.net>

// Renders a simple tree-like structure via recursion
// Branching angle calculated as a function of horizontal mouse location

// Created 2 May 2005

float theta;   
void setup() {
  size(200,200);
  smooth();
}

void draw() {
  background(100);
  framerate(30);
  stroke(255);
  // Let's pick an angle 0 to 90 degrees based on the mouse position
  float a = (mouseX / (float) width) * 90f;
  // Convert it to radians
  theta = radians(a);
  // Start the tree from the bottom of the screen
  translate(width/2,height);
  // Draw a line 60 pixels
  line(0,0,0,-60);
  // Move to the end of that line
  translate(0,-60);
  // Start the recursive branching!
  branch(60);

}

void branch(float h) {
  // Each branch will be 2/3rds the size of the previous one
  h *= 0.66f;
  
  // All recursive functions must have an exit condition!!!!
  // Here, ours is when the length of the branch is 2 pixels or less
  if (h > 2) {
    pushMatrix();    // Save the current state of transformation (i.e. where are we now)
    rotate(theta);   // Rotate by theta
    line(0,0,0,-h);  // Draw the branch
    translate(0,-h); // Move to the end of the branch
    branch(h);       // Ok, now call myself to draw two new branches!!
    popMatrix();     // Whenever we get back here, we "pop" in order to restore the previous matrix state
    
    // Repeat the same thing, only branch off to the "left" this time!
    pushMatrix();
    rotate(-theta);
    line(0,0,0,-h);
    translate(0,-h);
    branch(h);
    popMatrix();
  }
}


