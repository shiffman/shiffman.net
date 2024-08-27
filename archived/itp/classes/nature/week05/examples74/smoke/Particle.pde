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
    timer = 100.0;
    img = img_;
  }
  
  //look, we can have more than one constructor!
  Particle(Vector3D l,PImage img_) {
    float d = width/2;
    float x = (float) generator.nextGaussian();
    float y = (float) generator.nextGaussian();
    x = x*0.3;
    y = y*0.3 - 1.0;
   
    vel = new Vector3D(x,y,0);
    acc = new Vector3D(0,0,0);
    loc = l.copy();
    r = 10.0;
    timer = 100.0;
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
    timer -= 2.5;
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
    if (timer <= 0.0) {
      return true;
    } else {
      return false;
    }
  }
}


