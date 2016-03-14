import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class pendulum2 extends PApplet {Pendulum p;

public void setup() {
  size(400,400);
  background(0);
  colorMode(RGB,255,255,255,100);
  
  //make a new Pendulum with an origin location and armlength
  p = new Pendulum(new Vector2D(width/2,height/2),150.0f);
  smooth();
}

public void draw() {
  background(0);
  p.go();
}

public void mousePressed() {
  p.clicked(mouseX,mouseY);
}

public void mouseReleased() {
  p.stopDragging();
}





class Pendulum {

  Vector2D loc;      //location of pendulum ball
  Vector2D origin;   //location of arm origin
  float r;           //length of arm
  float theta;       //pendulum arm angle
  float theta_vel;   //angle velocity
  float theta_acc;   //angle acceleration

  float ballr;       //ball radius
  float damping;     //arbitary damping amount

  boolean dragging = false;

  //this constructor could be improved to allow a greater variety of pendulums
  Pendulum(Vector2D origin_, float r_) {
    //fill all variables
    origin = origin_.copy();
    r = r_;
    theta = 0.0f;
    
    //calculate the location of the ball using polar to cartesian conversion
    float x = r * sin(theta);
    float y = r * cos(theta);
    loc = new Vector2D(origin.x() + x, origin.y() + y);
    theta_vel = 0.0f;
    theta_acc = 0.0f;
    damping = 0.995f;   //arbitrary damping
    ballr = 16.0f;      //arbitrary ball radius
  }

  void go() {
    update();
    drag();    //for user interaction
    render();
  }

  //function to update location
  void update() {
    //as long as we aren't dragging the pendulum, let it swing!
    if (!dragging) {
      float G = 0.4f;                              //arbitrary universal gravitational constant
      theta_acc = (-1 * G / r) * sin(theta);      //calculate acceleration (see: http://www.myphysicslab.com/pendulum1.html)
      theta_vel += theta_acc;                     //increment velocity
      theta_vel *= damping;                       //arbitrary damping
      theta += theta_vel;                         //increment theta
    }
    loc.setXY(r*sin(theta),r*cos(theta));         //polar to cartesian conversion
    loc.add(origin);                              //make sure the location is relative to the pendulum's origin
  }

  //function to display
  void render() {
    stroke(255,50);
    //draw the arm
    line(origin.x(),origin.y(),loc.x(),loc.y());
    ellipseMode(CENTER);
    fill(200);
    if (dragging) fill(255);
    noStroke();
    //draw the ball
    ellipse(loc.x(),loc.y(),ballr,ballr);
  }

  /*THE FUNCTIONS BELOW ARE FOR MOUSE INTERACTION*/
  
  //this checks to see if we clicked on the pendulum ball
  void clicked(int mx, int my) {
    float d = dist(mx,my,loc.x(),loc.y());
    if (d < ballr) {
      dragging = true;
    }
  }

  //this tells us we are not longer clicking on the ball
  void stopDragging() {
    dragging = false;
  }

  void drag() {
    //if we are draging the ball, we calculate the angle between the 
    //pendulum origin and mouse location
    //we assign that angle to the pendulum
    if (dragging) {
      Vector2D diff = Vector2D.sub(origin,new Vector2D(mouseX,mouseY));   //difference between 2 points
      theta = atan2(-1*diff.y(),diff.x()) - radians(90);                  //angle relative to vertical axis
    }
  }

}

}