import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; import netscape.javascript.*; import javax.comm.*; import javax.sound.midi.*; import javax.sound.midi.spi.*; import javax.sound.sampled.*; import javax.sound.sampled.spi.*; import javax.xml.parsers.*; import javax.xml.transform.*; import javax.xml.transform.dom.*; import javax.xml.transform.sax.*; import javax.xml.transform.stream.*; import org.xml.sax.*; import org.xml.sax.ext.*; import org.xml.sax.helpers.*; public class NOC_week1_e extends BApplet {Thing t;

//this may prove useful later depending on whether or not we want to display vectors
boolean showVectors = true;

void setup() {
  size(640,480);
  background(0);
  Vector2D v1 = new Vector2D(0,0);
  Vector2D v2 = new Vector2D(0,0);
  Vector2D v3 = new Vector2D(width/2,height/2);
  t = new Thing(v1,v2,v3);
}

void loop() {
  background(0);
  t.go(showVectors,this);
  if (mousePressed) {
    //compute direction vector between mouse and object location
    //3 steps -- (1) Get Mouse Location, (2) Get Difference Vector, (3) Normalize difference vector
    Vector2D m = new Vector2D(mouseX,mouseY);
    Vector2D diff = Vector2D.sub(m,t.getLoc());
    diff.normalize();
    float factor = 2.0f;
    diff.mult(factor);
    //object accelerates towards mouse
    t.setAcc(diff);
  } else {
    t.setAcc(new Vector2D(0,0));
  }
}

void keyPressed() {
  showVectors = !showVectors;
}
}