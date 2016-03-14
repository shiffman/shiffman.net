


function Skeleton(x, y, scl_, who) {
  this.parts = [];
  this.dparts = [];
  this.springs = [];
  this.hsprings = [];

  this.origin = new Vec2D(x, y);
  this.scl = scl_;
  // Make two particles
  this.head = new Particle(new Vec2D(x, y), 16);
  this.neck = new Particle(new Vec2D(x, y+0*this.scl), 4);


  this.dhead = new Part(this.head, this.neck, who+"head.png", "head");

  this.lShoulder = new Particle(new Vec2D(x+20*this.scl, y+80*this.scl), 4*this.scl);
  this.rShoulder = new Particle(new Vec2D(x-20*this.scl, y+80*this.scl), 4*this.scl);
  this.lElbow = new Particle(new Vec2D(x+100*this.scl, y+80*this.scl), 8*this.scl);
  this.rElbow = new Particle(new Vec2D(x-100*this.scl, y+80*this.scl), 8*this.scl);  
  this.lHand = new Particle(new Vec2D(x+200*this.scl, y+80*this.scl), 16*this.scl);
  this.rHand = new Particle(new Vec2D(x-200*this.scl, y+80*this.scl), 16*this.scl);  
  this.belly = new Particle(new Vec2D(x, y+120*this.scl), 4*this.scl);

  //dbody = new Part(neck,belly,who+"body.png");
  //dlarm1 = new Part(lShoulder,lElbow,who+"larm1.png");
  //dlarm2 = new Part(lElbow,lHand,who+"larm2.png");

  this.sign = new Part(this.lElbow,this.lHand,null,"sign");

  this.lKnee = new Particle(new Vec2D(x+50*this.scl, y+230*this.scl), 4*this.scl);
  this.rKnee = new Particle(new Vec2D(x+50*this.scl, y+230*this.scl), 4*this.scl);

  this.lFoot = new Particle(new Vec2D(x+50*this.scl, y+280*this.scl), 16*this.scl);
  this.rFoot = new Particle(new Vec2D(x+50*this.scl, y+280*this.scl), 16*this.scl);


  this.head.lock();
  //rHand.lock();
  //lHand.lock();
  //lShoulder.lock();
  //rShoulder.lock();

  // Make a spring connecting both Particles
  var headneck=new VerletSpring2D(this.head.p, this.neck.p, 5*this.scl, 0.01);
  var spring2=new VerletSpring2D(this.neck.p, this.lShoulder.p, 20*this.scl, 0.01);
  var spring3=new VerletSpring2D(this.neck.p, this.rShoulder.p, 20*this.scl, 0.01);
  var spring4=new VerletSpring2D(this.rShoulder.p, this.rElbow.p, 40*this.scl, 0.01);
  var spring5=new VerletSpring2D(this.lShoulder.p, this.lElbow.p, 40*this.scl, 0.01);
  var shoulders = new VerletMinDistanceSpring2D(this.lShoulder.p, this.rShoulder.p, 100*this.scl, 0.01);


  var spring6=new VerletSpring2D(this.rHand.p, this.rElbow.p, 40*this.scl, 0.01);
  var spring7=new VerletSpring2D(this.lHand.p, this.lElbow.p, 40*this.scl, 0.01);

  var spring8=new VerletSpring2D(this.neck.p, this.belly.p, 50, 0.01);
  var belly1 = new VerletMinDistanceSpring2D(this.belly.p, this.rShoulder.p, 100*this.scl, 0.01);
  var belly2 = new VerletMinDistanceSpring2D(this.belly.p, this.lShoulder.p, 100*this.scl, 0.01);

  var head1 = new VerletMinDistanceSpring2D(this.head.p, this.rShoulder.p, 100*this.scl, 0.01);
  var head2 = new VerletMinDistanceSpring2D(this.head.p, this.lShoulder.p, 100*this.scl, 0.01);


  var spring9=new VerletSpring2D(this.belly.p, this.lKnee.p, 80*this.scl, 0.01);
  var spring10=new VerletSpring2D(this.belly.p, this.rKnee.p, 80*this.scl, 0.01);
  var knees = new VerletMinDistanceSpring2D(this.lKnee.p, this.rKnee.p, 50*this.scl, 0.01);

  var spring11=new VerletSpring2D(this.lFoot.p, this.lKnee.p, 80*this.scl, 0.01);
  var spring12=new VerletSpring2D(this.rFoot.p, this.rKnee.p, 80*this.scl, 0.01);

  this.parts.push(this.head);
  this.parts.push(this.neck);
  this.parts.push(this.lShoulder);
  this.parts.push(this.rShoulder);
  this.parts.push(this.lElbow);
  this.parts.push(this.rElbow);
  this.parts.push(this.lHand);
  this.parts.push(this.rHand);
  this.parts.push(this.belly);
  this.parts.push(this.lKnee);
  this.parts.push(this.rKnee);
  this.parts.push(this.lFoot);
  this.parts.push(this.rFoot);

  //dparts.push(dbody);
  this.dparts.push(this.dhead);
  //dparts.push(dlarm1);
  //dparts.push(dlarm2);
  this.dparts.push(this.sign);

  this.springs.push(headneck);
  this.springs.push(spring2);
  this.springs.push(spring3);
  this.springs.push(spring4);
  this.springs.push(spring5);
  this.springs.push(spring6);
  this.springs.push(spring7);
  this.springs.push(spring8);
  this.springs.push(shoulders);
  this.springs.push(belly1);
  this.springs.push(belly2);
  this.springs.push(spring9);
  this.springs.push(spring10);
  this.springs.push(spring11);
  this.springs.push(spring12);
  this.hsprings.push(head1);
  this.hsprings.push(head2);
  this.hsprings.push(knees);

  for (var i = 0; i < this.parts.length; i++) {
    var p = this.parts[i];
    physics.addParticle(p.p);
  }

  for (var i = 0; i < this.springs.length; i++) {
    var s = this.springs[i];
    physics.addSpring(s);
  }

  for (var i = 0; i < this.hsprings.length; i++) {
    var s = this.hsprings[i];
    physics.addSpring(s);
  }

  this.hangle = 0;
  this.vangle = 0;
}

Skeleton.prototype.click = function(mx, my) {
  for (var i = 0; i < this.parts.length; i++) {
    var p = this.parts[i];
    if (p.over(mx, my)) {
      p.grab();
    }
  }
}

Skeleton.prototype.hdance = function() {
  if (!this.head.drag) {
    this.head.p.x = this.origin.x + sin(this.hangle)*20; 
    //head.y = origin.y + cos(angle)*20; 
    this.hangle += 0.3;
  }
}

Skeleton.prototype.vdance = function() {
  //head.x = origin.x + sin(hangle)*20; 
  if (!this.head.drag) {
    this.head.p.y = this.origin.y + cos(this.vangle)*20; 
    this.vangle += 0.3;
  }
}

Skeleton.prototype.release = function() {
  for (var i = 0; i < this.parts.length; i++) {
    var p = this.parts[i];
    p.release();
  }
}

Skeleton.prototype.control = function() {
  for (var i = 0; i < this.parts.length; i++) {
    var p = this.parts[i];
    p.control();
  }
}


Skeleton.prototype.display = function() {
  for (var i = 0; i < this.springs.length; i++) {
    var s = this.springs[i];
    stroke(0);
    line(s.a.x, s.a.y, s.b.x, s.b.y);
  }

  if (showHidden) {
    for (var i = 0; i < this.hsprings.length; i++) {
      var s = this.hsprings[i];
      stroke(0);
      line(s.a.x, s.a.y, s.b.x, s.b.y);
    }
  }


  for (var i = 0; i < this.parts.length; i++) {
    //var p = this.parts[i];
    //p.display();
  }

  for (var i = 0; i < this.dparts.length; i++) {
    var p = this.dparts[i];
    p.display();
  }
}

