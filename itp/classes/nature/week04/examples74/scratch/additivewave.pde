final int SIN = 0;
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

void setup() {
  size(600,200);
  colorMode(RGB,255,255,255,100);
  w1 = new Wave(SIN,60,600);
  w2 = new Wave(COS,30,170);
  
  //declare the wave collection and add two waves to it
  waves = new WaveCollection();
  waves.add(w1);
  waves.add(w2);
}

void draw() {
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

