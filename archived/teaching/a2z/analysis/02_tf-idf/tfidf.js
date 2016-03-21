// Daniel Shiffman
// Programming from A to Z, Fall 2014
// https://github.com/shiffman/Programming-from-A-to-Z-F14

function TFIDF() {
  this.hash = {};
  this.keys = [];
  this.totalwords = 0;

  function split(text) {
    // Split into array of tokens
    return text.split(/\W+/);
  }

  // A function to validate a toke 
  function validate(token) {
    return /\w{2,}/.test(token);
  }

  // Count the words
  this.termFreq = function(data) {
    var tokens = split(data);
    // For every token
    for (var i = 0; i < tokens.length; i++) {
      // Lowercase everything to ignore case
      var token = tokens[i].toLowerCase();
      if (validate(token)) {
        this.increment(token);
        this.totalwords++;
      }
    }
  }
  
  // Get the document frequencies across all documents
  this.docFreq = function(data) {
    var tokens = split(data);
  
    // A temporary dictionary of words in this document
    var tempDict = {};
    // For every token
    for (var i = 0; i < tokens.length; i++) {
      // Lowercase everything to ignore case
      var token = tokens[i].toLowerCase();
      // Simpler we just need to see if it exists or not
      if (validate(token) && tempDict[token] === undefined) {
        tempDict[token] = true;
      }
    }

    for (var i = 0; i < this.keys.length; i++) {
      var key = this.keys[i];
      // Does this word exist in this document?
      if (tempDict[key]) {
        this.hash[key].docCount++;
      }
    }
  }
  
  // Get all the keys
  this.getKeys = function() {
    return this.keys;
  }
  
  // Get the count for one word
  this.getCount = function(word) {
    return this.hash[word].count;
  }
  
  // Get the score for one word
  this.getScore = function(word) {
    return this.hash[word].tfidf;
  }

  // Increment the count for one word
  this.increment = function(word) {
    // Is this a new word?
    if (this.hash[word] == undefined) {
      this.hash[word] = {};
      this.hash[word].count = 1;
      this.hash[word].docCount = 0;
      this.hash[word].word = word;
      this.keys.push(word);
    // Otherwise just increment its count
    } else {
      this.hash[word].count++;
    }
  }
  
  // Finish and calculate everything
  this.finish = function(totaldocs) {
    // calculate tf-idf score
    for (var i = 0; i < this.keys.length; i++) {
      var key = this.keys[i];
      var word = this.hash[key];
      var tf = word.count / this.totalwords;
      // See: 
      var idf = Math.log(totaldocs / word.docCount);
      word.tfidf = tf * idf;
    }
  }
  
  // Sort by word counts
  this.sortByCount = function() {
    // A fancy way to sort each element
    // Compare the counts
    var dict = this;
    this.keys.sort(function(a,b) {
      return (dict.getCount(b) - dict.getCount(a));
    });
  }
  
  // Sort by TFIDF score
  this.sortByScore = function() {
    // A fancy way to sort each element
    // Compare the counts
    var dict = this;
    this.keys.sort(function(a,b) {
      return (dict.getScore(b) - dict.getScore(a));
    });
  }

}


