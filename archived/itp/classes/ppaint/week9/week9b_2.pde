BImage a;

void setup()
{
  size(320, 240);
  a = loadImage("sunflower.jpg");
}

void loop()
{
  //we're only going to process a portion of the image so let's set the whole image as the background first
  image(a,0,0);

  //For drawing a black rectangle defining area for processing
  stroke(0);
  noFill();
  rectMode(CENTER_DIAMETER);
  rect(mouseX,mouseY,81,81);

  //what are the boundaries for the pixels we are processing
  int X1 = constrain(mouseX-40,0,a.width);
  int Y1 = constrain(mouseY-40,0,a.height);
  int X2 = constrain(mouseX+40,0,a.width);
  int Y2 = constrain(mouseY+40,0,a.height);

  float[] Matrix = new float[25];
  for (int i = 0; i < 25; i++) {
    Matrix[i] = 1.0f/25.0f;;
  }
  //begin our loop for every pixel
  for (int x = X1; x < X2; x++) {
    for (int y = Y1; y < Y2; y++ ) {

      color c = Convolve(x,y,Matrix,a);
      int loc = x + y*a.width;
      pixels[loc] = c;
    }
  }
}

color Convolve(int x, int y, float[] Matrix, BImage a)
{

  float sumR = 0.0;
  float sumG = 0.0;
  float sumB = 0.0;

  //is our matrix 3x3, 5x5, etc.?
  int MatrixSize = int(sqrt(Matrix.length));
  //to keep track of where we are in convolution matrix
  int countMatrix = 0;

  for (int i =-MatrixSize/2; i<=MatrixSize/2; i++){
    for (int j=-MatrixSize/2; j<=MatrixSize/2; j++){
      //what pixel are we testing
      int xloc = x+i;
      int yloc = y+j;
      int loc = xloc + a.width*(yloc+j);
      //make sure we haven't walked off our image
      loc = constrain(loc,0,a.pixels.length-1);
      //calculate the convolution
      sumB += (blue(a.pixels[loc]) * Matrix[countMatrix]);
      sumG += (green(a.pixels[loc]) * Matrix[countMatrix]);
      sumR += (red(a.pixels[loc]) * Matrix[countMatrix]);
      countMatrix++;
    }
  }

  //make sure RGB is withing range
  sumR = constrain(sumR,0,255);
  sumG = constrain(sumG,0,255);
  sumB = constrain(sumB,0,255);

  //return the resulting color
  return color(sumR,sumG,sumB);

}

