

class Skeleton {

  float scl = 1.0;

  Vec2D origin;

  Particle head;
  Particle neck;
  Particle lShoulder;
  Particle rShoulder;
  Particle lElbow;
  Particle rElbow;
  Particle lHand;
  Particle rHand;

  Part dhead;

  Part sign;




  Particle belly;
  Particle lKnee;
  Particle rKnee;
  Particle lFoot;
  Particle rFoot;

  ArrayList<Particle> parts = new ArrayList<Particle>();
  ArrayList<Part> dparts = new ArrayList<Part>();
  ArrayList<VerletSpring2D> springs = new ArrayList<VerletSpring2D>();
  ArrayList<VerletSpring2D> hsprings = new ArrayList<VerletSpring2D>();

  Skeleton(float x, float y, float scl_, String who) {
    origin = new Vec2D(x, y);
    scl = scl_;
    // Make two particles
    head = new Particle(new Vec2D(x, y), 16);
    neck = new Particle(new Vec2D(x, y+0*scl), 4);


    dhead = new Part(head,neck,who+"head.png","head");

    lShoulder = new Particle(new Vec2D(x+20*scl, y+80*scl), 4*scl);
    rShoulder = new Particle(new Vec2D(x-20*scl, y+80*scl), 4*scl);
    lElbow = new Particle(new Vec2D(x+100*scl, y+80*scl), 8*scl);
    rElbow = new Particle(new Vec2D(x-100*scl, y+80*scl), 8*scl);  
    lHand = new Particle(new Vec2D(x+200*scl, y+80*scl), 16*scl);
    rHand = new Particle(new Vec2D(x-200*scl, y+80*scl), 16*scl);  
    belly = new Particle(new Vec2D(x, y+120*scl), 4*scl);

    //dbody = new Part(neck,belly,who+"body.png");
    //dlarm1 = new Part(lShoulder,lElbow,who+"larm1.png");
    //dlarm2 = new Part(lElbow,lHand,who+"larm2.png");


    sign = new Part(lElbow,lHand,null,"sign");



    lKnee = new Particle(new Vec2D(x+50*scl, y+230*scl), 4*scl);
    rKnee = new Particle(new Vec2D(x+50*scl, y+230*scl), 4*scl);

    lFoot = new Particle(new Vec2D(x+50*scl, y+280*scl), 16*scl);
    rFoot = new Particle(new Vec2D(x+50*scl, y+280*scl), 16*scl);


    head.lock();
    //rHand.lock();
    //lHand.lock();
    //lShoulder.lock();
    //rShoulder.lock();

    // Make a spring connecting both Particles
    VerletSpring2D headneck=new VerletSpring2D(head.p, neck.p, 5*scl, 0.01);
    VerletSpring2D spring2=new VerletSpring2D(neck.p, lShoulder.p, 20*scl, 0.01);
    VerletSpring2D spring3=new VerletSpring2D(neck.p, rShoulder.p, 20*scl, 0.01);
    VerletSpring2D spring4=new VerletSpring2D(rShoulder.p, rElbow.p, 40*scl, 0.01);
    VerletSpring2D spring5=new VerletSpring2D(lShoulder.p, lElbow.p, 40*scl, 0.01);
    VerletMinDistanceSpring2D shoulders = new VerletMinDistanceSpring2D(lShoulder.p, rShoulder.p, 100*scl, 0.01);


    VerletSpring2D spring6=new VerletSpring2D(rHand.p, rElbow.p, 40*scl, 0.01);
    VerletSpring2D spring7=new VerletSpring2D(lHand.p, lElbow.p, 40*scl, 0.01);

    VerletSpring2D spring8=new VerletSpring2D(neck.p, belly.p, 50, 0.01);
    VerletMinDistanceSpring2D belly1 = new VerletMinDistanceSpring2D(belly.p, rShoulder.p, 100*scl, 0.01);
    VerletMinDistanceSpring2D belly2 = new VerletMinDistanceSpring2D(belly.p, lShoulder.p, 100*scl, 0.01);

    VerletMinDistanceSpring2D head1 = new VerletMinDistanceSpring2D(head.p, rShoulder.p, 100*scl, 0.01);
    VerletMinDistanceSpring2D head2 = new VerletMinDistanceSpring2D(head.p, lShoulder.p, 100*scl, 0.01);


    VerletSpring2D spring9=new VerletSpring2D(belly.p, lKnee.p, 80*scl, 0.01);
    VerletSpring2D spring10=new VerletSpring2D(belly.p, rKnee.p, 80*scl, 0.01);
    VerletMinDistanceSpring2D knees = new VerletMinDistanceSpring2D(lKnee.p, rKnee.p, 50*scl, 0.01);

    VerletSpring2D spring11=new VerletSpring2D(lFoot.p, lKnee.p, 80*scl, 0.01);
    VerletSpring2D spring12=new VerletSpring2D(rFoot.p, rKnee.p, 80*scl, 0.01);

    parts.add(head);
    parts.add(neck);
    parts.add(lShoulder);
    parts.add(rShoulder);
    parts.add(lElbow);
    parts.add(rElbow);
    parts.add(lHand);
    parts.add(rHand);
    parts.add(belly);
    parts.add(lKnee);
    parts.add(rKnee);
    parts.add(lFoot);
    parts.add(rFoot);

    //dparts.add(dbody);
    dparts.add(dhead);
    //dparts.add(dlarm1);
    //dparts.add(dlarm2);
    dparts.add(sign);

    springs.add(headneck);
    springs.add(spring2);
    springs.add(spring3);
    springs.add(spring4);
    springs.add(spring5);
    springs.add(spring6);
    springs.add(spring7);
    springs.add(spring8);
    springs.add(shoulders);
    springs.add(belly1);
    springs.add(belly2);
    springs.add(spring9);
    springs.add(spring10);
    springs.add(spring11);
    springs.add(spring12);
    hsprings.add(head1);
    hsprings.add(head2);
    hsprings.add(knees);

    for (Particle p : parts) {
      physics.addParticle(p.p);
    }

    for (VerletSpring2D s : springs) {
      physics.addSpring(s);
    }

    for (VerletSpring2D s : hsprings) {
      physics.addSpring(s);
    }
  }

  void click(float mx, float my) {
    for (Particle p : parts) {
      if (p.over(mx, my)) {
        p.grab();
      }
    }
  }

  float hangle = 0;
  void hdance() {
    if (!head.drag) {
      head.p.x = origin.x + sin(hangle)*20; 
      //head.y = origin.y + cos(angle)*20; 
      hangle += 0.3;
    }
  }

  float vangle = 0;

  void vdance() {
    //head.x = origin.x + sin(hangle)*20; 
    if (!head.drag) {
      head.p.y = origin.y + cos(vangle)*20; 
      vangle += 0.3;
    }
  }


  void release() {
    for (Particle p : parts) {
      p.release();
    }
  }

  void control() {
    for (Particle p : parts) {
      p.control();
    }
  }


  void display() {
    for (VerletSpring2D s : springs) {
      stroke(0);
      line(s.a.x, s.a.y, s.b.x, s.b.y);
    }

    if (showHidden) {
      for (VerletSpring2D s : hsprings) {
        stroke(0, 255, 0);
        //line(s.a.x, s.a.y, s.b.x, s.b.y);
      }
    }


    for (Particle p : parts) {
      //p.display();
    }

    for (Part p : dparts) {
      p.display();
    }
  }
}

