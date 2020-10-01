
//consider giving different boids different maxspeeds and maxforces

ParticleSystem ps;

void setup() {
  size(480,360);
  colorMode(RGB,255,255,255,100);
  ps = new FlockSystem();
  for (int i = 0; i < 25; i++) {
    ps.addParticle(new Boid(new Vector3D(width/2,height/2),4.0f,0.1f));
  }
  smooth();
}

void draw() {
  background(0);
  ps.run();
}

void mousePressed() {
  ps.addParticle(new Boid(new Vector3D(width/2,height/2),5.0f,0.1f));
}
