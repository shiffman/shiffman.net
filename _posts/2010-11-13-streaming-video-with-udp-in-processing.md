---
title: Streaming video with UDP in Processing
author: Daniel
layout: post
dsq_thread_id:
  - 249611830
pvc_views:
  - 11272
categories:
  - processing.org
  - udp
tags:
  - processing.org
---
<p><a href="http://www.flickr.com/photos/shiffman/5172329510/" title="UDP Video Streaming from Processing by shiffman, on Flickr"><img src="http://farm5.static.flickr.com/4060/5172329510_afd78f0bea_m.jpg" width="240" height="224" alt="UDP Video Streaming from Processing" /></a></p>
<p>ITP students Jeff Howard and Alex Vessels are working on a <a href="http://itp.nyu.edu/~arv224/blog/?p=234">video piece</a> for the upcoming <a href="http://itp.nyu.edu/bigscreens2010/">Big Screens show</a>.  One of the challenges we&#8217;ve always had working on the <a href="http://en.wikipedia.org/wiki/IAC_Video_Wall">IAC Video Wall</a> is that the computers that actually drive the wall are locked up in a control room far away from the lobby itself.  So any external devices (cameras, sensors, etc.) have to communicate with those machines over the network.  For this year&#8217;s show, we&#8217;ve developed a new solution using UDP streaming to send imagery from a camera to the control room. </p>
<p>This example owes a ton to the <a href="http://ubaa.net/shared/processing/udp/">Processing UDP Library</a> by Stephane Cousot.  I&#8217;m using the Java <a href="http://download.oracle.com/javase/tutorial/networking/datagrams/index.html">DataGram</a> classes directly instead of the Processing library, but the code draws heavily on techniques from the UDP library.</p>
<p>A couple quick notes about the code.  </p>
<p>First, whenever sending data over a network, it&#8217;s generally a good idea to try to minimize the amount of data.  A 320&#215;240 image has 76,800 pixels, each pixel having 3 bytes (red, green, and blue).  That&#8217;s 230,400 bytes that need to be sent 30 times per second.  It&#8217;s do-able, but if we could compress the image first (encoding it as a JPG, for example) before sending, this will save a ton.   Encoding a JPG in Java is pretty easy since the <a href="http://download.oracle.com/javase/1.4.2/docs/api/javax/imageio/ImageIO.html">ImageIO</a> classes take care of that for you.  Assuming you have a PImage variable &#8220;img&#8221; the code looks something like:</p>
{% highlight java %}
  // We need a buffered image to do the JPG encoding
  int w = img.width;
  int h = img.height;
  BufferedImage b = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);

  // Transfer pixels from localFrame to the BufferedImage
  img.loadPixels();
  b.setRGB( 0, 0, w, h, img.pixels, 0, w);

  // Need these output streams to get image as bytes for UDP
  ByteArrayOutputStream baStream = new ByteArrayOutputStream();
  BufferedOutputStream bos = new BufferedOutputStream(baStream);

  // JPG compression into BufferedOutputStream
  // Requires try/catch
  try {
    ImageIO.write(b, "jpg", bos);
  } catch (IOException e) {
    e.printStackTrace();
  }
{% endhighlight %}
<p>You might also notice that the above code includes a <a href="http://download.oracle.com/javase/tutorial/essential/exceptions/">try/catch</a> statement around the encoding of the JPG.  Certain &#8220;exceptions&#8221; (i.e. errors) in Java require special handling and try/catch is one way of fulfilling that requirement.  You&#8217;ll see in the example code that try/catch is also required for the UDP communication.  For more about exception handling in Processing check out: <a href="http://wiki.processing.org/w/Exceptions">http://wiki.processing.org/w/Exceptions</a>.</p>
<p>Finally, you&#8217;ll also notice in the examples that you are required to specify the IP address you are sending to and the port, i.e.</p>
{% highlight java %}
int clientPort = 9100; 
InetAddress client =  InetAddress.getByName("localhost"); 
ds.send(new DatagramPacket(packet,packet.length,client,clientPort));
{% endhighlight %}
<p>The port can be anything (as long as it doesn&#8217;t conflict with a port you are using for something else).  Generally, 9000 and above is a safe bet.   The IP address should be the IP of the machine receiving the video stream.  For demonstration purposes, it&#8217;s simply &#8220;localhost&#8221;, i.e. we&#8217;re going to send and receive on the same machine.</p>
<p>Finally, the checkForNewImage() function in the VideoReceiver example is blocking, meaning if no data is coming in Processing will stop and wait.  So really, it should exist in a separate thread.  Stay tuned for an updated threaded version as well as simple thread tutorial!</p>
<p>Update: the thread example is in the download and more here: </p>
<p><a href="http://www.shiffman.net/2010/11/13/processing-and-threads/">http://www.shiffman.net/2010/11/13/processing-and-threads/</a></p>
<p>Download the code for sending and receiving: <a href="http://www.shiffman.net/p5/image_streaming.zip">image_streaming.zip</a>.</p>
