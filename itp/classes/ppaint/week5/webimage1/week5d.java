import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; import netscape.javascript.*; import javax.comm.*; import javax.sound.midi.*; import javax.sound.midi.spi.*; import javax.sound.sampled.*; import javax.sound.sampled.spi.*; import javax.xml.parsers.*; import javax.xml.transform.*; import javax.xml.transform.dom.*; import javax.xml.transform.sax.*; import javax.xml.transform.stream.*; import org.xml.sax.*; import org.xml.sax.ext.*; import org.xml.sax.helpers.*; public class week5d extends BApplet {
BImage b;
threader object1;
Thread wrapper;

int x = 0;
int xdir = 4;

void setup()
{
  size(300 , 300);
  background(0);
  myLoadImage();
  object1 = new threader();
  wrapper=new Thread(object1);
  wrapper.start();
}

void myLoadImage() {
  b = loadImage("img2.php?url=http://207.251.86.248/cctv26.jpg");

}

class threader implements Runnable {

  int count = 0;
  boolean run = true;

  threader() {
  }
  public void run() {
    while(run) {
      myLoadImage();
      //delay(5000);
    }
  }
}

public void stop() {
  super.stop();
  object1.run = false;
  wrapper = null;
}

void loop()
{
  image(b,0,0,300,300);
  
  stroke(0);
  fill(200,200,200);
  rect(x,height/2,20,20);
  
  x += xdir;
  
  if ((x > width) || (x < 0)) {
    xdir *= -1;
  }
}

}