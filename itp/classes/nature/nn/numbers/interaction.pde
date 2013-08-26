// Deal with button release
void mouseReleased() {
  submit.released();
}

void mousePressed() {
  // Clicking the mouse exits the welcome screen
  if (welcome) {
    welcome = false;
    training = true;
    // Deal with the submit button
  } 
  else if (submit.clicked(mouseX,mouseY)) {
    // Time to guess!
    guessing = true;
    float[] binaryPixels = getPixels(inputImage);
    float result = nn.feedForward(binaryPixels);
    // Here is the result
    guess = result * total;
  }
}

void keyPressed() {
  // Quit training
  if (training && trainingComplete) {
    training = false;
    // If guessing
  } 
  else if (guessing) {
    // No longer guessing
    guessing = false;
    // What number did user  type
    int num = Integer.parseInt("" + key);
    float[] binaryPixels = getPixels(inputImage);
    // Retrain the network according to those pixels
    nn.train(binaryPixels, (float)num/total);
    // Clear what user has drawn for next time
    points.clear();
  }
}

