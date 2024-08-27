// Simple Particle System
// Daniel Shiffman <http://www.shiffman.net>

// A class to describe a group of Particles
// An ArrayList is used to manage the list of Particles 
// Functions are added to apply forces to all particles
// and all particles interact with Repeller objects

class ParticleSystem {

  ArrayList particles;    // An arraylist for all the particles
  Vector3D origin;        // An origin point for where particles are birthed

  ParticleSystem(int num, Vector3D v) {
    particles = new ArrayList();              // Initialize the arraylist
    origin = v.copy();                        // Store the origin point
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin));    // Add "num" amount of particles to the arraylist
    }
  }


  // A function to apply a force to all Particles
  void applyForce(Vector3D f) {
    for (int i = 0; i < particles.size(); i++) {
      Particle p = (Particle) particles.get(i);
      p.applyForce(f);
    }
  }

  // A function for particles to interact with all Repellers
  void applyRepellers(ArrayList a) {
    // For every Particle
    for (int i = 0; i < particles.size(); i++) {
      Particle p = (Particle) particles.get(i);
      // For every Repeller
      for (int j = 0; j < repellers.size(); j++) {
        Repeller r = (Repeller) repellers.get(j);
        // Calculate and apply a force from Repeller to Particle
        Vector3D repel = r.pushParticle(p);        
        p.applyForce(repel);
      }
    }
  }

  void run() {
    // Cycle through the ArrayList backwards b/c we are deleting
    for (int i = particles.size()-1; i >= 0; i--) {
      Particle p = (Particle) particles.get(i);
      p.run();
      if (p.dead()) {
        particles.remove(i);
      }
    }
  }

  void addParticle() {
    particles.add(new Particle(origin));
  }

  void addParticle(Particle p) {
    particles.add(p);
  }

  // A method to test if the particle system still has particles
  boolean dead() {
    if (particles.isEmpty()) {
      return true;
    } 
    else {
      return false;
    }
  }

}



