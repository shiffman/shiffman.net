---
title: Perceptron
author: Daniel
layout: post
dsq_thread_id:
  - 296590854
pvc_views:
  - 332
categories:
  - blog
  - ITP
  - neural
  - p5
  - programming
  - teaching_
---
<p><a href="http://shiffman.net/itp/classes/nature/week10_s06/perceptron/"><img id="image145" src="http://shiffman.net/wp-content/uploads/2006/11/perceptron.jpg" alt="perceptron" /></a><br />
<a href="http://shiffman.net/itp/classes/nature/week10_s06/perceptron/">view applet and source</a></p>
<p>Long overdue, I&#8217;ve started working on a series of <a href="http://www.processing.org"Processing</a> examples that implement neural networks.  First up is the simplest, a little </a><a href="http://en.wikipedia.org/wiki/Perceptron">Perceptron</a> that learns whether points live on one side of a line (in Cartesian space) or the other.</p>
<p>y = x*0.9-0.2</p>
<p>In this example, the perceptron is trained via an array of known point objects (with known answers), and the resulting &#8220;guess&#8221; line is displayed in real-time.  I made the learning constant rather low so that one can see the slow progression of changing weights.  I&#8217;ve been spending some quality time with <a href="http://www.cs.unm.edu/~luger/index.html">Artifical Intelligence, by George Luger</a>.  It&#8217;s a wonderful book, and even better, it&#8217;s free for download online!</p>
<p>All the code is in the link, but here&#8217;s a quick peek at the meat of the matter: a function inside the Perceptron class that adjusts weights according to 3 input values and their corresponding &#8220;known&#8221; output.  (Note if the perceptron&#8217;s guess output produces the desired result, the weights are not changed.)</p>
<p>A more involved write-up will arrive online at some point. . . </p>
{% highlight java %}
// Function to train the Perceptron
// Weights are adjusted based on "desired" answer
void train(float[] vals, int desired) {
  // Sum all the weights
  float sum = 0;
  for (int i = 0; i < weights.length; i++) {
    sum += vals[i]*weights[i];
  }
  // The result is the sign of the sum
  int result = 1;  // Start with 1
  if (sum < 0) result = -1; // If less than zero, change to -1
  // Compute factor to change weight
  // (DESIRED - RESULT): note this can only be 0, -2, or 2
  // Multiply by learning constant
  float weightChange = c*(desired - result);
  // Adjust weights based on weightChange * input
  for (int i = 0; i < weights.length; i++) {
    weights[i] += weightChange * vals[i];         
  }
}
{% endhighlight %}
<p>For related work, check out <a href="http://www.robotacid.com/PBeta/AILibrary/">Aaron Steed's site</a>.</p>
