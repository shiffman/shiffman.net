BImage a;
int MAX = 6400;

//set up some global variables as arrays now
int[] x = new int[MAX];
int[] y = new int[MAX];
int r = 6;

background(0);
size(320, 240);
a = loadImage("sunflower.jpg");
colorMode(RGB,255,255,255,50);
//initialize all arrays
for (int i = 0; i < MAX; i++) {
  x[i] = int(random(width));
  y[i] = int(random(height));
}

//background(0);
smooth();
ellipseMode(CENTER_DIAMETER);   //set our ellipse mode
noStroke();

for (int i = 0; i < MAX; i++) {

  int loc = x[i] + y[i]*a.width;
  loc = constrain(loc,0,a.pixels.length-1);
  
  float R = red(a.pixels[loc]);
  float G = green(a.pixels[loc]);
  float B = blue(a.pixels[loc]);
  
  fill(R,G,B,50);
  ellipse(x[i],y[i],r,r);

}

