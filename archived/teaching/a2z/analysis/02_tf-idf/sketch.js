// Daniel Shiffman
// Programming from A to Z, Fall 2014
// https://github.com/shiffman/Programming-from-A-to-Z-F14

var tfidf;

var fileCount = 0;

function setup() {  
  // No canvas
  // Though doing something visual with this is a great idea for an assignment
  noCanvas();
  // For drag and dropping files
  setupDropZone();
}


// And process the data for a file
function process(data) {
  var text;

  // Did we get an array from loadStrings()
  // or just some raw text
  if (data instanceof Array) {
    text = data.join(' ');
  } else {
    text = data;
  }
  
  // Process this data into the tfidf object
  tfidf.termFreq(text);

  // Now we need to read all the rest of the files
  // for document occurences
  for (var i = 0; i < files.length; i++) {
    var reader = new FileReader();
    reader.readAsText(files[i]);
    reader.onload = function(e) {
      // count IDF
      tfidf.docFreq(e.target.result);

      // When we're finished
      fileCount++;
      if (fileCount === files.length) {
        // All the probabilities and divs
        tfidf.finish(fileCount);
        tfidf.sortByScore();
        makeDivs();
        fileCount = 0;
      }
    }
  }
}

// This makes a callback function for a specific file
// This will be tied to clicking on a file in the list
function makeFileHandler(file) {
  return function() {
    readFile(file);
  }
}

// This is where we read the file
function readFile(file) {
  console.log('Ready to read: ' + file.name);
  // Make a dictionary object
  // This will hold every word and its count
  tfidf = new TFIDF();
  // Read the file and process the result
  var reader = new FileReader();
  reader.readAsText(file);
  reader.onload = function(e) {
    process(e.target.result);
  }
}


// Make a bunch of divs to show results
function makeDivs() {
  // If this gets called multiple times we're removing all the divs that show counts
  // before making new ones
  clearDivs();

  // Get all the words
  var keys = tfidf.getKeys();

  // Get the count for each word and display
  for (var i = 0; i < keys.length; i++) {
    var count = tfidf.getScore(keys[i]);
    var div = createDiv(keys[i] + ': '+ count);
    div.class('concordance');
  }

}

// Go through and remove all the divs
function clearDivs() {
  var divs = getElements('concordance');
  for (var i = 0; i < divs.length; i++) {
    divs[i].remove();
  }
}
