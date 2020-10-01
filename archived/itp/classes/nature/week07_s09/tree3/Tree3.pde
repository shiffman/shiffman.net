// Recursive Tree (w/ ArrayList)
// Daniel Shiffman <http://www.shiffman.net>

// Recursive branching "structure" without an explicitly recursive function
// Instead we have an ArrayList to hold onto N number of elements
// For every element in the ArrayList, we add 2 more elements, etc. (this is the recursion)

// An arraylist that will keep track of all current branches
ArrayList a;
ArrayList leaves;

void setup() {
  size(200,200);
  background(255);
  smooth();

  // Setup the arraylist and add one branch to it
  a = new ArrayList();
  leaves = new ArrayList();
  // A branch has a starting location, a starting "velocity", and a starting "timer" 
  Branch b = new Branch(new PVector(width/2,height),new PVector(0f,-0.5),100);
  // Add to arraylist
  a.add(b);
}

void draw() {
  background(255);

  // Let's stop when the arraylist gets too big
  // For every branch in the arraylist
  for (int i = a.size()-1; i >= 0; i--) {
    // Get the branch, update and draw it
    Branch b = (Branch) a.get(i);
    b.update();
    b.render();
    // If it's ready to split
    if (b.timeToBranch()) {
      if (a.size() < 1024) {
        //a.remove(i);             // Delete it
        a.add(b.branch( 30f));   // Add one going right
        a.add(b.branch(-25f));   // Add one going left
      } 
      else {
        leaves.add(new Leaf(b.end));
      }
    }
  }
  
  for (int i = 0; i < leaves.size(); i++) {
     Leaf leaf = (Leaf) leaves.get(i);
     leaf.display(); 
  }

}




