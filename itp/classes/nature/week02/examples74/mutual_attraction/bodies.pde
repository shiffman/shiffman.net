
/*In this example, there is no fixed Attractive body
  Each object asserts a gravitational force on all objects*/

//establish an array of MAX Things
final int MAX = 6;
Thing[] t = new Thing[MAX];
boolean showVectors = true;

void setup() {
  size(480,360);
  background(0);
  reset();
  smooth();
}

void draw() {
  //framerate(10);
  background(0);
  for (int i = 0; i < t.length; i++) {          //for every Thing t[i]
    for (int j = 0; j < t.length; j++) {        //for every Thing t[j]
      if (i != j) {                             //make sure we are not calculating gravtional pull on oneself
        Vector2D f = t[i].calcGravForce(t[j]);  //use the function we wrote above
        t[i].add_force(f);                      //add the force to the object to affect its acceleration
      }
    }
    t[i].go();                                  //implement the rest of the object's functionality
  }
}

/*A reset function that initializes all the objects*/
void reset() {
  for (int i = 0; i < t.length; i++) {
    Vector2D ac = new Vector2D(0.0,0.0);
    Vector2D ve = new Vector2D(0.0,0.0);
    Vector2D lo = new Vector2D(random(width),random(height));
    t[i] = new Thing(ac,ve,lo,random(5,10),0.2);
  }

}

void mousePressed() {
  reset();
}

void keyPressed() {
  showVectors = !showVectors;
}

public void drawVector(Vector2D vel, Vector2D loc, float scayl) {
  push();
  float arrowsize = 10;
  //translate to location to render vector
  translate(loc.x(),loc.y());
  stroke(255);
  //call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(vel.heading());
  //calculate length of vector & scale it to be bigger or smaller if necessary
  float len = vel.magnitude()*scayl;
  //draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  pop();
}
