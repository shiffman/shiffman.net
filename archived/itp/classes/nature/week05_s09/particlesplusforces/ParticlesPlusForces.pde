// Demo of forces + particle system
// Particles interact with a list of "Repeller" objects
// Daniel Shiffman <http://www.shiffman.net>

ParticleSystem ps;

ArrayList repellers;

void setup() {
  size(300,300);
  ps = new ParticleSystem(1,new PVector(width/2,height/4));

  // Create a list of Repeller objects
  repellers = new ArrayList();
  for (int i = 0; i < 15; i++) {
    repellers.add(new Repeller(random(width),random(height)));

  }
  smooth();
}

void draw() {
  background(255);

  // Apply gravity force to all Particles
  PVector gravity = new PVector(0,0.1);
  ps.applyForce(gravity);
  // Apply repeller objects to all Particles
  ps.applyRepellers(repellers);
  // Run the Particle System
  ps.run();
  // Add more particles
  ps.addParticle();

  // Display all repellers
  for (int i = 0; i < repellers.size(); i++) {
    Repeller r = (Repeller) repellers.get(i); 
    r.display();
    r.drag();
  }

}

void mousePressed() {
  for (int i = 0; i < repellers.size(); i++) {
    Repeller r = (Repeller) repellers.get(i); 
    r.clicked(mouseX,mouseY);
  }
}

void mouseReleased() {
  for (int i = 0; i < repellers.size(); i++) {
    Repeller r = (Repeller) repellers.get(i); 
    r.stopDragging();
  }
}


