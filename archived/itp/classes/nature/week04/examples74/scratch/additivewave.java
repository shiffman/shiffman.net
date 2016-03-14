import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class additivewave extends PApplet {final int SIN = 0;
final int COS = 1;

/* THESE ARE NO LONGER VARIABLES IN OUR WAVE CLASS
   SINCE OUR COLLECTION WILL BREAK IF THE ARRAYS HAVE
   DIFFERENT SIZES */
final int XSPACING = 5;
final int W = 600;

//two waves
Wave w1,w2;
//one wave collection
WaveCollection waves;

public void setup() {
  size(600,200);
  colorMode(RGB,255,255,255,100);
  w1 = new Wave(SIN,60,600);
  w2 = new Wave(COS,30,170);
  
  //declare the wave collection and add two waves to it
  waves = new WaveCollection();
  waves.add(w1);
  waves.add(w2);
}

public void draw() {
  background(0);
  w1.calcWave();
  w2.calcWave();
  
  waves.sumWaves();
  translate(0,height/2);  //again kind of a bad place for this
  waves.render();
  
  //if we wanted to see the individual waves
  //w1.render();
  //w2.render();
  
}



class Wave {

  //int xspacing;   //how far should each x point be spaced
  //int w;          //width of entire wave
  int type;       //SINE OR COSINE?

  float theta;    //starting angle
  float amplitude;//height
  float period;   //how many pixels go by before we repeat
  float dx;       //calculated based on period
  float[] yvalues;//using an array to store height values for the wave

  //**CONSTRUCTOR INITIALIZES ALL INSTANCE VARIABLES**/
  Wave(int t, float a, float p_) {
    type = t;
    amplitude = a;
    period = p_;
    dx = (TWO_PI / period) * XSPACING;
    yvalues = new float[W/XSPACING];    //note declaring the array here forces us to have fixed size wave
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
      ellipse(x*XSPACING,yvalues[x],16,16);
    }
  }

  float[] getValues() {
    return yvalues;
  }

}


class WaveCollection {

  ArrayList waves;   //We're using a java ArrayList here
  float[] yvalues;   //an array to keep track of all the height values

  WaveCollection() {
    waves = new ArrayList();
    yvalues = new float[W/XSPACING];
  }
  
  //*FUNCTION TO ADD A WAVE TO THE COLLECTION**/
  void add(Wave w) {
    waves.add(w);
  }

  void sumWaves() {
    //set all the height values to 0
    for (int i = 0; i < yvalues.length; i++) {
      yvalues[i] = 0;
    }
    
    //for every wave in the collection, add up the values
    for(int i = 0; i < waves.size(); i++) {
      Wave w = (Wave) waves.get(i);
      float[] y = w.getValues();
      for (int j = 0; j < yvalues.length; j++) {
        yvalues[j] += y[j];
      }
    }
  }

  //same boring render function, we could do better here
  void render() {
    smooth();
    for (int x = 0; x < yvalues.length; x++) {
      noStroke();
      fill(0,255,0,50);
      ellipseMode(CENTER);
      ellipse(x*XSPACING,yvalues[x],16,16);
    }
  }
}

}