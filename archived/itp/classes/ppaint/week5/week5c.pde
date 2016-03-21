
int MAX = 20;
Oscillator[] o = new Oscillator[MAX];
 
float x = 0.0f;

int r = 0;
int g = 100;
int b = 255;

int rdir, gdir, bdir = 1;

void setup() 
{ 
  //smooth();
  size(400, 400);
  colorMode(RGB,255,255,255,100);
  for (int i = 0; i < MAX; i++) {
    color tempcolor = color((i*15)%255,(i*12)%255,(i*10)%255,50); 
    o[i] = new Oscillator(tempcolor,0.0f,0.0f,random(2), random(2), random(10,30), random(width/2),random(height/2));
  }  
} 
 
void loop() 
{ 
  //background(0); 
  noStroke();
  smooth();
  r += rdir;
  g += gdir;
  b += bdir;
 
  if ((r < 0) || (r > 255)) { rdir *= -1; }
  if ((g < 0) || (g > 255)) { gdir *= -1; }
  if ((b < 0) || (b > 255)) { bdir *= -1; }
  
  fill(r,g,b,2);
  rect(0,0,width,height);
  
  for (int i = 0; i < MAX; i++) {
    o[i].updateAngle();
    o[i].updateLoc();
    o[i].draw();
  }

} 


class Oscillator {
   
   color col;
   float ang1;
   float ang2;
   float angSpeed1;
   float angSpeed2;
   float radius;
   float xloc;
   float yloc;
   float factor1;
   float factor2;
 
   Oscillator( color c, float a1, float a2, float as1, float as2, float r, float f1, float f2) {
     col = c;
     ang1 = a1;
     ang2 = a2;
     angSpeed1 = as1;
     angSpeed2 = as2;
     radius = r;
     xloc = 0;
     yloc = 0;
     factor1 = f1;
     factor2 = f2;
   }
   
   void updateAngle() {
     ang1 += angSpeed1;
     ang2 += angSpeed2;
     if (ang1 > 360) { ang1 = 0; }
     if (ang2 > 360) { ang2 = 0; }
   }
   
   void updateLoc() {
     float temp1 = radians(ang1);
     float temp2 = radians(ang2);
     xloc = sin(temp1) * factor1;
     yloc = cos(temp2) * factor2;
   }
     
   void draw() {
     fill(col);
     noStroke();
     ellipse(width/2 + xloc,height /2 + yloc,radius,radius);
   }
   
 }
     
   
   
   