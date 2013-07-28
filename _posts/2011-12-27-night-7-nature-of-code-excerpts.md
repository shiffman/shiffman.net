---
title: 'Night #7: Nature of Code excerpts'
author: Daniel
layout: post
pvc_views:
  - 5455
dsq_thread_id:
  - 518074201
categories:
  - processing.org
tags:
  - fractal
  - genetic algorithm
  - nature of code
  - neural network
  - processing.org
  - tree
---
<p>For tonight&#8217;s post, I&#8217;m going to include three new examples from my upcoming Nature of Code book.  I&#8217;ll also excerpt some of the text with these examples below.</p>
<p>This first example expands on the existing <a href="http://processing.org/learning/topics/tree.html">Recursive Tree</a> example that comes with Processing.</p>
<p><strong>Chapter 8: Recursion and Fractals</strong></p>
<p>The recursive tree fractal is a nice example of a scenario in which adding a little bit of randomness can make the tree look more natural.  Take a look outside and you’ll notice that branch lengths and angles vary from branch to branch, not to mention the fact that branches don’t all have exactly the same number of smaller branches.   First, let’s see what happens when we simply vary the angle and length.  This is a pretty easy one, given that we can just ask Processing for a random number each time we draw the tree.</p>
{% highlight java %}
void branch(float len) {	
  // Start by picking a random angle for each branch
  float theta = random(0,PI/3);  

  line(0, 0, 0, -len);
  translate(0, -len);
  len *= 0.66;
  if (len > 2) {
    pushMatrix();    
    rotate(theta);   
    branch(len);
    popMatrix();     
    pushMatrix();
    rotate(-theta);
    branch(len);
    popMatrix();
  }
}
{% endhighlight %}
<p>In the above function, we always call branch() twice.  But why not pick a random number of branches and call branch() that number of times?</p>
{% highlight java %}
void branch(float len) {	
  
  line(0, 0, 0, -len);
  translate(0, -len);
  
  if (len > 2) {

    // Call branch() a random number of times
    int n = int(random(1,4));		 
    for (int i = 0; i < n; i++) {	
  
      // Each branch gets its own random angle
      float theta = random(-PI/2, PI/2); 
      pushMatrix();     
      rotate(theta);
      branch(h);
      popMatrix();
    }
  }
}
{% endhighlight %}
<p>The example below takes the above a few steps further.  It uses Perlin noise to generate the angles, as well as animate them.  In addition, it draws each branch with a thickness according to its level and sometimes shrinks a branch by a factor of two to vary where the levels begin.</p>
<p><a href='http://shiffman.net/wp/wp-content/uploads/2011/12/TreeStochasticNoise.zip'><img src="http://shiffman.net/wp/wp-content/uploads/2011/12/tree1.png" alt="" title="tree" width="580" height="347" class="alignnone size-full wp-image-1058" /></a></p>
<p><a href='http://shiffman.net/wp/wp-content/uploads/2011/12/TreeStochasticNoise.zip'>TreeStochasticNoise.zip</a></p>
<p>Next up an excerpt from the Genetic Algorithm chapter.</p>
<p><strong>Chapter 9: Evolution and Code</strong></p>
<p>In 2009, <a href="http://blog.blprnt.com">Jer Thorp</a>  released a great genetic algorithms example on his blog entitled “<a href="http://blog.blprnt.com/blog/blprnt/project-smart-rockets">Smart Rockets</a>.”   Jer points out that NASA uses evolutionary computing techniques to solve all sorts of problems, from satellite antenna design to rocket firing patterns. This inspired him to create a Flash demonstration of evolving rockets.  Here is a description of the scenario:</p>
<p>A population of rockets launches from the bottom of the screen with the goal of hitting a target at the top of the screen (with obstacles blocking a straight line path).   </p>
<p><img src="http://shiffman.net/wp/wp-content/uploads/2011/12/rockets.png" alt="" title="rockets" width="300" height="187" class="alignnone size-full wp-image-1061" /></p>
<p>Each rocket is equipped with five thrusters of variable strength and direction.    The thrusters don’t fire all at once and continuously; rather, they fire one at a time in a custom sequence.  In this example, we’re going to evolve our own simplified Smart Rockets, inspired by Jer Thorp’s.   You can leave implementing some of Jer’s additional advanced features as an exercise.  </p>
<p>Our rockets will have only one thruster, and this thruster will be able to fire in any direction with any strength in every single frame of animation.  This isn't particularly realistic, but it will make building out the framework a little easier. (We can always make the rocket and its thrusters more advanced and realistic later.)   </p>
<p><a href='http://shiffman.net/wp/wp-content/uploads/2011/12/SmartRockets.zip'><img src="http://shiffman.net/wp/wp-content/uploads/2011/12/rockets2.png" alt="" title="rockets2" width="590" height="352" class="alignnone size-full wp-image-1063" /></a></p>
<p>Source: <a href='http://shiffman.net/wp/wp-content/uploads/2011/12/SmartRockets.zip'>SmartRockets.zip</a></p>
<p>And here's a short excerpt from the beginning of the chapter on neural networks, as well as the example that closes out the chapter demonstrating how to visualize the flow of information through a network.</p>
<p><strong>Chapter 10: The Brain</strong></p>
<p>Computer scientists have long been inspired by the human brain.   In 1943, Warren S. McCulloch, a neuroscientist, and Walter Pitts, a logician, developed the first conceptual model of an artificial neural network.  In their paper, "A logical calculus of the ideas imminent in nervous activity,” they describe the concept of a neuron, a single cell living in a network of cells that receives inputs, processes those inputs, and generates an output.</p>
<p>Their work, and the work of many scientists and researchers that followed, was not meant to accurately describe how the biological brain works.  Rather, an artificial neural network (which we will now simply refer to as a “neural network”) was designed as a computational model based on the brain that can solve certain kinds of problems.</p>
<p>It’s probably pretty obvious to you that there are certain problems that are incredibly simple for a computer to solve, but difficult for you.  Take the square root of 964,324, for example.  A quick line of code produces the value 982, a number Processing computed in less than a millisecond.   There are, on the other hand, problems that are incredibly simple for you or me to solve, but not so easy for a computer.   Show any toddler a picture of a kitten or puppy and they’ll be able to tell you very quickly which one is which.   Say hello and shake my hand one morning and you should be able to pick me out of a crowd of people the next day.  But need a machine to perform one of these tasks?  People have already spent careers researching and implementing complex solutions.</p>
<p>The most common application of neural networks in computing today is to perform one of these easy-for-a-human, difficult-for-a-machine” tasks, often referred to as pattern classification.   Applications range from optical character recognition (turning printed or handwritten scans into digital text) to facial recognition.  We don’t have the time or need to use some of these more elaborate artificial intelligence algorithms here, but if you are interested in researching neural networks, I’d recommend the books Artificial Intelligence: A Modern Approach by Stuart J. Russell and Peter Norvig and AI for Game Developers by David M. Bourg and Glenn Seemann.</p>
<p>In this chapter, we’ll instead begin with a conceptual overview of the properties and features of neural networks and build the simplest example possible of one (a network that consists of a singular neuron).  Afterwards, we’ll examine strategies for building a “Brain” object that can be inserted into our Vehicle class and used to determine steering.   Finally, we’ll also look at techniques for visualizing and animating a network of neurons.</p>
<p><script type="application/processing"></p>
<p>Network network;</p>
<p>void setup() {
  size(590, 360); 
  smooth();</p>
<p>  // Create the Network object
  network = new Network(width/2, height/2);</p>
<p>  // Create a bunch of Neurons
  Neuron a = new Neuron(-300, 0);
  Neuron b = new Neuron(-200, 0);
  Neuron c = new Neuron(0, 100);
  Neuron d = new Neuron(0, -100);
  Neuron e = new Neuron(200, 0);
  Neuron f = new Neuron(300, 0);</p>
<p>  // Connect them
  network.connect(a, b,1);
  network.connect(b, c,random(1));
  network.connect(b, d,random(1));
  network.connect(c, e,random(1));
  network.connect(d, e,random(1));
  network.connect(e, f,1);</p>
<p>  // Add them to the Network
  network.addNeuron(a);
  network.addNeuron(b);
  network.addNeuron(c);
  network.addNeuron(d);
  network.addNeuron(e);
  network.addNeuron(f);
}</p>
<p>void draw() {
  background(255);
  // Update and display the Network
  network.update();
  network.display();</p>
<p>  // Every 30 frames feed in an input
  if (frameCount % 30 == 0) {
    network.feedforward(random(1));
  }
}</p>
<p>class Connection {
  // Connection is from Neuron A to B
  Neuron a;
  Neuron b;</p>
<p>  // Connection has a weight
  float weight;</p>
<p>  // Variables to track the animation
  boolean sending = false;
  PVector sender;</p>
<p>  // Need to store the output for when its time to pass along
  float output = 0;</p>
<p>  Connection(Neuron from, Neuron to, float w) {
    weight = w;
    a = from;
    b = to;
  }</p>
<p>  // The Connection is active
  void feedforward(float val) {
    output = val*weight;        // Compute output
    sender = a.location.get();  // Start animation at Neuron A
    sending = true;             // Turn on sending
  }</p>
<p>  // Update traveling sender
  void update() {
    if (sending) {
      // Use a simple interpolation
      sender.x = lerp(sender.x, b.location.x, 0.1);
      sender.y = lerp(sender.y, b.location.y, 0.1);
      float d = PVector.dist(sender, b.location);
      // If we've reached the end
      if (d < 1) {
        // Pass along the output!
        b.feedforward(output);
        sending = false;
      }
    }
  }</p>
<p>  // Draw line and traveling circle
  void display() {
    stroke(0);
    strokeWeight(1+weight*4);
    line(a.location.x, a.location.y, b.location.x, b.location.y);</p>
<p>    if (sending) {
      fill(0);
      strokeWeight(1);
      ellipse(sender.x, sender.y, 16, 16);
    }
  }
}</p>
<p>class Network {</p>
<p>  // The Network has a list of neurons
  ArrayList neurons;</p>
<p>  // The Network now keeps a duplicate list of all Connection objects.
  // This makes it easier to draw everything in this class
  ArrayList connections;
  PVector location;</p>
<p>  Network(float x, float y) {
    location = new PVector(x, y);
    neurons = new ArrayList();
    connections = new ArrayList();
  }</p>
<p>  // We can add a Neuron
  void addNeuron(Neuron n) {
    neurons.add(n);
  }</p>
<p>  // We can connection two Neurons
  void connect(Neuron a, Neuron b, float weight) {
    Connection c = new Connection(a, b, weight);
    a.addConnection(c);
    // Also add the Connection here
    connections.add(c);
  } </p>
<p>  // Sending an input to the first Neuron
  // We should do something better to track multiple inputs
  void feedforward(float input) {
    Neuron start = (Neuron) neurons.get(0);
    start.feedforward(input);
  }</p>
<p>  // Update the animation
  void update() {
    for (int i = 0; i < connections.size(); i++) {
      Connection c = (Connection) connections.get(i);
      c.update();
    }
  }</p>
<p>  // Draw everything
  void display() {
    pushMatrix();
    translate(location.x, location.y);
    for (int i = 0; i < neurons.size(); i++) {
      Neuron n = (Neuron) neurons.get(i);
      n.display();
    }</p>
<p>    for (int i = 0; i < connections.size(); i++) {
      Connection c = (Connection) connections.get(i);
      c.display();
    }
    popMatrix();
  }
}</p>
<p>// An animated drawing of a Neural Network
// Daniel Shiffman <http://shiffman.net>
// Nature of Code</p>
<p>class Neuron {
  // Neuron has a location
  PVector location;</p>
<p>  // Neuron has a list of connections
  ArrayList connections;</p>
<p>  // We now track the inputs and sum them
  float sum = 0;</p>
<p>  // The Neuron's size can be animated
  float r = 32;</p>
<p>  Neuron(float x, float y) {
    location = new PVector(x, y);
    connections = new ArrayList();
  }</p>
<p>  // Add a Connection
  void addConnection(Connection c) {
    connections.add(c);
  } </p>
<p>  // Receive an input
  void feedforward(float input) {
    // Accumulate it
    sum += input;
    // Activate it?
    if (sum > 1) {
      fire();
      sum = 0;  // Reset the sum to 0 if it fires
    }
  }</p>
<p>  // The Neuron fires
  void fire() {
    r = 64;   // It suddenly is bigger</p>
<p>    // We send the output through all connections
    for (int i = 0; i < connections.size(); i++) {
      Connection c = (Connection) connections.get(i);</p>
<p>      c.feedforward(sum);
    }
  }</p>
<p>  // Draw it as a circle
  void display() {
    stroke(0);
    strokeWeight(1);
    // Brightness is mapped to sum
    float b = map(sum, 0, 1, 255, 0);
    fill(b);
    ellipse(location.x, location.y, r, r);</p>
<p>    // Size shrinks down back to original dimensions
    r = lerp(r, 32, 0.1);
  }
}</p>
<p></script></p>
<p>Source: <a href='http://shiffman.net/wp/wp-content/uploads/2011/12/NetworkAnimation.zip'>NetworkAnimation.zip</a></script></p>
