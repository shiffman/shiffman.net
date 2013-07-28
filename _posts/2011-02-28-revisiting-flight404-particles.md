---
title: Revisiting Flight404 Particles
author: Daniel
layout: post
dsq_thread_id:
  - 249553380
pvc_views:
  - 3352
categories:
  - processing.org
tags:
  - flight404
  - nature of code
  - particles
---
<p><img src="http://www.shiffman.net/wp/wp-content/uploads/2011/02/flight404_particles.jpg" alt="" title="flight404_particles" width="400" height="411" class="alignnone size-full wp-image-793" /></p>
<p>In February 2008, Robert Hodgin <a href="http://www.flight404.com/blog/?m=200802">published a series of Particle System examples</a> that demonstrated many of the techniques behind his amazing work (i.e.: <a href="http://roberthodgin.com/magnetosphere-itunes-visualizer/">Magnetosphere</a>).   The examples, however, use some advanced GL calls (accessing JOGL directly) and no longer work in Processing 1.0.   I would say a student comes to ask me about updating these examples several times a semester and I usually respond by pretending I know what I&#8217;m talking about and pointing the student some vague openGL direction.   It&#8217;s time for me to bit the bullet and figure this out myself.  </p>
<p>Step 1 is simply to recreate the examples using Processing&#8217;s core drawing functions sans fancy GL.   Now, they run a great deal slower than Robert&#8217;s original examples because they are no longer using display lists.</p>
<p>Check out the <a href="https://github.com/shiffman/The-Nature-of-Code/tree/master/chp4_systems/flight404">source</a> or download <a href="https://github.com/shiffman/The-Nature-of-Code/blob/master/chp4_systems/flight404.zip?raw=true">flight404.zip</a>.</p>
<p>Step 2 is to use the wonderful <a href="http://codeanticode.wordpress.com/">Andrés Colubri</a>&#8216;s <a href="http://glgraphics.sourceforge.net/">GLGraphics</a> library to optimize for performance.   Stay tuned.</p>
<p>UPDATE: Step 1a is to create OPENGL textures directly like so:</p>
{% highlight java %}
particleTex = TextureIO.newTexture(new File(dataPath("particle.png")), true);
{% endhighlight %}
<p>(Thanks to Jeff Gentes and Ben Hosken for chiming in)</p>
