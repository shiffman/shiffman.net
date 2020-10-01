// Interactive Selection
// http://www.genarts.com/karl/papers/siggraph91.html
// Daniel Shiffman <http://www.shiffman.net>

class Button {
  Rectangle r;  //button's rectangle
  String txt;   //button's text
  boolean clicked;  //did i click on it?
  boolean rollover; //did i rollover it?

  Button(int x, int y, int w, int h, String s) {
    r = new Rectangle(x,y,w,h);
    txt = s;
  }

  void render() {
    //draw rectangle and text based on whether rollover or clicked
    rectMode(CORNER);
    stroke(0); noFill();
    if (rollover) fill(0.5);
    if (clicked) fill(0);
    rect(r.x,r.y,r.width,r.height);
    float b = 0.0;
    if (clicked) b = 1;
    else if (rollover) b = 0.2;
    else b = 0;
    fill(b);
    textAlign(LEFT);
    text(txt,r.x+10,r.y+14);

  }
  
  
  //methods to check rollover, clicked, or released (must be called from appropriate
  //places in draw, mousePressed, mouseReleased
  boolean rollover(int mx, int my) {
    if (r.contains(mx,my)) rollover = true;
    else rollover = false;
    return rollover;
  }

  boolean clicked(int mx, int my) {
    if (r.contains(mx,my)) clicked = true;
    return clicked;
  }

  void released() {
    clicked = false;
  }

}
