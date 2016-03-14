
BImage b;
int x = 0;
int xdir = 4;

void setup()
{
  size(300 , 300);
  background(0);
  myLoadImage();
}

void myLoadImage() {
  b = loadImage("img2.php?url=http://207.251.86.248/cctv22.jpg");

}



void loop()
{

  myLoadImage();
  
  image(b,0,0,300,300);
  
  stroke(0);
  fill(200,200,200);
  rect(x,height/2,20,20);
  
  x += xdir;
  
  if ((x > width) || (x < 0)) {
    xdir *= -1;
  }
}

