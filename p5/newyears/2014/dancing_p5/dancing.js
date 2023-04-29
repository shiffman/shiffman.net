// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// Simple Toxiclibs Spring
var VerletPhysics2D = toxi.physics2d.VerletPhysics2D,
  GravityBehavior = toxi.physics2d.behaviors.GravityBehavior,
  AttractionBehavior = toxi.physics2d.behaviors.AttractionBehavior,
  VerletParticle2D = toxi.physics2d.VerletParticle2D,
  VerletSpring2D = toxi.physics2d.VerletSpring2D,
  VerletMinDistanceSpring2D = toxi.physics2d.VerletMinDistanceSpring2D,
  Vec2D = toxi.geom.Vec2D,
  Rect = toxi.geom.Rect;

// Reference to physics world
var physics;

var s1;
var s2;

//make a new HTML5 audio object named audio
// Audio audio = new Audio();
// make string that will house the audio extension
var fileExt;

var showHidden = false;

var mouseAttractor;

var mousePos;

var dance = false;

var audio;

var started = false;

function preload() {
  audio = loadSound('/p5/newyears/2014/auld2.mp3');
}

function setup() {
  var canvas = createCanvas(600, 400);
  canvas.parent('dancing');

  // Initialize the physics
  physics = new VerletPhysics2D();
  physics.addBehavior(new GravityBehavior(new Vec2D(0, 0.5)));

  // Set the world's bounding box
  physics.setWorldBounds(new Rect(0, 0, width, height));

  s1 = new Skeleton((3 * width) / 4, 100, 1, 'e');
  s2 = new Skeleton(width / 4, 160, 0.75, 'o');
  dance = true;
}

var counter = 0;

function draw() {
  if (!started) {
    background(0);
    textAlign(CENTER);
    textSize(24);
    fill(255);
    noStroke();
    text('click to begin', width / 2, height / 2);
  } else {
    // Update the physics world
    //if (dance || frameCount < 2) {
    physics.update();
    counter++;
    //}

    background(255);

    push();
    translate(width / 2, height / 2);
    rotate(counter * 0.01);
    var delta = TWO_PI / 20;
    var r = width;
    colorMode(HSB);
    for (var a = 0; a < TWO_PI; a += delta) {
      var c = color(
        int(map(a + counter / 20.0, 0, TWO_PI, 0, 255)) % 255,
        255,
        255
      );
      fill(c);
      stroke(c);

      beginShape();
      vertex(0, 0);
      vertex(r * cos(a), r * sin(a));
      vertex(r * cos(a + delta), r * sin(a + delta));
      endShape(CLOSE);
    }
    fill(255);
    stroke(255);
    ellipse(0, 0, 8, 8);
    pop();
    colorMode(RGB);

    s1.display();
    s1.control();
    s2.display();
    s2.control();

    s1.hdance();
    s2.vdance();
  }
}

function mousePressed() {
  if (!started) {
    started = true;
    audio.play();
  } else {
    //s1.click(mouseX, mouseY);
    //s2.click(mouseX, mouseY);

    mousePos = new Vec2D(mouseX, mouseY);
    // create a new positive attraction force field around the mouse position (radius=250px)
    mouseAttractor = new AttractionBehavior(mousePos, width, -5);
    physics.addBehavior(mouseAttractor);
  }
}

function mouseDragged() {
  // update mouse attraction focal point
  mousePos.set(mouseX, mouseY);
}

function mouseReleased() {
  // remove the mouse attraction when button has been released
  physics.removeBehavior(mouseAttractor);
  //s1.release();
  //s2.release();
}

function keyPressed() {
  if (key == ' ') {
    //showHidden = !showHidden;
  }
}
