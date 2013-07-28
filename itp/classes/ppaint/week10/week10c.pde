
void setup()
{
  //set up size of window and video
  size(320, 240);
  beginVideo(320, 240, 15);
}

void loop()
{
  background(0);

  for (int x = 0; x < video.width; x++) {
    for (int y = 0; y < video.height; y++ ) {
      //calculate the 1D location from a 2D grid
      int loc = x + y*video.width;
      //calculate a threshold from 0-255 based on mouseX
      int threshold = int(((float) mouseX / width) * 255);
      color c;
      //do a threshold test based on brightness
      //the resulting pixel is only white or black
      if (brightness(video.pixels[loc]) > threshold) {
        c = color(255,255,255);
      } else {
        c = color(0,0,0);
      }
      pixels[loc] = c;
    }
  }

}

