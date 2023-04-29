
int trackColor;

void setup()
{
  size(320, 240);
  colorMode(RGB,255,255,255,100);
  rectMode(CENTER_DIAMETER);
  background(0);
  //start our video stream
  beginVideo(width,height,15);
  
  trackColor = color(0,0,0);
}

void loop()
{
  //draw the video image on the background
  image(video,0,0);
  //local variables to track the brightess location
  float closestDiff = 500.0f;
  int closestX = 0;
  int closestY = 0;
  //begin loop to walk through every pixel
  for ( int x = 0; x < width;x++) {
    for ( int y = 0; y < height;y++) {
      // our pixel loc calculation
      int loc = x + y*video.width;
      //what is current color
      color currentColor = video.pixels[loc];
      float r1 = red(currentColor); float g1 = green(currentColor); float b1 = blue(currentColor);
      float r2 = red(trackColor);   float g2 = green(trackColor);   float b2 = blue(trackColor);
      //test current color against closest color
      float diff = sqrt(sq(r1 - r2) + sq(g1 - g2) + sq(b1 - b2));
      //if current color is more similar to tracked color than
      //closest color, then save current location, and current difference
      if (diff < closestDiff) {
        closestDiff = diff;
        closestX = x;
        closestY = y;
      }
    }
  }
  noFill();
  stroke(0);
  //draw a rect at the tracked pixel
  rect(closestX,closestY,30,30);
}

void mousePressed() {
  //save color where the mouse is clicked in trackColor variable
  int loc = mouseX + mouseY*video.width;
  trackColor = video.pixels[loc];
}
