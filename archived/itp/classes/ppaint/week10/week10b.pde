//boolean variable to keep track of when there's a new frame
boolean newFrame = false;

void setup()
{
  //set up size of window and video
  size(320, 240);
  beginVideo(320, 240, 15);
  background(0);
}

void videoEvent() {
  newFrame = true;
}

void loop()
{
  //when there's a new frame, display the video
  if(newFrame) {
    image(video, 0, 0,mouseX,mouseY);
  }
}

