---
title: Asynchronous HTTP Requests in Processing, now with callbacks!
author: Daniel
layout: post
dsq_thread_id:
  - 249553729
  - 249553729
pvc_views:
  - 642
categories:
  - blog
  - ITP
  - p5
  - teaching_
---
<p>I&#8217;m working on a new library that makes asynchronous http requests (web pages, xml feeds, etc.) in <a href="http://www.processing.org">Processing</a> without blocking possible.   It runs its own thread and uses a callback (just like with the serial, video, etc. libraries)  This developed out of a need that I noticed in student projects in my <a href="http://shiffman.net/teaching/icm">Introduction to Computational Media</a> course at ITP.</p>
<p>It&#8217;s all very rough and could use some better documentation, but I thought I might let folks take a look, test it out, and provide feedback.  <a href="http://shiffman.net/teaching/simpleml/">Download and read about it</a>.  Source code is in the zip for the curious.</p>
<p>It needs a better name, clearly. . .</p>
