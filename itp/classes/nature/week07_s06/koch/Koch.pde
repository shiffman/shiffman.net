// Koch Curve
// Daniel Shiffman <http://www.shiffman.net>

// Renders a simple fractal, the Koch snowflake
// Each recursive level drawn in sequence

// Created 2 May 2005

KochFractal k;

void setup() {
  size(200,200);
  background(0);
  framerate(1);  // Animate slowly
  k = new KochFractal();
  smooth();
}

void draw() {
  background(100);
  // Draws the snowflake!
  k.render();
  // Iterate
  k.nextLevel();
  // Let's not do it more than 5 times. . .
  if (k.getCount() > 5) {
    k.restart();
  }

}

