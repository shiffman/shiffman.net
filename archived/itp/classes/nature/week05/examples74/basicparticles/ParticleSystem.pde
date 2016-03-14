/*A class to describe a group of Particles*/

class ParticleSystem {

  ArrayList particles;    //an arraylist for all the particles
  Vector3D origin;        //an origin point for where particles are birthed

  ParticleSystem(int num, Vector3D v) {
    particles = new ArrayList();              //initialize the arraylist
    origin = v.copy();                        //store the origin point
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin));    //add "num" amount of particles to the arraylist
    }
  }

  void run() {
    //cycle through the ArrayList backwards b/c we are deleting
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

  //a function to test if the particle system still has particles
  boolean dead() {
    if (particles.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

}

