---
title: 'Night #4: Sorting the Vertices of a Polygon'
author: Daniel
layout: post
pvc_views:
  - 6304
dsq_thread_id:
  - 514377049
categories:
  - General
tags:
  - arraylist
  - polygon
  - processing.org
  - selection sort
---
<p>The following problem came up in my ICM course this year.  A student was working on a sketch that involved tiling polygons arbitrarily drawn by a user.  Allowing a user to set the vertices of a polygon should be easy enough, right?  But what if the user does not happen to draw them in a nice clock-wise (or counter clock-wise) order?</p>
<p>Imagine for a moment, you have an ArrayList of PVectors called &#8220;vertices.&#8221;  When the user clicks, the mouse you could add that mouse location to that ArrayList.</p>

{% highlight java %}
void mousePressed() {
  vertices.add(new PVector(mouseX,mouseY));
}
{% endhighlight %}

<p>You could then draw that list as a polygon using beginShape() and endShape().</p>

{% highlight java %}
void draw() {
  beginShape();
  for (PVector v : vertices) {
    vertex(v.x, v.y);
  } 
  endShape(CLOSE);
}
{% endhighlight %}

<p>But depending on how the user set the vertices, you might end up with:</p>
<p><img src="http://shiffman.net/wp/wp-content/uploads/2011/12/unsorted.png" alt="" title="unsorted" width="500" height="298" class="alignnone size-full wp-image-1006" /></p>
<p>when what you really want is the following:</p>
<p><img src="http://shiffman.net/wp/wp-content/uploads/2011/12/sorted.png" alt="" title="sorted" width="500" height="298" class="alignnone size-full wp-image-1007" /></p>
<p>One solution for solving this problem is to always sort all of the vertices according to their relative angle from the center.  Let&#8217;s say you calculate the center of the polygon as the average location of all vertices.</p>

{% highlight java %}
  PVector centroid = new PVector();
  for (PVector v : vertices) {
    centroid.add(v);
  } 
  centroid.div(vertices.size());
{% endhighlight %}

<p>You can then make a vector that points from the center to each vertex and get its direction (using PVector&#8217;s heading2D() method).</p>

{% highlight java %}
  for (PVector v : vertices) {
    PVector dir = PVector.sub(v, centroid);
    float a = dir.heading2D() + PI;
  }
{% endhighlight %}

<p>Note how we add PI to the angle.  The heading2D() function will return an angle between -PI and PI and it&#8217;ll be easier to sort if we just have an angle between 0 and TWO_PI.  One way to sort an ArrayList is called a <a href="http://en.wikipedia.org/wiki/Selection_sort">Selection Sort</a>.  In the example below, you&#8217;ll find a variation of the selection sort.   The code iterates through the ArrayList, finds the element with the highest angle, removes that element and sticks it at the end of a new ArrayList.  It does this again and again until the original ArrayList is empty.  The result is a new ArrayList in sorted order.</p>
<p>Following is that example which implements all of the above into a class. If you are looking for an exercise, try allowing the user to move or delete existing vertices.   Also, how you would make a system of these polygons so that the user can draw more than one?</p>

<p><script type="application/processing">
// Daniel Shiffman
// Hanukkah 2011
// 8 nights of Processing examples
// http://shiffman.net</p>
<p>// A Polygon object
Poly p;</p>
<p>void setup() {
  size(586, 293);
  smooth();
  // An empty one
  p = new Poly();
}</p>
<p>void draw() {
  background(50);
  // Draw the polygon
  p.display();</p>
<p>  textAlign(CENTER);
  fill(255);
  text("click mouse to add vertices", width/2, height-16);
}</p>
<p>void keyPressed() {
  // Clear it when you press the mouse
  if (key == ' ') {
    p.clear();
  } 
  // If you want to see the polygon unsorted, comment
  // out the automatic sortVertices() in the class
  /*else if (key == 's') {
    p.sortVertices();
  }*/
}</p>
<p>// Add a vertex!
void mousePressed() {
  PVector mouse = new PVector(mouseX, mouseY);
  p.addVertex(mouse);
}</p>
<p>// Daniel Shiffman
// Hanukkah 2011
// 8 nights of Processing examples
// http://shiffman.net</p>
<p>// A class that generates a polygon sorted
// according to relative angle from center</p>
<p>class Poly {
  // A list of vertices
  ArrayList vertices;
  // The center
  PVector centroid;</p>
<p>  Poly() {
    // Empty at first
    vertices = new ArrayList();
    centroid = new PVector();
  }</p>
<p>  // We can clear the whole thing if necessary
  void clear() {
    vertices.clear();
  }</p>
<p>  // Add a new vertex
  void addVertex(PVector newVertex) {
    vertices.add(newVertex);
    // Whenever we have a new vertex
    // We have to recalculate the center
    // and re-sort the vertices
    updateCentroid();
    // Come out the sorting if you want to see it drawn incorrectly
    sortVertices();
  }</p>
<p>  // The center is the average location of all vertices
  void updateCentroid() {
    centroid = new PVector();
    for (int i = 0; i < vertices.size(); i++) {
      PVector v = (PVector) vertices.get(i);
      centroid.add(v);
    } 
    centroid.div(vertices.size());
  }</p>
<p>  // Sorting the ArrayList
  void sortVertices() {</p>
<p>    // This is something like a selection sort
    // Here, instead of sorting within the ArrayList
    // We'll just build a new one sorted
    ArrayList newVertices = new ArrayList();</p>
<p>    // As long as it's not empty
    while (!vertices.isEmpty ()) {
      // Let's find the one with the highest angle
      float biggestAngle = 0;
      PVector biggestVertex = null;
      // Look through all of them
    for (int i = 0; i < vertices.size(); i++) {
      PVector v = (PVector) vertices.get(i);
        // Make a vector that points from center
        PVector dir = PVector.sub(v, centroid);
        // What is it's heading
        // The heading function will give us values between -PI and PI
        // easier to sort if we have from 0 to TWO_PI
        float a = dir.heading2D() + PI;
        // Did we find it
        if (a > biggestAngle) {
          biggestAngle = a;
          biggestVertex = v;
        }
      }</p>
<p>      // Put the one we found in the new arraylist
      newVertices.add(biggestVertex);
      // Delete it so that the next biggest one 
      // will be found the next time
      vertices.remove(biggestVertex);
    }
    // We've got a new ArrayList
    vertices = newVertices;
  }</p>
<p>  // Draw everything!
  void display() {</p>
<p>    // First draw the polygon
    stroke(255);
    fill(255, 127);
    beginShape();
    for (int i = 0; i < vertices.size(); i++) {
      PVector v = (PVector) vertices.get(i);</p>
<p>      vertex(v.x, v.y);
    } 
    endShape(CLOSE);</p>
<p>    // Then we'll draw some addition information
    // at each vertex to show the sorting
    for (int i = 0; i < vertices.size(); i++) {</p>
<p>      // This is overkill, but we want the numbers to
      // appear outside the polygon so we extend a vector
      // from the center
      PVector v = (PVector) vertices.get(i);      
      PVector dir = PVector.sub(v, centroid);
      dir.normalize();
      dir.mult(12);</p>
<p>      // Number the vertices
      fill(255);
      stroke(255);
      ellipse(v.x, v.y, 4, 4);
      textAlign(CENTER);
      text(i, v.x+dir.x, v.y+dir.y+6);
    } </p>
<p>    // Once we have two vertices draw the center
    if (vertices.size() > 1  ) {
      fill(255);
      ellipse(centroid.x, centroid.y, 8, 8);
      text("centroid", centroid.x, centroid.y+16);
    }
  }
}
</script></p>
<p>Source: <a href='http://shiffman.net/wp/wp-content/uploads/2011/12/PolygonVertexSorting.zip'>PolygonVertexSorting.zip</a></p>
