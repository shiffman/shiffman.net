
class Wave {
  int xspacing;   //how far should each x point be spaced
  int w;          //width of entire wave
  
  float yoff;      //2nd dimension will be "time"
  float amplitude;//height
  float period;   //we don't repeat here, but still tells us about the character of the wave-like structure
  float dx;       //calculated based on period
  float[] yvalues;//using an array to store height values for the wave

  //**CONSTRUCTOR INITIALIZES ALL INSTANCE VARIABLES**/
  Wave(float a, int xspace, int w_, float p_) {
    amplitude = a;
    xspacing = xspace;
    w = w_;
    period = p_;
    dx = (1.0f / period) * xspacing;
    yvalues = new float[w/xspacing];    //note declaring the array here forces us to have fixed size wave
                                        //we would need to redeclare it if we ever change those values
  }

  void calcWave() {
    //increment time by 0.01 (this should be made into an instance variable);
    yoff += 0.01;
    
    //For every x value, calculate a y value based on sine or cosine
    float x = yoff;   //**OPTION #1****/
    //float x = 0.0f;   //**OPTION #2****/
    for (int i = 0; i < yvalues.length; i++) {
      yvalues[i] = (noise(x)-0.5f)*amplitude*2.0f;  //**OPTION #1****/
      //yvalues[i] = (noise(x,yoff)-0.5f)*amplitude*2.0f;  //**OPTION #2****/
      x+=dx;
    }
  }

  void render() {
    //a simple way to draw the wave with an ellipse at each location
    //this could be improved in so many ways
    smooth();
    for (int x = 0; x < yvalues.length; x++) {
      noStroke();
      fill(0,0,255,100);
      ellipseMode(CENTER);
      ellipse(x*xspacing,yvalues[x],16,16);
    }
  }
}
