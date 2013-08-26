import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; import netscape.javascript.*; import javax.comm.*; import javax.sound.midi.*; import javax.sound.midi.spi.*; import javax.sound.sampled.*; import javax.sound.sampled.spi.*; import javax.xml.parsers.*; import javax.xml.transform.*; import javax.xml.transform.dom.*; import javax.xml.transform.sax.*; import javax.xml.transform.stream.*; import org.xml.sax.*; import org.xml.sax.ext.*; import org.xml.sax.helpers.*; public class NOC_week1_c extends BApplet {Thing t;

//this may prove useful later depending on whether or not we want to display vectors
boolean showVectors = true;

void setup() {
  size(200,480);
  background(0);

  Vector2D a = new Vector2D(0.0f,0.125f);
  Vector2D v = new Vector2D(0.0f,0.0f);
  Vector2D l = new Vector2D(width/2,0);
  t = new Thing(a,v,l);
}

void loop() {
  background(0);
  t.go();
}


/*A class to describe a thing in our world, has variables for location, velocity, and acceleration*/
class Thing {
  Vector2D loc;
  Vector2D vel;
  Vector2D acc;
  
  //The Constructor (called when the object is first created)
  Thing(Vector2D a, Vector2D v, Vector2D l) {
    acc = a;
    vel = v;
    loc = l;
  }

  //main function to operate object)
  void go() {
    update();
    render();
  }
  
  //function to update location
  void update() {
    if (loc.y() > height) {
      vel.setY(-vel.y());
      loc.setY(height);
    }
    vel.add(acc);
    loc.add(vel);
  }

  //function to display
  void render() {
    rectMode(CENTER_DIAMETER);
    noStroke();
    fill(0,0,255);
    rect(loc.x(),loc.y(),20,20);

    if (showVectors) {
      drawVector(vel,loc,10);
    }
  }

}


void drawVector(Vector2D v, Vector2D loc, float scayl) {
  push();
  float arrowsize = 10;
  //translate to location to render vector
  translate(loc.x(),loc.y());
  stroke(255);
  //call vector heading function to get direction (note that pointing up is a heading of 0) and rotate
  rotate(v.heading());
  //calculate length of vector & scale it to be bigger or smaller if necessary
  float len = v.magnitude()*scayl;
  //draw three lines to make an arrow (draw pointing up since we've rotate to the proper direction)
  line(0,0,len,0);
  line(len,0,len-arrowsize,+arrowsize/2);
  line(len,0,len-arrowsize,-arrowsize/2);
  pop();
}

void mousePressed() {
  showVectors = !showVectors;
}



}