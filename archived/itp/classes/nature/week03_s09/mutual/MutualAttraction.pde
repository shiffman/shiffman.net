// Mutual Attraction
// Daniel Shiffman <http://www.shiffman.net>

// Demonstrates gravitational pull amongst a group of moving objects
// G ---> universal gravitational constant
// m1 --> mass of object #1
// m2 --> mass of object #2
// d ---> distance between objects
// F = (G*m1*m2)/(d*d)

// Mouse click resets all bodies to random locations
// Key press turns on and off vector display


int MAX = 5;
Thing[] t = new Thing[MAX];
boolean showVectors = false;

void setup() {
  size(200,200);
  smooth();
  reset();
}

void draw() {
  background(255);

  for (int i = 0; i < t.length; i++) {          // For every Thing t[i]
    for (int j = 0; j < t.length; j++) {        // For every Thing t[j]
      if (i != j) {                             // Make sure we are not calculating gravtional pull on oneself
        PVector f = t[i].calcGravForce(t[j]);   // Use the function we wrote above
        t[i].applyForce(f);                     // Add the force to the object to affect its acceleration
      }
    }
    t[i].go();                                  // Implement the rest of the object's functionality
  }

}

void reset() {
  // Some random bodies
  for (int i = 0; i < t.length; i++) {
    PVector ac = new PVector(0.0,0.0);
    PVector ve = new PVector(0.0,0.0);
    PVector lo = new PVector(random(width),random(height));
    t[i] = new Thing(ac,ve,lo,random(8,16));
  }
}

void mousePressed() {
  reset();
}

void keyPressed() {
  showVectors = !showVectors;
}

// Renders a vector object 'v' as an arrow and a location 'loc'
void drawVector(PVector v, PVector loc, float scayl) {
  if (v.mag() > 0.0) {
    pushMatrix();
    float arrowsize = 4;
    // Translate to location to render vector
    translate(loc.x,loc.y);
    stroke(0);
    // Call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
    rotate(v.heading2D());
    // Calculate length of vector & scale it to be bigger or smaller if necessary
    float len = v.mag()*scayl;
    // Draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
    line(0,0,len,0);
    line(len,0,len-arrowsize,+arrowsize/2);
    line(len,0,len-arrowsize,-arrowsize/2);
    popMatrix();
  }
}

