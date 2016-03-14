---
title: Most Pixels Ever v2.0 (UDP)
author: Daniel
layout: post
dsq_thread_id:
  - 250257037
pvc_views:
  - 1250
categories:
  - General
tags:
  - big screens
  - ITP
  - java
  - mpe
  - processing.org
  - udp
---
<p>First, I&#8217;m pleased to announce that my students&#8217; work from last year&#8217;s <a href="http://itp.nyu.edu/bigscreens/">Big Screens</a> class is featured in the <a href="http://processing.org/exhibition/curated_page_new.html">Processing exhibition</a>.</p>
<p><a href="http://www.flickr.com/photos/shiffman/2756926159/" title="processing_exhibition by shiffman, on Flickr"><img src="http://farm4.static.flickr.com/3205/2756926159_4bb8f62d96_t.jpg" width="100" height="100" alt="processing_exhibition" /></a></p>
<p>In honor of this achievement as well as this coming fall&#8217;s repeat extravaganza, the &#8220;Most Pixels Ever&#8221; library is moving!   I&#8217;m slowly getting rid of the old site and shifting over to <a href="http://code.google.com/hosting/">google code hosting</a>, which has lots of great features for collaborative open source projects.</p>
<p><a href="http://code.google.com/p/mostpixelsever/">http://code.google.com/p/mostpixelsever/</a></p>
<p>Because of the performance issues with v1.0 (especially on windows), I&#8217;m rewriting the library to use UDP.  So far my tests are promising and it looks like this new version should be speedier and more reliable.   Preliminary downloads and source are available <a href="http://code.google.com/p/mostpixelsever/">on the new site</a>.</p>
<p>Here&#8217;s a video of our new test set-up in action at <a href="http://itp.nyu.edu">ITP</a>:</p>
<p><object width="325" height="245"><param name="allowfullscreen" value="true" /><param name="allowscriptaccess" value="always" /><param name="movie" value="http://www.vimeo.com/moogaloop.swf?clip_id=1517179&amp;server=www.vimeo.com&amp;show_title=1&amp;show_byline=1&amp;show_portrait=0&amp;color=00ADEF&amp;fullscreen=1" /><embed src="http://www.vimeo.com/moogaloop.swf?clip_id=1517179&amp;server=www.vimeo.com&amp;show_title=1&amp;show_byline=1&amp;show_portrait=0&amp;color=00ADEF&amp;fullscreen=1" type="application/x-shockwave-flash" allowfullscreen="true" allowscriptaccess="always" width="325" height="245"></embed></object><br />
<a href="http://www.vimeo.com/1517179">http://www.vimeo.com/1517179</a></p>
<p>Also, I should point out that the library will ultimately be expanded to include several different messaging &#8220;modes,&#8221; depending on exactly what type of multi-screen application you are building.  Right now, the only mode available is <b><i>overkill mode</i></b> where the server and client have to shake hands for every single frame rendered. </p>
<p>Finally, there&#8217;s a major bug briefly described on the &#8220;issues&#8221; tab.  Am working to fix it now, but if anyone discovers anything, please let me know. </p>
