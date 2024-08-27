/*A class to describe a group of Particles*/
//this class does very little.
//it doesn't care what the particles do, it's just for managing the arraylist of particles

class ParticleSystem {
  
  //this particle system has an origin point, and an ArrayList of particles
  ArrayList particles;
  Vector3D origin;

  //The Constructor (called when the object is first created)
  ParticleSystem(int num, Vector3D v) {
    //create the arraylist and set the origin point
    particles = new ArrayList();
    origin = v.copy();
    
    //add an initial group of particles to the ArrayList
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin));
      //there is a 10% chance we will add a "crazy particle" into the system
      if (random(1) < 0.1) {
        particles.add(new CrazyParticle(origin));
      }
    }
  }

  void run() {
  
    //Notice how we are going backwards through the arraylist here
    //This is very important, we can't go forwards if we are deleting!!
    for (int i = particles.size()-1; i >= 0; i--) {
      //***POLYMORPHISM HERE!!!  Even though some of the particles in the ArrayList are of type "Particle"
      //and some are of type "CrazyParticle", we can treat them all as just a regular "Particle"
      //and not have to worry about managing what is what      
      Particle p = (Particle) particles.get(i);
      p.run();
      //if the particle is no longer useful, delete it.
      if (p.dead()) {
        particles.remove(i);
      }
    }
  }
  
  void addParticle() {
    particles.add(new Particle(origin));
  }
  
  void addCrazyParticle() {
    particles.add(new CrazyParticle(origin));
  }
  
  //a method to say if the system has no more particles left and can be discarded
  boolean dead() {
    if (particles.size() < 1) {
      return true;
    } else {
      return false;
    }
  }

}

