
class Button {
  Rectangle r;      // Button's rectangle
  String txt;       // Button's text
  boolean clicked;  // Did i click on it?
  boolean rollover; // Did i rollover it?

  Button(int x, int y, int w, int h, String s) {
    r = new Rectangle(x,y,w,h);
    txt = s;
  }

  void render() {
    // Draw rectangle and text based on whether rollover or clicked
    rectMode(PConstants.CORNER);
    strokeWeight(1);
    stroke(0); 
    fill(255);
    if (rollover) fill(127);
    if (clicked) fill(0);
    rect(r.x,r.y,r.width,r.height);
    int b = 0;
    if (clicked) b = 255;
    else if (rollover) b = 50;
    else b = 0;
    fill(b);
    textAlign(PConstants.CENTER);
    text(txt,r.x+r.width/2,r.y+r.height-6);
  }

  // Methods to check rollover, clicked, or released (must be called from appropriate
  // places in draw, mousePressed, mouseReleased
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



