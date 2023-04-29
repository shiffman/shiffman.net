import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; import netscape.javascript.*; import javax.comm.*; import javax.sound.midi.*; import javax.sound.midi.spi.*; import javax.sound.sampled.*; import javax.sound.sampled.spi.*; import javax.xml.parsers.*; import javax.xml.transform.*; import javax.xml.transform.dom.*; import javax.xml.transform.sax.*; import javax.xml.transform.stream.*; import org.xml.sax.*; import org.xml.sax.ext.*; import org.xml.sax.helpers.*; public class inclass3 extends BApplet {//a variable to store the maximum # of elements in the array
int MAX = 500;

//declare array names and allocate the space to store them
int[] x = new int[MAX];
int[] y = new int[MAX];

int[] yspeed = new int[MAX];
int[] xspeed = new int[MAX];

int[] c = new int[MAX];

void setup() {
  size(500,500);

  colorMode(RGB,255,255,255,100);

  //use a for loop to initialize each element of the array
  //note the use of "random()" and "int()" here -- visit:
  //http://processing.org/reference/random_.html
  //http://processing.org/reference/int_.html

  for (int i = 0; i < MAX; i++) {
    x[i] = (int)( random(0,width ) );
    y[i] = (int)( random(0,height) );
    yspeed[i] = (int) (random(-5,3));
    xspeed[i] = (int) (random(-3,5));
    c[i] = color(200, i % 255,0,(i) % 100);
  }
}

void loop() {
  //set background stroke and fill
  background(0);
  noStroke();

  //use a for loop to draw MAX #'s of ellipses based on the array values
  for (int i = 0; i < MAX; i++) {
    fill(c[i]);
    ellipse(x[i],y[i],i/20,i/20);
    y[i] = y[i] + yspeed[i]; //add this line of code to increment each element of the array "y"
    x[i] = x[i] + xspeed[i];
  }
}



}