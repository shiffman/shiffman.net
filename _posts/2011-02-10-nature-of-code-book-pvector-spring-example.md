---
title: 'Nature of Code book: PVector Spring example'
author: Daniel
layout: post
dsq_thread_id:
  - 249850857
pvc_views:
  - 2610
categories:
  - nature of code
  - processing.org
tags:
  - nature of code
  - processing.org
  - springs
---
<p>I&#8217;m working this week on Chapter 3 of my <a href="https://www.kickstarter.com/projects/shiffman/the-nature-of-code-book-project">upcoming Nature of Code book</a>.   For the most part, if you are looking to connect particles with springs, I recommend the wonderful verlet physics of <a href="http://toxiclibs.org/">toxiclibs</a>, and I have some examples for that <a href="http://www.shiffman.net/teaching/nature/toxiclibs/">here</a>.  Nevertheless, I am including an elementary implementation of a single &#8220;bob&#8221; connected  to an &#8220;anchor&#8221; point via a &#8220;spring&#8221; force.   The example implements <a href="http://en.wikipedia.org/wiki/Hooke's_law">Hooke&#8217;s Law</a> (Spring Force = -k * displacement) with the <a href="http://processing.org/reference/PVector.html">PVector</a> class, using the Euler integration model of all my other examples.  Here it is below.</p>
<p><script type="application/processing">
// Mover object
Bob bob;</p>
<p>// Spring object
Spring spring;</p>
<p>void setup() {
  size(430, 200);
  smooth();
  frameRate(60);
  // Create objects at starting location
  // Note third argument in Spring constructor is "rest length"
  spring = new Spring(width/2,10,100); 
  bob = new Bob(width/2,100); </p>
<p>}</p>
<p>void draw()  {
  background(50); 
  // Apply a gravity force to the bob
  PVector gravity = new PVector(0,1);
  bob.applyForce(gravity);</p>
<p>  // Connect the bob to the spring (this calculates the force)
  spring.connect(bob);
  // Constrain spring distance between min and max
  spring.constrainLength(bob,30,200);
  bob.dragIt(mouseX,mouseY);
  bob.update();</p>
<p>  spring.displayLine(bob);
  bob.display();
  spring.display();</p>
<p>  fill(255);
  text("click on bob to drag",10,height-5);</p>
<p>}</p>
<p>// For mouse interaction with bob</p>
<p>void mousePressed()  {
  bob.clicked(mouseX,mouseY);
}</p>
<p>void mouseReleased()  {
  bob.stopDragging(); 
}</p>
<p>class Bob { 
  PVector location;
  PVector velocity;
  PVector acceleration;
  float mass = 10;</p>
<p>  // Arbitrary damping to simulate friction / drag 
  float damping = 0.98;</p>
<p>  // For mouse interaction
  PVector drag;
  boolean dragging = false;</p>
<p>  // Constructor
  Bob(float x, float y) {
    location = new PVector(x,y);
    velocity = new PVector();
    acceleration = new PVector();
    drag = new PVector();
  } </p>
<p>  // Standard Euler integration
  void update() { 
    velocity.add(acceleration);
    velocity.mult(damping);
    location.add(velocity);
    acceleration.mult(0);
  }</p>
<p>  // Newton's law: F = M * A
  void applyForce(PVector force) {
    PVector f = force.get();
    f.div(mass);
    acceleration.add(f);
  }</p>
<p>  // Draw the bob
  void display() { 
    stroke(255);
    fill(100);
    if (dragging) {
      fill(255);
    }
    ellipse(location.x,location.y,mass*2,mass*2);
  } </p>
<p>  void dragIt(int mx, int my) {
    if (dragging) {
      location.x = mx + drag.x;
      location.y = my + drag.y;
    }
  }
  // This checks to see if we clicked on the mover
  void clicked(int mx, int my) {
    float d = dist(mx,my,location.x,location.y);
    if (d < mass) {
      dragging = true;
      drag.x = location.x-mx;
      drag.y = location.y-my;
    }
  }</p>
<p>  void stopDragging() {
    dragging = false;
  }</p>
<p>}</p>
<p>class Spring { </p>
<p>  // Location
  PVector anchor;</p>
<p>  // Rest length and spring constant
  float len;
  float k = 0.1;</p>
<p>  // Constructor
  Spring(float x, float y, int l) {
    anchor = new PVector(x,y);
    len = l;
  } </p>
<p>  // Calculate spring force
  void connect(Bob b) {
    // Vector pointing from anchor to bob location
    PVector force = PVector.sub(b.location,anchor);
    // What is distance
    float d = force.mag();
    // Stretch is difference between current distance and rest length
    float stretch = d - len;</p>
<p>    // Calculate force according to Hooke's Law
    // F = k * stretch
    force.normalize();
    force.mult(stretch);
    force.mult(-k);
    b.applyForce(force);
  }</p>
<p>  // Constrain the distance between bob and anchor between min and max
  void constrainLength(Bob b, float minlen, float maxlen) {
    PVector dir = PVector.sub(b.location,anchor);
    float d = dir.mag();
    // Is it too short?
    if (d < minlen) {
      dir.normalize();
      dir.mult(minlen);
      // Reset location and stop from moving (not realistic physics)
      b.location = PVector.add(anchor,dir);
      b.velocity.mult(0);
    // Is it too long?
    } else if (d > maxlen) {
      dir.normalize();
      dir.mult(maxlen);
      // Reset location and stop from moving (not realistic physics)
      b.location = PVector.add(anchor,dir);
      b.velocity.mult(0);
    }
  }</p>
<p>  void display() { 
    fill(100);
    rectMode(CENTER);
    rect(anchor.x,anchor.y,10,10);
  }</p>
<p>  void displayLine(Bob b) {
    stroke(255);
    line(b.location.x,b.location.y,anchor.x,anchor.y);
  }</p>
<p>}
</script></p>
<p>Source: <a href="http://www.shiffman.net/itp/classes/nature/week04_s11/_03spring.zip">_03spring.zip</a></p>
