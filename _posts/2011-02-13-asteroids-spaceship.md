---
title: Asteroids Spaceship
author: Daniel
layout: post
dsq_thread_id:
  - 249554449
pvc_views:
  - 2023
categories:
  - processing.org
tags:
  - asteroids
  - book
  - nature of code
  - PVector
  - trigonometry
---
<p>One of the &#8220;trignometry + forces&#8221; exercises from my <a href="">upcoming Nature of Code book</a> is to implement in Processing the spaceship from asteroids.   Here&#8217;s a solution!</p>
<p><script type="application/processing"></p>
<p>// Nature of Code 2011
// Daniel Shiffman
// Chapter 3: Asteroids exercise
// http://www.shiffman.net</p>
<p>// Mover object
Spaceship ship;</p>
<p>void setup() {
  size(435, 200);
  frameRate(60);
  smooth();
  ship = new Spaceship();
}</p>
<p>void draw() {
  background(50); </p>
<p>  // Update location
  ship.update();
  // Wrape edges
  ship.wrapEdges();
  // Draw ship
  ship.display();</p>
<p>  fill(255);
  textSize(14);
  text("arrow keys turn ship, press 'z' to thrust",10,height-5);</p>
<p>  // Turn or thrust the ship depending on what key is pressed
  if (keyPressed) {
    if (key == CODED &#038;&#038; keyCode == LEFT) {
      ship.turn(-0.03);
    } else if (key == CODED &#038;&#038; keyCode == RIGHT) {
      ship.turn(0.03);
    } else if (key == 'z') {
      ship.applyThrust(); 
    }
  }
}</p>
<p>// Nature of Code 2011
// Daniel Shiffman
// Chapter 3: Asteroids</p>
<p>class Spaceship { 
  // All of our regular motion stuff
  PVector location;
  PVector velocity;
  PVector acceleration;</p>
<p>  // Arbitrary damping to slow down ship
  float damping = 0.995;
  float topspeed = 6;</p>
<p>  // Variable for heading!
  float heading = 0;</p>
<p>  // Size
  float r = 16;</p>
<p>  // Are we thrusting (to color boosters)
  boolean thrust = false;</p>
<p>  Spaceship() {
    location = new PVector(width/2,height/2);
    velocity = new PVector();
    acceleration = new PVector();
  } </p>
<p>  // Standard Euler integration
  void update() { 
    velocity.add(acceleration);
    velocity.mult(damping);
    velocity.limit(topspeed);
    location.add(velocity);
    acceleration.mult(0);
  }</p>
<p>  // Newton's law: F = M * A
  void applyForce(PVector force) {
    PVector f = force.get();
    //f.div(mass); // ignoring mass right now
    acceleration.add(f);
  }</p>
<p>  // Turn changes angle
  void turn(float a) {
    heading += a;
  }</p>
<p>  // Apply a thrust force
  void applyThrust() {
    // Offset the angle since we drew the ship vertically
    float angle = heading - PI/2;
    // Polar to cartesian for force vector!
    PVector force = new PVector(cos(angle),sin(angle));
    force.mult(0.1);
    applyForce(force); 
    // To draw booster
    thrust = true;
  }</p>
<p>  void wrapEdges() {
    float buffer = r*2;
    if (location.x > width +  buffer) location.x = -buffer;
    else if (location.x < -buffer) location.x = width+buffer;
    if (location.y > height + buffer) location.y = -buffer;
    else if (location.y < -buffer) location.y = height+buffer;
  }</p>
<p>  // Draw the ship
  void display() { 
    stroke(255);
    pushMatrix();
    translate(location.x,location.y+r);
    rotate(heading);
    fill(100);
    if (thrust) fill(255,0,0);
    // Booster rockets
    rect(-r/2,r,r/3,r/2);
    rect(r/2,r,r/3,r/2);
    fill(100);
    // A triangle
    beginShape();
    vertex(-r,r);
    vertex(0,-r);
    vertex(r,r);
    endShape(CLOSE);
    rectMode(CENTER);
    popMatrix();</p>
<p>    thrust = false;
  }
}
</script></p>
<p>Source: <a href="http://www.shiffman.net/itp/classes/nature/week04_s11/_03_asteroids.zip">_03_asteroids.zip</a></script></p>
