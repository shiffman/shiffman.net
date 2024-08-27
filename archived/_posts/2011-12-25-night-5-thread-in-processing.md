---
title: 'Night #5: thread() in Processing'
author: Daniel
layout: post
dsq_thread_id:
  - 515703543
pvc_views:
  - 5924
dsq_needs_sync:
  - 1
categories:
  - General
tags:
  - processing.org
  - threads
---
<p>I just updated the <a href="http://wiki.processing.org/w/Threading">Thread tutorial</a> on the Processing wiki to include a little known function in Processing called thread().  It&#8217;s undocumented as of now, but will be a part of the 2.0 documentation.  Here&#8217;s how it works.</p>
<p>(This following is excerpted from the tutorial).</p>
<p>You are likely familiar with the idea of writing a program that follows a specific sequence of steps &#8212; setup() first then draw() over and over and over again! A Thread is also a series of steps with a beginning, a middle, and an end. A Processing sketch is a single thread, often referred to as the &#8220;Animation&#8221; thread. Other threads sequences, however, can run independently of the main &#8220;Processing&#8221; sketch. In fact, you can launch any number of threads at one time and they will all run concurrently.</p>
<p>Processing does this all the time, whenever you write an event callback, such as serialEvent(), or captureEvent(), etc. these functions are triggered by a different thread running behind the scenes, and they alert Processing whenever they have something to report. This is useful whenever you need to perform a task that takes too long and would slow down the main animation&#8217;s frame rate, such as grabbing data from the network (XML, database, etc.) If a separate thread gets stuck or has an error, the entire program won&#8217;t grind to a halt, since the error only stops that individual thread. To create independent, asynchronous threads, you can use the thread() function built into Processing.</p>
{% highlight java %}
void setup() {
  size(200,200);
  // This will tell someFunction() to execute now as a separate thread
  thread("someFunction");
}
 
void draw() {
 
}
 
void someFunction() {
  // This function will run as a thread when called via
  // thread("someFunction") as it was in setup!
}
{% endhighlight %}
<p>The thread() function receives a String as an argument. The String should match the name of the function you want to run as a thread.   While using the thread() function is a very simple way of getting an independent thread, it is somewhat limited. Being able to make a thread object is a great deal more powerful, and this can be done by extending java&#8217;s <a href="http://docs.oracle.com/javase/6/docs/api/java/lang/Thread.html">Thread</a> class.</p>
<p>For more about how to do that, take a look at <a href="http://wiki.processing.org/w/Threading">the full tutorial</a>.</p>
<p>Following is an example draws a loading bar in the &#8220;animation&#8221; thread that reports progress on another thread().  This is a nice demonstration, however, it would not be necessary in a sketch where you wanted to load data in the background and hide this from the user, allowing the draw() loop to simply continue.</p>
<p><a href='http://shiffman.net/wp/wp-content/uploads/2011/12/Threads.zip'><img src="http://shiffman.net/wp/wp-content/uploads/2011/12/threads.png" alt="" title="threads" width="500" height="298" class="alignnone size-full wp-image-1029" /></a></p>
<p><a href='http://shiffman.net/wp/wp-content/uploads/2011/12/Threads.zip'>Threads.zip</a></p>
