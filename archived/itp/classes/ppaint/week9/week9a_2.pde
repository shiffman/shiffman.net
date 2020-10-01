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
      //get the R,G,B values from image
      float r,g,b;
      r = red   (a.pixels[loc]);
      g = green (a.pixels[loc]);
      b = blue  (a.pixels[loc]);
      //do some sort of calculation to the RGB values (increase brightness according to the mouse here)
      r += ((float) mouseX / width) * 255;
      g += ((float) mouseX / width) * 255;
      b += ((float) mouseX / width) * 255;
      //constrain RGB to make sure they are within 0-255 color range
      r = constrain(r,0,255);
      g = constrain(g,0,255);
      b = constrain(b,0,255);
      //make a new color and set pixel in the window
      color c = color(r,g,b);
      pixels[loc] = c;//a.pixels[loc];
    }
  }
}

