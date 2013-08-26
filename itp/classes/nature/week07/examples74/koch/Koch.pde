
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
