---
title: 'Night #1: Zoom and Pan in 2D'
author: Daniel
layout: post
pvc_views:
  - 5172
dsq_thread_id:
  - 511208615
dsq_needs_sync:
  - 1
categories:
  - General
tags:
  - 2D
  - pan
  - processing.org
  - zoom
---
<p>This came up in my course &#8220;Introduction to Computational Media&#8221; this year.  How does one pan and/or zoom in a 2D Processing world?  We could certainly introduce P3D into the mix, but there is a nice, elegant way we can create the effect of panning and zooming and still live in 2D.  Here&#8217;s how it works.</p>
<p>First, you need to make sure you translate to the center of your window.</p>
{% highlight java %}
  translate(width/2, height/2);
{% endhighlight %}
<p>Then you can use the <a href="http://processing.org/learning/basics/scale.html">scale()</a> function to scale the world according to a percentage (i.e. 2.0 is 200%, 0.5 is 50%).  We&#8217;ll use a variable called zoom.</p>
{% highlight java %}
  float zoom = 1.5;  // 150%
  scale(zoom);
{% endhighlight %}
<p>Then we can translate additionally to pan according to some offset.</p>
{% highlight java %}
  float offsetX = 100;  // Some arbitrary offset
  float offsetY = 0;
  translate(offsetX,offsetY);
{% endhighlight %}
<p>Here is an example (running in processing.js) that allows the user to pan around a design by dragging the mouse, and zoom in and out using key presses.</p>
<p><script type="application/processing">
// The scale of our world
float zoom;
// A vector to store the offset from the center
PVector offset;
// The previous offset
PVector poffset;
// A vector for the mouse position
PVector mouse;</p>
<p>void setup() {
  size(586, 293);
  zoom = 1.0;
  offset = new PVector(0, 0);
  poffset = new PVector(0, 0);</p>
<p>  smooth();
}</p>
<p>void draw() {
  background(50);
  pushMatrix();
  // Everything must be drawn relative to center
  translate(width/2, height/2);</p>
<p>  // Use scale for 2D "zoom"
  scale(zoom);
  // The offset (note how we scale according to the zoom)
  translate(offset.x, offset.y);</p>
<p>  // An arbitrary design so that we have something to see!
  randomSeed(1);
  for (int i = 0; i < 500; i++) {
    stroke(255);
    fill(255,50);
    rectMode(CENTER);
    float h = 100;
    if (random(1) < 0.5) {
      rect(random(-h,h),random(-h,h),12,12);
    } else {
      ellipse(random(-h,h),random(-h,h),12,12);
    } 
  }
  popMatrix();</p>
<p>  // Draw some text (not panned or zoomed!)
  fill(255);
  text("a: zoom innz: zoom outndrag mouse to pan",10,32);</p>
<p>}</p>
<p>// Zoom in and out when the key is pressed
void keyPressed() {
  if (key == 'a') {
    zoom += 0.1;
  } 
  else if (key == 'z') {
    zoom -= 0.1;
  }
  zoom = constrain(zoom,0,100);
}</p>
<p>// Store the mouse and the previous offset
void mousePressed() {
  mouse = new PVector(mouseX, mouseY);
  poffset.set(offset);
}</p>
<p>// Calculate the new offset based on change in mouse vs. previous offsey
void mouseDragged() {
  offset.x = (mouseX - mouse.x)/zoom + poffset.x;
  offset.y = (mouseY - mouse.y)/zoom + poffset.y;
}</p>
<p></script></p>
<p>Source: <a href='http://www.shiffman.net/wp/wp-content/uploads/2011/02/PanZoom2D.zip'>PanZoom2D.zip</a></p>
<p></script></p>
