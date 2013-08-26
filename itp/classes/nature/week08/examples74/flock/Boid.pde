//our Boid extends Particle, has a few more properties

/* some improvements to try:
   -- make weights alignment, cohesion, separation variables controlled via some form of interaction perhaps
   -- make neighbor scanning & separation distance variables
   -- we calculate the distances between boids constantly.  Couldn't we do this just once every cycle to save processing power?
   -- what other rules / steering behaviors can we add here to make this more interesting?
*/

class Boid extends Particle {

  float wandertheta;          //the "wander" angle
  float maxforce,maxspeed;    //maxforce for steering, maxspeed for vel

  Boid(Vector3D l, float ms, float mf) {
    super(l);
    r = 4.0f;
    wandertheta = 0.0f;
    maxspeed = ms;
    maxforce = mf;
  }

  //we add one additional step here, which is "flock"
  void run(ArrayList boids) {
    flock(boids);
    update();
    borders();
    render();

  }

  //we accumulate a new acceleration each time based on three principles
  //SEPARATION, ALIGNMENT, COHESION
  void flock(ArrayList boids) {
    Vector3D sep = separate(boids);   //separation
    Vector3D ali = align(boids);      //alignment
    Vector3D coh = cohesion(boids);   //cohesion
    //arbitrarily weight these forces
    //this could be improved using variables
    sep.mult(10.0f);
    ali.mult(1.0f);
    coh.mult(2.0f);
    //add the force vectors to acceleration
    acc.add(sep);
    acc.add(ali);
    acc.add(coh);
  }

  //function to update location
  void update() {
    vel.add(acc);
    vel.limit(maxspeed);
    loc.add(vel);
    acc.setXYZ(0,0,0);
  }

  void seek(Vector3D target) {
    acc.add(steer(target,false));
  }

  void arrive(Vector3D target) {
    acc.add(steer(target,true));
  }

  //a method that calculates a steering vector towards a target
  //takes a second argument, if true, it slows down as it approaches the target
  Vector3D steer(Vector3D target, boolean slowdown) {
    Vector3D steer;  //the steering vector
    Vector3D desired = Vector3D.sub(target,loc);  //a vector pointing from the location to the target
    float d = desired.magnitude(); //distance from the target is the magnitude of the vector
    //if the distance is greater than 0, calc steering (otherwise return zero vector)
    if (d > 0) {
      //normalize desired
      desired.normalize();
      //two options for magnitude (1 -- based on distance, 2 -- maxspeed)
      if ((slowdown) && (d < 100.0f)) desired.mult(maxspeed*(d/100.0f));
      else desired.mult(maxspeed);
      //STEERING VECTOR IS DESIREDVECTOR MINUS VELOCITY
      steer = Vector3D.sub(desired,vel);
      steer.limit(maxforce);  //limit to maximum steering force
    } else {
      steer = new Vector3D(0,0);
    }
    return steer;
  }

  void render() {
    //draws a triangle rotated in the direction of velocity
    float theta = vel.heading2D() + radians(90);
    fill(150);
    stroke(255);
    push();
    translate(loc.x(),loc.y());
    rotateZ(theta);
    beginShape(TRIANGLES);
    vertex(0, -r*2);
    vertex(-r, r*2);
    vertex(r, r*2);
    endShape();
    pop();
  }

  void borders() {
    if (loc.x() < -r) loc.setX(width+r);
    if (loc.y() < -r) loc.setY(height+r);
    if (loc.x() > width+r) loc.setX(-r);
    if (loc.y() > height+r) loc.setY(-r);
  }

  //*****SEPARATION************//
  //Function checks for nearby boids and steers away
  Vector3D separate (ArrayList boids) {
    float desiredseparation = 50.0f;
    Vector3D sum = new Vector3D(0,0,0);
    int count = 0;
    //for every boid in the system, check if it's too close
    for (int i = 0 ; i < boids.size(); i++) {
      Boid other = (Boid) boids.get(i);
      float d = Vector3D.distance(loc,other.getLoc());
      //if the distance is greater than 0 and less than an arbitrary amount
      if ((d > 0) && (d < desiredseparation)) {
        //calculate vector pointing away from neighbor
        Vector3D diff = Vector3D.sub(loc,other.getLoc());
        diff.normalize();
        diff.div(d);        //weight by distance
        sum.add(diff);
        count++;            //keep track of how many
      }
    }
    //divide by how many
    if (count > 0) {
      sum.div((float)count);
    }
    return sum;
  }
  
  //*****ALIGNMENT************//
  //for every nearby boid in the system, calculate the average velocity
  Vector3D align (ArrayList boids) {
    float neighbordist = 100.0f;
    Vector3D sum = new Vector3D(0,0,0);
    int count = 0;
    for (int i = 0 ; i < boids.size(); i++) {
      Boid other = (Boid) boids.get(i);
      float d = Vector3D.distance(loc,other.getLoc());
      if ((d > 0) && (d < neighbordist)) {
        sum.add(other.getVel());
        count++;
      }
    }
    if (count > 0) {
      sum.div((float)count);
      sum.limit(maxforce);
    }
    return sum;
  }

  //******COHESION************//
  //for the average location (i.e. center) of all nearby boids, calculate steering vector towards that location
  Vector3D cohesion (ArrayList boids) {
    float neighbordist = 100.0f;
    Vector3D sum = new Vector3D(0,0,0);   //start with empty vector to accumulate all locations
    int count = 0;
    for (int i = 0 ; i < boids.size(); i++) {
      Boid other = (Boid) boids.get(i);
      float d = Vector3D.distance(loc,other.getLoc());
      if ((d > 0) && (d < neighbordist)) {
        sum.add(other.getLoc()); //add location
        count++;
      }
    }
    if (count > 0) {
      sum.div((float)count);
      return steer(sum,false);  //steer towards the location
    }
    return sum;
  }
}
