---
title: OpenCV Matching Faces Over Time
author: Daniel
layout: post
dsq_thread_id:
  - 288882523
pvc_views:
  - 5301
categories:
  - General
tags:
  - opencv
  - processing.org
---
<p><iframe src="http://player.vimeo.com/video/22873042?title=0&amp;byline=0&amp;portrait=0&amp;autoplay=0" width="398" height="299" frameborder="0"></iframe></p>
<p>One of the most common questions I get regarding blob tracking is &#8220;memory.&#8221;   How do I know which blob is which over time?  Computer vision libraries, for the most part, simply pass you a list of blobs (with x, y, width, and height properties) for any given moment in time.  But the blobs themselves represent only a snapshot of that particular moment and contain no information related to whether the blobs existed before this very moment.   This may seem absurd given that as human beings it&#8217;s so easy for us to watch a rectangle moving across a screen and understand conceptually that it is the same rectangle.  But without additional information (such as color matching, an AR marker, etc.) there&#8217;s no way for an algorithm that analyzes one frame of video to know anything about a previous frame.  And so we need to apply the same intuitions our brain uses (it was there a moment ago, it&#8217;s probably still there now) to our algorithms.  </p>
<p>To illustrate one solution to this problem, I&#8217;ve created an example that tags an OpenCV face &#8220;rectangle&#8221; with an ID number and attempts to track that face over time, matching new faces that OpenCV finds with earlier ones.  This example is somewhat of an oversimplification whose purpose is to demonstrate a particular technique &#8212; a new face is the same as the previous one that was closest to it.  But there are certainly additional and more sophisticated ways that the match could be made.  In addition, it&#8217;s likely useful to add some interpolation to the face&#8217;s movement and size changes so that it appears less jittery.</p>
<p>First, we need to establish our own Face class.  OpenCV just gives us a new array of Rectangle objects every frame so we need our own Face object that persists (in an ArrayList).</p>
{% highlight java %}
class Face {
  
  // A Rectangle
  Rectangle r;
  
  // Am I available to be matched?
  boolean available;
  
  // Should I be deleted?
  boolean delete;
  
  // How long should I live if I have disappeared?
  int timer = 127;
  
  // Assign a number to each face
  int id;
{% endhighlight %}
<p>Our main program then needs an ArrayList to keep track of the Face objects that currently exist:</p>
{% highlight java %}
ArrayList faceList;
{% endhighlight %}
<p>Finally, in draw(), OpenCV gives us an array of Rectangle objects, the faces it currently sees.</p>
{% highlight java %}
  Rectangle[] faces = opencv.detect();
{% endhighlight %}
<p>It&#8217;s our job to match these with any Face objects we have in our ArrayList.  The way I see it, there are three scenarios.</p>
<p>1) We have nothing in our Face ArrayList.  In this case, we add a new Face object for every single Rectangle in the faces array, i.e.</p>
{% highlight java %}
  if (faceList.isEmpty()) {
    // Just make a Face object for every face Rectangle
    for (int i = 0; i < faces.length; i++) {
      faceList.add(new Face(faces[i].x,faces[i].y,faces[i].width,faces[i].height));
    }
{% endhighlight %}
<p>2) OpenCV found more faces than we currently have in our list.   In this case, we need to match our current Face objects with an OpenCV Rectangle and then add new Face objects for any remaining Rectangles.</p>
{% highlight java %}
} else if (faceList.size() < = faces.length) {
    boolean[] used = new boolean[faces.length];
    // Match existing Face objects with a Rectangle
    for (Face f : faceList) {
       // Find faces[index] that is closest to face f
       // set used[index] to true so that it can't be used twice
       float record = 50000;
       int index = -1;
       for (int i = 0; i < faces.length; i++) {
         float d = dist(faces[i].x,faces[i].y,f.r.x,f.r.y);
         if (d < record &#038;&#038; !used[i]) {
           record = d;
           index = i;
         } 
       }
       // Update Face object location
       used[index] = true;
       f.update(faces[index]);
    }
{% endhighlight %}
<p>Notice how in the above code boolean variables are used to keep track of which Rectangles have already been matched.  We don't want two Face objects to think they are the same face!</p>
<p>3) Finally, the third scenario is that we have more Face objects than OpenCV has found.  In this case, we need to match our existing Face objects and then mark any leftover ones for deletion.</p>
{% highlight java %}
  } else {
    // All Face objects start out as available
    for (Face f : faceList) {
      f.available = true;
    } 
    // Match Rectangle with a Face object
    for (int i = 0; i < faces.length; i++) {
      // Find face object closest to faces[i] Rectangle
      // set available to false
       float record = 50000;
       int index = -1;
       for (int j = 0; j < faceList.size(); j++) {
         Face f = faceList.get(j);
         float d = dist(faces[i].x,faces[i].y,f.r.x,f.r.y);
         if (d < record &#038;&#038; f.available) {
           record = d;
           index = j;
         } 
       }
       // Update Face object location
       Face f = faceList.get(index);
       f.available = false;
       f.update(faces[i]);
    } 
{% endhighlight %}
<p>Full source is here: <a href="http://www.shiffman.net/p5/whichface.zip">whichface.zip</a></p>
