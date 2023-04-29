// Path Following
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Via Reynolds: // http://www.red3d.com/cwr/steer/PathFollow.html

// Using this variable to decide whether to draw all the stuff
boolean debug = true;

PFont f;

// A path object (series of connected points)
Path path;

// Two boids
Boid boid0;
Boid boid1;

void setup() {
  size(640,320);
  smooth();
  f = createFont("Georgia",12,true);

  // Call a function to generate new Path object
  newPath();

  //Each boid has different maxspeed and maxforce for demo purposes
  boid0 = new Boid(new PVector(0,height/2),3f,0.1f);
  boid1 = new Boid(new PVector(0,height/2),5f,1f);
}

void draw() {
  background(255);
  // Display the path
  path.display();
  // The boids follow the path
  boid0.follow(path);
  boid1.follow(path);
  // Call the generic run method (update, borders, display, etc.)
  boid0.run();
  boid1.run();

  // Instructions
  textFont(f);
  fill(0);
  text("Hit space bar to toggle debugging lines.\nClick the mouse to generate a new path.",10,height-30);
}

void newPath() {
  // A path is a series of connected points
  // A more sophisticated path might be a curve
  path = new Path();
  path.addPoint(0,height/2);
  path.addPoint(random(0,width/2),random(0,height));
  path.addPoint(random(width/2,width),random(0,height));
  path.addPoint(width,height/2);
}

public void keyPressed() {
  debug = !debug;
}

public void mousePressed() {
  newPath();
}



