//our Boid extends Particle, has a few more properties

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
  
  void run() {
    update();
    borders();
    render();
  }
  
  //function to update location
  void update() {
    vel.add(acc);
    vel.limit(maxspeed);
    loc.add(vel);
    ///***DRAW STEERING VECTOR****//
    //drawVector(acc,loc,250.0f);
    acc.setXYZ(0,0,0);
  }

  void seek(Vector3D target) {
    acc.add(steer(target,false));
  }
 
  void arrive(Vector3D target) {
    acc.add(steer(target,true));
  }

  void wander() {
    float wanderR = 40.0f;           //radius for our "wander circle"
    float wanderD = 80.0f;           //distance for our "wander circle"
    float change = 0.5f;
    wandertheta += random(-change,change);     //randomly change wander theta

    //now we have to calculate the new location to steer towards on the wander circle
    Vector3D circleloc = vel.copy();  //start with velocity
    circleloc.normalize();    //normalize to get heading
    circleloc.mult(wanderD);  //multiply by distance
    circleloc.add(loc);       //make it relative to boid's location
    //calc the offset on the wander circle
    float actualtheta = wandertheta + vel.heading2D(); //adjust wandertheta to circle direction
    Vector3D circleOffSet = new Vector3D(wanderR*cos(actualtheta),wanderR*sin(actualtheta));
    Vector3D target = Vector3D.add(circleloc,circleOffSet);
    acc.add(steer(target,false));  //steer towards it
    
    //DEBUG DRAWING STUFF ASSOCIATED WITH WANDERING
    //drawWanderStuff(loc,circleloc,target,wanderR);
    
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
    fill(200);
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

}
