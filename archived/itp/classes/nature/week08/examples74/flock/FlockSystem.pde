//a child of our ParticleSystem class
class FlockSystem extends ParticleSystem {

  FlockSystem() {
    super();
  }

  //identical to ParticleSystem, but here we pass the particles ArrayList through "run"
  void run() {
    for (int i = 0; i < particles.size(); i++) {
      Boid b = (Boid) particles.get(i);
      b.run(particles);
    }
  }

}
