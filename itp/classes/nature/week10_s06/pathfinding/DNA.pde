// Pathfinding w/ Genetic Algorithms
// Daniel Shiffman <http://www.shiffman.net>

// DNA is an array of vectors

class DNA {

  //The genetic sequence
  Vector3D[] dna;
  
  //Constructor (makes a DNA of random Vectors)
  DNA(int num) {
    dna = new Vector3D[num];
    for (int i = 0; i < dna.length; i++) {
      dna[i] = new Vector3D(random(-1,1),random(-1,1));
      dna[i].normalize();
    }
  }
  
  //Constructor #2, creates the instance based on an existing array
  DNA(Vector3D[] newdna) {
    //dna = (Vector3D []) newdna.clone();  //not working as an applet?
    dna = newdna;
  }
  
  //returns one element from char array 
  Vector3D getGene(int index) {
    // just doing some error handling
    if (index < dna.length) return dna[index].copy();
    else return new Vector3D(0,0);
  }
  
  //**CROSSOVER***//
  //creates new DNA sequence from two (this & and a partner)
  DNA mate(DNA partner) {
    Vector3D[] child = new Vector3D[dna.length];
    //*pick a midpoint*//
    int crossover = int(random(dna.length));
    //*take "half" from one and "half" from the other*//
    for (int i = 0; i < dna.length; i++) {
      if (i > crossover) child[i] = getGene(i);
      else               child[i] = partner.getGene(i);
    }
    
    DNA newdna = new DNA(child);
    return newdna;
  }
  
  //based on a mutation probability, picks a new random Vector
  void mutate(float m) {
    for (int i = 0; i < dna.length; i++) {
      if (random(1) < m) {
         dna[i] = new Vector3D(random(-1,1),random(-1,1));
         dna[i].normalize();
      }
    }
  }
}
