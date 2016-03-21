import processing.core.*; 
import processing.xml.*; 

import toxi.geom.*; 
import toxi.physics2d.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class ForceDirectedGraph extends PApplet {

// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2011
// Toxiclibs example: http://toxiclibs.org/

// Force directed graph
// Heavily based on: http://code.google.com/p/fidgen/




// Reference to physics world
VerletPhysics2D physics;

// A list of cluster objects
ArrayList clusters;

// Boolean that indicates whether we draw connections or not
boolean showPhysics = true;
boolean showParticles = true;

// Font
PFont f;

public void setup() {
  size(600,600);
  smooth();
  frameRate(30);
  f = createFont("Georgia",12,true);

  // Initialize the physics
  physics=new VerletPhysics2D();
  physics.setWorldBounds(new Rect(10,10,width-20,height-20));

  // Spawn a new random graph
  newGraph();
}

// Spawn a new random graph
public void newGraph() {

  // Clear physics
  physics.clear();

  // Create new ArrayList (clears old one)
  clusters = new ArrayList();

  // Create 8 random clusters
  for (int i = 0; i < 8; i++) {
    Vec2D center = new Vec2D(width/2,height/2);
    clusters.add(new Cluster((int) random(3,8),random(20,100),center));
  }

  //	All clusters connect to all clusters	
  for (int i = 0; i < clusters.size(); i++) {
    for (int j = i+1; j < clusters.size(); j++) {
      Cluster ci = (Cluster) clusters.get(i);
      Cluster cj = (Cluster) clusters.get(j);
      ci.connect(cj);
    }
  }
}

public void draw() {

  // Update the physics world
  physics.update();

  background(255);

  // Display all points
  if (showParticles) {
    for (int i = 0; i < clusters.size(); i++) {
      Cluster c = (Cluster) clusters.get(i);
      c.display();
    }
  }

  // If we want to see the physics
  if (showPhysics) {
    for (int i = 0; i < clusters.size(); i++) {
      // Cluster internal connections
      Cluster ci = (Cluster) clusters.get(i);
      ci.showConnections();

      // Cluster connections to other clusters
      for (int j = i+1; j < clusters.size(); j++) {
        Cluster cj = (Cluster) clusters.get(j);
        ci.showConnections(cj);
      }
    }
  }

  // Instructions
  fill(0);
  textFont(f);
  text("'p' to display or hide particles\n'c' to display or hide connections\n'n' for new graph",10,20);
}

// Key press commands
public void keyPressed() {
  if (key == 'c') {
    showPhysics = !showPhysics;
    if (!showPhysics) showParticles = true;
  } 
  else if (key == 'p') {
    showParticles = !showParticles;
    if (!showParticles) showPhysics = true;
  } 
  else if (key == 'n') {
    newGraph();
  }
}

// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// Toxiclibs example: http://toxiclibs.org/

// Force directed graph
// Heavily based on: http://code.google.com/p/fidgen/

class Cluster {

  // A cluster is a grouping of nodes
  ArrayList nodes;

  float diameter;

  // We initialize a Cluster with a number of nodes, a diameter, and centerpoint
  Cluster(int n, float d, Vec2D center) {

    // Initialize the ArrayList
    nodes = new ArrayList();

    // Set the diameter
    diameter = d;

    // Create the nodes
    for (int i = 0; i < n; i++) {
      // We can't put them right on top of each other
      nodes.add(new Node(center.add(Vec2D.randomVector())));
    }

    // Connect all the nodes with a Spring
    for (int i = 1; i < nodes.size(); i++) {
      VerletParticle2D pi = (VerletParticle2D) nodes.get(i);
      for (int j = 0; j < i; j++) {
        VerletParticle2D pj = (VerletParticle2D) nodes.get(j);
        // A Spring needs two particles, a resting length, and a strength
        physics.addSpring(new VerletSpring2D(pi,pj,diameter,0.01f));
      }
    }
  }

  public void display() {
    // Show all the nodes
    for (int i = 0; i < nodes.size(); i++) {
      Node n = (Node) nodes.get(i);
      n.display();
    }
  }

  // This functons connects one cluster to another
  // Each point of one cluster connects to each point of the other cluster
  // The connection is a "VerletMinDistanceSpring"
  // A VerletMinDistanceSpring is a string which only enforces its rest length if the 
  // current distance is less than its rest length. This is handy if you just want to
  // ensure objects are at least a certain distance from each other, but don't
  // care if it's bigger than the enforced minimum.
  public void connect(Cluster other) {
    ArrayList otherNodes = other.getNodes();
    for (int i = 0; i < nodes.size(); i++) {
      VerletParticle2D pi = (VerletParticle2D) nodes.get(i);
      for (int j = 0; j < otherNodes.size(); j++) {
        VerletParticle2D pj = (VerletParticle2D) otherNodes.get(j);
        // Create the spring
        physics.addSpring(new VerletMinDistanceSpring2D(pi,pj,(diameter+other.diameter)*0.5f,0.05f));
      }
    }
  }


  // Draw all the internal connections
  public void showConnections() {
    stroke(0,150);
    for (int i = 0; i < nodes.size(); i++) {
      VerletParticle2D pi = (VerletParticle2D) nodes.get(i);
      for (int j = i+1; j < nodes.size(); j++) {
        VerletParticle2D pj = (VerletParticle2D) nodes.get(j);
        line(pi.x,pi.y,pj.x,pj.y);
      }
    }
  }

  // Draw all the connections between this Cluster and another Cluster
  public void showConnections(Cluster other) {
    stroke(0,50);
    ArrayList otherNodes = other.getNodes();
    for (int i = 0; i < nodes.size(); i++) {
      VerletParticle2D pi = (VerletParticle2D) nodes.get(i);
      for (int j = 0; j < otherNodes.size(); j++) {
        VerletParticle2D pj = (VerletParticle2D) otherNodes.get(j);
        line(pi.x,pi.y,pj.x,pj.y);
      }
    }
  }

  public ArrayList getNodes() {
    return nodes;
  }
}
// The Nature of Code
// <http://www.shiffman.net/teaching/nature>
// Spring 2010
// Toxiclibs example: http://toxiclibs.org/

// Force directed graph
// Heavily based on: http://code.google.com/p/fidgen/

// Notice how we are using inheritance here!
// We could have just stored a reference to a VerletParticle object
// inside the Node class, but inheritance is a nice alternative

class Node extends VerletParticle2D {

  Node(Vec2D pos) {
    super(pos);
  }

  // All we're doing really is adding a display() function to a VerletParticle
  public void display() {
    fill(0,150);
    stroke(0);
    ellipse(x,y,16,16);
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "ForceDirectedGraph" });
  }
}
