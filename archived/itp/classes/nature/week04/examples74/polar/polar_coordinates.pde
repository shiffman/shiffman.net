float r;

//angle and angular velocity, accleration
float theta;
float theta_vel;
float theta_acc;

void setup() {
  size(200,200);
  colorMode(RGB,255,255,255,100);
  
  //initialize all values
  r = 50.0f;
  theta = 0.0f;
  theta_vel = 0.0f;
  theta_acc = 0.00001f;
}

void draw() {
  background(0);
  //move the origin point to the center of the screen
  translate(width/2,height/2);
  
  //convert polar to cartesian
  float x = r * cos(theta);
  float y = r * sin(theta);
  
  //draw the rectangle
  rectMode(CENTER);
  noStroke();
  fill(0,0,200,100);
  rect(x,y,20,20);
  
  //Apply acceleration and velocity to angle
  //vel = vel + acc
  theta_vel += theta_acc;
  //loc = loc + vel
  theta += theta_vel;

}
