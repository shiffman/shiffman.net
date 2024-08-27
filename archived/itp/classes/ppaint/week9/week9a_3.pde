BImage a;
void setup()
{
  size(320, 240);
  a = loadImage("sunflower.jpg");
}
void loop()
{
  background(0);
  image(a,0,0);
  for (int x = 0; x < a.width; x++) {
    for (int y = 0; y < a.height; y++ ) {
      //calculate the 1D location from a 2D grid
      int loc = x + y*a.width;
      //calculate a threshold from 0-255 based on mouseX
      int threshold = int(((float) mouseX / width) * 255);
      color c;
      //do a threshold test based on brightness
      if (brightness(a.pixels[loc]) > threshold) {
        c = color(255,255,255);
      } else {
        c = color(0,0,0);
      }
      pixels[loc] = c;
    }
  }
}

