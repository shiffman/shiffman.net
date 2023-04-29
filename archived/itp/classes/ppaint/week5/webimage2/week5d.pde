
BImage b;
threader object1;
Thread wrapper;

int x = 0;
int xdir = 1;

void setup()
{
  size(300 , 300);
  background(0);
  myLoadImage();
  object1 = new threader();
  wrapper=new Thread(object1);
  wrapper.start();
}

void myLoadImage() {
  b = loadImage("img2.php?url=http://207.251.86.248/cctv22.jpg");

}

class threader implements Runnable {

  int count = 0;
  boolean run = true;

  threader() {
  }
  public void run() {
    while(run) {
      myLoadImage();
      //delay(5000);
    }
  }
}

public void stop() {
  super.stop();
  object1.run = false;
  wrapper = null;
}

void loop()
{
  image(b,0,0,300,300);
  
  stroke(0);
  fill(200,200,200);
  rect(x,height/2,20,20);
  
  x += xdir;
  
  if ((x > width) || (x < 0)) {
    xdir *= -1;
  }
}

