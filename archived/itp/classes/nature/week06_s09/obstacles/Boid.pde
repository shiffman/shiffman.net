// Obstacle Avoidance
// Daniel Shiffman <http://www.shiffman.net>
// The Nature of Code, Spring 2009

class Boid {

  // All the usual stuff
  PVector loc;
  PVector vel;
  PVector acc;
  float r;
  float maxforce;    // Maximum steering force
  float maxspeed;    // Maximum speed

    // How far ahead can the boid see
  float sight = 100;

  // Constructor initialize all values
  Boid(PVector l, float ms, float mf) {
    loc = l.get();
    r = 4.0;
    maxspeed = ms;
    maxforce = mf;
    acc = new PVector(0,0);
    vel = new PVector(maxspeed,0);
  }

  // Main "run" function
   void run() {
    update();
    borders();
    render();
  }



  // A function to get a perpendicular vector
  PVector getNormal(PVector v) {
    return new PVector(-v.y,v.x);
  }

  // A function to rotate a vector
  void rotateVector(PVector v, float theta) {
    float m = v.mag();
    float a = v.heading2D();
    a += theta;
    v.x = m*PApplet.cos(a);
    v.y = m*PApplet.sin(a);
  }

  // This function implements Craig Reynolds' obstacle avoidance algorithm
  // http://www.red3d.com/cwr/steer/Obstacle.html
  // Algorithm also based on James Tu's example from Dynamic Bodies

  void avoid(ArrayList obstacles) {


    // Make a vector that will be the position of the object
    // relative to the Boid rotated in the direction of boid's velocity
    PVector closestRotated = new PVector(sight+1,sight+1);
    float closestDistance = 99999;
    Obstacle avoid = null;

    // Let's look at each obstacle
    for (int i = 0; i < obstacles.size(); i++) {  
      Obstacle o = (Obstacle) obstacles.get(i);

      float d = PVector.dist(loc,o.loc);
      PVector dir = vel.get();
      dir.normalize();
      PVector diff = PVector.sub(o.loc,loc);

      // Now we use the dot product to rotate the vector that points from boid to obstacle
      // Velocity is the new x-axis
      PVector rotated = new PVector(diff.dot(dir),diff.dot(getNormal(dir)));

      // Is the obstacle in our path?
      if ( PApplet.abs(rotated.y) < (o.radius + r) ) {
        // Is it the closest obstacle?
        if ( (rotated.x > 0) && (rotated.x < closestRotated.x) ) {
          closestRotated = rotated;
          avoid = o;
        }
      }
    }


    // Can we actually see the closest one?
    if ( PApplet.abs(closestRotated.x) < sight ) {

      // The desired vector should point away from the obstacle
      // The closer to the obstacle, the more it should steer
      PVector desired = new PVector (closestRotated.x, -closestRotated.y*sight/closestRotated.x);
      desired.normalize();
      desired.mult(closestDistance);
      desired.limit(maxspeed);
      // Rotate back to the regular coordinate system
      rotateVector(desired,vel.heading2D());

      // Draw some debugging stuff
      if (debug) {
        stroke(0);
        line(loc.x,loc.y,loc.x+desired.x*10,loc.y+desired.y*10);
        avoid.highlight(true);
      }

      // Apply Reynolds steering rules
      desired.sub(vel);
      desired.limit(maxforce);
      acc.add(desired);
    } 
  }





  // Method to update location
  void update() {
    // Update velocity
    vel.add(acc);
    // Limit speed
    vel.limit(maxspeed);
    loc.add(vel);
    // Reset accelertion to 0 each cycle
    acc.mult(0);
  }

  void render() {
    // Draw a triangle rotated in the direction of velocity
    float theta = vel.heading2D() + PApplet.radians(90);
    fill(100);
    stroke(0);
    pushMatrix();
    translate(loc.x,loc.y);
    rotate(theta);
    beginShape(PConstants.TRIANGLES);
    vertex(0, -r*2);
    vertex(-r, r*2);
    vertex(r, r*2);
    endShape();

    if (debug) {
      stroke(50);
      line(0,0,0,-sight);
    }
    popMatrix();

  }

  // Wraparound
  void borders() {
    if (loc.x < -r) loc.x = width+r;
    if (loc.y < -r) loc.y = height+r;
    if (loc.x > width+r) loc.x = -r;
    if (loc.y > height+r) loc.y = -r;
  }

}



