
ParticleSystem ps;
Random generator;

void setup() {
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

void draw() {
  background(0);
  
  //*CALCULATE WIND B
  float dx = (mouseX - width/2) / 1000.0;
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
