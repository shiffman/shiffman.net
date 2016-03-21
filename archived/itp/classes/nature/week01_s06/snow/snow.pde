float increment = 0.02;

void setup() {
  size(200,200);
  framerate(30);
}

void draw() {
  background(0);
  loadPixels();
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      float bright = random(255);
      pixels[x+y*width] = color(bright);
    }
  }
  updatePixels();
}
