// Daniel Shiffman
// Programming from A to Z, Fall 2014
// https://github.com/shiffman/Programming-from-A-to-Z-F14

// An object to store all the info related to a concordance

function Concordance() {
  this.hash = {};
  this.keys = [];
  

  // Splitting up the text
  function split(text) {
    // Split into array of tokens
    return text.split(/\W+/);
  }

  // A function to validate a toke 
  function validate(token) {
    return /\w{2,}/.test(token);
  }

  // Process new text
  this.process = function(data) {
    var tokens = split(data);
    // For every token
    for (var i = 0; i < tokens.length; i++) {
      // Lowercase everything to ignore case
      var token = tokens[i].toLowerCase();
      if (validate(token)) {
        // Increase the count for the token
        this.increment(token);
      }
    }
  }
  
  // An array of keys
  this.getKeys = function() {
    return this.keys;
  }
  
  // Get the count for a word
  this.getCount = function(word) {
    return this.hash[word];
  }
  
  // Increment the count for a word
  this.increment = function(word) {
    // Is this a new word?
    if (this.hash[word] == undefined) {
      this.hash[word] = 1;
      this.keys.push(word);
    // Otherwise just increment its count
    } else {
      this.hash[word]++;
    }
  }
  
  // Sort array of keys by counts
  this.sortByCount = function() {
    // A fancy way to sort each element
    // Compare the counts
    var dict = this;
    this.keys.sort(function(a,b) {
      return (dict.getCount(b) - dict.getCount(a));
    });
  }

}


