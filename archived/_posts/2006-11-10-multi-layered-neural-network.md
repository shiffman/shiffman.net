---
title: Multi-Layered Neural Network
author: Daniel
layout: post
dsq_thread_id:
  - 249553724
pvc_views:
  - 722
categories:
  - blog
  - ITP
  - neural
  - p5
  - programming
  - teaching_
---
<p><a href="http://shiffman.net/itp/classes/nature/nn/xor/"><img src="http://shiffman.net/itp/classes/nature/nn/xor/xor.jpg" alt="nn" /></a><br />
<a href="http://shiffman.net/itp/classes/nature/nn/xor/">view applet and source</a></p>
<p>So after a fierce battle with my own neurons, I am ready to release part II of my <a href="http://www.processing.org">Processing</a> series: &#8220;Neural Network!  Huah!  What is it good for? (Sing it again, now.)&#8221;</p>
<p>This example implements a multi-layered neural network that learns via &#8220;back propogation.&#8221; It&#8217;s specifically trained to solve XOR.  In other words, there are two inputs and the desired result is input1 XOR input2.</p>
<p>0,1 &#8211;> 1<br />
1,0 &#8211;> 1<br />
0,0 &#8211;> 0<br />
1,1 &#8211;> 0</p>
<p>The structure looks something like this:</p>
<p><a href="http://www.flickr.com/photos/shiffman/293987721/" title="Photo Sharing"><img src="http://static.flickr.com/117/293987721_280d21ba51_m.jpg" width="240" height="180" alt="11102006163" /></a></p>
<p>However, I think there might be a flaw in my back propogation learning algorithm.  For whatever reason, with the above neural structure, I can only successfully train my network (starting with random connection weights between -1 and 1) approximately 60% of the time.  For the other 40%, the network gets stuck and can&#8217;t find the proper solution.  If I add two more neurons to the hidden layer, like so. . . </p>
<p><a href="http://www.flickr.com/photos/shiffman/293989845/" title="Photo Sharing"><img src="http://static.flickr.com/107/293989845_1ebffca388_m.jpg" width="240" height="180" alt="11102006164" /></a></p>
<p>. . . it trains flawlessly, finding a reasonable solution space after  a few thousand training iterations 100% of the time (or at least as far as I can reasonably test.)   What am I missing?</p>
<p>Anyway, a more involved tutorial about the theory, concepts, algorithms, and code behind neural networks is forthcoming. . . at some point. . .  after I invent that machine that makes time that is . .</p>
<p><del>If you are <a href="http://shiffman.net/itp/classes/nature/nn/xor/xor.zip">downloading the source</a>, note that the code for the nn.jar package is contained in /xor/code/src/nn.  Because I&#8217;m using a large number of classes in the design of the network, I didn&#8217;t want to restrict myself to Processing tabs.</del> Update (2/08/10): New download link: <a href="http://shiffman.net/teaching/nature/nn/">http://shiffman.net/teaching/nature/nn/</a></p>
