---
title: Updated Kinect Library for Processing
author: Daniel
layout: post
dsq_thread_id:
  - 249554407
pvc_views:
  - 8332
categories:
  - kinect
  - library
  - processing.org
---
<p><iframe src="http://player.vimeo.com/video/18058700?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="435" height="282" frameborder="0"></iframe></p>
<p>A quick post to announce an updated Kinect library for Processing on github. This new library uses the most recent libfreenect drivers (mac os x only still)  and builds off of the existing <a href="http://openkinect.org/wiki/Java_Wrapper">JNI Java Wrapper</a>.  Links:</p>
<p>Download library and example:<br />
<a href="https://github.com/shiffman/libfreenect/raw/master/wrappers/java/processing/distribution/openkinect.zip">openkinect.zip</a></p>
<p>Source:<br />
<a href="https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing">https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing</a></p>
<p>My tests show 30 FPS no problem for either the depth or RGB image, if you request both images, however, I&#8217;m only currently getting about 20 FPS.  The update fixes a few bugs, exposes the raw depth data and includes a point cloud demo (see above video).</p>
<p>I have a list of &#8220;to-do&#8221; items here:</p>
<p><a href="https://github.com/shiffman/libfreenect/issues">https://github.com/shiffman/libfreenect/issues</a><br />
 (a shortened version of the very long list in my head)</p>
