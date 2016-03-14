import processing.core.*; import noc.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class trilateration extends PApplet {// Trilateration
// from: http://en.wikipedia.org/wiki/Trilateration
// Daniel Shiffman <http://www.shiffman.net>
// April 2006



Vector3D p1,p2,p3;
float d1,d2,d3;
Vector3D it;

float mx;// = width/2;
float my;// = height/2;

public void setup() {
  size(200,200);
  mx = width/2;
  my = height/2;

  p1 = new Vector3D(10,10);
  p2 = new Vector3D(width-10,10);
  p3 = new Vector3D(width/2,height-10);

}

public void draw() {
  background(100);
  smooth();
  fill(255);
  noStroke();
  ellipse(p1.x,p1.y,4,4);
  ellipse(p2.x,p2.y,4,4);
  ellipse(p3.x,p3.y,4,4);

  //noLoop();

  if (mousePressed) {
    mx = mouseX;
    my = mouseY;
  }
  it = new Vector3D(mx,my);

  float r1 = Vector3D.distance(it,p1);
  float r2 = Vector3D.distance(it,p2);
  float r3 = Vector3D.distance(it,p3);

  //////////We know p1,p2,p3 and r1,r2,r3 (distance).  Now we have to reverse engineer x & y for it

  float d = p2.x - p1.x;
  float i = p3.x - p1.x;
  float j = p3.y - p1.y;   

  float x = (r1*r1 - r2*r2 + d*d) / (2 * d) + p1.x;

  float a = r1*r1 - r3*r3 + (x-i)*(x-i);
  float b = (r1*r1 - r2*r2 + d*d);
  b = b*b;
  float c = 8*d*d*j;

  float y = (a / (2*j)) + (j / 2) - (b / c) + p1.y;

  Vector3D newit = new Vector3D(x,y);


  fill(255,150);
  ellipse(it.x,it.y,24,24); 

  fill(255,150);
  ellipse(newit.x,newit.y,16,16);

  stroke(255);
  line(p1.x,p1.y,newit.x,newit.y);
  line(p2.x,p2.y,newit.x,newit.y);
  line(p3.x,p3.y,newit.x,newit.y);

}




static public void main(String args[]) {   PApplet.main(new String[] { "trilateration" });}}