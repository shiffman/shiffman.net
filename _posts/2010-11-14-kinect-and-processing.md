---
title: Kinect and Processing
author: Daniel
layout: post
dsq_thread_id:
  - 249554365
pvc_views:
  - 36147
dsq_needs_sync:
  - 1
categories:
  - kinect
  - processing.org
---
<p><a href="http://www.flickr.com/photos/shiffman/5176786585/" title="Kinect Processing Library in Action by shiffman, on Flickr"><img src="http://farm2.static.flickr.com/1384/5176786585_7f4e7b534f.jpg" width="500" height="359" alt="Kinect Processing Library in Action" /></a></p>
<p>This is all very preliminary, but here is a first pass as a Processing Kinect library:</p>
<p><del>http://www.shiffman.net/p5/kinect.zip</del><br />
(Mac OSX only for now, sorry!)<br />
UPDATE (12/18/10): New version of the library can be downloaded from github:</p>
<p><a href="https://github.com/shiffman/libfreenect/raw/master/wrappers/java/processing/distribution/openkinect.zip">openkinect.zip</a>.  </p>
<p>Source: <a href="https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing">https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing</a></p>
<p>None of this would have been possible without the heroic efforts of <a href="http://marcansoft.com/">Hector Martin</a>, the <a href="https://github.com/OpenKinect/libfreenect">OpenKinect</a> project, and various members of the <a href="http://www.openframeworks.cc/">openFrameworks</a> community.  There&#8217;s a great thread with discussion and code here: </p>
<p><a href="http://www.openframeworks.cc/forum/viewtopic.php?f=14&#038;t=4947">http://www.openframeworks.cc/forum/viewtopic.php?f=14&#038;t=4947</a></p>
<p>Video in action here:</p>
<p><iframe src="http://player.vimeo.com/video/16832724?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="501" height="282" frameborder="0"></iframe></p>
<p>Processing code looks like:</p>
{% highlight java %}

import shiffman.kinect.*;

PImage img;
PImage depth;

 void setup() {
  size(640,240);
  NativeKinect.init();
  img = createImage(640,480,RGB);
  depth = createImage(640,480,RGB);
}

 void draw() {
  NativeKinect.update();
  img.pixels = NativeKinect.getPixels();
  img.updatePixels();

  depth.pixels = NativeKinect.getDepthMap();
  depth.updatePixels();

  image(img,0,0,320,240);
  image(depth,320,0,320,240);
}
</pre>
