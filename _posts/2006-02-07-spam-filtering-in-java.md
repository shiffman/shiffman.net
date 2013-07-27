---
title: Spam Filtering in Java
author: Daniel
layout: post
dsq_thread_id:
  - 255805439
pvc_views:
  - 1443
categories:
  - blog
  - ITP
  - p5
  - teaching_
---
<p><a href="http://www.shiffman.net/itp/classes/a2z/week04/spam.jpg"><img src="http://www.shiffman.net/itp/classes/a2z/week04/spam_small.jpg"/></a></p>
<p>I&#8217;ve developed a basic Java implementation of <a href="http://www.paulgraham.com/">Paul Graham&#8217;s</a> bayesian <a href="http://www.paulgraham.com/spam.html">spam filter</a> as an example for my <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/">Programming from A to Z</a> course.  <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/bayesian/">A full explanation is available</a>.  This isn&#8217;t a robust spam filter by any means, but simply demonstrates the basics.</p>
<p>The above visualization was quickly hacked out with <a href="http://www.processing.org">Processing</a> and shows the words most likely to indicate a spam e-mail with size tied to frequency of occurence (Note these aren&#8217;t the same thing, just because it appears more often doesn&#8217;t mean it&#8217;s more likely to indicate spam.  It could just as well appear <i>more often</i> in so-called &#8220;good&#8221; e-mails.)  This also uses an incredibly lame (i.e. small) training set of &#8220;bad&#8221; and &#8220;good&#8221; messages and is flawed in many other ways.  Someday, I might actually do something interesting with this.  Sigh.</p>
