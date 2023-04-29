void setup()
{
  size(320, 240);
  colorMode(RGB,255,255,255,100);
  rectMode(CENTER_DIAMETER);
  background(0);
  //start our video stream
  beginVideo(width,height,15);
}

void loop()
{
  //draw the video image on the background
  image(video,0,0);
  //local variables to track the brightess location
  int brightest = 0;
  int bx = 0;
  int by = 0;
  //begin loop to walk through every pixel
  for ( int x = 0; x < width;x++) {
    for ( int y = 0; y < height;y++) {
      // our pixel loc calculation
      int loc = x + y*video.width;
      //what is the brightness at current loc and at brightest loc
      float b1 = brightness(video.pixels[loc]);
      //what is the brightness at the "brightest" location
      float b2 = brightness(video.pixels[brightest]);
      //if our current location is brighter than "brightest"
      //than brightest becomes our current location
      if (b1 > b2) {
        brightest = loc;
        //save x and y locs for drawing a rect 
        bx = x;
        by = y;
      }
    }
  }
  noFill();
  stroke(0);
  //draw a rect at the brightest pixel
  rect(bx,by,30,30);
}
