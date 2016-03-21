/*A class to describe a group of Particles*/

class ParticleSystem {

  ArrayList particles;
  boolean dead;
  int totalp;
  Vector3D origin;
  PImage img;

  //The Constructor (called when the object is first created)
  ParticleSystem(int num, Vector3D v, PImage img_) {
    particles = new ArrayList();
    dead = false;
    origin = v.copy();
    img = img_;
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin,img));
    }
  }

  void run() {
    for (int i = particles.size()-1; i >= 0; i--) {
      Particle p = (Particle) particles.get(i);
      p.run();
      if (p.dead()) {
        particles.remove(i);
      }
    }
  }
  
  void wind(Vector3D dir) {
    for (int i = particles.size()-1; i >= 0; i--) {
      Particle p = (Particle) particles.get(i);
      p.add_force(dir);
    }
  
  }

  void addParticle() {
    particles.add(new Particle(origin,img));
  }
  
  Vector3D getOrigin() {
    return origin.copy();
  }

}

