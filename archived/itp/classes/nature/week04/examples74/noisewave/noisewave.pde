Wave w;

void setup() {
  size(640,200);
  colorMode(RGB,255,255,255,100);
  w = new Wave(75,16,width,300);
}

void draw() {
  background(0);
  w.calcWave();
  translate(0,height/2);  /*hmmm, maybe this should be taken care of in the wave class itself
                            how do we deal with the relative positioning of a wave?*/
  w.render();
}


