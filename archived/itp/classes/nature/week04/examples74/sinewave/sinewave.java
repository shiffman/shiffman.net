import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class sinewave extends PApplet {final int SIN = 0;
final int COS = 1;

Wave w;
public void setup() {
  size(400,200);
  colorMode(RGB,255,255,255,100);
  w = new Wave(SIN,75,8,width,600);
}

public void draw() {
  background(0);
  w.calcWave();
  translate(0,height/2);  /*hmmm, this should be taken care of in the wave class itself
                            think about better ways to deal with the relative positioning of a wave?*/
  w.render();

}



class Wave {
  int xspacing;   //how far should each x point be spaced
  int w;          //width of entire wave
  int type;       //SINE OR COSINE?
  
  float theta;    //starting angle
  float amplitude;//height
  float period;   //how many pixels go by before we repeat
  float dx;       //calculated based on period
  float[] yvalues;//using an array to store height values for the wave

  //**CONSTRUCTOR INITIALIZES ALL INSTANCE VARIABLES**/
  Wave(int t, float a, int xspace, int w_, float p_) {
    type = t;
    amplitude = a;
    xspacing = xspace;
    w = w_;
    period = p_;
    dx = (TWO_PI / period) * xspacing;
    yvalues = new float[w/xspacing];    //note declaring the array here forces us to have fixed size wave
                                        //we would need to redeclare it if we ever change those values
  }

  void calcWave() {
    //increment theta by 0.01 (this should be made into an instance variable);
    theta += 0.01f;
    
    //For every x value, calculate a y value based on sine or cosine
    float x = theta;
    for (int i = 0; i < yvalues.length; i++) {
      if (type == SIN)  yvalues[i] = sin(x)*amplitude;
      if (type == COS)  yvalues[i] = cos(x)*amplitude;
      x+=dx;
    }
  }

  void render() {
    //a simple way to draw the wave with an ellipse at each location
    //this could be improved in so many ways
    smooth();
    for (int x = 0; x < yvalues.length; x++) {
      noStroke();
      fill(0,0,255,50);
      ellipseMode(CENTER);
      ellipse(x*xspacing,yvalues[x],16,16);
    }
  }
}
}