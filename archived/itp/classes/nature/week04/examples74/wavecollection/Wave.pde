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
    theta += 0.01;

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
