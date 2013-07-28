//size of each rect
int cellsize = 16;
//number of columns and rows in our system
int COLS, ROWS;

void setup()
{
  size(640, 480);
  //set up columns and rows
  COLS = width/cellsize;
  ROWS = height/cellsize;
  colorMode(RGB,255,255,255,100);
  rectMode(CENTER_DIAMETER);
  background(0);
  //start our video stream  
  beginVideo(COLS,ROWS,15);
}

void loop()
{
  background(0);
  //begin loop for columns
  for ( int i = 0; i < COLS;i++) {
    //begin loop for rows
    for ( int j = 0; j < ROWS;j++) {
      int loc = i + j*video.width;
      loc = constrain(loc,0,video.pixels.length-1);
      float R = red(video.pixels[loc]);
      float G = green(video.pixels[loc]);
      float B = blue(video.pixels[loc]);
      noStroke();
      //make a new color with alpha value
      color c = color(R,G,B,100);
      //our drawing code, we are using translate b/c we will rotate
      push();
      int x = i*cellsize + cellsize/2;
      int y = j*cellsize + cellsize/2;
      fill(255);
      translate(x,y);
      //each rectangle colored white with a size determined by brightness
      float sz = ((brightness(c)/255.0f)*cellsize)-1;
      rect(0,0,sz,sz);
      pop();
    }
  }
}
