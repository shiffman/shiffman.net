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


<iframe src="http://player.vimeo.com/video/132727296?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="400" height="225" frameborder="0"></iframe>

# Kinect and Processing

The Microsoft Kinect sensor is a peripheral device (designed for XBox and windows PCs) that functions much like a webcam.  However, in addition to providing an RGB image, it also provides a depth map. Meaning for every pixel seen by the sensor, the Kinect measures distance from the sensor.  This makes a variety of computer vision problems like background removal, blob detection, and more easy and fun! 

The Kinect sensor itself only measures color and depth.  However, once that information is on your computer, lots more can be done like "skeleton" tracking (i.e. detecting a model of a person and tracking his/her movements).  To do skeleton tracking you'll need to use Thomas Lengling's windows-only [Kinect v2 processing libray](https://github.com/ThomasLengeling/KinectPV2).  However, if you're on a Mac and all you want is raw data from the Kinect, you are in luck!  This library uses  [libfreenect](https://github.com/OpenKinect/libfreenect) and [libfreenect2](https://github.com/OpenKinect/libfreenect2) open source drivers to access that data for Mac OS X ([windows support coming soon](https://github.com/shiffman/OpenKinect-for-Processing/issues/13)).

(At the time of this writing, kinect v2 support as well as other [issues](https://github.com/shiffman/OpenKinect-for-Processing/issues) are in progress.  [Chime in on github](https://github.com/shiffman/OpenKinect-for-Processing) if you’d like to help!)

## What hardware do I need?

First you need a “stand-alone” kinect.  You do not need to buy an Xbox.  

* **[Standalone Kinect Sensor v1](http://amzn.to/1S2zH4j)**.  I believe this one comes with the power supply so you do not need a separate adapter listed next. However, if you have a kinect v1 that came with an XBox, it will not include the [Kinect Sensor Power Supply](http://amzn.to/1RfUnuO).
* **[Standalone Kinect Sensor v2](http://amzn.to/1KGoYxG)**.   You also probably need the [Kinect Adapter for Windows](http://www.amazon.com/Kinect-Adapter-Windows-xbox-one/dp/B00NMSHT7E/).  Don't be thrown off, although it says windows, this will allow you to connect it to your mac via USB.  Finally, you'll also want to make sure your computer supports USB 3.  Most modern machines do, but if you are unsure you can [find out more here for Mac OS X](https://support.apple.com/en-gb/HT201163#13).


## Some additional notes about different models:

* **Kinect 1414**: This is the original kinect and works with the library documented on this page in Processing 2.2.1+</li>
* **Kinect 1473**: This looks identical to the 1414, but is an updated model. It should work with this library, but I don't have one to test.  [Please let me know if it does or does not](https://github.com/shiffman/OpenKinect-for-Processing/issues)!
* **Kinect for Windows version 1**: ???? Help?  Does this one work?
* **Kinect for Windows version 2**: This is the brand spanking new kinect with all the features found in the XBox One Kinect.  Support for this kinect via [libfreenect2](https://github.com/OpenKinect/libfreenect2) is arriving soon!

## SimpleOpenNI

You could also consider using the [SimpleOpenNI library](http://code.google.com/p/simple-openni/) and read Greg Borenstein’s [Making Things See](a href="http://www.amazon.com/gp/product/1449307078/ref=as_li_ss_tl?ie=UTF8&camp=1789&creative=390957&creativeASIN=1449307078&linkCode=as2&tag=learniproces-20) book.  OpenNI has features (skeleton tracking, gesture recognition, etc.) that are not available in this library.  Unfortunately, [OpenNI was recently purchased by Apple](http://www.openni.org/) and appears to be shutting down.  It's unclear what the future will be of OpenNI and SimpleOpenNI.

## I’m ready to get started right now

The new updated version of the library is in progress.  Until it is available via the Processing contributions manager, you'll need to [get it here](https://github.com/shiffman/OpenKinect-for-Processing/releases) and install the library manually.

<s>The easiest way to install the library is with the contributions manager *Sketch → Import Libraries → Add library* and search for "Kinect".  A button will appear labeled "install".
If you want to install it manually download [the most recent release](https://github.com/shiffman/OpenKinect-for-Processing/releases) and extract it in the libraries folder.  Restart Processing, open up one of the examples in the examples folder and you are good to go!</s>


## What is Processing?

[Processing](processing.org) is an open source programming language and environment for people who want to create images, animations, and interactions. Initially developed to serve as a software sketchbook and to teach fundamentals of computer programming within a visual context, Processing also has evolved into a tool for generating finished professional work. Today, there are tens of thousands of students, artists, designers, researchers, and hobbyists who use Processing for learning, prototyping, and production.

## What if I don’t want to use Processing?

If you are comfortable with C++ I suggest you consider using [openFrameworks](http://www.openframeworks.cc/) or [Cinder](http://libcinder.org/) with the Kinect.  These environments have some additional features and you also may get a C++ speed advantage when processing the depth data, etc.:

* [ofxKinect](https://github.com/ofTheo/ofxKinect)
* [Kinect CinderBlock](https://github.com/cinder/Cinder-Kinect)
* More resources from: [The OpenKinect Project](http://openkinect.org)

## What code do I write?

First thing is to include the proper import statements at the top of your code:

{% highlight java %}
import org.openkinect.processing.*;
{% endhighlight %}

As well as a reference to a `Kinect` object, i.e.

{% highlight java %}
Kinect kinect;
{% endhighlight %}

Then in `setup()` you can initialize that kinect object:

{% highlight java %}
void setup() {
  kinect = new Kinect(this);
}
{% endhighlight %}

Once you’ve done this you can begin to access data from the kinect sensor.  Currently, the library makes data available to you in five ways:

* **`PImage (RGB)`** from the kinect video camera.
* **`PImage (grayscale)`** from the kinect IR camera.
* **`PImage (grayscale)`** with each pixel’s brightness mapped to depth (brighter = closer).
* **`PImage (RGB)`** with each pixel’s hue mapped to depth.
* **`int[] array`** with raw depth data (11 bit numbers  between 0 and 2048).

Let’s look at these one at a time.  If you want to use the Kinect just like a regular old webcam, you can request that the RGB image is captured:

{% highlight java %}
kinect.startVideo();
{% endhighlight %}

Then you simply ask for the image as a [PImage](http://processing.org/reference/PImage.html)!

{% highlight java %}
PImage img = kinect.getVideoImage(); 
image(img, 0, 0);
{% endhighlight %}

You can simply ask for this image in `draw()`, however, if you can also use `videoEvent()` to know when a new image is available.

{% highlight java %}
void videoEvent(Kinect k) {
  // There has been a video event!
}
{% endhighlight %}


If you want to IR image:

{% highlight java %}
kinect.setIR(true);
{% endhighlight %}

You cannot get both the RGB image and the IR image.  They are both passed back via getVideoImage() so whichever one was most recently enabled is the one you will get.

Now, if you want the depth image, you can:
{% highlight java %}
kinect.startDepth();
{% endhighlight %}

and request the grayscale image:

{% highlight java %}
PImage img = kinect.getDepthImage();
image(img, 0, 0);
{% endhighlight %}

As well as the raw depth data:

{% highlight java %}
int[] depth = kinect.getRawDepth();
{% endhighlight %}

For the color depth image, use `kinect.setColorDepth(true);`.  And just like with the video image, there's a depth event you can access if necessary.

{% highlight java %}
void depthEvent(Kinect k) {
  // There has been a depth event!
}
{% endhighlight %}

Finally, you can also adjust the camera angle with the tilt() function.
{% highlight java %}
float angle = kinect.getTilt();
angle = angle + 1;
kinect.tilt(angle);
{% endhighlight %}

So, there you have it, here are all the useful functions you might need to use the Processing kinect library:

* **`startVideo()`** — start video
* **`stopVideo()`** — stop video
* **`toggleVideo()`** — stop or start video
* **`setIR(boolean)`** — turn on or off the IR camera image
* **`startDepth()`** — start depth
* **`toggleDepth()`** — stop or start depth
* **`stopDepth()`** — stop depth
* **`setColorDepth(boolean)`** — turn on or off the depth values as color image
* **`PImage getVideoImage()`** — grab the RGB or IR video image
* **`PImage getDepthImage()`** — grab the depth map image
* **`int[] getRawDepth()`** — grab the raw depth data
* **`float getTilt()`** — get the current sensor angle (between 0 and 30 degrees)
* **`tilt(float)`** — adjust the sensor angle (between 0 and 30 degrees)

For everything else, you can also take a look at [the javadoc reference](http://shiffman.net/p5/kinect/reference/).

## Examples

There are four basic examples:

### Display RGB, IR, and Depth Images

<iframe src="http://player.vimeo.com/video/132727296?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="500" height="313" frameborder="0"></iframe>

Code:[RGBDepthTest](https://github.com/shiffman/OpenKinect-for-Processing/blob/master/OpenKinect-Processing/examples/RGBDepthTest/RGBDepthTest.pde)</p>

This example uses all of the above listed functions to display the data from the kinect sensor.

### Point Cloud

<iframe src="http://player.vimeo.com/video/18058700?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="501" height="282" frameborder="0"></iframe>

Code: [PointCloud](https://github.com/shiffman/OpenKinect-for-Processing/blob/master/OpenKinect-Processing/examples/PointCloud/PointCloud.pde)</p>

Here, we’re doing something a bit fancier.  Number one, we’re using the 3D capabilities of Processing to draw points in space.  You’ll want to familiarize yourself with [translate()](http://processing.org/reference/translate_.html), [rotate()](http://processing.org/reference/rotate_.html), [pushMatrix()](http://processing.org/reference/pushMatrix_.html), [popMatrix()](http://processing.org/reference/popMatrix_.html).  This [tutorial](http://processing.org/learning/transform2d/) is also a good place to start.  In addition, the example uses a PVector to describe a point in 3D space.  More here: [PVector tutorial](http://processing.org/learning/pvector/).

The real work of this example, however, doesn’t come from me at all.  The raw depth values from the kinect are not directly proportional to physical depth.  Rather, they scale with the inverse of the depth according to this formula:

{% highlight java %}
depthInMeters = 1.0 / (rawDepth * -0.0030711016 + 3.3309495161);
{% endhighlight %}

Rather than do this calculation all the time, we can precompute all of these values in a lookup table since there are only 2048 depth values.

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
{% endhighlight java %}

Thanks to [Matthew Fisher](http://graphics.stanford.edu/~mdfisher/Kinect.html) for the above formula.  (Note: for the results to be more accurate, you would need to calibrate your specific kinect device, but the formula is close enough for me so I'm sticking with it for now.  More about calibration in a moment.)

Finally, we can draw some points based on the depth values in meters:

{% highlight java %}
  for(int x = 0; x < w; x += skip) {
    for(int y = 0; y < h; y += skip) {
      int offset = x + y * kinect.width;

      // Convert kinect data to world xyz coordinate
      int rawDepth = depth[offset];
      PVector v = depthToWorld(x, y, rawDepth);

      stroke(255);
      pushMatrix();
      // Scale up by 200
      float factor = 200;
      translate(v.x * factor, v.y * factor, factor-v.z * factor);
      // Draw a point
      point(0,0);
      popMatrix();
    }
  }
{% endhighlight %}

### Average Point Tracking

The real magic of the kinect lies in its computer vision capabilities.  With depth information, you can do all sorts of fun things like say: "the background is anything beyond 5 feet.  Ignore it!"  Without depth, background removal involves all sorts of painstaking pixel comparisons.  As a quick demonstration of this idea, here is a very basic example that compute the average xy location of any pixels in front of a given depth threshold.

<iframe src="http://player.vimeo.com/video/18750684?title=0&amp;byline=0&amp;portrait=0&amp;color=ff9933" width="500" height="313" frameborder="0"></iframe>

Source: [AveragePointTracking](https://github.com/shiffman/OpenKinect-for-Processing/tree/master/OpenKinect-Processing/examples/AveragePointTracking)

In this example, I declare two variables to add up all the appropriate x's and y's and one variable to keep track of how many there are.

{% highlight java %}
float sumX = 0;
float sumY = 0;
float count = 0;
{% endhighlight %}

Then, whenever we find a given point that complies with our threshold, I add the x and y to the sum:

{% highlight java %}
  if (rawDepth < threshold) {
    sumX += x;
    sumY += y;
    count++;
  }
{% endhighlight %}

When we're done, we calculate the average and draw a point!

{% highlight java %}
if (count != 0) {
  float avgX = sumX / count;
  float avgY = sumY / count;
  fill(255, 0, 0);
  ellipse(avgX, avgY, 16, 16);
}
{% endhighlight %}

## Why don’t the RGB images and depth values correspond properly?

Unfortunately, b/c the RGB camera and the IR camera are not physically located in the same spot, we have a stereo vision problem.  Pixel XY in one image is not the same XY in an image from a camera an inch to the right. Tools for calibrating these two images are on my to-do list. For more: [Theory on depth/color calibration and registration](http://nicolas.burrus.name/index.php/Research/KinectCalibration).

## What’s missing?
* Everything is being tracked [via github issues](https://github.com/shiffman/OpenKinect-for-Processing/issues).

## FAQ
1. What are there shadows in the depth image? [Kinect Shadow diagram](http://media.zero997.com/kinect_shadow.pdf)
2. What is the range of depth that the kinect can see? ~0.7–6 meters or 2.3–20 feet.  Note you will get black pixels (or raw depth value of 2048) at both elements that are too far away and too close.

