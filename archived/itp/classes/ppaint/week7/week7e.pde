int cellsize = 4;
int COLS, ROWS;
//game of life board
int[][] old_board, new_board;

void setup()
{
  size(640, 240);

  //initialize rows, columns and set-up arrays
  COLS = width/cellsize;
  ROWS = height/cellsize;
  old_board = new int[COLS][ROWS];
  new_board = new int[COLS][ROWS];
  colorMode(RGB,255,255,255,100);
  background(0);
  //call function to fill array with random values 0 or 1
  initBoard();
}

void loop()
{
  //background(0);

  //loop through every spot in our 2D array and check spots neighbors
  for (int x = 0; x<COLS;x++) {
    for (int y = 0; y<ROWS;y++) {
      int nb = 0;
      //Note the use of mod ("%") below to ensure that cells on the edges have "wrap-around" neighbors
      //above row
    if (old_board[(x+COLS-1) % COLS ][(y+ROWS-1) % ROWS ] > 0) { nb++; }
    if (old_board[x                 ][(y+ROWS-1) % ROWS ] > 0) { nb++; }
    if (old_board[(x+1)    % COLS   ][(y+ROWS-1) % ROWS ] > 0) { nb++; }
      //middle row
    if (old_board[(x+COLS-1) % COLS ][ y ] > 0)                { nb++; }
    if (old_board[(x+1)    % COLS   ][ y ] > 0)                { nb++; }
      //bottom row
    if (old_board[(x+COLS-1) % COLS ][(y+1) % ROWS ] > 0)      { nb++; }
    if (old_board[x                 ][(y+1) % ROWS ] > 0)      { nb++; }
    if (old_board[(x+1)      % COLS ][(y+1) % ROWS ] > 0)      { nb++; }

      //RULES OF "LIFE" HERE
      //if dead
    if      ((old_board[x][y] == 0) && (nb == 3)) { new_board[x][y] = 1; }
      //if alive
    else if ((old_board[x][y] > 0) && (nb == 2)) { new_board[x][y] = 2; }
    else if ((old_board[x][y] > 0) && (nb == 3)) { new_board[x][y] = 3; }
    else                                          { new_board[x][y] = 0; }
    }
  }
  int blend = 5;
  //RENDER game of life based on "new_board" values
  for ( int i = 0; i<COLS;i++) {
    for ( int j = 0; j<ROWS;j++) {
      if ((new_board[i][j] > 0 )) {
        int R,G,B;  R = G = B = 0;
        if ( new_board[i][j] == 1) {
          R = 200; G = 200;
        } else if (new_board[i][j] == 2) {
          G = 200; B = 200;
        } else if (new_board[i][j] == 3) {
          B = 200; R = 200;
        }
        fill(R,G,B,blend);
        //noStroke();
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
void initBoard() {
  background(0);
  for (int i =0;i<COLS;i++) {
    for (int j =0;j<ROWS;j++) {
      if (int(random(2)) == 0) {
        old_board[i][j] = 1;
      } else {
        old_board[i][j] = 0;
      }
    }
  }
}

//re-set board when mouse is pressed
void mousePressed() {
  initBoard();
}

