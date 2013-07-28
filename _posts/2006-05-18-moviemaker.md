---
title: MovieMaker Processing Library
author: Daniel
layout: post
dsq_thread_id:
  - 249553477
pvc_views:
  - 8669
categories:
  - blog
  - ITP
  - p5
  - teaching_
---
<p>Update:  MovieMaker is now part of <a href="http://processing.org">Processing&#8217;s</a> <a href="http://www.processing.org/reference/libraries/video/index.html">core video library</a> so this post is now irrelevant!</p>
<p><font color = #CCC></p>
<p>Based on some code from Shawn Van Every and Dan O&#8217;Sullivan, I&#8217;ve created a Processing library that allows you to take any pixel array (from the window or from a PImage) and create a quicktime movie.  </p>
<li class="arrow">It might be buggy if the movie file already exists.  In theory, it should delete the file and rewrite a new one, but this is not always working.</li>
<li class="arrow">You must call finishMovie() yourself.   You might try calling it in stop() or destroy() but it doesn&#8217;t seem to work consistently so in my example below, I just call it in mousePressed().  Ultimately, I&#8217;d like to have the library call it automatically when the applet quits. . .</li>
<li class="arrow">Need to implement more codecs (you can actually pass in any available constants, i just made nice little variables for a few.)</li>
<li class="arrow">This needs to be tested on Windows.  In theory, the library checks your OS and flips pixels if you are using Windows, but haven&#8217;t tested it yet. Oh, and there might be a problem on intel macs if they think like windows in terms of pixels.</li>
<p>&nbsp;<br />
E-mail me bug reports, questions, comments, etc.!</p>
<p>Example code:</p>
{% highlight java %}
import moviemaker.*;

MovieMaker mm;
float theta = 0;

void setup() {
  size(200,200);
  mm = new MovieMaker(this,width,height,"p5test.mov", MovieMaker.JPEG, MovieMaker.HIGH,30);
}

void draw() {
  background(sin(theta)*127+127,255,cos(theta)*127+127);
  theta += 0.05;
  loadPixels();
  mm.addFrame(pixels,width,height);
}

public void mousePressed() {
  mm.finishMovie();
}
</pre>
<p>Available constants:<br />
code: RAW, JPEG, CINEPAK, SORENSON, VIDEO<br />
quality: LOW, MEDIUM, HIGH, BEST<br />
</font></p>
