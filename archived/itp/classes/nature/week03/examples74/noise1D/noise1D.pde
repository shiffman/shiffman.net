float xoff = 0.0;
float xincrement = 0.005;

void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,100);
  smooth();
  noStroke();
}

void draw()
{
  //create an alpha blended background
  fill(0,1);
  rect(0,0,width,height);
  
  /***float n = random(0,width);  //TRY THIS LINE INSTEAD OF NOISE AND SEE WHAT HAPPENS****/
  
  //get a noise value based on x and scale it according to the window's width
  float n = noise(xoff)*width;
  //with each cycle, increment xoff
  xoff += xincrement;
  
  //draw the ellipse at the value produced by perlin noise
  fill(255);
  ellipse(n,height/2,8,8);
}

