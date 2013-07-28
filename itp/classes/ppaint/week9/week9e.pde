BImage a;
//size of each rect
int cellsize = 8;
//number of columns and rows in our system
int COLS, ROWS;
//global angle variable
float angle = 0.0f;

void setup()
{
  size(320, 240);
  a = loadImage("sunflower.jpg");
  COLS = width/cellsize;
  ROWS = height/cellsize;
  colorMode(RGB,255,255,255,100);
  rectMode(CENTER_DIAMETER);
  background(0);
}

void loop()
{
  background(0);
  fill(100,100,100,50);
  stroke(200);
  smooth();
  //increment angle for sine calculation
  angle += 0.04f;
  float zoom = sin(angle)+1.0f;

  //begin loop for columns
  for ( int i = 0; i < COLS;i++) {
    //begin loop for rows
    for ( int j = 0; j < ROWS;j++) {
      //save transformation matrix
      push();
      //calculate x and y location and translate
      int x = i*cellsize + cellsize/2;
      int y = j*cellsize + cellsize/2;
      translate(x,y,0);
      int loc = x + y*width;
      loc = constrain(loc,0,a.pixels.length-1);
      float z = zoom*(brightness(a.pixels[loc])/2.0f);
      rotateZ(angle);
      translate(0.0f,0.0f,z);
      float R = red(a.pixels[loc]);
      float G = green(a.pixels[loc]);
      float B = blue(a.pixels[loc]);
      noStroke();
      fill(R,G,B,75);
      rect(0,0,cellsize,cellsize);
      //restore transformation matrix
      pop();
    }
  }
}
