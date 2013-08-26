final int SIN = 0;
final int COS = 1;

Wave w;
void setup() {
  size(400,200);
  colorMode(RGB,255,255,255,100);
  w = new Wave(SIN,75,8,width,600);
}

void draw() {
  background(0);
  w.calcWave();
  translate(0,height/2);  /*hmmm, this should be taken care of in the wave class itself
                            think about better ways to deal with the relative positioning of a wave?*/
  w.render();

}


