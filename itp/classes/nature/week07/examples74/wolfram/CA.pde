//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

//a class to describe a wolfram basic cellular automata
//includes 1D array to store state of each cell, variable to keep track of current generation, 
//variable to describe size of each cell (in pixels)

class CA {

  int[] cells;
  int generation;
  int scl;

  int[] rules;

  CA(int[] r) {
    rules = r;
    scl = 1;
    cells = new int[width/scl];
    restart();
  }
  
   CA() {
    scl = 1;
    cells = new int[width/scl];
    randomize();
    restart();
  }
  
  //set the rules of the CA
  void setRules(int[] r) {
    rules = r;
  }
  
  //randomize the rules of the CA
  void randomize() {
    /**Let's make a random ruleset***/
    println();
    for (int i = 0; i < 8; i++) {
      rules[i] = int(random(2));
      print(rules[i]);
    }
  }
  
  //resent to generation 0
  void restart() {
    for (int i = 0; i < cells.length; i++) {
      cells[i] = 0;
    }
    cells[cells.length/2] = 1;    //we arbitrarily start with just the middle cell having a state of "1"
    generation = 0;
  }

  //the process of creating the new generation
  void generate() {
    //first we create an empty array for the new values
    int[] nextgen = new int[cells.length];
    //for every spot, determine new state by examing current state, and neighbor states
    for (int i = 1; i < cells.length-1; i++) {
      int left = cells[i-1];   //left neighbor state
      int me = cells[i];       //current state
      int right = cells[i+1];  //right neighbor state
      nextgen[i] = rules(left,me,right); //compute next generation value based on rules
    }
    //copy the array into current value
    cells = (int[]) nextgen.clone();
    generation++;
  }
  
  //this is the easy part, just draw the cells, fill 255 for '1', fill 0 for '0'
  void render() {
    for (int i = 0; i < cells.length; i++) {
      if (cells[i] == 1) fill(255);
      else               fill(0);
      noStroke();
      rect(i*scl,generation*scl, scl,scl);
    }
  }
  
  //implementing the Wolfram rules
  //could be improved and made more concise, but here we can explicitly see what is going on for each case
  int rules (int a, int b, int c) {
    if (a == 1 && b == 1 && c == 1) return rules[0];
    if (a == 1 && b == 1 && c == 0) return rules[1];
    if (a == 1 && b == 0 && c == 1) return rules[2];
    if (a == 1 && b == 0 && c == 0) return rules[3];
    if (a == 0 && b == 1 && c == 1) return rules[4];
    if (a == 0 && b == 1 && c == 0) return rules[5];
    if (a == 0 && b == 0 && c == 1) return rules[6];
    if (a == 0 && b == 0 && c == 0) return rules[7];
    return 0;
  }
  
  //the CA is completed if it reaches the bottom of the screen
  boolean finished() {
    if (generation > height/scl) {
       return true;
    } else {
       return false;
    }
  }
}
