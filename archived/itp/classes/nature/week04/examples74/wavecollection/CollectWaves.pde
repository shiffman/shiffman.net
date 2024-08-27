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

