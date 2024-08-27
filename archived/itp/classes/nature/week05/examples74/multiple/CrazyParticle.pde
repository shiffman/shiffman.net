/*A class to describe a one singular particle*/

class CrazyParticle extends Particle {

  //just adding one new variable to a CrazyParticle
  //it still has all the variables from "Particle", and we don't have to retype them!
  float theta;

  // The CrazyParticle constructor can call the parent class (super class) constructor
  CrazyParticle(Vector3D l) {
    //super says do everything from the constructor in Particle
    super(l);
    //one more line of code to deal with the new variable, theta
    theta = 0.0;

  }

  /*Notice we don't have the method "run" here b/c it inherits it
  from the class Particle*/

  // This update() method overrides the parent class update() method
  void update() {
    //do what update does
    super.update();
    //and then do some more stuff
    float theta_vel = (vel.x() * vel.magnitude()) / 10.0;
    theta += theta_vel;
  }

  //totally rewrite timer for this class
  void timer() {
    timer -= 0.2;
  }

  //function to display
  void render() {
    //render the ellipse just like in a regular particle
    super.render();

    //then do some fancy other stuff
    //to add a rotating line
    push();
    translate(loc.x(),loc.y());
    rotate(theta);
    stroke(0,255,255,timer);
    line(0,0,25,0);
    pop();
  }
}

