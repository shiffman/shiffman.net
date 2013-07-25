---
title: 'Night #8: Rendering an image sequence'
author: Daniel
layout: post
pvc_views:
  - 6066
dsq_thread_id:
  - 518939724
categories:
  - processing.org
tags:
  - image sequence
  - png
  - render
  - saveFrame
---
<p>How can one render a movie from a Processing sketch?  In Processing 1.5.1, there is a class called MovieMaker which generates a Quicktime file directly.  However, this class uses ye old Quicktime for Java and will be removed from Processing 2.0.  Instead, 2.0 is going to introduce a MovieMaker &#8220;tool&#8221; which will generate a movie file from a directory containing an image sequence.  So how do you get this image sequence?  Easy:</p>

{% highlight java %}
void draw() {
  saveFrame("output/frames####.png");
}
{% endhighlight %}

<p>This will create a folder called &#8220;output&#8221; in your sketch folder and for each frame of draw(), it will write a file &#8212; frames0001.png, frames0002.png, etc.  The &#8216;#&#8217; sign tells processing to auto-number the images.</p>
<p>There are a couple additional tricks included in this new example.  First, it uses a boolean variable to turn rendering on and off and cycles the boolean in keyPressed() allowing recording to be stopped and started by pressing &#8216;r&#8217;. </p>

{% highlight java %}
void draw() {
  // If we are recording call saveFrame!
  if (recording) {
    saveFrame("output/frames####.png");
  }
}

void keyPressed() {  
  // If we press r, start or stop recording!
  if (key == 'r' || key == 'R') {
    recording = !recording;
  }
}
{% endhighlight %}

<p>In addition, if you draw anything after saveFrame() it won&#8217;t actually appear in the output, but will be seen on screen.  This is useful for debugging information that you don&#8217;t want included in the render.</p>
<pre lang="java">
void draw() {
 
  // Draw lots of stuff to be recorded!
  
  if (recording) {
    saveFrame("output/frames####.png");
  }
   
  // Draw our debugging stuff that won't be recorded!
}
</pre>
<p>Until 2.0 is released, I recommend you use 3rd party software to take the image sequence and turn it into a movie.  The old Quicktime 7 will do the trick, as well as any number of video production applications like final cut, after effects, iMovie, etc.  The nice thing about using a PNG sequence is that the images are uncompressed, but aren&#8217;t as large as say TIFFs.  I don&#8217;t recommend saving JPGs because then you will likely be compressing twice (once when saving the image, once when exporting the movie file).</p>
<p><a href='http://www.shiffman.net/wp/wp-content/uploads/2011/12/SavingFrames.zip'><img src="http://www.shiffman.net/wp/wp-content/uploads/2011/12/frames.png" alt="" title="frames" width="590" height="352" class="alignnone size-full wp-image-1075" /></a></p>
<p>Source: <a href='http://www.shiffman.net/wp/wp-content/uploads/2011/12/SavingFrames.zip'>SavingFrames.zip</a></p>
