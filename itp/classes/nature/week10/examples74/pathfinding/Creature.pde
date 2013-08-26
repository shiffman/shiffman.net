//Daniel Shiffman, Nature of Code, 3/29/2005
//http://stage.nyu.edu/nature

//Creature class -- this is just like our Boid / Particle class
//the only difference is that it has DNA & fitness

class Creature {

  //*ALL THE STUFF WE HAD BEFORE*//
  Vector3D loc;
  Vector3D vel;
  Vector3D acc;
  float r;

  //**FITNESS AND DNA**//
  float fitness;
  DNA genes;

  boolean stopped;  //am I stuck?
  int finish;       //what was my finish? (first, second, etc. . . )

  //constructor
  Creature(Vector3D l, DNA dna, int f) {
    acc = new Vector3D(0.0,0.0,0.0);
    vel = new Vector3D(0.0,0.0,0.0);
    loc = l.copy();
    r = 2.0f;
    genes = dna;
    stopped = false;
    finish = f;
  }

  //***FITNESS FUNCTION*****//
  // distance = distance from target
  // finish = what order did i finish (first, second, etc. . .)
  // f(distance,finish) =   (1.0f / finish^1.5) * (1.0f / distance^6);
  // a lower finish is rewarded (exponentially) and/or shorter distance to target (exponetially)
  void calcFitness() {
    float d = Vector3D.distance(loc,target);
    if (d < diam/2) {
      d = 1.0f;
    }
    //reward finishing faster and getting closer
    fitness = (1.0f / pow(finish,1.5f)) * (1.0f / (pow(d,6)));
  }

  void setFinish(int f) {
    finish = f;
    //print(finish + " ");
  }

  //Run in relation to all the obstacles
  //If I'm stuck, don't bother updating or checking for intersection
  void run(ArrayList o) {
    if (!stopped) {
      update();
      //if I hit an edge or an obstacle
      if ((borders()) || (obstacles(o))) {
        stopped = true;
      }
    }
    //draw me!
    render();
  }

  //*DID I HIT AN EDGE??*//
  boolean borders() {
    if ((loc.x() < 0) || (loc.y() < 0) || (loc.x() > width) || (loc.y() > height)) {
      return true;
    } else {
      return false;
    }
  }

  //**DID I MAKE IT TO THE TARGET??*//
  boolean finished() {
    float d = Vector3D.distance(loc,target);
    if ( d < diam/2) {
      stopped = true;
      return true;
    }
    return false;
  }

  //**DID I HIT AN OBSTACLE?**//
  boolean obstacles(ArrayList o) {
    for (int i = 0; i < o.size(); i++) {
      Obstacle obs = (Obstacle) o.get(i);
      if (obs.contains(loc)) {
        return true;
      }
    }
    return false;
  }

  void update() {
    if (!finished()) {
      //where are we?  Our location will tell us what steering vector to look up in our DNA;
      int x = (int) loc.x()/gridscale;
      int y = (int) loc.y()/gridscale;
      x = constrain(x,0,width/gridscale-1);  //make sure we are not off the edge
      y = constrain(y,0,height/gridscale-1); //make sure we are not off the edge

      //Get the steering vector out of our genes in the right spot
      acc.add(genes.getGene(x+y*width/gridscale));

      //this is all the same stuff we've done before
      acc.mult(maxforce);
      vel.add(acc);
      vel.limit(maxspeed);
      loc.add(vel);
      acc.setXYZ(0,0,0);
    }
  }

  void render() {
    //draws a triangle rotated in the direction of velocity
    float theta = vel.heading2D() + radians(90);
    if (stopped) fill(255,25); else fill(255);
    noStroke();
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

  float getFitness() {
    return fitness;
  }

  DNA getGenes() {
    return genes;
  }
  
  boolean stopped() {
    return stopped;
  }

}
