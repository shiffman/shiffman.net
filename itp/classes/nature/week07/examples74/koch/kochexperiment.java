import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class kochexperiment extends PApplet {//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

//our "snowflake"
KochFractal k;

public void setup() {
 
  size(600,200);
  background(0);
  //a new fractal
  k = new KochFractal();
  smooth();
}

public void draw() {
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






class KochFractal {
  private Point start;    //a point for the start
  private Point end;      //a point for the end
  private ArrayList lines;   //a list to keep track of all the lines
  int count;
  
  public KochFractal()
  {
    start = new Point(0,height-15);
    end = new Point(width,height-15);
    lines = new ArrayList();
    restart();
  }

  void nextLevel()
  {  
    //for every line that is in the arraylist
    //create 4 more lines in a new arraylist
    lines = iterate(lines);
    count++;
  }

  void restart()
  { 
    count = 0;      //reset count
    lines.clear();  //empty the array list
    lines.add(new KochLine(start,end));  // add one line (from one end point to the other) to the arraylist    
  }
  
  int getCount() {
    return count;
  }
  
  //this is easy, just draw all the lines
  void render()
  {
    for(int i = 0; i < lines.size(); i++) {
      KochLine l = (KochLine)lines.get(i);
      l.render();
    }
  }

  //This is where the **MAGIC** happens
  /*Step 1: Create an empty arraylist
    Step 2: For every line currently in the arraylist
              - calculate 4 line segments based on Koch algorithm
              - add all 4 line segments into the new arraylist
    Step 3: Return the new arraylist and it becomes the list of line segments for the structure
    (as we do this over and over again, each line gets broken into 4 lines, which gets broken into 4 lines, and so on. .. */
  ArrayList iterate(ArrayList before)
  {
    ArrayList now = new ArrayList();    //emtpy arraylist
    for(int i = 0; i < before.size(); i++)
    {
      KochLine l = (KochLine)lines.get(i);   //a line segment inside the list
      //5 kock points (done for us by the line object)
      Point a = l.start();                 
      Point b = l.kochleft();
      Point c = l.kochmiddle();
      Point d = l.kochright();
      Point e = l.end();
      //make line segments between all the points and add them
      now.add(new KochLine(a,b));
      now.add(new KochLine(b,c));
      now.add(new KochLine(c,d));
      now.add(new KochLine(d,e));
    }
    return now;
  }

}

//a nice little class that holds the information for a line segment
//includes methods to calculate midpoints along the line according to the Koch algorithm

class KochLine {
  
  //two points,
  //a is the "left" point and 
  //b is the "right point
  Point a,b;
  
  KochLine(Point a_, Point b_) {
     a = a_.copy();
     b = b_.copy();
  }
  
  void render() {
    stroke(255);
    line(a.x,a.y,b.x,b.y);
  }
  
  Point start() {
    return a.copy();
  }
  
  Point end() {
    return b.copy();
  }
      
  //this is easy, just 1/3 of the way
  Point kochleft()
  {
    float x = a.x + (b.x - a.x) / 3f;
    float y = a.y + (b.y - a.y) / 3f;
    return new Point(x,y);
  }    
  
  //more complicated, have to use a little trig to figure out where this point is!
  Point kochmiddle()
  {
    float x = a.x + 0.5f * (b.x - a.x) + (sin(radians(60))*(b.y-a.y)) / 3;
    float y = a.y + 0.5f * (b.y - a.y) - (sin(radians(60))*(b.x-a.x)) / 3;
    return new Point(x,y);
  }    
  
  //easy, just 2/3 of the way
  Point kochright()
  {
    float x = a.x + 2*(b.x - a.x) / 3f;
    float y = a.y + 2*(b.y - a.y) / 3f;
    return new Point(x,y);
  }    
   

}

//a simple class to keep track of one point
//similar to our Vector3D class, but without all the fancy stuff. . .

class Point {
  float x,y;
  
  Point(float x_, float y_) {
    x = x_;
    y = y_;
  }
  
  Point copy() {
    return new Point(x,y);
  }
}
}