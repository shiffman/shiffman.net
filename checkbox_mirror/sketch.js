// ITP Networked Media, Fall 2014
// https://github.com/shiffman/itp-networked-media
// Daniel Shiffman

var cam;
var canvas;

var divs;




var vidScale = 10;

var cols;
var rows;

var threshold = 0;

function setup() {
  //cam = createCapture(VIDEO);
  //cam.hide();
  cols = 320 / vidScale;
  rows = 240 / vidScale;
  canvas = createCanvas(cols,rows);


  // threshold = 100;

  var container = createDiv('');
  // //container.style('line-height','12px');

  divs = [];
  for (var j = 0; j < rows; j++) {
    createDiv('<br/>').style('display','inline').parent(container);
    for (var i = 0; i < cols; i++) {
      //var div = createDiv('&nbsp;a&nbsp;');
      var div = createInput('');
      div.attribute('type','checkbox');
      div.style('display','inline');
      div.parent(container);
      //divs.push(div);
      divs[i + j * cols] = div;
    }
  }
}

function draw() {
  background(0);

  fill(255);
  stroke(255);
  rect(0,0,width/2,height);
  
  //image(cam,0,0,cols,rows);
  loadPixels();
  for(var i = 0; i < cols; i++) {
    for(var j = 0; j < rows; j++) {
      var index = i + j * cols;
      var col = pixels[index*4];
      if (col > threshold) {
        divs[index].elt.checked = true;
      } else {
        divs[index].elt.checked = false;  
      }
    }
  }

}
