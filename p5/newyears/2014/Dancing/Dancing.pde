// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// Simple Toxiclibs Spring

var VerletPhysics2D = toxi.physics2d.VerletPhysics2D,
    GravityBehavior = toxi.physics2d.behaviors.GravityBehavior,
    AttractionBehavior = toxi.physics2d.behaviors.AttractionBehavior,
    VerletParticle2D = toxi.physics2d.VerletParticle2D,
    VerletSpring2D = toxi.physics2d.VerletSpring2D,
    VerletMinDistanceSpring2D = toxi.physics2d.VerletMinDistanceSpring2D,
    Vec2D = toxi.geom.Vec2D,
    Rect =toxi.geom.Rect;


import toxi.physics2d.*;
import toxi.physics2d.behaviors.*;
import toxi.geom.*;

// Reference to physics world
VerletPhysics2D physics;

Skeleton s1;
Skeleton s2;

//make a new HTML5 audio object named audio
Audio audio = new Audio();
// make string that will house the audio extension
String fileExt;

boolean showHidden = false;

AttractionBehavior mouseAttractor;

Vec2D mousePos;

boolean dance = false;

void setup() {
  size(600, 400);

  // Initialize the physics
  physics=new VerletPhysics2D();
  physics.addBehavior(new GravityBehavior(new Vec2D(0, 0.5)));

  // Set the world's bounding box
  physics.setWorldBounds(new Rect(0, 0, width, height));

  s1 = new Skeleton(3*width/4, 100,1,"e");
  s2 = new Skeleton(width/4, 160,0.75,"o");
  audio.setAttribute("src","http://shiffman.net/p5/newyears/2014/auld2.mp3");
  dance = true;
  audio.play();
}

int counter = 0;

void draw() {
  
  // Update the physics world
  if (dance || frameCount < 2) {
    physics.update();
    counter++;
  }

  background(255);
  
  pushMatrix();
  translate(width/2,height/2);
  rotate(counter*0.01);
  float delta = TWO_PI/20;
  float r = width;
  colorMode(HSB);
  for (float a = 0; a < TWO_PI; a += delta) {
    color c =color(int(map(a+counter/20.0,0,TWO_PI,0,255))%255,255,255);
    fill(c);
    stroke(c);
    
    beginShape();
    vertex(0,0);
    vertex(r*cos(a),r*sin(a));
    vertex(r*cos(a+delta),r*sin(a+delta));
    endShape(CLOSE);
  }
  fill(255);
  stroke(255);
  ellipse(0,0,8,8);
  popMatrix();
  colorMode(RGB);
  
  

  s1.display();
  s1.control();
  s2.display();
  s2.control();
  
  
  s1.hdance();
  s2.vdance();
  
}

void mousePressed() {
  //s1.click(mouseX, mouseY);
  //s2.click(mouseX, mouseY);

  mousePos = new Vec2D(mouseX, mouseY);
  // create a new positive attraction force field around the mouse position (radius=250px)
  mouseAttractor = new AttractionBehavior(mousePos, width, -5f);
  physics.addBehavior(mouseAttractor);
}

void mouseDragged() {
  // update mouse attraction focal point
  mousePos.set(mouseX, mouseY);
}

void mouseReleased() {
  // remove the mouse attraction when button has been released
  physics.removeBehavior(mouseAttractor);
  //s1.release(); 
  //s2.release();
}

void keyPressed() {
  if (key == ' ') {
    //showHidden = !showHidden;
  }
}  

