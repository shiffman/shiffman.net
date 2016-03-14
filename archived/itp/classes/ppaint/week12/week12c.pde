BImage img;

void setup() {
  size(320,240);
  beginVideo(160,120,15);
  img = new BImage(160,120);
}

void loop() {
  background(0);
  //display video in upper left
  image(video,0,0);
  //display background image in upper right
  image(img,160,0);

  //background removal, walk through every pixel
  for (int x = 0; x < video.width; x++) {
    for (int y = 0; y < video.height; y++) {
      int loc = x + y*video.width;
      //pixel from video
      color pix = video.pixels[loc];
      //pixel from background
      color pixb = img.pixels[loc];
      //check "distance" between color values, if greater than 50 place in bottom left
      if (dist(red(pix),green(pix),blue(pix),red(pixb),green(pixb),blue(pixb)) > 50) {
        int newy = 120+y;
        int newloc = x + newy*width;
        pixels[newloc] = pix;
      //if less than 50 place in bottom right
      } else {
        int newy = 120+y;
        int newx = 160+x;
        int newloc = newx + newy*width;
        pixels[newloc] = pix;
      }
    }
  }

}

//when the mouse in pressed
//copy the video image into IMG
void mousePressed() {
  img=video.copy();
}
