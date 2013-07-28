---
title: 'Night #6: Image Sequence Object (with variable speed)'
author: Daniel
layout: post
pvc_views:
  - 5313
dsq_thread_id:
  - 516738861
categories:
  - processing.org
tags:
  - animation
  - image sequence
  - PImage
  - processing.org
---
<p>I have <a href="http://www.learningprocessing.com/exercises/chapter-15/exercise-15-5/">an example from Learning Processing</a> which demonstrates how to package a &#8220;pre-made&#8221; animation (i.e. sequence of images) into an object in Processing so that it can be duplicated many times on screen.   For tonight&#8217;s example, I&#8217;m going to make a new version that improves a few key points.</p>
<p>First, in the original example the the image files are loaded in the class itself.  This is problematic.  Sure, if you make one object then you are loading files from the hard drive once.  However, if you make many objects, then you are loading the same images over and over again which is totally unnecessary (and can cause problems like using too much memory, stuttering if objects are made during draw(), taking too long to start up, etc.).</p>
<p>We can fix this by loading an array of images in setup() and passing it to the object.</p>
{% highlight java %}
Animation a;

void setup() {
  // Load the image sequence first!
  PImage[] seq = new PImage[40];
  for (int i = 0; i < seq.length; i++) {
    seq[i] = loadImage("stick/stick"+nf(i+1,2)+".png"); 
  }

  // Now when you make the animation object, you pass it the image array!
  a = new Animation(seq);  
}
</pre>
<p>The class then receives the array in the constructor and passes it to its own array.</p>
{% highlight java %}
class Animation {
  // The array of images
  PImage[] images;
  
  Animation(PImage[] images_) {
    images = images_;
  }
</pre>
<p>This way (as you&#8217;ll see in the example) if we make an array of objects, each one uses the same array of images (which we loaded only once). Another feature of this improvement is that the Animation object is more generic, and can be created with any arbitrary array of images.</p>
<p>The original example demonstrated how to have the sequences start at different images so that they didn&#8217;t all appear to be perfectly in sync.  However, the question I usually get is instead: &#8220;How can the sequences play back at variable speeds?&#8221;   </p>
<p>The original example used an integer to keep track of the current &#8220;frame&#8221; of the animation.</p>
{% highlight java %}
int index = 0;

void next() {
  index = (index + 1) % images.length;
}
</pre>
<p>Here, you see that we move one spot in the array each frame, and the animation is then shown at the frame rate of our sketch.  So in theory, you could change the above to &#8220;index = index + 2&#8243; to, say, double the speed.   A more flexible way to vary the rate of the animation, however, is to use a float for the index in the array, i.e.</p>
{% highlight java %}
float index = 0;
float speed = random(1,5);

void next() {
  // Move the index forward in the animation sequence
  index += speed;
  // If we are at the end, go back to the beginning
  if (index >= images.length) {
    // We could just say index = 0
    // but this is slightly more accurate
    index -= images.length;
  } 
}
</pre>
<p>Of course, you can&#8217;t actually use this float when you go to look up an image in the array &#8212; indices must be integers!  So we simply convert it to an int when the time comes to draw the image.</p>
{% highlight java %}
void display() {
  int imageIndex = int(index);
  image(images[imageIndex], x, y);
}
</pre>
<p>Here is the example.</p>
<p><script type="application/processing">
// An array of "Animation" objects
Animation[] animations = new Animation[6];</p>
<p>// The image sequence will be loaded outside of the object
// We don't want multiple instances of an object
// to load images again and again, just to point to an array
// of pre-loaded images</p>
<p>void setup() {
  size(640,360);</p>
<p>  // Load the image sequence
  PImage[] seq = new PImage[40];
  for (int i = 0; i < seq.length; i++) {
    seq[i] = loadImage("http://www.shiffman.net/p5/stick/stick"+nf(i+1,2)+".png"); 
  }</p>
<p>  // Make all the objects
  float y = 0;
  for (int i = 0; i < animations.length; i ++ ) {
    // Each object gets an image array and an x,y location
    animations[i] = new Animation(seq,0,y);
    y += 58;
  }
}</p>
<p>void draw() {</p>
<p>  background(255);</p>
<p>  // Display, cycle, and move all the animation objects
  for (int i = 0; i < animations.length; i ++ ) {
    animations[i].display();
    animations[i].next();
    animations[i].move();
  }
}</p>
<p>// Daniel Shiffman
// Hanukkah 2011
// 8 nights of Processing examples
// http://www.shiffman.net</p>
<p>// The animation object</p>
<p>class Animation {
  float x;  // location for Animation
  float y;  // location for Animation</p>
<p>  // The index into the array is a float!
  // This allows us to vary the speed of the animation
  // It will have to be converted to an int before the actual image is displayed
  float index = 0; </p>
<p>  // Speed, this will control both the animations movement
  // as well as how fast it cycles through the images
  float speed;</p>
<p>  // The array of images
  PImage[] images;</p>
<p>  Animation(PImage[] images_, float x_, float y_) {
    images = images_;
    x = x_;
    y = y_;</p>
<p>    // A random speed
    speed = random(1,5);
    // Starting at the beginning
    index = 0;</p>
<p>  }</p>
<p>  void display() {
    // We must convert the float index to an int first!
    int imageIndex = int(index);
    image(images[imageIndex], x, y);
  }</p>
<p>  void move() {
    // Object only moves horizontally
    x += speed;
    if (x > width) {
      x = -images[0].width;
    }
  }</p>
<p>  void next() {
    // Move the index forward in the animation sequence
    index += speed;
    // If we are at the end, go back to the beginning
    if (index >= images.length) {
      // We could just say index = 0
      // but this is slightly more accurate
      index -= images.length;
    } 
  }
}
</script></p>
<p>Download source: <a href='http://www.shiffman.net/wp/wp-content/uploads/2011/12/AnimationExample.zip'>AnimationExample.zip</a></p>
