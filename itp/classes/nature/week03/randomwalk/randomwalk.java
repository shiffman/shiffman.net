import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class randomwalk extends PApplet {Walker w;

public void setup() {
  size(200,200);
  background(0);
  colorMode(RGB,255,255,255,255);
  w = new Walker();

}

public void draw() {
  fill(0,0,0,1);
  rect(0,0,width,height);

  w.walk();
  w.render();
}


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
      int x = PApplet.toInt(random(3))-1;
      int y = PApplet.toInt(random(3))-1;
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
}