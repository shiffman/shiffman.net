var checkbox;
var dropdown;
//var slider;
//var input;

var testDiv;

function setup() {
  
  createCanvas(100, 100);
  // I am so lazy
  createP('');

  checkbox = createElement('input');
  var label = createElement('label');
  label.html('checkbox label');
  checkbox.attribute('type','checkbox');

  testDiv = createDiv('not checked');
  checkbox.elt.onchange = function() {
    if (this.checked) {
      testDiv.html('checked');
    } else {
      testDiv.html('not checked');
    }
  }

  // I am so lazy
  createP('');

  dropdown = createElement('select');
  var options = ['apple','orange','pear'];
  for (var i = 0; i < options.length; i++) {
    var option = createElement('option');
    option.attribute('value',options[i]);
    option.html(options[i]);
    option.parent(dropdown);
  }

  var droptest = createDiv('what is selected?')

  dropdown.elt.onchange = function() {
    droptest.html(this.value);
  }

}

function draw() {
  background(0);

  if (checkbox.elt.checked) {
    background(0, 0, 255);
  }

  if (dropdown.elt.value === 'pear') {
    fill(0, 255, 0);
  } else if (dropdown.elt.value === 'apple') {
    fill(255, 0, 0);  
  } else if (dropdown.elt.value === 'orange') {
    fill(255, 128, 0);
  }
  ellipse(50, 50, 50, 50);

}