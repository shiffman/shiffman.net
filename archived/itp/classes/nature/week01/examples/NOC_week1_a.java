import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; import netscape.javascript.*; import javax.comm.*; import javax.sound.midi.*; import javax.sound.midi.spi.*; import javax.sound.sampled.*; import javax.sound.sampled.spi.*; import javax.xml.parsers.*; import javax.xml.transform.*; import javax.xml.transform.dom.*; import javax.xml.transform.sax.*; import javax.xml.transform.stream.*; import org.xml.sax.*; import org.xml.sax.ext.*; import org.xml.sax.helpers.*; public class NOC_week1_a extends BApplet {void setup() {
  size(200,200);
  background(0);
}

void loop() {
  background(0);
  
  //a "vector" (really a point) to store the mouse location and screen center location
  Vector2D mouseLoc = new Vector2D(mouseX,mouseY);
  Vector2D centerLoc = new Vector2D(width/2,height/2);  
  
  //aha, a real vector to store the displacement between the mouse and center
  Vector2D v = Vector2D.sub(mouseLoc,centerLoc);
  
  //call a function to render a vector at a location (we might use this later for debugging in other programs)
  drawVector(v,centerLoc,1.0f);

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



}