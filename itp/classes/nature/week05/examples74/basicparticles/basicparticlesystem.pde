
ParticleSystem ps;

void setup() {
  size(320,240);
  colorMode(RGB,255,255,255,100);
  ps = new ParticleSystem(1,new Vector3D(width/2,height/2,0));
  smooth();
}

void draw() {
  background(0);
  ps.run();
  ps.addParticle();
}
