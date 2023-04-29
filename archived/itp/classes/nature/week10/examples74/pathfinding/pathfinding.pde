//Daniel Shiffman, Nature of Code, 3/29/2005
//http://stage.nyu.edu/nature

//This example produces an obstacle course with a start and finish
//Virtual "creatures" are rewarded for making it closer to the finish
//a simple "pathfinding" AI program

//For ever creature, we grid out the screen and their DNA offers
//a steering vector for each cell on the screen

final int W = 480; final int H = 360;  //screen size
final int gridscale = 24;              //scale of grid is 1/24 of screen size
/* DNA needs one vector for every spot on the grid 
   (it's like a pixel array, but with vectors instead of colors) */
final int DNASIZE = (W / gridscale) * (H / gridscale); 

final int lifetime = 150;  //how long should each generation live

//global maxforce and maxspeed (hmmm, could make this part of DNA??)
float maxspeed = 8.0f;
float maxforce = 1.0f;

Population popul;  //population
int lifecycle;     //timer for cycle of generation
int recordtime;    //fastest time to target
Vector3D target;   //target location
Vector3D start;    //start location
float diam = 24.0f;//size of target
PFont f;           //font for display

ArrayList obstacles;  //an array list to keep track of all the obstacles!

void setup() {
  size(480,360);
  colorMode(RGB,255,255,255,100);
  f = loadFont("CourierNewPS-BoldMT-14.vlw");
  smooth();
  
  //*initialize the globals*//
  lifecycle = 0;
  recordtime = lifetime;
  target = new Vector3D(width-diam,height/2);
  start = new Vector3D(diam,height/2);

  //*create a population with a mutation rate, and population max*//
  int popmax = 2000;
  float mutationRate = 0.05f;
  popul = new Population(mutationRate,popmax);
    
  //*Create the obstacle course!*//
  obstacles = new ArrayList();
  obstacles.add(new Obstacle(150,0,10,225));
  obstacles.add(new Obstacle(225,175,10,height-175));
  obstacles.add(new Obstacle(290,0,10,200));
  obstacles.add(new Obstacle(375,175,10,height-175));
  obstacles.add(new Obstacle(0,260,225,10));
  obstacles.add(new Obstacle(160,135,135,10));
  obstacles.add(new Obstacle(230,310,145,10));
  obstacles.add(new Obstacle(300,75,width-295,10));
}

void draw() {
  background(0);

  //*DRAW THE START NAND TARGET*//
  ellipseMode(CENTER);
  noStroke();
  fill(0,100,200);
  ellipse(start.x(),start.y(),diam,diam);
  ellipse(target.x(),target.y(),diam,diam);

  //*DRAW THE OBSTACLES*//
  for (int i = 0; i < obstacles.size(); i++) {
    Obstacle obs = (Obstacle) obstacles.get(i);
    obs.render();
  }

  //**IF WE HAVEN'T REACHED THE END OF OUR LIVES, KEEP GOING!*//
  if (lifecycle < lifetime) {
    popul.live(obstacles);
    if ((popul.targetReached()) && (lifecycle < recordtime)) {
      recordtime = lifecycle;
    }
    lifecycle++;
  //**OTHERWISE, LET'S MAKE A NEW GENERATION*//
  } else {
    lifecycle = 0;
    popul.calcFitness();
    popul.naturalSelection();
    popul.generate();
  }

  //DISPLAY SOME INFO
  textFont(f);
  textMode(ALIGN_RIGHT);
  textSpace(SCREEN_SPACE);
  fill(255);
  text("Generation #:" + popul.getGenerations(),470,18);
  text("Time left:" +(lifetime-lifecycle),470,36);
  text("Record time: " + recordtime,470,54);
}

//**MOVE THE TARGET IF WE CLICK THE MOUSE**//
void mousePressed() {
  //obstacles.add(new Obstacle(mouseX,mouseY));
  target = new Vector3D(mouseX,mouseY);
  recordtime = lifetime;
}

