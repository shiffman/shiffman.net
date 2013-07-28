---
title: Rotate a Vector (Processing.js!)
author: Daniel
layout: post
dsq_thread_id:
  - 249554432
pvc_views:
  - 5932
categories:
  - General
---
<p>The question of how to rotate a PVector object (the data of the vector itself, I&#8217;m not talking about rotating while drawing) came up in my <a href="http://www.shiffman.net/teaching/nature/">nature of code</a> course yesterday.   To do this, you&#8217;ll need to convert the vector to polar coordinates (radius + angle), adjust the angle, and the convert it back to cartesian to solve for the components (x and y).  A function would look something like:</p>
{% highlight java %}
// Rotate a vector in 2D
void rotate2D(PVector v, float theta) {
  // What's the magnitude?
  float m = v.mag();
  // What's the angle?
  float a = v.heading2D();
  
  // Change the angle
  a += theta;
  
  // Polar to cartesian for the new xy components
  v.x = m * cos(a);
  v.y = m * sin(a);
}
</pre>
<p>(Update thanks to Vilhelm&#8217;s comment below: You can also use a 2D rotation matrix!)</p>
{% highlight java %}
void rotate2D(PVector v, float theta) {
  float xTemp = v.x;
  v.x = v.x*cos(theta) - v.y*sin(theta);
  v.y = xTemp*sin(theta) + v.y*cos(theta);
}
</pre>
<p>Now, this is really just a ruse.  A big excuse for me to figure out how to get a Processing example in a wordpress post using processing.js!  I was able to do this fairly quickly with three quick steps:</p>
<p>1) Download <a href="http://processingjs.org/download">processing.js 1.0</a>.<br />
2) Install <a href="http://wordpress.org/extend/plugins/processingjs/">wordpress processing.js plug-in</a>.<br />
3) Update the plug-in to use processing.js 1.0 (it hasn&#8217;t been yet).  <a href="http://wordpress.org/support/topic/plugin-processing-js-updating-to-processingjs-10">Follow these instructions</a>.</p>
<p>And here it is!</p>
<p><script type="application/processing">
// Rotate2D function
// Daniel Shiffman</p>
<p>void setup() {
  size(200,200);
  smooth();
}</p>
<p>void draw() {
  background(100);
  PVector xaxis = new PVector(75,0);
  float theta = map(mouseX,0,width,0,TWO_PI);
  rotate2D(xaxis,theta);
  drawVector(xaxis,width/2,height/2,1.0);
}</p>
<p>// Rotate a vector in 2D
void rotate2D(PVector v, float theta) {
  // What's the magnitude?
  float m = v.mag();
  // What's the angle?
  float a = v.heading2D();</p>
<p>  // Change the angle
  a += theta;</p>
<p>  // Now use polar to cartesian coordinates to calculate the new xy components
  v.x = m * cos(a);
  v.y = m * sin(a);
}</p>
<p>// Renders a vector object 'v' as an arrow and a location 'loc'
void drawVector(PVector v, float x, float y, float scayl) {
  pushMatrix();
  float arrowsize = 4;
  // Translate to location to render vector
  translate(x,y);
  stroke(255);
  // Call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading2D());
  // Calculate length of vector &#038; scale it to be bigger or smaller if necessary
  float len = v.mag()*scayl;
  // Draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  popMatrix();
}
</script></p>
<p>And now the source:</p>
{% highlight java %}
// Rotate2D function
// Daniel Shiffman

void setup() {
  size(200,200);
  smooth();
}

void draw() {
  background(100);
  PVector xaxis = new PVector(75,0);
  float theta = map(mouseX,0,width,0,TWO_PI);
  rotate2D(xaxis,theta);
  drawVector(xaxis,width/2,height/2,1.0);
}

// Rotate a vector in 2D
void rotate2D(PVector v, float theta) {
  // What's the magnitude?
  float m = v.mag();
  // What's the angle?
  float a = v.heading2D();
  
  // Change the angle
  a += theta;
  
  // Polar to cartesian for the new xy components
  v.x = m * cos(a);
  v.y = m * sin(a);
}

// Renders a vector object 'v' as an arrow at a location xy
void drawVector(PVector v, float x, float y, float scayl) {
  pushMatrix();
  float arrowsize = 4;
  // Translate to location to render vector
  translate(x,y);
  stroke(255);
  // Vector heading to get direction (pointing up is a heading of 0)
  rotate(v.heading2D());
  // Scale it to be bigger or smaller if necessary
  float len = v.mag()*scayl;
  // Draw three lines to make an arrow
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  popMatrix();
}
</pre>
