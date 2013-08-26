/* A class to describe a psuedo-DNA, i.e. genotype
   Here, a virtual organism's DNA is an array of character.
   Functionality:
      -- convert DNA into a string
      -- calculate DNA's "fitness"
      -- mate DNA with another set of DNA
      -- mutate DNA
*/       

class DNA {

  //The genetic sequence
  char[] dna;
  
  //Constructor (makes a random DNA)
  DNA(int num) {
    dna = new char[num];
    for (int i = 0; i < dna.length; i++) {
      dna[i] = (char) random(0,255);
    }
  }
  
  //Constructor #2, creates the instance based on an existing character array
  DNA(char[] newdna) {
    dna = (char []) newdna.clone();
  }
  
  //converts character array to a String and returns in
  String getString() {
    return new String(dna);
  }
  
  //Fitness function (returns floating point % of "correct character)
  float fitness (String target) {
     int score = 0;
     for (int i = 0; i < dna.length; i++) {
        if (dna[i] == target.charAt(i)) {
          score++;
        }
     }
     float fitness = (float)score / (float)target.length();
     return fitness;
  }
  
  //returns one element from char array 
  char getDNA(int index) {
    return dna[index];
  }
  
  //**CROSSOVER***//
  //creates new DNA sequence from two (this & 
  DNA mate(DNA partner) {
    char[] child = new char[dna.length];
    int crossover = int(random(dna.length));
    
    for (int i = 0; i < dna.length; i++) {
      if (i > crossover) child[i] = dna[i];
      else               child[i] = partner.getDNA(i);
    }
    
    DNA newdna = new DNA(child);
    return newdna;
  }
  
  //based on a mutation probability, picks a new random character in array spots
  void mutate(float m) {
    for (int i = 0; i < dna.length; i++) {
      if (random(1) < m) {
        dna[i] = (char) random(0,255);
      }
    }
  }
}
