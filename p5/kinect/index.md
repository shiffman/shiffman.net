---
title: Getting Started with Kinect and Processing
author: Daniel
layout: page
dsq_thread_id:
  - 
pvc_views:
  - 149402
dsq_needs_sync:
  - 1

---
<p><iframe src="http://player.vimeo.com/video/18058700?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="400" height="225" frameborder="0"></iframe></p>
<p>So, you want to use the Kinect in Processing.  Great.  This page will serve to document the current state of my Processing Kinect library, with some tips and info.</p>

<h2>The current state of affairs</h2>

Since the kinect launched in November 2010, there have been several models released.  Here's a quick list of what is out there and what is supported in Processing for Mac OS X.

<ul>
  <li><b>Kinect 1414</b>: This is the original kinect and works with the library documented on this page in Processing 2.1</li>
  <li><b>Kinect 1473</b>: This looks identical to the 1414, but is an updated model.  This kinect does not currently work with any of the Processing libraries, but I hope to update the library soon for compatibility.  If you would like to help with this, please get in touch!</li>
  <li><b>Kinect for Windows version 1</b>: This kinect also does not curently work with Processing, but as with the 1473, I hope to get the library updated soon.
  <li><b>Kinect for Windows version 2</b>: This is the brand spanking new kinect with all the features found in the XBox One Kinect.  As <a href="https://github.com/OpenKinect/libfreenect2">libfreenect2</a> gets further along, I hope to be able to add support for this kinect in Processing.
</ul>

<p>Now, before you proceed, you may want to instead consider using the <a href="http://code.google.com/p/simple-openni/">SimpleOpenNI library</a> and read Greg Borenstein&#8217;s <a href="http://urbanhonking.com/ideasfordozens/2011/10/08/making-things-see-available-for-early-release/">Making Things See</a> book!  OpenNI has lots of features (skeleton tracking, gesture recognition, etc.) that are not available in this library.  Unfortunately, <a href="http://www.openni.org/">OpenNI was recently purchased by Apple</a> and appears to be shutting down.  It's unclear what the future will be of OpenNI and SimpleOpenNI.</p>

<h2>I&#8217;m ready to get started right now</h2>
<ul>
<li>Install the library via the Processing Contribution manager or <a href="https://github.com/shiffman/OpenKinect-for-Processing/releases">get it here</a>.</li>
<li>Source code: <a href="https://github.com/shiffman/OpenKinect-for-Processing">https://github.com/shiffman/OpenKinect-for-Processing</a></li>
<li>Open Kinect: <a href="http://openkinect.org/">openkinect.org</a></li>
</ul>
<h2>What hardware do I need?</h2>
<p>First you need a &#8220;stand-alone&#8221; kinect (model 1414 only for now!).  You do not need to buy an Xbox.  If you get the stand-alone kinect listed below, it will come with a USB adapter and power supply.</p>
<p><a href="http://www.amazon.com/gp/product/B002BSA298?ie=UTF8&#038;tag=learniproces-20&#038;linkCode=as2&#038;camp=1789&#038;creative=390957&#038;creativeASIN=B002BSA298">Standalone Kinect Sensor</a><img src="http://www.assoc-amazon.com/e/ir?t=learniproces-20&#038;l=as2&#038;o=1&#038;a=B002BSA298" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;" /></p>
<p>If you have a previous kinect that came with an XBox, it will not include the USB adapter.  You&#8217;ll need to purchase this separately:</p>
<p><a href="http://store.microsoft.com/microsoft/Kinect-Sensor-Power-Supply/product/9A4CFC08">Kinect Sensor Power Supply</a></p>
<h2>Um, what is Processing?</h2>
<p>I&#8217;m going to assume you are familiar with Processing, but just in case you are not, I suggest checking out: <a href="http://processing.org/">processing.org</a>  (Processing is an open source programming language and environment for people who want to create images, animations, and interactions. Initially developed to serve as a software sketchbook and to teach fundamentals of computer programming within a visual context, Processing also has evolved into a tool for generating finished professional work. Today, there are tens of thousands of students, artists, designers, researchers, and hobbyists who use Processing for learning, prototyping, and production.)</p>
<h2>What if I don&#8217;t want to use Processing?</h2>
<p>If you are comfortable with C++ I suggest you consider using <a href="">openFrameworks</a> or <a href="">Cinder</a> with the Kinect.  At the time of this writing, these environments have some features I haven&#8217;t implemented yet and you also get a C++ speed advantage when processing the depth data, etc.:</p>
<p><a href="https://github.com/ofTheo/ofxKinect">ofxKinect</a><br />
<a href="https://github.com/cinder/Cinder-Kinect">Kinect CinderBlock</a></p>
<p>More resources from: <a href="http://openkinect.org">The OpenKinect Project</a></p>
<h2>I&#8217;ve got Processing, how do I download and install the library?</h2>
<p>By default Processing will create a &#8220;sketchbook&#8221; folder in your Documents directory, i.e. on my machine it&#8217;s:</p>

<pre>
/Users/daniel/Documents/Processing/
</pre>

<p>The easiest way to install the library is to go to SKETCH-->IMPORT LIBRARIES-->ADD LIBRARY and search for "Kinect".  A button will appear labeled "install".</p>

<p>If you want to install it manually, find the libraries folder (create it if it isn't already there).  On my machine it's:</p>

<pre>
/Users/daniel/Documents/Processing/libraries/
</pre>

<p>Then go and download <a href="https://github.com/shiffman/OpenKinect-for-Processing/releases">the most recent release</a> and extract it in the libraries folder.  Restart Processing, open up one of the examples in the examples folder and you are good to go!</p>

<h2>What code do I write?</h2>
<p>To get started using the library, you need to include the proper import statements at the top of your code:</p>
{% highlight java %}
import org.openkinect.*;
import org.openkinect.processing.*;
{% endhighlight %}
<p>As well as a reference to a &#8220;Kinect&#8221; object, i.e.</p>
{% highlight java %}
// Kinect Library object
Kinect kinect;
{% endhighlight %}
<p>Then in setup() you can initialize that kinect object:</p>
{% highlight java %}
  kinect = new Kinect(this);
  kinect.start();
{% endhighlight %}
<p>Once you&#8217;ve done this you can begin to access data from the kinect sensor.</p>
<p>Currently, the library makes data available to you in four ways: </p>
<ol>
<li>RGB image from the kinect camera as a PImage.</li>
<li>Grayscale image from the IR camera as a PImage</li>
<li>Grayscale image with each pixel&#8217;s brightness mapped to depth (brighter = closer). </li>
<li>Raw depth data (11 bit numbers  between 0 and 2048) as an int[] array</li>
</ol>
<p>Let&#8217;s look at these one at a time.  If you want to use the Kinect just like a regular old webcam, you can request that the RGB image is captured:</p>
{% highlight java %}
  kinect.enableRGB(true);
{% endhighlight %}
<p>Then you simply ask for the image as a <a href="http://processing.org/reference/PImage.html">PImage!</a></p>
{% highlight java %}
  PImage img = kinect.getVideoImage();
  image(img,0,0);
{% endhighlight %}
<p>Alternatively, you can enable the IR image:</p>
{% highlight java %}
  kinect.enableIR(true);
{% endhighlight %}
<p>Currently, you cannot have both the RGB image and the IR image.  They are both passed back via getVideoImage() so whichever one was most recently enabled is the one you will get.</p>
<p>Now, if you want the depth image, you can:</p>
{% highlight java %}
  kinect.enableDepth(true);
{% endhighlight %}
<p>and request the grayscale image:</p>
{% highlight java %}
  PImage img = kinect.getDepthImage();
  image(img,0,0);
{% endhighlight %}
<p>As well as the raw depth data:</p>
{% highlight java %}
  int[] depth = kinect.getRawDepth();
{% endhighlight %}
<p>If you are looking at the raw depth data only, you can turn off the library&#8217;s behind the scenes depth image processing to make it slightly more efficient:</p>
{% highlight java %}
  kinect.processDepthImage(false);
{% endhighlight %}
<p>Finally, you can also adjust the camera angle with the tilt() function, i.e.:</p>
{% highlight java %}
float deg = 15;
kinect.tilt(deg);
{% endhighlight %}
<p>So, there you have it, here are all the useful functions you might need to use the Processing kinect library:</p>
<ol>
<li><b>enableRGB(boolean)</b> &#8212; turn on or off the RGB camera image</li>
<li><b>enableIR(boolean)</b> &#8212; turn on or off the IR camera image</li>
<li><b>enableDepth(boolean)</b> &#8212; turn on or off the depth tracking</li>
<li><b>processDepthImage(boolean)</b> &#8212; turn on or off the depth image processing</li>
<li><b>PImage getVideoImage()</b> &#8212; grab the RGB or IR video image</li>
<li><b>PImage getDepthImage()</b> &#8212; grab the grayscale depth map image</li>
<li><b>int[] getRawDepth()</b> &#8212; grab the raw depth data</li>
<li><b>tilt(float)</b> &#8212; adjust the camera angle (between 0 and 30 degrees)</li>
</ol>
<h2>Where&#8217;s the javadoc?</h2>
<p>Stay tuned, I&#8217;ll get something up soon.  All the source is here:</p>
<p><a href="https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing">https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing</a></p>
<h2>So now what?</h2>
<p>So far, I only have three basic examples:</p>
<h2>Display RGB, IR, and Depth Images</h2>
<p><iframe src="http://player.vimeo.com/video/18748853?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="500" height="313" frameborder="0"></iframe><br />
Code:<a href="https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing/distribution/openkinect/examples/RGBDepthTest">RGBDepthTest</a></p>
<p>(NOTE: KNOWN BUG IN IR IMAGE RIGHT NOW)</p>
<p>This example does nothing but use all of the above listed functions to display the data from the kinect sensor.</p>
<h2>Point Cloud</h2>
<p><iframe src="http://player.vimeo.com/video/18058700?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="501" height="282" frameborder="0"></iframe><br />
Code: <a href="https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing/distribution/openkinect/examples/PointCloud">PointCloud</a></p>
<p>Here, we&#8217;re doing something a bit fancier.  Number one, we&#8217;re using the 3D capabilities of Processing to draw points in space.  You&#8217;ll want to familiarize yourself with <a href="http://processing.org/reference/translate_.html">translate()</a>, <a href="http://processing.org/reference/rotate_.html">rotate()</a>, <a href="http://processing.org/reference/pushMatrix_.html">pushMatrix()</a>, <a href="http://processing.org/reference/popMatrix_.html">popMatrix()</a>.  This <a href="http://processing.org/learning/transform2d/">tutorial</a> is also a good place to start.  In addition, the example uses a PVector to describe a point in 3D space.  More here: <a href="http://processing.org/learning/pvector/">PVector tutorial</a>.</p>
<p>The real work of this example, however, doesn&#8217;t come from me at all.  The raw depth values from the kinect are not directly proportional to physical depth.  Rather, they scale with the inverse of the depth according to this formula:</p>
{% highlight java %}
depthInMeters = 1.0 / (rawDepth * -0.0030711016 + 3.3309495161);
{% endhighlight %}
<p>Rather than do this calculation all the time, we can precompute all of these values in a lookup table since there are only 2048 depth values.</p>
{% highlight java %}
float[] depthLookUp = new float[2048];
for (int i = 0; i < depthLookUp.length; i++) {
  depthLookUp[i] = rawDepthToMeters(i);
}

float rawDepthToMeters(int depthValue) {
  if (depthValue < 2047) {
    return (float)(1.0 / ((double)(depthValue) * -0.0030711016 + 3.3309495161));
  }
  return 0.0f;
}
{% endhighlight %}
<p>Thanks to <a href="http://graphics.stanford.edu/~mdfisher/Kinect.html">Matthew Fisher</a> for the above formula.  (Note: for the results to be more accurate, you would need to calibrate your specific kinect device, but the formula is close enough for me so I'm sticking with it for now.  More about calibration in a moment.)</p>
<p>Finally, we can draw some points based on the depth values in meters:</p>
{% highlight java %}
  for(int x = 0; x < w; x += skip) {
    for(int y = 0; y < h; y += skip) {
      int offset = x+y*w;

      // Convert kinect data to world xyz coordinate
      int rawDepth = depth[offset];
      PVector v = depthToWorld(x,y,rawDepth);

      stroke(255);
      pushMatrix();
      // Scale up by 200
      float factor = 200;
      translate(v.x*factor,v.y*factor,factor-v.z*factor);
      // Draw a point
      point(0,0);
      popMatrix();
    }
  }
 {% endhighlight %}
<h2>Average Point Tracking</h2>
<p>The real magic of the kinect lies in its computer vision capabilities.  With depth information, you can do all sorts of fun things like say: "the background is anything beyond 5 feet.  Ignore it!"  Without depth, background removal involves all sorts of painstaking pixel comparisons.  As a quick demonstration of this idea, here is a very basic example that compute the average xy location of any pixels in front of a given depth threshold.</p>
<p><iframe src="http://player.vimeo.com/video/18750684?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="500" height="313" frameborder="0"></iframe></p>
<p>Source: <a href="https://github.com/shiffman/libfreenect/tree/master/wrappers/java/processing/distribution/openkinect/examples/AveragePointTracking">AveragePointTracking</a></p>
<p>In this example, we declare two variables to add up all the appropriate x's and y's and one variable to keep track of how many there are.</p>
{% highlight java %}
float sumX = 0;
float sumY = 0;
float count = 0;
{% endhighlight %}
<p>Then, whenever we find a given point that complies with our threshold, we add the x and y to the sum:</p>
{% highlight java %}
  if (rawDepth < threshold) {
    sumX += x;
    sumY += y;
    count++;
  }
{% endhighlight %}
<p>When we're done, we calculate the average and draw a point!</p>
{% highlight java %}
if (count != 0) {
  float avgX = sumX/count;
  float avgY = sumY/count;
  fill(255,0,0);
  ellipse(avgX,avgY,16,16);
}
{% endhighlight %}
<h2>Why don&#8217;t the RGB images and depth values correspond properly?</h2>
<p>Unfortunately, b/c the RGB camera and the IR camera are not physically located in the same spot, we have a stereo vision problem.  Pixel XY in one image is not the same XY in an image from a camera an inch to the right.  I&#8217;m hoping to stretch my brain to try to understand this better and work out some examples that calibrate the data in Processing.  Stay tuned!  </p>
<p>If you are interested in more (and software that will do this very job!) check out Nicolas Burrus&#8217; amazing work:</p>
<p><a href="http://nicolas.burrus.name/index.php/Research/KinectCalibration">Theory on depth/color calibration and registration</a><br />
<a href="http://nicolas.burrus.name/index.php/Research/KinectRgbDemoV3">version 0.3 of RGBDemo</a></p>
<h2>What&#8217;s missing?</h2>
<p>Lots! Open a github issue if you want to add an item to my to do list!</p>
<p><a href="https://github.com/shiffman/OpenKinect-for-Processing/">https://github.com/shiffman/OpenKinect-for-Processing/releases</a></p>
<h2>FAQ</h2>
<p>1. What are there shadows in the depth image?</p>
<p><a href="http://media.zero997.com/kinect_shadow.pdf">Kinect Shadow diagram</a></p>
<p>2. What is the range of depth that the kinect can see?</p>
<p>~0.7–6 meters or 2.3–20 feet.  Note you will get black pixels (or raw depth value of 2048) at both elements that are too far away and too close.</p>
