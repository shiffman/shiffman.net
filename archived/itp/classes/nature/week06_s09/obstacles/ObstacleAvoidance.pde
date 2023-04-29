// Obstacle Avoidance
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009


// Using this variable to decide whether to draw all the stuff
boolean debug = true;

PFont f;

// A bunch of obstacles
ArrayList obstacles;
// A bunch of boids
ArrayList boids;

void setup() {
  size(600,400);
  smooth();
  f = createFont("Georgia",12,true);

  obstacles = new ArrayList();
  boids = new ArrayList();

  // A little algorithm to pick a bunch of random obstacles that don't overlap
  for (int i = 0; i < 100; i++) {
    float x = random(width);
    float y = random(height);
    float r = random(50-i/2,50);
    boolean ok = true;
    for (int j = 0; j < obstacles.size(); j++) {
      Obstacle o = (Obstacle) obstacles.get(j);
      if (dist(x,y,o.loc.x,o.loc.y) < o.radius + r + 20) {
        ok = false;
      }
    }
    if (ok) obstacles.add(new Obstacle(x,y,r));
  }

  // Starting with three boids
  boids.add(new Boid(new PVector(random(width),random(height)),3f,0.2f));
  boids.add(new Boid(new PVector(random(width),random(height)),3f,0.1f));
  boids.add(new Boid(new PVector(random(width),random(height)),2f,0.05f));

}

void draw() {
  background(255);

  // Turn off highlighting for all obstalces
  for (int i = 0; i < obstacles.size(); i++) {
    Obstacle o = (Obstacle) obstacles.get(i);
    o.highlight(false);
  }

  // Act on all boids
  for (int i = 0; i < boids.size(); i++) {
    Boid b = (Boid) boids.get(i);
    b.avoid(obstacles);
    b.run();
  }

  // Display the obstacles
  for (int i = 0; i < obstacles.size(); i++) {
    Obstacle o = (Obstacle) obstacles.get(i);
    o.display();
  }

  // Instructions
  textFont(f);
  fill(0);
  text("Hit space bar to toggle debugging lines.\nClick the mouse to generate a new boids.",10,height-30);
}

void keyPressed() {
  debug = !debug;
}

void mousePressed() {
  boids.add(new Boid(new PVector(mouseX,mouseY),random(2,5),random(0.05f,0.2f)));
}



