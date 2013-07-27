---
title: Look Ma, No Windows!
author: Daniel
layout: post
dsq_thread_id:
  - 249553107
pvc_views:
  - 237
categories:
  - blog
  - ITP
  - p5
  - teaching_
---
<p>How to export a processing application full-screen:</p>
<p><img src="http://www.shiffman.net/p5/p5_fullscreen.jpg" alt="p5 fullscreen"/></p>
<p>I&#8217;ve received several inquiries regarding how to make a Processing exported application run fullscreen. . .  Here are a few simple steps and some code (using my <a href="http://www.processing.org/learning/examples/mandelbrot.html">Mandelbrot</a> example). . . More information can be found in the <a href="http://www.processing.org/faq/bugs.html#application">Processing FAQ</a>.</p>
<p>Step #1:<br />
In order to auto-launch in &#8220;present&#8221; mode, you must write your own &#8220;main&#8221; function and specify the relevant arguments:</p>
<p><i>static public void main(String args[]) {<br />
  PApplet.main(new String[] { &#8220;&#8211;present&#8221;, &#8220;&#8211;display=1&#8243;, &#8220;mandelbrot&#8221; });<br />
}</i></p>
<p>There are various arguments you can provide for the main method, and <a href="http://dev.processing.org/reference/core/javadoc/processing/core/PApplet.html#main(java.lang.String[])">you can read full documentation here.</a>  (In this case, &#8216;present&#8217; means run in present mode, &#8216;display=1&#8242; indicates use the default display, and &#8216;mandelbrot&#8217; is the name of the main class.)</p>
<p>Also, if you want to application to run at really really really full screen, simply set the size to screen.width x screen.height, i.e.:</p>
<p><i>size(screen.width,screen.height);</i></p>
<p>(Use the above code with caution, if your screen resolution is very large, slowness may ensue. . .) </p>
<p>Application and Source for Example:<br />
<a href="http://www.shiffman.net/p5/mandelbrot_mac.zip">Mac Version</a><br />
<a href="http://www.shiffman.net/p5/mandelbrot_windows.zip">Windows Version</a><br />
<a href="http://www.shiffman.net/p5/mandelbrot_linux.zip">Linux Version</a></p>
