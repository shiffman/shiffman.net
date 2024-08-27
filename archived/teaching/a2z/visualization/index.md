---
title: Text Visualization
author: Daniel
layout: page
---

<script language="javascript" type="text/javascript" src="//cdn.jsdelivr.net/p5.js/0.3.8/p5.min.js"></script>
<script language="javascript" type="text/javascript" src="//cdn.jsdelivr.net/p5.js/0.3.8/addons/p5.dom.js"></script>
<script language="javascript" type="text/javascript" src="//shiffman.net/javascript/toxiclibs.js"></script>
<script language="javascript" type="text/javascript" src="//shiffman.net/javascript/toxichelper.js"></script>
<script language="javascript" type="text/javascript" src="viz.js"></script>

## This week&#8217;s topics:
* [Drawing Text with Canvas](#canvas)
* [Drawing Text with DOM Elements](#dom)
* [Classic Text Visualization Techniques](#classic)

## Example Code
* [Text display basics](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/00_basics)
* [More with canvas](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/01_canvas_drawingtext)
* [More with DOM](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/02_DOM_drawingtext)
* [Boxfitting](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/04_boxfitting)
* [Treemap](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/05_treemap)
* [Network diagram](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/06_network_diagram)
* [Physics](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/07_physics)
* [APIs](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/08_APIs/)

## Related references
* [Stefanie Posavec](http://www.stefanieposavec.co.uk/-everything-in-between/)
* [Textarc by Bradford Paley](http://www.textarc.org/)
* [Ariel Malka](http://chronotext.org/)
* [OpenBible](http://www.openbible.info/blog/2011/10/applying-sentiment-analysis-to-the-bible/)
* [State of the Union, NY Times](http://www.nytimes.com/ref/washington/20070123_STATEOFUNION.html)
* [On the Origin of Species: The Preservation of Favoured Traces, Ben Fry](http://fathom.info/traces)
* [Office of Creative Research](http://o-c-r.org/work/)
* [Jeff Clark](http://neoformix.com/2013/NovelViews.html)
* Jonathan Corum, [word incidence](http://style.org/lyrics/)
* Lynn Cherny, [talk](https://www.youtube.com/watch?v=f41U936WqPM) [pinterest](http://www.pinterest.com/arnicas/textvis/)
* [Matthew Jockers](http://www.matthewjockers.net/)
* [wordle algorithm](http://static.mrfeinberg.com/bv_ch03.pdf)
* [SoSoLimited Reconstitution 2008](http://www.sosolimited.com/work/reconstitution-2008/)
* [Rob Seward Word Association](http://robseward.com/blog/2009/02/23/word-association-apps/)

<a name ="canvas"></a>
## Drawing Text with Canvas

Let's start by using [p5.js](http://p5js.org) to create and draw text to an HTML5 canvas.  This is quite similar to drawing text with [Processing](http://processing.org) and you can read more in this tutorial](http://processing.org/tutorials/text/).  One small difference you might notice below is the addition of a `stroke`.  p5 can color the outline and interior of text separately.

<div id="canvas1"></div>

{% highlight javascript %}
function setup() {
  createCanvas(400, 100);
}

function draw() {
  background(175);
  fill(0);
  stroke(255);
  textSize(64);
  text('Drawing some text.', 10, 90);
}
{% endhighlight %}

A larger difference, however, is font selection.  p5 uses web fonts.  Certain web fonts are known as “safe”, i.e. they are available by default in all browsers.  Some examples are “Arial”, “Georgia”, “Consolas”.  [Here's a full list](http://cssfontstack.com/).

<div id="canvas2"></div>

{% highlight javascript %}
fill(0);
textSize(32);
textFont('Arial');
text('Arial', 10, 90);
textFont('Georgia');
text('Georgia', 10, 190);
textFont('Monaco');
text('Monaco', 10, 290);
{% endhighlight %}

Note how the above example also uses `textAlign()` to set the alignment.  

Other web fonts can be used as well.  However, if they are not available by defaul then you'll need to explicitly link to the font files wherever they might be stored.  This can be accomplished using the CSS [@font-face](https://developer.mozilla.org/en-US/docs/Web/CSS/@font-face) or by simply linking to the path in your HTML file.  Google, for example, provides many [open source web fonts](https://www.google.com/fonts) that can be accessed as follows:

<pre style="height: 50%"><code style="font-size: 75%">
&lt;link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'&gt;
</code></pre>

<div id="canvas3"></div>

Most of the examples for this week involve fitting text into a particular visual design.  A key function for this sort of work is `textWidth()` which calculates and returns the width of string.

{% highlight javascript %}
var s = 'rectangle below has the same width';
text(s, 10, 32);
// What's the width of that String?
var w = textWidth(s);
rect(s, 10, 36, w, 10);
{% endhighlight %}

<div id="canvas4"></div>

The height of a block of text can also be determined by `textAscent()` and `textDescent()`.  As of this writing they are not yet documented for p5 but you can look at the [Processing reference](http://www.processing.org/reference/textAscent_.html).

Let's take the example of displaying text with each character rendered individually.  This is necessary if each character needs to moved or be colored independently.

We can display each character in loop, using the [`charAt()`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/charAt).

<div id="canvas5"></div>

{% highlight javascript %}
var message = "Each character is written individually.";

// The first character is at pixel 10.
var x = 10; 
for (var i = 0; i < message.length; i++) {
  // Each character is displayed one at a time with the charAt() function.
  text(message.charAt(i),x,height/2);
  // All characters are spaced 16 pixels apart.
  x += 16; 
}
{% endhighlight %}

The above code has a pretty major flaw — the x location is increased by 16 pixels for each character.  Although this is approximately correct, because each character is not exactly 32 pixels wide, the spacing is off.

The proper spacing can be achieved using the `textWidth()` and setting each character's location by the width of the previous character.  Note how this example achieves the proper spacing even with each character being a random size!

<div id="canvas6"></div>

{% highlight javascript %}
var message = "Each character is written individually.";
for (var i = 0; i < message.length; i++) {
  textSize(random(12,64));
  text(message.charAt(i),x,height/2);
  // textWidth() spaces the characters out properly.
  x += textWidth(message.charAt(i)); 
}
{% endhighlight %}


This approach can also be used to display display text along a curve.  To place a set of rectangles along a circular path, for example, we can use [polar coordinates](http://natureofcode.com/book/chapter-3-oscillation/#chapter03_section5).

<div id="canvas7"></div>

{% highlight javascript %}
// The radius of a circle
var r = 100;

// The width and height of the boxes
var w = 24;
var h = 24;

function setup() {
  createCanvas(360, 360);
}

function draw() {
  background(100);

  // Start in the center and draw the circle
  translate(width/2, height/2);
  noFill();
  stroke(0);
  // Our curve is a circle with radius r in the center of the window.
  ellipse(0, 0, r*2, r*2); 
  // 10 boxes along the curve
  var totalBoxes = floor(TWO_PI*r/w);
  // We must keep track of our position along the curve
  var arclength = 0;
  // For every box
  for (var i = 0; i < totalBoxes; i++) {
    // Each box is centered so we move half the width
    arclength += w/2; 

    // Angle in radians is the arclength divided by the radius
    var theta = arclength / r;

    push();
    // Polar to cartesian coordinate conversion
    translate(r*cos(theta), r*sin(theta));
    // Rotate the box
    rotate(theta);

    // Display the box
    fill(0, 100);
    rectMode(CENTER);
    rect(0, 0, w, h);
    pop();

    // Move halfway again
    arclength += w/2;
  }
}
{% endhighlight %}

What we need to do is replace each box with a character from a String that fits inside the box.  And since characters all do not have the same width, instead of using a variable "w" that stays constant, each box will have a variable width along the curve according to the textWidth() function.

<div id="canvas8"></div>

{% highlight javascript %}

  // We must keep track of our position along the curve
  var arclength = 0;

  // For every box
  for (var i = 0; i < message.length; i++) {

    // The character and its width
    var currentChar = message.charAt(i);
    // Instead of a constant width, we check the width of each character.
    var w = textWidth(currentChar); 
    // Each box is centered so we move half the width
    arclength += w/2;

    // Angle in radians is the arclength divided by the radius
    // Starting on the left side of the circle by adding PI
    var theta = PI + arclength / r;

    push();

    // Polar to Cartesian conversion allows us to find the povar along the curve. See Chapter 13 for a review of this concept.
    translate(r*cos(theta), r*sin(theta)); 
    // Rotate the box (rotation is offset by 90 degrees)
    rotate(theta + PI/2); 

    // Display the character
    fill(0);
    text(currentChar, 0, 0);

    pop();

    // Move halfway again
    arclength += w/2;
{% endhighlight %}

(Thanks to [Ariel Malka](http://ariel.chronotext.org/) for his advice on this last curved text example.)

There are [many more examples here](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/00_basics) and [here](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/01_canvas_drawingtext).

<a name ="dom"></a>
## Drawing Text with DOM elements

The above section covered the basics of drawing text to canvas in much the same way one might with [Processing](http://processing.org).  However, a magical feature of p5.js is that we can animate and manipulate text onscreen using DOM elements as well.  In fact, all of the above examples [can be reworked to use DOM elements](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/02_DOM_drawingtext)!  Let's briefly go over some of the basic key points.

To place text onscreen, you can dynamically generate a [div](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/div) with the p5 function `createDiv()`.


{% highlight javascript %}
// Not drawing to canvas but making a div
var div = createDiv('It was a dark and stormy night.');
{% endhighlight %}

The `style()` method allows you to set CSS properties in order to affect the text's color, background, size, font, etc.

<div id="div9"></div>

{% highlight javascript %}
// Size and color
div.style('font-size','16pt');
div.style('color','DarkOrchid');
div.style('background-color','#FFFFFF');
div.style('padding','12px');
div.style('text-align','center');
{% endhighlight %}

Unfortunately, there is no simple `textWidth()` function for divs.  However, divs that are displayed “inline” or absolute positioned will conform to the width of the text.  We can then access the width dynamically with the element's [`offsetWidth`](https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement.offsetWidth).  While this is not currently part of the p5 API, it [may be in the future](https://github.com/lmccart/p5.js/issues/391).  

{% highlight javascript %}
var w = div.elt.offsetWidth;
var h = div.elt.offsetHeight;
{% endhighlight %}

Here's an example object that makes an individual DIV for a single character.  [In the full code](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week5_visualization/02_DOM_drawingtext/05_text_charbychar/sketch.js) for this example, you'll see that each character's starting location is determined using the div's width.

<div id="div10" style="height: 32px"></div>

{% highlight javascript %}
// A class to describe a single Letter
function Letter(x_, y_, letter_) {
  
  // The object knows its original " home " location
  this.homex = x_;
  this.homey = y_;
  this.x = x_;
  this.y = y_;
  this.letter = letter_;
  
  // Each letter is a div with absolute positioning
  this.div = createDiv(this.letter);
  this.div.position(this.x, this.y);
  this.div.style('font-family','Georgia');
  this.div.style('font-size', '64pt');

  // What is the width of this Letter
  this.getWidth = function() {
    return this.div.elt.offsetWidth;
  }

  // Move the letter randomly
  this.shake = function() {
    this.x += random(-2,2);
    this.y += random(-2,2);

    // If it moves update the position
    this.div.position(this.x, this.y);
  }
  
  // At any point, the current location can be set back to the home location by calling the home() function.
  this.home = function() { 
    this.x = lerp(this.x, this.homex, 0.1);
    this.y = lerp(this.y, this.homey, 0.1);

    // If it moves update the position
    this.div.position(this.x, this.y);
  }
}
{% endhighlight %}

Translation and rotation can also be applied to divs with [CSS transforms](https://developer.mozilla.org/en-US/docs/Web/CSS/transform).

<div id="div11"></div>

{% highlight javascript %}
function draw() {
  // Set the CSS transform for rotation
  div.style('transform','rotate('+angle+'deg)');
  // This assumes a variable called angle, thinking in terms of degrees
  angle = angle + 1;
}
{% endhighlight %}

<a name ="classic"></a>
## Classic Text Visualization Techniques

A good place to start when thinking about visualizing text is [Jonathan Feinberg's](http://mrfeinberg.com/) [Wordle](http://www.wordle.net/).  In fact, I highly recommend you read [his chapter about wordle](http://static.mrfeinberg.com/bv_ch03.pdf) from the [O'Reilly book “Beautiful Visualization”](http://oreilly.com/catalog/0636920000617).

![wordle1](http://www.wordle.net/thumb/wrdl/1092476/Period_G) ![wordle1](http://www.wordle.net/thumb/wrdl/1381018/US_Constitution)

Wordle's core strategy for placing non-overlapping text is a randomized greedy algorithm.  This means it tries to place a rectangles on the screen and if it doesn't intersect with any of the existing rectangles then it gets to stay. If it does, however, it is deleted and wordle tries again.

We can implement something similar using p5.  First we need a function to [determine if two rectangles overlap](http://stackoverflow.com/questions/306316/determine-if-two-rectangles-overlap-each-other).  Here's a quick and dirty JS implementation of the idea.

{% highlight javascript %}
// A random box object
function Box() {
  this.x = random(width);
  this.y = random(height);
  this.w = random(10, 200);
  this.h = random(1, 200);
  
  // Does this box overlap another box?
  this.overlaps = function(other) {
    
    // If it's to the right it does not
    if (this.x                   > other.x + other.w) return false;
    // If it's to the left it does not
    if (this.x + this.w          < other.x)           return false;
    // If it's below it does not
    if (this.y                   > other.y + other.h) return false;
    // If it's above it does not
    if (this.y + this.h         < other.y)            return false;
    // Well if none of these are true then it overlaps
    return true; 
  }
{% endhighlight %}

We can now write an algorithm to make a box and see if it overlaps any existing boxes.  Assuming it does not, it gets to go into an array called `boxes`.

{% highlight javascript %}
// Did we find a valid box?
var success = false;

// Keep trying until we find one
while (!success) {
  // Make a new box
  var b = new Box(boxsize);
  // Does it fit on the screen?
  found = true;
  for (var i = 0; i < boxes.length; i++) {
    if (b.overlaps(boxes[i])) {
      success = false;
    }
  }
  if (success) {
    // If so add it to the list
    boxes.push(b);
  }
}
{% endhighlight %}

Here's what this would look like with the boxes containing text.

<div id='canvas12'></div>

[Full code for a series of examples like this.](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/04_boxfitting)

A variation on this idea is a [treemap technique](http://treemapart.wordpress.com/) invented by Ben Shneiderman.  A well-known implementation of this is the [Map of the Market](http://www.bewitched.com/marketmap.html) by [Martin Wattenberg](http://www.bewitched.com/).

![Treemap](http://www.bewitched.com/marketmap/marketmap-490b.png)

This technique can be used to visualize a concordance of text.  [Here are some examples](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/05_treemap) that use the JavaScript [Treemap library](https://github.com/imranghory/treemap-squared/) by Imran Ghory.

Another classic example of text visualization is the [visual thesauras](http://www.visualthesaurus.com/).  Using concepts from the [Nature of Code: Chapter 5](http://natureofcode.com/book/chapter-5-physics-libraries/), we can create a network of words that are connected to each other according to a set of relationships (like being a synonym).  Click and drag to move the text around below.

<div id='canvas13'></div>

{% highlight javascript %}
var nodes = [];
var connections = [];

// Font size
var fs = 24;

function setup() {

  // Make some arbitrary nodes
  nodes[0] = new Node(100, 100, 'test');
  nodes[1] = new Node(200, 200, 'network');
  nodes[2] = new Node(300, 100, 'itp');
  nodes[3] = new Node(400, 200, 'javascript');
  
  // Connect the nodes
  connections[0] = new Connection(nodes[0], nodes[1], 100);
  connections[1] = new Connection(nodes[0], nodes[2], 100);
  connections[2] = new Connection(nodes[1], nodes[3], 150);
  connections[3] = new Connection(nodes[1], nodes[2], 100);
}
{% endhighlight %}

For the full code for these examples and more, [take a look here](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/06_network_diagram) as well as these [box2d examples](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week5_visualization/07_physics).

