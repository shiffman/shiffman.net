// ITP Networked Media, Fall 2014
// https://github.com/shiffman/itp-networked-media
// Daniel Shiffman

var cam;
var canvas;

var divs;


var cols;
var rows;

var threshold = 100;

var slider;

function setup() {
  cam = createCapture(VIDEO);
  cam.hide();
  cols = 40;
  rows = 30;
  canvas = createCanvas(cols,rows);
  
  createDiv('threshold: ');
  slider = createSlider(0,255,127);



  // threshold = 100;

  var container = getElement('boxes');
  // //container.style('line-height','12px');

  divs = [];
  for (var j = 0; j < rows; j++) {
    createDiv('<br/>').style('display','inline');//.parent(container);
    for (var i = 0; i < cols; i++) {
      //var div = createDiv('&nbsp;a&nbsp;');
      var div = createInput('');
      div.attribute('type','checkbox');
      div.style('display','inline');
      //div.parent(container);
      //divs.push(div);
      divs[i + j * cols] = div;
    }
  }
}

function draw() {
  background(0);

  // fill(255);
  // stroke(255);
  // rect(0,0,width/2,height);
  scale(-1,1);
  image(cam,-cols,0,cols,rows);
  loadPixels();
  for(var i = 0; i < cols; i++) {
    for(var j = 0; j < rows; j++) {
      var index = i + j * cols;
      var col = pixels[index*4];
      if (col < slider.value()) {
        divs[index].elt.checked = true;
      } else {
        divs[index].elt.checked = false;  
      }
    }
  }

}
