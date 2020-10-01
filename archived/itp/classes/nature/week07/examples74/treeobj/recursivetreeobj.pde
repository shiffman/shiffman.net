//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

/*randomly draws trees at random locations on the screen
  we achieve this by not erasing the background, but could
  get more interesting results by using an ArrayList.
  This program excessively uses random(), and could easily
  be improved*/


void setup() {
  size(200,200);
  background(0);
  smooth();
}

void draw() {
  if (random(1) < 0.001f) {
    Tree t = new Tree(random(width),random(height));
    t.render();
  }
}

void mousePressed() {
  Tree t = new Tree(mouseX,mouseY);
  t.render();
}

