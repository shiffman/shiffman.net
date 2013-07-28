---
title: Most Pixels Ever
author: Daniel
layout: post
enclosure:
  - |
    |
        http://shiffman.net/movies/mpe.mov
        1219277
        video/quicktime
        
  - |
    |
        http://shiffman.net/movies/mpe.mov
        1219277
        video/quicktime
        
  - |
    |
        http://shiffman.net/movies/mpe.mov
        1219277
        video/quicktime
        
  - |
    |
        http://shiffman.net/movies/mpe.mov
        1219277
        video/quicktime
        
  - |
    |
        http://shiffman.net/movies/mpe.mov
        1219277
        video/quicktime
        
  - |
    |
        http://shiffman.net/movies/mpe.mov
        1219277
        video/quicktime
        
dsq_thread_id:
  - 249553836
  - 249553836
pvc_views:
  - 2285
categories:
  - ITP
  - java
  - p5
  - vlog
---
<p>Coming soon to a <a href="http://www.processing.org">Processing</a> library folder near you, I&#8217;m pleased to announce a new project I&#8217;m developing at ITP with <a href="http://www.funnydata.com/">Chris Kairalla</a>, tentatively titled &#8220;Most Pixels Ever.&#8221;  </p>
<p>&#8220;Most Pixels Ever&#8221; (not to be confused with &#8220;Best Pixels Ever&#8221;) is an open source Java framework for spanning real-time graphics applets/applications across multiple screens.   The above video is a quick demonstration of the first prototype.  Three client applications on three Mac Pros connect to six 32 inch LCD displays (each Mac has a dual video card, but this could just have easily work with 6 client machines).  One of the Macs is also running a server application.  The server tells each client about the master pixel dimensions of all the screens combined (here 8160&#215;768). The client keeps track of its own location dimensions (say 2720&#215;768) as well as its location with in master dimensions (say 5040,0).  The server keeps everyone in line, making sure that frames are rendered in sync.</p>
<p>In theory, the system is scalable to whatever your network will allow.  If you want to run 100 LCD displays off of 100 mac minis, you can (and I really hope you do.)</p>
<p>We&#8217;ve got quite a bit of testing and tweaking to do before we release the library to the public along with documentation and instructions.  So stay tuned!</p>
<p>Thanks to <a href="http://www.plocktau.com/">Caleb Clark</a> for the video.</p>
