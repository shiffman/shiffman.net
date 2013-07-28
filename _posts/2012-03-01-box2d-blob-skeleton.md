---
title: Box2D Blob Skeleton
author: Daniel
layout: post
pvc_views:
  - 6606
dsq_thread_id:
  - 595282766
categories:
  - processing.org
tags:
  - box2d
  - nature of code
  - processing.org
---
<p>This week in nature of code, we talked about using a physics engine to build a skeleton for an onscreen character.   Two projects we referenced are:</p>
<p><iframe src="http://player.vimeo.com/video/1472427" width="250" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe> <iframe src="http://player.vimeo.com/video/8487873" width="250" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe></p>
<p>Karsten Schmidt&#8217;s <a href="http://postspectacular.com/work/nokia/friends/start">Nokia Friends</a> and Elie Zananiri&#8217;s <a href="http://www.bigscreams.com">Big Screams</a>.</p>
<p>Both of these projects involve an underlying skeleton (Nokia Friends uses toxiclibs verlet physics and Big Screams uses Box2D) that serves as the structure for a cute, cuddly, jiggly creature drawn on top.   In my efforts to expand the nature of code materials this semester, I&#8217;ve released a new example that demonstrates this technique.  It constructs a skeleton of out Box2D bodies and distance joints and draws a curvy polygon with eyes and mouth on top.</p>
<p><img src="http://shiffman.net/wp/wp-content/uploads/2012/03/blob11.png" alt="" title="blob1" width="240" height="143" class="alignnone size-full wp-image-1108" />  <img src="http://shiffman.net/wp/wp-content/uploads/2012/03/blob21.png" alt="" title="blob2" width="240" height="143" class="alignnone size-full wp-image-1108" /> </p>
<p>Source: <a href='http://shiffman.net/wp/wp-content/uploads/2012/03/BlobSkeleton.zip'>BlobSkeleton.zip</a><br />
(Requires pbox2d: <a href="https://github.com/shiffman/PBox2D">https://github.com/shiffman/PBox2D</a></p>
