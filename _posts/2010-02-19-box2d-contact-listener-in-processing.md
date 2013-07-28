---
title: Box2D ContactListener in Processing
author: Daniel
layout: post
dsq_thread_id:
  - 249560988
pvc_views:
  - 2107
categories:
  - box2d
  - nature of code
  - processing.org
---
<p><a href="http://shiffman.net/itp/classes/nature/box2d_2010/collisions/"><img src="http://shiffman.net/itp/classes/nature/box2d_2010/collisions.jpg"/></a></p>
<p><a href="http://shiffman.net/itp/classes/nature/box2d_2010/collisions/">view applet</a>, <a href="http://shiffman.net/itp/classes/nature/box2d_2010/CollisionsAndControl.zip">download source</a></p>
<p>Above is a new Box2D Processing example that demonstrates two key aspects of working with Box2D:</p>
<p>1) Though tempting as it may be, you cannot set the location manually of an object in the Box2D world and expect the physics to continue to work.  Box2D doesn&#8217;t understand teleportation (which is the equivalent of telling an object to disappear and then reappear at a different pixel).  Rather, if you want to move an object manually, you can attach a joint to the object and tug it around.  This way, you control its motion and yet it still lives within the world of Box2D physics. The example demonstrates how this is done with a MouseJoint.  The object moves according to an perlin noise algorithm (unless the mouse is pressed in which case it follows the mouse).</p>
<p>2) The example also demonstrates how to use Box2D&#8217;s ContactListener to know when objects have collided.  The circles turn red when they encounter the square.</p>
<p>It&#8217;s my intention to eventually add this example with further explanation to the <a href="http://shiffman.net/teaching/nature/box2d-processing/">Box2D tutorial</a>.</p>
