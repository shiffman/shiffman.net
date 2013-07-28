void setup()
{
  //set up size of window and video
  size(320, 240);
  beginVideo(320, 240, 15);
  background(0);
}

void loop()
{

  //calculate an alpha value based on the mouseX location
  int alph = int(((float) mouseX / width) * 255);
  //use tint command to blend video frames into each other
  tint(255, alph);
  image(video, 0, 0);
}

