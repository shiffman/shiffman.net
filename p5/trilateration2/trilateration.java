import processing.core.*; import noc.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class trilateration extends PApplet {// Trilateration
// from: http://en.wikipedia.org/wiki/Trilateration
// Daniel Shiffman <http://www.shiffman.net>
// April 2006



Vector3D p1,p2,p3;
float d1,d2,d3;
Vector3D it;

float mx;// = width/2;
float my;// = height/2;
float mz;

float theta = 0;

public void setup() {
  size(200,200,P3D);
  mx = width/2;
  my = height/2;
  mz = 0;
  framerate(30);

  p1 = new Vector3D(-width/2+10,-height/2+10);
  p2 = new Vector3D(width/2-10,-height/2+10);
  p3 = new Vector3D(0,height/2-10);

}

public void draw() {
  translate(width/2,height/2,-100);
  rotateX(PI/2);
  rotateZ(theta);
  background(0);
  //smooth();
  fill(200);
  lights();
  noStroke();

  pushMatrix();
  translate(p1.x,p1.y,p1.z);
  sphere(4);
  popMatrix();

  pushMatrix();
  translate(p2.x,p2.y,p2.z);
  sphere(4);
  popMatrix();

  pushMatrix();
  translate(p3.x,p3.y,p3.z);
  sphere(4);
  popMatrix();

  //noLoop();

  if (mousePressed) {
    mx = mouseX;
    my = mouseY;
    mz = dist(mouseX,mouseY,width/2,height/2);
  }

  it = new Vector3D(mx-width/2,my-height/2,mz);

  float r1 = Vector3D.distance(it,p1);
  float r2 = Vector3D.distance(it,p2);
  float r3 = Vector3D.distance(it,p3);

  //////////We know p1,p2,p3 and r1,r2,r3 (distance).  Now we have to reverse engineer x & y for it

  float d = p2.x - p1.x;
  float i = p3.x - p1.x;
  float j = p3.y - p1.y;   

  float x = (r1*r1 - r2*r2 + d*d) / (2 * d);

  float a = r1*r1 - r3*r3 + (x-i)*(x-i);
  float b = (r1*r1 - r2*r2 + d*d);
  b = b*b;
  float c = 8*d*d*j;

  float y = (a / (2*j)) + (j / 2) - (b / c);

  float z = sqrt((r1*r1) - (x*x) - (y*y));

  Vector3D newit = new Vector3D(x+p1.x,y+p1.y,z+p1.z);

  //println(z);

  rectMode(CENTER);
  noStroke();
  fill(255,150);
  pushMatrix();
  translate(it.x,it.y,it.z);
  sphere(24); 
  popMatrix();

  fill(255,150);
  pushMatrix();
  translate(newit.x,newit.y,it.z);
  sphere(12); 
  popMatrix();

  stroke(255);
  beginShape(LINES);
  vertex(p1.x,p1.y,p1.z); 
  vertex(newit.x,newit.y,newit.z);
  vertex(p2.x,p2.y,p2.z); 
  vertex(newit.x,newit.y,newit.z);
  vertex(p3.x,p3.y,p3.z); 
  vertex(newit.x,newit.y,newit.z);
  endShape();

  theta += 0.01f;

}




static public void main(String args[]) {   PApplet.main(new String[] { "trilateration" });}}