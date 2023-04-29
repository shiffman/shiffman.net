// Daniel Shiffman
// Programming from A to Z, Fall 2014
// https://github.com/shiffman/Programming-from-A-to-Z-F14

var classifier;

var trainedA = false;
var trainedB = false;

var aCount = 0;
var bCount = 0;

function setup() {
  
  // No canvas
  // Though doing something visual with this is a great idea for an assignment
  noCanvas();

  // For file input
  setupInputs();

  classifier = new Classifier();

  // Just loading from a file for simplicity
  // var stuff = loadStrings('data/test.txt', process);
}

// Go through and remove all the divs
function clearDivs() {
  var divs = getElements('results');
  for (var i = 0; i < divs.length; i++) {
    divs[i].remove();
  }
}

function makeDivs(results) {
  clearDivs();

  var prob = 100*results.pA;
  if (prob < 0.01) {
    prob = 0;
  }
  createDiv('This document has a ' + prob.toPrecision(4) + '% chance of being in category A').class('results');

  var info = createDiv('Here are some words that are relevant along with their category A probability.').class('results'); 
  for (var i = 0; i < results.words.length; i++) {
    createDiv(results.words[i].word + ' ' + results.words[i].probA).class('results');
  }

}

function readyToGuess() {
  classifier.probabilities();

  var guessDiv = createDiv('Pick a file to guess');
  guessDiv.style('padding','5px');
  var guessInput = createInput();
  guessInput.parent(guessDiv);
  // Set attribute to file
  guessInput.attribute('type','file');
  guessInput.attribute('multiple','');

  guessInput.elt.addEventListener('change', guess, false);
     
  // Function to handle when a file is selected
  function guess(evt) {    
    // A FileList
    var files = evt.target.files;
    // Show some properties
    for (var i = 0, f; f = files[i]; i++) {
      // Read the file and process the result
      var reader = new FileReader();
      reader.readAsText(f);
      reader.onload = function(e) {
        var results = classifier.guess(e.target.result);
        makeDivs(results);
      }
    }
  }
}



function setupInputs() {

  var divA = createDiv('train category A');
  var divB = createDiv('train category B');
  divA.style('padding','5px');
  divB.style('padding','5px');

  //<input type="file" id="files" name="files[]" multiple />
  // Make the file input
  var fileInputA = createInput();
  fileInputA.parent(divA);
  // Set attribute to file
  fileInputA.attribute('type','file');
  // If we want to allow multiple files
  fileInputA.attribute('multiple','');
  // If a file is selected this event will be triggered
  fileInputA.elt.addEventListener('change', trainA, false);
   
  // Function to handle when a file is selected
  function trainA(evt) {    
    // A FileList
    var files = evt.target.files;
    // Show some properties
    for (var i = 0, f; f = files[i]; i++) {
      // Read the file and process the result
      var reader = new FileReader();
      reader.readAsText(f);
      reader.onload = function(e) {
        classifier.train(e.target.result, 'A');
        aCount++;
        if (aCount === files.length) {
          trainedA = true;
          if (trainedB) {
            readyToGuess();
          }
        }
      }
    }
  }

  // Make the file input
  var fileInputB = createInput();
  fileInputB.parent(divB);
  // Set attribute to file
  fileInputB.attribute('type','file');
  // If we want to allow multiple files
  fileInputB.attribute('multiple','');
  // If a file is selected this event will be triggered
  fileInputB.elt.addEventListener('change', trainB, false);
   
  // Function to handle when a file is selected
  function trainB(evt) {    
    // A FileList
    var files = evt.target.files;
    // Show some properties
    for (var i = 0, f; f = files[i]; i++) {
      // Read the file and process the result
      var reader = new FileReader();
      reader.readAsText(f);
      reader.onload = function(e) {
        classifier.train(e.target.result, 'B');
        bCount++;
        if (bCount === files.length) {
          trainedB = true;
          if (trainedA) {
            readyToGuess();
          }
        }
      }
    }
  }
}