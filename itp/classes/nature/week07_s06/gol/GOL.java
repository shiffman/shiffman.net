import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class GOL extends PApplet {//Daniel Shiffman, Nature of Code, 3/1/2005
//http://stage.nyu.edu/nature

//a basic implementation of John Conway's Game of Life CA
//how could this be improved to use object oriented programming?
//think of it as similar to our particle system, with a "cell" class
//to describe each individual cell and a "cellular automata" class
//to describe a collection of cells

int cellsize = 2;
int COLS, ROWS;
//game of life board
int[][] old_board, new_board;

public void setup()
{
  size(200, 200);
  smooth();
  //initialize rows, columns and set-up arrays
  COLS = width/cellsize;
  ROWS = height/cellsize;
  old_board = new int[COLS][ROWS];
  new_board = new int[COLS][ROWS];
  colorMode(RGB,255,255,255,100);
  background(0);
  //call function to fill array with random values 0 or 1
  initBoard();
  framerate(30);
}

public void draw()
{
  background(100);

  //loop through every spot in our 2D array and check spots neighbors
  for (int x = 0; x < COLS;x++) {
    for (int y = 0; y < ROWS;y++) {
      int nb = 0;
      //Note the use of mod ("%") below to ensure that cells on the edges have "wrap-around" neighbors
      //above row
    if (old_board[(x+COLS-1) % COLS ][(y+ROWS-1) % ROWS ] == 1) { nb++; }
    if (old_board[ x                ][(y+ROWS-1) % ROWS ] == 1) { nb++; }
    if (old_board[(x+1)      % COLS ][(y+ROWS-1) % ROWS ] == 1) { nb++; }
      //middle row
    if (old_board[(x+COLS-1) % COLS ][ y                ] == 1) { nb++; }
    if (old_board[(x+1)      % COLS ][ y                ] == 1) { nb++; }
      //bottom row
    if (old_board[(x+COLS-1) % COLS ][(y+1)      % ROWS ] == 1) { nb++; }
    if (old_board[ x                ][(y+1)      % ROWS ] == 1) { nb++; }
    if (old_board[(x+1)      % COLS ][(y+1)      % ROWS ] == 1) { nb++; }

      //RULES OF "LIFE" HERE
    if      ((old_board[x][y] == 1) && (nb <  2)) { new_board[x][y] = 0; }      //loneliness
    else if ((old_board[x][y] == 1) && (nb >  3)) { new_board[x][y] = 0; }      //overpopulation
    else if ((old_board[x][y] == 0) && (nb == 3)) { new_board[x][y] = 1; }      //reproduction
    else                                          { new_board[x][y] = old_board[x][y]; }  //stasis
    }
  }

  //RENDER game of life based on "new_board" values
  for ( int i = 0; i < COLS;i++) {
    for ( int j = 0; j < ROWS;j++) {
      if ((new_board[i][j] == 1)) {
        fill(255);
        noStroke();
        rect(i*cellsize,j*cellsize,cellsize,cellsize);
      }
    }
  }
  //swap old and new game of life boards
  int[][] tmp = old_board;
  old_board = new_board;
  new_board = tmp;
}

//init board with random "alive" squares
public void initBoard() {
  background(0);
  for (int i =0;i < COLS;i++) {
    for (int j =0;j < ROWS;j++) {
      if (PApplet.toInt(random(2)) == 0) {
        old_board[i][j] = 1;
      } else {
        old_board[i][j] = 0;
      }
    }
  }
}

//re-set board when mouse is pressed
public void mousePressed() {
  initBoard();
}
static public void main(String args[]) {   PApplet.main(new String[] { "GOL" });}}