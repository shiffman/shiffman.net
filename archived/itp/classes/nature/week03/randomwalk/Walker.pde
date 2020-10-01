
class Walker {
  Vector2D loc;
  Vector2D vel;
  Vector2D lastvel;

  Walker() {
    loc = new Vector2D(0f,0f);
    vel = new Vector2D(0f,0f);
    lastvel = new Vector2D(0f,0f);

  }

  void render() {
    stroke(255);
    point(loc.x(),loc.y());
  }

  void walk() {
    int count = 0;
    do {
      int x = int(random(3))-1;
      int y = int(random(3))-1;
      vel.setX(x); vel.setY(y);
      count++;
    } while (lastvel.equals(vel));
    
    lastvel.copy(vel);
    lastvel.mult(-1);
    loc.add(vel);
    loc.setX(constrain(loc.x(),0,width-1));
    loc.setY(constrain(loc.y(),0,height-1));

  }
}
