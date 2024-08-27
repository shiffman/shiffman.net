// Crowd Path Following
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

// Via Reynolds: http://www.red3d.com/cwr/steer/CrowdPath.html

// Using this variable to decide whether to draw all the stuff
boolean debug = false;

PFont f;

// A path object (series of connected points)
Path path;

// Two boids
ArrayList boids;

void setup() {
  size(640,480);
  smooth();
  f = createFont("Georgia",12,true);

  // Call a function to generate new Path object
  newPath();

  // We are now making random boids and storing them in an ArrayList
  boids = new ArrayList();
  for (int i = 0; i < 120; i++) {
    newBoid(random(width),random(height));
  }
}

void draw() {
  background(255);
  // Display the path
  path.display();

  for (int i = 0; i < boids.size(); i++) {
    Boid boid = (Boid) boids.get(i);
    // Path following and separation are worked on in this function
    boid.applyForces(boids,path);
    // Call the generic run method (update, borders, display, etc.)
    boid.run();
  }

  // Instructions
  textFont(f);
  fill(0);
  text("Hit 'd' to toggle debugging lines.  Click the mouse to generate new boids.",10,height-16);
}

void newPath() {
  // A path is a series of connected points
  // A more sophisticated path might be a curve
  path = new Path();
  float offset = 60;
  path.addPoint(offset,offset);
  path.addPoint(width-offset,offset);
  path.addPoint(width-offset,height-offset);
  path.addPoint(width/2,height-offset*3);
  path.addPoint(offset,height-offset);
}

void newBoid(float x, float y) {
  float maxspeed = random(2,4);
  float maxforce = 0.3;
  boids.add(new Boid(new PVector(x,y),maxspeed,maxforce));
}

void keyPressed() {
  if (key == 'd') {
    debug = !debug;
  }
}

void mousePressed() {
  newBoid(mouseX,mouseY);
}



