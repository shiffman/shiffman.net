//Daniel Shiffman, Nature of Code, 3/29/2005
//http://stage.nyu.edu/nature

PFont f;
Population popul;
Button button;

void setup() {
  //*GENERAL SETUP STUFF*//
  size(780,200);
  colorMode(RGB,1.0f,1.0f,1.0f,1.0f);
  f = loadFont("CourierNewPS-BoldMT-14.vlw");
  smooth();
  int popmax = 10;
  float mutationRate = 0.05f;  // a pretty high mutation rate here, our population is so small we need to enforce variety
  //*create a population with a target phrase, mutation rate, and population max*//
  popul = new Population(mutationRate,popmax);
  //*a simple button class*//
  button = new Button(15,30,190,20, "evolve new generation");
}

void draw() {
  background(0);
  int mx = mouseX; int my = mouseY;
  //display the faces
  popul.display();
  popul.rollover(mx,my);
  //display some text
  textFont(f);
  textMode(ALIGN_LEFT);
  textSpace(SCREEN_SPACE);
  stroke(1); fill(1);
  text("Generation #:" + popul.getGenerations(),15,18);

  //display the button
  button.render();
  button.rollover(mx,my);

}

//if the button is clicked, evolve next generation
void mousePressed() {
  if (button.clicked(mouseX,mouseY)) {
    popul.naturalSelection();
    popul.generate();
  }
}

void mouseReleased() {
  button.released();
}
