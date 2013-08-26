// Recursive Tree (w/ ArrayList)
// Daniel Shiffman <http://www.shiffman.net>

// Recursive branching "structure" without an explicitly recursive function
// Instead we have an ArrayList to hold onto N number of elements
// For every element in the ArrayList, we add 2 more elements, etc. (this is the recursion)

// Created 2 May 2005

// An arraylist that will keep track of all current branches
ArrayList a;

void setup() {
  size(200,200);
  framerate(30);
  colorMode(RGB,255,255,255,100);
  background(100);
  smooth();
  
  // Setup the arraylist and add one branch to it
  a = new ArrayList();
  // A branch has a starting location, a starting "velocity", and a starting "timer" 
  Branch b = new Branch(new Vector3D(width/2,height),new Vector3D(0f,-0.5f),100f);
  // Add to arraylist
  a.add(b);
}

void draw() {
  // Try erasing the background to see how it works
  // background(100);
  
  // Let's stop when the arraylist gets too big
  if (a.size() < 1024) {
    // For every branch in the arraylist
    for (int i = a.size()-1; i >= 0; i--) {
      // Get the branch, update and draw it
      Branch b = (Branch) a.get(i);
      b.update();
      b.render();
      // If it's ready to split
      if (b.timeToBranch()) {
        a.remove(i);             // Delete it
        a.add(b.branch( 30f));   // Add one going right
        a.add(b.branch(-25f));   // Add one going left
      }
    }
  }
}


