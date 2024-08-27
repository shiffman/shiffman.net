// A little welcome message
void welcome() {
  textFont(f);
  textAlign(CENTER);
  fill(0);
  text("Hello.  I am a very simple neural network. \n" + 
    "I can recognize 0's and 1's. \n" + 
    "Click the mouse and I'll start training.",width/2,height/2);
}


// Train the network
void train() {
  // Adjust this number to make training appear to be faster / slower
  int cyclesPerFrame = 50;

  for (int i = 0; i < cyclesPerFrame; i++) {
    // Pick a number 
    int num = (int) random(total);
    // Look at the pixels for that number
    float[] pixies = (float[]) pixelData.get(num);  
    // Train the network according to those pixels
    nn.train(pixies, (float)num/total);
    // Increase the training iteration count
    trainingCount++;
  }
}

// Display info about the training
void showTraining() {
  pushMatrix();
  translate(20,150);
  textFont(f);
  textAlign(LEFT);

  // How much training is complete
  float percentage = (float) trainingCount / maxTrainingIterations;
  // Draw status bar
  float statusBar = percentage*width/2;
  stroke(0);
  noFill();
  rect(5,5,width/2,10);
  fill(0);
  rect(5,5,statusBar,10);
  text("Training. . .",5,30);

  // Now we'll show what the network is currently guessing for each input image
  fill(0);
  float mse = 0;
  for (int i = 0; i < pixelData.size(); i++) {
    float[] inp = (float[]) pixelData.get(i); 
    float known = (float) i/total;
    float result = nn.feedForward(inp);
    text("My guess for image # " + i + " is " + nf((float)result * total,1,2),5,100+i*20);
    mse += (result - known)*(result - known);
  }
  // How many interations
  text("Total iterations: " + trainingCount,5,60);
  // Root mean squarted error
  float rmse = sqrt(mse/pixelData.size());
  text("Root mean squared error: " + nf(rmse,1,4), 5,80);
  // If we've finished training
  if (percentage >= 1.0) {
    trainingComplete = true;
    text("I'm done training!  Press the space bar\nto draw a shape for me to guess.",5,-50);
  }
  popMatrix();
}

// Load all the images
void loadTrainingImages() {
  pixelData = new ArrayList();
  for (int i = 0; i < total; i++) {
    trainingImages[i] = loadImage("numbers/"+i+".tif");
    float[] binaryPixels = getPixels(trainingImages[i]);
    pixelData.add(binaryPixels);
  }
}

void userDraw() {
  // Keep a list of all mouse locations
  if (mousePressed && !submit.clicked && !guessing) {
    points.add(new Point(mouseX,mouseY));
  }

  // Draw those mouse locations as really wide continous line
  // This could really be improved
  noFill();
  stroke(0);
  strokeWeight(50);
  beginShape();
  for (int i = 0; i < points.size(); i++) {
    Point p = (Point) points.get(i);
    vertex(p.x,p.y);
  }
  endShape();
  // copy what was drawn onto the neural network's input image
  inputImage.copy(this.g,0, 0,width,height,0,0,w,h);
  inputImage.updatePixels();
}

// What to do with the user's input image
void guess() {
  // If it's not time to guess yet just give the user some instructions
  // and show submit button
  if (!guessing) {
    textFont(f);
    fill(50);
    textAlign(CENTER);
    text("Draw something and submit!",width/2,20);
    submit.rollover(mouseX,mouseY);
    submit.render();
    // Otherwise, show the result of the guess!
  } 
  else {
    noStroke();
    fill(255);
    rect(0,400,width,height);
    fill(0);
    textAlign(CENTER);
    textSize(14);
    text("My output is " + nf(guess,1,2) + " so I guess " + (int)(round(guess)) + 
      "\n Please type the correct # to help train me.",width/2,420);
  }
}

// Get an array of floats 0 to 1 from an input image
float[] getPixels(PImage img) {
  // This isn't terribly optimized, making floating point arrays for pixels that are 1 or 0
  // However, this would theoretically work with grayscale pixels mapped from 0 to 1
  float[] binaryPixels = new float[w*h];
  for (int pix = 0; pix < w*h; pix++) {
    float b = brightness(img.pixels[pix]);
    // Each pixel is back or what (1 or 0)
    if (b > 0) binaryPixels[pix] = 1;
    else binaryPixels[pix] = 0;
  }
  return binaryPixels;
}
