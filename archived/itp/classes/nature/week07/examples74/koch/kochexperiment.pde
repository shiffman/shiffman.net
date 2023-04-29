//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

//our "snowflake"
KochFractal k;

void setup() {
 
  size(600,200);
  background(0);
  //a new fractal
  k = new KochFractal();
  smooth();
}

void draw() {
  background(0);
  framerate(1);    //animate slowly
  //draws the snowflake!
  k.render();
  //iterate
  k.nextLevel();
  //let's not do it more than 6 times. . .
  if (k.getCount() > 6) {
    k.restart();
  }
      
}

/*void mousePressed() {
  k.nextLevel();
  count++;
  if (count > 6) {
    k.restart();
    count = 0;
  }
}*/




