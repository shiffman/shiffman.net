// Evolution EcoSystem
// Daniel Shiffman <http://www.shiffman.net>
// Spring 2007, The Nature of Code

// A collection of food in the world

class Food {
  ArrayList food;
 
  Food(int num) {
    // Start with some food
    food = new ArrayList();
    for (int i = 0; i < num; i++) {
       food.add(new Vector3D(random(width),random(height))); 
    }
  } 
  
  // Add some food at a location
  void add(Vector3D l) {
     food.add(l.copy()); 
  }
  
  // Display the food
  void run() {
    for (int i = 0; i < food.size(); i++) {
       Vector3D loc = (Vector3D) food.get(i);
       rectMode(CENTER);
       fill(199,218,236);
       rect(loc.x,loc.y,8,8);
    } 
    
    // There's a small chance food will appear randomly
    if (random(1) < 0.001) {
       food.add(new Vector3D(random(width),random(height))); 
    }
  }
  
  // Return the list of food
  ArrayList getFood() {
    return food; 
  }
}
