//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

CA ca;   //an instance object to describe the Wolfram basic Cellular Automata

void setup() {
  size(320,240);
  background(0);
  int[] ruleset = {0,1,0,1,1,0,1,0};    //an initial rule system
  ca = new CA(ruleset);                 //initialize CA
}

void draw() {
  framerate(30);
  ca.render();    //draw the CA
  ca.generate();  //generate the next level
  
  if (ca.finished()) {   //if we're done, clear the screen, pick a new ruleset and restart
    background(0);
    ca.randomize();
    ca.restart();
  }
}

void mousePressed() {
  background(0);
  ca.randomize();
  ca.restart();
}

