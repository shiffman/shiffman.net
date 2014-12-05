// ITP Networked Media, Fall 2014
// https://github.com/shiffman/itp-networked-media
// Daniel Shiffman

// Camera, canvas, divs
var cam;
var canvas;
var divs;

// Columns and rows
var cols;
var rows;

// Some threshold
var threshold = 100;
// Change the threshold
var slider;

function setup() {
  // Let's get some video!
  cam = createCapture(VIDEO);
  cam.hide();
  // Arbitrary size
  cols = 40;
  rows = 30;
  canvas = createCanvas(cols,rows);
  
  // The slider
  createDiv('threshold: ');
  slider = createSlider(0,255,127);

  
  // Not using this currently
  // var container = getElement('boxes');
  // container.style('line-height','12px');

  divs = [];
  for (var j = 0; j < rows; j++) {
    createDiv('<br/>').style('display','inline');//.parent(container);
    for (var i = 0; i < cols; i++) {
      // Let's make a checkbox
      var div = createInput('');
      div.attribute('type','checkbox');
      div.style('display','inline');
      // div.parent(container);
      divs[i + j * cols] = div;
    }
  }
}

function draw() {
  background(0);
  
  // Draw the image backwards
  scale(-1,1);
  image(cam,-cols,0,cols,rows);
  loadPixels();
  for(var i = 0; i < cols; i++) {
    for(var j = 0; j < rows; j++) {
      var index = (i + j * cols)*4;
      // Average brightness
      var col = pixels[index] + pixels[index+1] + pixels[index+2];
      col /= 3;

      // To check or not to check
      if (col < slider.value()) {
        divs[index].elt.checked = true;
      } else {
        divs[index].elt.checked = false;  
      }
    }
  }

}
