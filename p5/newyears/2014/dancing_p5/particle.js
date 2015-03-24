// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com
// Notice how we are using inheritance here!

// We could have just stored a reference to a VerletParticle object


function Particle(loc, r_, s) {
  this.p = new VerletParticle2D(loc);
  this.r = r_;
  if (s) {
    this.img = loadImage(s);
  }
  this.drag = false;
  //physics.addBehavior(new AttractionBehavior(p, r*2, -r));
}

// All we're doing really is adding a display() function to a VerletParticle
Particle.prototype.display = function() {
  fill(127);
  stroke(0);
  strokeWeight(2);
  if (this.img) {
    imageMode(CENTER);
    image(this.img, this.p.x, this.p.y);
  } 
  else {
    ellipse(this.p.x, this.p.y, this.r*2, this.r*2);
  }
}

Particle.prototype.control = function() {
  if (this.drag) {
    //lock();
    //p.x = mouseX;
    //p.y = mouseY;
    //unlock();
  }
}

Particle.prototype.grab = function() {
  this.drag = true;
}

Particle.prototype.lock = function() {
  this.p.lock(); 
}

Particle.prototype.unlock = function() {
  this.p.unlock(); 
}
Particle.prototype.release = function() {
  this.drag = false;
}

Particle.prototype.over= function(px, py) {
  if (dist(this.p.x, this.p.y, px, py) < r*2) {
    return true;
  } 
  else {
    return false;
  }
}


