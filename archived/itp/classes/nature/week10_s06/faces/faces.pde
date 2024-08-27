// Interactive Selection
// http://www.genarts.com/karl/papers/siggraph91.html
// Daniel Shiffman <http://www.shiffman.net>

// Created 2 May 2005

PFont f;
Population popul;
Button button;

void setup() {
  size(780,200);
  colorMode(RGB,1.0f,1.0f,1.0f,1.0f);
  f = loadFont("GillSans-12.vlw");
  smooth();
  int popmax = 10;
  float mutationRate = 0.05f;  // A pretty high mutation rate here, our population is rather small we need to enforce variety
  // Create a population with a target phrase, mutation rate, and population max
  popul = new Population(mutationRate,popmax);
  // A simple button class
  button = new Button(15,30,190,20, "evolve new generation");
}

void draw() {
  background(0.2);
  int mx = mouseX; int my = mouseY;
  // Display the faces
  popul.display();
  popul.rollover(mx,my);
  // Display some text
  textFont(f);
  textAlign(LEFT);
  stroke(1); fill(1);
  text("Generation #:" + popul.getGenerations(),15,18);

  // Display the button
  button.render();
  button.rollover(mx,my);

}

// If the button is clicked, evolve next generation
void mousePressed() {
  if (button.clicked(mouseX,mouseY)) {
    popul.naturalSelection();
    popul.generate();
  }
}

void mouseReleased() {
  button.released();
}
