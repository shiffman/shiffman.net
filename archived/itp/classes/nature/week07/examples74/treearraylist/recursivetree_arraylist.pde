//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

//Recursive branching "structure" without an explicity recursive function
//Instead we have an ArrayList to hold onto N number of elements
//For every element in the ArrayList, we add 2 more elements, etc. (this is the recursion)


//an arraylist that will keep track of all current branches
ArrayList a;

void setup() {
  size(400,400);
  colorMode(RGB,255,255,255,100);
  background(0);
  smooth();
  
  //setup the arraylist and add one branch to it
  a = new ArrayList();
  //a branch has a starting location, a starting "velocity", and a starting "timer"
  Branch b = new Branch(new Vector3D(width/2,height),new Vector3D(0f,-0.5f),200f);
  //add to arraylist
  a.add(b);
}

void draw() {
  //let's stop when the arraylist gets too big
  if (a.size() < 1024) {
    //for every branch in the arraylist
    for (int i = a.size()-1; i >= 0; i--) {
      //get the branch, update and draw it
      Branch b = (Branch) a.get(i);
      b.update();
      b.render();
      //if it's ready to split
      if (b.timeToBranch()) {
        a.remove(i);             //delete it
        a.add(b.branch( 30f));   //add one going right
        a.add(b.branch(-25f));   //add one going left
      }
    }
  }
}

