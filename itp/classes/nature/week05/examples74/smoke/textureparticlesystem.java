import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class textureparticlesystem extends PApplet {
ParticleSystem ps;
Random generator;

public void setup() {
  size(320,240);
  colorMode(RGB,255,255,255,100);
    generator = new Random();
    
  //*CREATING AN ALPHA TEXTURED BLANK IMAGE*//
  PImage mask = loadImage("texture.gif");
  PImage img = new PImage(mask.width,mask.height);
  for (int i = 0; i < img.pixels.length; i++) img.pixels[i] = color(255);
  img.alpha(mask);

  ps = new ParticleSystem(1,new Vector3D(width/2,height-20,0),img);

  smooth();
}

public void draw() {
  background(0);
  
  //*CALCULATE WIND B
  float dx = (mouseX - width/2) / 1000.0f;
  Vector3D wind = new Vector3D(dx,0,0);
  ps.wind(wind);
  drawVector(wind, new Vector3D(width/2,50,0),500);
  
  ps.run();
  
  for (int i = 0; i < 2; i++) {
    ps.addParticle();
  }
}



void drawVector(Vector3D v, Vector3D loc, float scayl) {
  push();
  float arrowsize = 10;
  //translate to location to render vector
  translate(loc.x(),loc.y());
  stroke(255);
  //call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading2D());
  //calculate length of vector & scale it to be bigger or smaller if necessary
  float len = v.magnitude()*scayl;
  //println(v.x() + " " + v.y());
  //println(len);
  //draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  pop();
}

/*A class to describe a one singular particle*/

//using inheritance we should have probably made this "image-based" particle a subclass of our particle
//class from the previous example (adding just an image and some necessary methods for wind, etc.)

class Particle {
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float r;
  float timer;
  PImage img;

  //The Constructor (called when the object is first created)
  Particle(Vector3D a, Vector3D v, Vector3D l, float r_,PImage img_) {
    acc = a.copy();
    vel = v.copy();
    loc = l.copy();
    r = r_;
    timer = 100.0f;
    img = img_;
  }
  
  //look, we can have more than one constructor!
  Particle(Vector3D l,PImage img_) {
    float d = width/2;
    float x = (float) generator.nextGaussian();
    float y = (float) generator.nextGaussian();
    x = x*0.3f;
    y = y*0.3f - 1.0f;
   
    vel = new Vector3D(x,y,0);
    acc = new Vector3D(0,0,0);
    loc = l.copy();
    r = 10.0f;
    timer = 100.0f;
    img = img_;
  }


  void run() {
    update();
    render();
  }
  
  void add_force(Vector3D v) {
    acc.add(v);
  }
  //function to update location
  void update() {
    vel.add(acc);
    loc.add(vel);
    timer -= 2.5f;
    acc.setXYZ(0,0,0);
  }

  //function to display
  void render() {
    /*ellipseMode(CENTER);
    noStroke();
    fill(0,100,255,timer);
    ellipse(loc.x(),loc.y(),r,r);*/
    
    imageMode(CENTER);
    tint(255,timer);
    image(img,loc.x(),loc.y());
    
    
  }
  
  boolean dead() {
    if (timer <= 0.0f) {
      return true;
    } else {
      return false;
    }
  }
}



/*A class to describe a group of Particles*/

class ParticleSystem {

  ArrayList particles;
  boolean dead;
  int totalp;
  Vector3D origin;
  PImage img;

  //The Constructor (called when the object is first created)
  ParticleSystem(int num, Vector3D v, PImage img_) {
    particles = new ArrayList();
    dead = false;
    origin = v.copy();
    img = img_;
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin,img));
    }
  }

  void run() {
    for (int i = particles.size()-1; i >= 0; i--) {
      Particle p = (Particle) particles.get(i);
      p.run();
      if (p.dead()) {
        particles.remove(i);
      }
    }
  }
  
  void wind(Vector3D dir) {
    for (int i = particles.size()-1; i >= 0; i--) {
      Particle p = (Particle) particles.get(i);
      p.add_force(dir);
    }
  
  }

  void addParticle() {
    particles.add(new Particle(origin,img));
  }
  
  Vector3D getOrigin() {
    return origin.copy();
  }

}

}