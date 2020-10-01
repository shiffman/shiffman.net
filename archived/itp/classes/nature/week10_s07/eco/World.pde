// Evolution EcoSystem
// Daniel Shiffman <http://www.shiffman.net>
// Spring 2007, The Nature of Code

// The World we live in
// Has bloops and food

class World {

  ArrayList bloops;    // An arraylist for all the creatures
  Food food;

  // Constructor
  World(int num) {
    // Start with initial food and creatures
    food = new Food(num);
    bloops = new ArrayList();              // Initialize the arraylist
    for (int i = 0; i < num; i++) {
      Vector3D l = new Vector3D(random(width),random(height));
      DNA dna = new DNA();
      bloops.add(new Bloop(l,dna));
    }
  }

  // Make a new creature
  void born(float x, float y) {
    Vector3D l = new Vector3D(x,y);
    DNA dna = new DNA();
    bloops.add(new Bloop(l,dna));
  }

  // Run the world
  void run() {
    // Deal with food
    food.run();
    
    // Cycle through the ArrayList backwards b/c we are deleting
    for (int i = bloops.size()-1; i >= 0; i--) {
      // All bloops run and eat
      Bloop b = (Bloop) bloops.get(i);
      b.run();
      b.eat(food);
      // If it's dead, kill it and make food
      if (b.dead()) {
        bloops.remove(i);
        food.add(b.loc);
      }
      // Perhaps this bloop would like to make a baby?
      Bloop child = b.reproduce();
      if (child != null) bloops.add(child);
    }
  }
}



