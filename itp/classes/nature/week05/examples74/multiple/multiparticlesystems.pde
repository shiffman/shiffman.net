
ArrayList psystems;

void setup() {
  size(600,400);
  colorMode(RGB,255,255,255,100);
  psystems = new ArrayList();
  smooth();
}

void draw() {
  background(0);

  for (int i = psystems.size()-1; i >= 0; i--) {
    ParticleSystem psys = (ParticleSystem) psystems.get(i);
    psys.run();

    if (psys.dead()) {
      psystems.remove(i);
    }
  }

  if (random(1) < 0.01) {
    psystems.add(new ParticleSystem(int(random(5,25)),new Vector3D(random(width),random(height),0)));
  }

}

void mousePressed() {
  psystems.add(new ParticleSystem(int(random(5,25)),new Vector3D(mouseX,mouseY,0)));
}
