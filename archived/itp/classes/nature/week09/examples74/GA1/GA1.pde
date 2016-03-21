PFont f48,f18;
String phrase;
int popmax;
float mutationRate;
Population popul;

void setup() {
  size(500,160);
  f48 = loadFont("Georgia-48.vlw");
  f18 = loadFont("ArialMT-18.vlw");
  phrase = "To be or not to be.";
  popmax = 150;
  mutationRate = 0.01f;

  //*create a population with a target phrase, mutation rate, and population max*//
  popul = new Population(phrase,mutationRate,popmax);
}

void draw() {
  popul.naturalSelection();
  popul.generate();
  popul.calcFitness();
  displayInfo();

  if (popul.finished()) {
    noLoop();
  }
}

void displayInfo() {
  background(0);
  //*Display current status of population*//
  String answer = popul.getBest();
  textFont(f48,48);
  textMode(ALIGN_LEFT);
  stroke(255);
  text(answer,10,75);
  textFont(f18,18);
  text("total generations: " + popul.getGenerations(),10,120);
  text("average fitness: " + popul.getAverageFitness(),220,120);
  text("total population: " + popmax,10,140);
  text("mutation rate: " + int(mutationRate * 100) + "%",220,140);
}
