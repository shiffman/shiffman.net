

var s1 = function(sketch) {
 
  sketch.setup = function() {
    sketch.createCanvas(400, 64);
  };

  sketch.draw = function() {
    sketch.background(255);
    sketch.fill(127);
    sketch.stroke(0);
    sketch.textSize(32);
    sketch.text('Drawing some text.', 10, 48);
    sketch.noLoop();
  };
}

var p51 = new p5(s1, 'canvas1');



var s2 = function(sketch) {
 
  sketch.setup = function() {
    sketch.createCanvas(400, 200-50+32);
  };

  sketch.draw = function() {
    sketch.background(255);
    
    sketch.stroke(0, 51);
    sketch.line(200, 0, 200, 200);

    sketch.noStroke();
    sketch.fill(0);
    sketch.textSize(32);
    sketch.textFont('Arial');
    sketch.textAlign(sketch.LEFT);
    sketch.text('Arial', 200, 50);
    sketch.textFont('Georgia');
    sketch.textAlign(sketch.CENTER);
    sketch.text('Georgia', 200, 100);
    sketch.textFont('Monaco');
    sketch.textAlign(sketch.RIGHT);
    sketch.text('Monaco', 200, 150);
    sketch.noLoop();
  };
}

var p52 = new p5(s2, 'canvas2');


var s3 = function(sketch) {
 
  sketch.setup = function() {
    sketch.createCanvas(400, 64);
  };

  sketch.draw = function() {
    sketch.background(255);
    sketch.fill(0);
    sketch.noStroke();
    sketch.textSize(32);
    sketch.textFont('Pacifico');
    sketch.text('Pacifico', 10, 48);
    if (sketch.frameCount > 5000) {
      sketch.noLoop();
    }
  };
}

var p53 = new p5(s3, 'canvas3');


var s4 = function(sketch) {
 
  sketch.setup = function() {
    sketch.createCanvas(400, 64);
  };

  sketch.draw = function() {
    sketch.background(255);
    sketch.fill(0);
    sketch.noStroke();
    sketch.textSize(16);
    var s = 'rectangle below has the same width';
    sketch.text(s, 10, 32);
    sketch.rect(10, 36, sketch.textWidth(s), 10);
    sketch.noLoop();
  };
}

var p54 = new p5(s4, 'canvas4');

var s5 = function(sketch) {
 
  sketch.setup = function() {
    sketch.createCanvas(600, 64);
  };

  sketch.draw = function() {
    sketch.background(255);
    sketch.fill(0);
    sketch.noStroke();
    sketch.textSize(16);
    var s = 'one character at a time';
    var x = 10;
    for (var i = 0; i < s.length; i++) {
      sketch.text(s.charAt(i), x, 32);
      x += 12;
    }
    sketch.noLoop();
  };
}

var p55 = new p5(s5, 'canvas5');



var s6 = function(sketch) {
 
  sketch.setup = function() {
    sketch.createCanvas(400, 64);
  };

  sketch.draw = function() {
    sketch.background(255);
    sketch.fill(0);
    sketch.noStroke();
    sketch.textSize(16);
    var s = 'one character at a time';
    var x = 10;
    for (var i = 0; i < s.length; i++) {
      sketch.textSize(sketch.random(12,64));
      sketch.text(s.charAt(i), x, 60);
      x += sketch.textWidth(s.charAt(i));
    }
    sketch.noLoop();
  };
}

var p56 = new p5(s6, 'canvas6');


var s7 = function(sketch) {
  var r = 100;

  // The width and height of the boxes
  var w = 24;
  var h = 24;

  sketch.setup = function() {
    sketch.createCanvas(250, 250);
  }

  sketch.draw = function() {
    sketch.background(255);

    // Start in the center and draw the circle
    sketch.translate(sketch.width/2, sketch.height/2);
    sketch.noFill();
    sketch.stroke(0);
    // Our curve is a circle with radius r in the center of the window.
    sketch.ellipse(0, 0, r*2, r*2); 
    // 10 boxes along the curve
    var totalBoxes = sketch.floor(sketch.TWO_PI*r/w);
    // We must keep track of our position along the curve
    var arclength = 0;
    // For every box
    for (var i = 0; i < totalBoxes; i++) {
      // Each box is centered so we move half the width
      arclength += w/2; 

      // Angle in radians is the arclength divided by the radius
      var theta = arclength / r;

      sketch.push();
      // Polar to cartesian coordinate conversion
      sketch.translate(r*sketch.cos(theta), r*sketch.sin(theta));
      // Rotate the box
      sketch.rotate(theta);

      // Display the box
      sketch.fill(0, 100);
      sketch.rectMode(sketch.CENTER);
      sketch.rect(0, 0, w, h);
      sketch.pop();

      // Move halfway again
      arclength += w/2;
    }
    sketch.noLoop();
  }
}

var p57 = new p5(s7,'canvas7');

var s8 = function(sketch) {
    // The message to be displayed
  var message = "text along a curve";

  // The radius of a circle
  var r = 100;

  sketch.setup = function() {
    sketch.createCanvas(250, 250);
    sketch.textFont('Georgia');
    sketch.textSize(32);
    // The text must be centered!
    sketch.textAlign(sketch.CENTER);
  }

  sketch.draw = function() {
    sketch.background(255);

    // Start in the center and draw the circle
    sketch.translate(sketch.width/2, sketch.height/2);
    sketch.noFill();
    sketch.stroke(0);
    sketch.ellipse(0, 0, r*2, r*2);

    // We must keep track of our position along the curve
    var arclength = 0;

    // For every box
    for (var i = 0; i < message.length; i++) {

      // The character and its width
      var currentChar = message.charAt(i);
      // Instead of a constant width, we check the width of each character.
      var w = sketch.textWidth(currentChar); 
      // Each box is centered so we move half the width
      arclength += w/2;

      // Angle in radians is the arclength divided by the radius
      // Starting on the left side of the circle by adding PI
      var theta = sketch.PI + arclength / r;

      sketch.push();

      // Polar to Cartesian conversion allows us to find the povar along the curve. See Chapter 13 for a review of this concept.
      sketch.translate(r*sketch.cos(theta), r*sketch.sin(theta)); 
      // Rotate the box (rotation is offset by 90 degrees)
      sketch.rotate(theta + sketch.PI/2); 

      // Display the character
      sketch.fill(0);
      sketch.text(currentChar, 0, 0);

      sketch.pop();

      // Move halfway again
      arclength += w/2;
    }
    sketch.noLoop();
  }
}

var p58 = new p5(s8,'canvas8');


var s9 = function(sketch) {
  sketch.setup = function() {
    //sketch.noCanvas();
    var c = sketch.createCanvas(10,10);
    c.id('removethis');
    c.remove();

    // Not drawing to canvas but making a DIV
    var div = sketch.createDiv('It was a dark and stormy night.');
    // Size and color
    div.style('font-size','16pt');
    div.style('color','DarkOrchid');
    div.style('background-color','#FFFFFF');
    div.style('padding','12px');
    div.style('text-align','center');
  } 
}
var s9 = new p5(s9,'div9');

var s10 = function(sketch) {
  var message = "click mouse to shake it up";

  // An array of Letter objects
  var letters = [];



  sketch.setup = function() {
    var c = sketch.createCanvas(10,10);
    c.id('removethis');
    c.remove();
    //var parentdiv = sketch.getElement('div10');

    // Initialize Letters at the correct x location
    var x = 20;
    for (var i = 0; i < message.length; i++) {
      // Letter objects are initialized with their location within the String as well as what character they should display.
      var s = message.charAt(i);
      if (s === ' ') {
        s = '&nbsp;';
      }

      letters[i] = new Letter(x, 50, s); 
      x += letters[i].getWidth();
    }
    //sketch.noLoop();
  }


  sketch.draw = function() {
    for (var i = 0; i < letters.length; i++ ) {
      // If the mouse is pressed the letters shake
      // If not, they return to their original location
      if (sketch.mouseIsPressed) {
        letters[i].shake();
      } else {
        letters[i].home();
      }
    }
  }

  // A class to describe a single Letter
  function Letter(x_, y_, letter_) {
    
    // The object knows its original " home " location
    this.homex = x_;
    this.homey = y_;
    this.x = x_;
    this.y = y_;
    this.letter = letter_;

    this.parentdiv = sketch.getElement('div10');
    var eltx = this.parentdiv.elt.offsetLeft;
    var elty = this.parentdiv.elt.offsetTop;
   
    // Each letter is a div with absolute positioning
    this.div = sketch.createDiv(this.letter);
    this.div.position(this.x + eltx, this.y + elty);
    this.div.style('font-family','Georgia');
    this.div.style('font-size', '16pt');

    // What is the width of this Letter
    this.getWidth = function() {
      return this.div.elt.offsetWidth;
    }

    // Move the letter randomly
    this.shake = function() {
      this.x += sketch.random(-2,2);
      this.y += sketch.random(-2,2);
    var eltx = this.parentdiv.elt.offsetLeft;
    var elty = this.parentdiv.elt.offsetTop;
      // If it moves update the position
      this.div.position(this.x + eltx, this.y + elty);
    }
    
    // At any point, the current location can be set back to the home location by calling the home() function.
    this.home = function() { 
      this.x = sketch.lerp(this.x, this.homex, 0.1);
      this.y = sketch.lerp(this.y, this.homey, 0.1);
    var eltx = this.parentdiv.elt.offsetLeft;
    var elty = this.parentdiv.elt.offsetTop;
      // If it moves update the position
      this.div.position(this.x + eltx, this.y + elty);
    }
  }
}

var p510 = new p5(s10,'div10');

var s11 = function(sketch) {
  var message = "this text is spinning";
  var angle = 0;
  var div;

   sketch.setup = function() {
    var c = sketch.createCanvas(10,10);
    c.id('removethis');
    c.remove();
    // A div instead of canvas
    div = sketch.createDiv(message);
    div.style('font-size','16pt');
    div.style('textAlign','CENTER');
  } 

   sketch.draw = function() {

    // Using a CSS transform
    div.style('transform','rotate('+angle+'deg)');

    // The angle is in degrees!
    angle++;
  }
}

var p511 = new p5(s11,'div11');

var s12 = function(sketch) {
    // An array of boxes
  var boxes = [];
  // Size of the box
  var boxsize = 32;
  
  var txt = 'here are some words that this example can use';
  var words = txt.split(/\s+/);

  sketch.setup = function() {
    // Make a canvas
    sketch.createCanvas(600,200);
  }

  sketch.draw = function() {
    // Clear the canvas
    sketch.background(255);
    
    // Show all the boxes
    for (var i = 0; i < boxes.length; i++) {
      boxes[i].display();
    }
    
    // Did we find a valid box?
    var found = false;

    // How many times have we tried?
    var count = 0;

    // Keep trying until we find one
    while (!found) {
      // Make a new box
      var b = new Box(boxsize);
      // Does it fit on the screen?
      if (b.fits(boxes)) {
        // If so add it to the list
        boxes.push(b);
        found = true;
      }
      
      // Have we tried so many times we should stop trying?
      count++;
      if (count > 5000) {
        break;
      }
    }

    // Start with big boxes, shrink them over time
    if (boxsize > 12) { 
      boxsize--;

    // If we can't find spots with small boxes we're done
    } else if (!found) {
      sketch.noLoop();
    } 
  }

  function Box(size) {
    var index = sketch.floor(sketch.random(words.length));
    this.txt = words[index];
    this.fs = size;
    sketch.textSize(this.fs);
    this.x = sketch.random(sketch.width);
    this.y = sketch.random(sketch.height);
    this.w = sketch.textWidth(this.txt);
    this.h = this.fs;

    this.vertical = false;
    if (sketch.random(1) < 0.5) {
      this.vertical = true;
      var temp = this.h;
      this.h = this.w;
      this.w = temp;
    }


        
    // Draw the rectangle
    this.display = function() {
      sketch.stroke(0);
      sketch.noFill();
      //sketch.rectMode(sketch.CORNER);
      //sketch.rect(this.x,this.y,this.w,this.h);

      sketch.textSize(this.fs);
      sketch.fill(0);
      sketch.noStroke();
      sketch.textAlign(sketch.CENTER);
      sketch.push();
      if (this.vertical) {
        sketch.translate(this.x + this.w/2 - this.fs/4,this.y + this.h/2);
        sketch.rotate(sketch.PI/2);
      } else {
        sketch.translate(this.x + this.w/2, this.y + this.h - this.fs/4);

      }
      sketch.text(this.txt, 0, 0);
      sketch.pop();
    }
    
    // Is this box off the screen?
    this.offscreen = function() {
      // A 2 pixel buffer
      var buffer = 2;
      if (this.x + this.w + buffer  > sketch.width)  return true;
      if (this.y + this.h + buffer  > sketch.height) return true;
      return false;
    }

    // Does this box overlap another box?
    this.overlaps = function(other) {
      var buffer = 2;
      
      // If it's to the right it does not
      if (this.x                   > other.x + other.w + buffer) return false;
      // If it's to the left it does not
      if (this.x + this.w + buffer < other.x)                    return false;
      // If it's below it does not
      if (this.y                   > other.y + other.h + buffer) return false;
      // If it's above it does not
      if (this.y + this.h + buffer < other.y)                    return false;
      // Well if none of these are true then it overlaps
      return true; 
    }
    
    // Check if this box fits on the screen
    // i.e. it does not overlap any other boxes
    this.fits = function(boxes) {
      // If it's off the screen we don't like it
      if (this.offscreen()) {
        return false;
      }

      // If it overlaps any other box we don't like it
      for (var i = 0; i < boxes.length; i++) {
        if (this.overlaps(boxes[i])) {
          return false;
        }
      }
      // Hey, it's ok!
      return true;
    }
  }
}

var p512 = new p5(s12,'canvas12');

var s13 = function(sketch) {
  var nodes = [];
  var connections = [];

  // Font size
  var fs = 24;

  // A toxiclibs physics world
  var physics;

  sketch.setup = function() {

    // Make a canvas
    sketch.createCanvas(600,200);

    // Initialize the physics
    physics=new VerletPhysics2D();

    // Set the world's bounding box
    physics.setWorldBounds(new Rect(0,0,sketch.width,sketch.height));
    physics.setDrag(0.05);

    // set the size
    sketch.textSize(fs);

    // Make some arbitrary nodes
    nodes[0] = new Node(100, 100, 'test');
    nodes[1] = new Node(200, 200, 'network');
    nodes[2] = new Node(300, 100, 'itp');
    nodes[3] = new Node(400, 200, 'javascript');
    
    // Connect the nodes
    connections[0] = new Connection(nodes[0], nodes[1], 100);
    connections[1] = new Connection(nodes[0], nodes[2], 100);
    connections[2] = new Connection(nodes[1], nodes[3], 100);
    connections[3] = new Connection(nodes[1], nodes[2], 100);
  }

  sketch.draw = function() {
    sketch.background(255);

    // Update the physics
    physics.update();

    // Draw all the connections
    for (var i = 0; i < connections.length; i++) {
      connections[i].display();
    }

    // Draw all the nodes
    for (var i = 0; i < nodes.length; i++) {
      nodes[i].dragIt(sketch.mouseX, sketch.mouseY);
      nodes[i].display();
    }
  }

  // This code is all for user dragging the nodes
  sketch.mousePressed = function() {
    for (var i = 0; i < nodes.length; i++) {
      if (nodes[i].over(sketch.mouseX, sketch.mouseY)) {
        nodes[i].setDrag(true);
      }
    }
  }

  sketch.mouseReleased = function() {
    for (var i = 0; i < nodes.length; i++) {
      nodes[i].setDrag(false);
    }
  }
  // A node 
  function Node(x,y,s) {
    // A VerletParticle instead of an x,y
    this.p = new VerletParticle2D(x,y);
    // Add to the physics world
    physics.addParticle(this.p);
    // Make the particle slightly repulsive
    physics.addBehavior(new AttractionBehavior(this.p, 100, -1));
    
    // The String
    this.word = s;
    // How wide is it?
    this.w = sketch.textWidth(this.word) + 8;
    // How tall is it?
    this.h = fs + 8;
    this.drag = false;
    
    // A function to check if a point is inside the node
    this.over = function(x,y) {
      if (x > this.p.x-this.w/2 && x < this.p.x + this.w/2 && y > this.p.y-this.h/2 && y < this.p.y + this.h/2) {
        return true;
      } else {
        return false;
      }
    }
      // Is this being dragged or not?
    this.setDrag = function(bool) {
      this.drag = bool;
    }

    // Drag it somewhere
    // (This should really track an offset for better interaction)
    this.dragIt = function(x,y) {
      // Lock and unlock the Particle when moving
      if (this.drag) {
        this.p.lock();
        this.p.x = x;
        this.p.y = y;
        this.p.unlock();
      }
    }

    // Draw the node
    this.display = function(){
      sketch.fill(175);
      sketch.stroke(0);
      // Red if being dragged
      if (this.drag) {
        sketch.fill(255,0,0);
      }
      sketch.strokeWeight(2);
      sketch.rectMode(sketch.CENTER);
      sketch.rect(this.p.x,this.p.y, this.w, this.h);
      sketch.textAlign(sketch.CENTER);

      sketch.fill(0);
      sketch.noStroke();
      sketch.text(this.word, this.p.x, this.p.y + this.h/4);      
    }
  }

  // A connection between two nodes
  function Connection(a, b, len) {
    // Make a spring between two particles with a rest length and strength
    var s = new VerletSpring2D(a.p,b.p,len,0.01);
    // Add to the physics world
    physics.addSpring(s);
    this.from = a;
    this.to = b;

    this.display = function() {
      sketch.stroke(0);
      sketch.line(this.from.p.x, this.from.p.y, this.to.p.x, this.to.p.y);
    }
  }
}

var p513 = new p5(s13,'canvas13');


